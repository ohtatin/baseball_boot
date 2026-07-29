package work.luegg.baseball_boot.resource;

import work.luegg.baseball_boot.model.Club;
import work.luegg.baseball_boot.service.ClubService;
import work.luegg.baseball_boot.dto.ClubDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/club")
@Tag(
	    name = "會員管理",
	    description = "會員註冊相關 API"
	)
public class ClubResource {

    @Autowired
    private ClubService service;
    @Operation(
    	    summary = "會員註冊",
    	    description = "建立新的球隊帳號"
    	)
    @PostMapping("/register")
    
    public String register(@RequestBody ClubDTO dto) {
        Club club = new Club();
        club.setTeam(dto.getTeam());
        club.setPassword(dto.getPassword());
        club.setRole("USER");

        return service.register(club);
    }
}