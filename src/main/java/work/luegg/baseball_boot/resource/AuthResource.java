package work.luegg.baseball_boot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import work.luegg.baseball_boot.dto.ClubDTO;
import work.luegg.baseball_boot.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody ClubDTO dto) {

        return authService.login(dto);
    }
}