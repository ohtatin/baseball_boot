package work.luegg.baseball_boot.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    // 至少 32 字元
    private final String SECRET =
            "lueggbaseball20260519poiuytrewqq";

    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    private Key getSignKey() {
        return new SecretKeySpec(
                SECRET.getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }

    // 建立 token
    public String generateToken(String team, String role) {

        return Jwts.builder()
                .setSubject(team)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION)
                )
                .signWith(getSignKey())
                .compact();
    }

    // 解析 token
    public Claims parseToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 取得 team
    public String getTeam(String token) {
        return parseToken(token).getSubject();
    }

    // 取得 role
    public String getRole(String token) {
        return parseToken(token)
                .get("role", String.class);
    }
}