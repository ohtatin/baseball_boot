package work.luegg.baseball_boot.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import work.luegg.baseball_boot.dto.ClubDTO;
import work.luegg.baseball_boot.model.Club;
import work.luegg.baseball_boot.repository.ClubRepository;
import work.luegg.baseball_boot.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthResource {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody ClubDTO dto) {

        Optional<Club> optionalClub =
                clubRepository.findByTeam(dto.getTeam());

        // 帳號不存在
        if (optionalClub.isEmpty()) {
            return "帳號不存在";
        }

        Club club = optionalClub.get();

        // BCrypt 密碼比對
        if (!passwordEncoder.matches(
                dto.getPassword(),
                club.getPassword())) {

            return "密碼錯誤";
        }

        // 產生 JWT
        String token = jwtUtil.generateToken(
                club.getTeam(),
                club.getRole()
        );

        return token;
    }
}