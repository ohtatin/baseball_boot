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
import work.luegg.baseball_boot.model.AlltimeLeaderboard;
import work.luegg.baseball_boot.service.AlltimeLeaderboardService;

@RestController
@RequestMapping("/mlb")
@CrossOrigin
@Tag(
	    name = "MLB 生涯排行榜",
	    description = "查詢 MLB 生涯排行榜"
	)
public class AlltimeLeaderboardResource {

    @Autowired
    private AlltimeLeaderboardService service;
    @Operation(
    	    summary = "查詢生涯排行榜"
    	)
    @GetMapping("/alltime_leaderboard")
    public List<AlltimeLeaderboard> getLeaderboard(@RequestParam String type) {
        return service.getByType(type);
    }
}