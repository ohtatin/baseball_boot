package work.luegg.baseball_boot.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import work.luegg.baseball_boot.util.JwtUtil;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println("目前進來的 path = " + path);

        // 登入 API 不用檢查 token
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
        	    || path.equals("/favicon.ico")){
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid token");
            return;
        }

        String token = authHeader.substring(7);

        try {
        	Claims claims = jwtUtil.parseToken(token);

        	String team = claims.getSubject();
        	String role = claims.get("role", String.class);

        	request.setAttribute("team", team);
        	request.setAttribute("role", role);

        	UsernamePasswordAuthenticationToken authentication =
        	        new UsernamePasswordAuthenticationToken(
        	                team,
        	                null,
        	                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        	        );

        	SecurityContextHolder.getContext().setAuthentication(authentication);

        	filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
        }
    }
}