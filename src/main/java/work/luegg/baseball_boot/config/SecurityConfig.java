package work.luegg.baseball_boot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
    private JwtFilter jwtFilter;
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/api/auth/login",
            					 "/html/**",
            					 "/css/**",
            					 "/js/**",
            					 "/images/**",
            					 "/club/login",
            					 "/club/register",
            				     "/mlb/leaderboard",
            					 "/mlb/alltime_leaderboard",
            					 "/favicon.ico"
            				   
            	).permitAll()
                .anyRequest().authenticated()
            )
        	
        	.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            );
        	
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}