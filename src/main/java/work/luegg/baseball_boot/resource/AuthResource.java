package work.luegg.baseball_boot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import work.luegg.baseball_boot.dto.ClubDTO;
import work.luegg.baseball_boot.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Tag(
	    name = "登入驗證",
	    description = "JWT 登入驗證"
	)
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(
    	    summary = "會員登入",
    	    description = "登入成功後取得 JWT Token"
    	)
    public String login(@RequestBody ClubDTO dto) {

        return authService.login(dto);
    }
}