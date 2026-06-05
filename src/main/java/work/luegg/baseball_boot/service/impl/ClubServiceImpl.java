package work.luegg.baseball_boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import work.luegg.baseball_boot.model.Club;
import work.luegg.baseball_boot.repository.ClubRepository;
import work.luegg.baseball_boot.service.ClubService;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository repo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔷 註冊
    @Override
    public String register(Club club) {

        //  檢查帳號是否存在
    	if (club.getTeam() == null || club.getTeam().isBlank()) {
            return "隊名不能空白";
        }

        if (club.getPassword() == null || club.getPassword().isBlank()) {
            return "密碼不能空白";
        }
        if (repo.existsByTeam(club.getTeam())) {
            return "帳號已存在";
        }

        club.setPassword(passwordEncoder.encode(club.getPassword()));
       
        //  存進資料庫
        repo.save(club);

        return "註冊成功";
    }

    // 🔷 登入
    @Override
    public String login(Club club) {
    	
    	if (club.getTeam() == null || club.getTeam().isBlank()) {
            return "帳號不能空白";
        }

        if (club.getPassword() == null || club.getPassword().isBlank()) {
            return "密碼不能空白";
        }

        return repo.findById(club.getTeam())
                .map(u -> {
                    //  比對密碼
                	if (passwordEncoder.matches(club.getPassword(), u.getPassword())) {
                        return "success";
                    } else {
                        return "密碼錯誤";
                    }
                })
                //  找不到帳號
                .orElse("帳號不存在");
    }
}