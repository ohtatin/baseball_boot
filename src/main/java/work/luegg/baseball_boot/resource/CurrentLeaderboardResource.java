package work.luegg.baseball_boot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import work.luegg.baseball_boot.model.CurrentLeaderboard;
import work.luegg.baseball_boot.service.CurrentLeaderboardService;

@RestController
@RequestMapping("/mlb")
@CrossOrigin
public class CurrentLeaderboardResource {
	
	@Autowired
    private CurrentLeaderboardService service;
	
	@GetMapping("/leaderboard")
	public List<CurrentLeaderboard> getLeaderboard(@RequestParam String type) {
	    return service.getByType(type);
    }
}
