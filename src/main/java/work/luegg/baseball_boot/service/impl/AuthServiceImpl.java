package work.luegg.baseball_boot.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import work.luegg.baseball_boot.dto.ClubDTO;
import work.luegg.baseball_boot.model.Club;
import work.luegg.baseball_boot.repository.ClubRepository;
import work.luegg.baseball_boot.service.AuthService;
import work.luegg.baseball_boot.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log =
            LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(ClubDTO dto) {

        log.info("球隊嘗試登入: {}", dto.getTeam());

        Optional<Club> optionalClub =
                clubRepository.findByTeam(dto.getTeam());

        // 帳號不存在
        if (optionalClub.isEmpty()) {

            log.warn("登入失敗，帳號不存在: {}", dto.getTeam());

            return "帳號不存在";
        }

        Club club = optionalClub.get();

        // BCrypt 密碼比對
        if (!passwordEncoder.matches(
                dto.getPassword(),
                club.getPassword())) {

            log.warn("登入失敗，密碼錯誤: {}", dto.getTeam());

            return "密碼錯誤";
        }

        // 產生 JWT
        String token = jwtUtil.generateToken(
                club.getTeam(),
                club.getRole()
        );

        log.info("球隊登入成功: {}", club.getTeam());

        return token;
    }
}