package work.luegg.baseball_boot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import work.luegg.baseball_boot.model.CurrentLeaderboard;
import work.luegg.baseball_boot.service.CurrentLeaderboardService;

@RestController
@RequestMapping("/mlb")
@CrossOrigin
@Tag(
	    name = "2025各項成績排行榜",
	    description = "查詢2025成績排行"
	)
public class CurrentLeaderboardResource {
	
	@Autowired
    private CurrentLeaderboardService service;
	@Operation(
    	    summary = "查詢2025成績排行榜"
    	)
	@GetMapping("/leaderboard")
	public List<CurrentLeaderboard> getLeaderboard(@RequestParam String type) {
	    return service.getByType(type);
    }
}
