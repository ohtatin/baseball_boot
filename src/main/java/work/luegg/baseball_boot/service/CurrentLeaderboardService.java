package work.luegg.baseball_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import work.luegg.baseball_boot.model.CurrentLeaderboard;
import work.luegg.baseball_boot.repository.CurrentLeaderboardRepository;

@Tag(
	    name = "MLB 本季排行榜",
	    description = "查詢 MLB 本季各項排行榜"
	)
@Service
public class CurrentLeaderboardService {
	
	@Autowired
    private CurrentLeaderboardRepository repo;
	@Operation(
		    summary = "查詢本季排行榜"
		)
	public List<CurrentLeaderboard> getByType(String type) {
    return repo.findByTypeOrderByRankAsc(type);
}

}
