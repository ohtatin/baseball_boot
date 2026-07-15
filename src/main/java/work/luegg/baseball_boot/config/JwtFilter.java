package work.luegg.baseball_boot.config;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import work.luegg.baseball_boot.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log =
            LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        log.info("收到 Request: method={}, path={}", method, path);

        // 這些路徑不用檢查 token
        if (path.equals("/")
                || path.equals("/api/auth/login")
                || path.equals("/club/login")
                || path.equals("/club/register")
                || path.equals("/mlb/leaderboard")
                || path.equals("/mlb/alltime_leaderboard")
                || path.startsWith("/html/")
                || path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.startsWith("/images/")
                || path.equals("/favicon.ico")) {

            log.debug("此路徑跳過 JWT 檢查: path={}", path);

            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            log.warn("缺少或格式錯誤的 Authorization Header: method={}, path={}",
                    method, path);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid token");
            return;
        }

        // 不記錄完整 token，避免洩漏 JWT
        String token = authHeader.substring(7);

        log.debug("偵測到 Bearer Token，開始驗證: path={}", path);

        try {
            Claims claims = jwtUtil.parseToken(token);

            String team = claims.getSubject();
            String role = claims.get("role", String.class);

            log.info("JWT 驗證成功: team={}, role={}, path={}",
                    team, role, path);

            request.setAttribute("team", team);
            request.setAttribute("role", role);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            team,
                            null,
                            List.of(
                                    new SimpleGrantedAuthority("ROLE_" + role)
                            )
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            log.debug("已建立 Spring Security 登入身分: team={}, role={}",
                    team, role);

            filterChain.doFilter(request, response);

        } catch (Exception e) {

            log.warn("JWT 驗證失敗: method={}, path={}, error={}",
                    method, path, e.getMessage());

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
        }
    }
}