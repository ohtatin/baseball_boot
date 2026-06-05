package work.luegg.baseball_boot.resource;

import work.luegg.baseball_boot.model.Club;
import work.luegg.baseball_boot.service.ClubService;
import work.luegg.baseball_boot.dto.ClubDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubResource {

    @Autowired
    private ClubService service;

    @PostMapping("/register")
    public String register(@RequestBody ClubDTO dto) {
        Club club = new Club();
        club.setTeam(dto.getTeam());
        club.setPassword(dto.getPassword());
        club.setRole("USER");

        return service.register(club);
    }
}