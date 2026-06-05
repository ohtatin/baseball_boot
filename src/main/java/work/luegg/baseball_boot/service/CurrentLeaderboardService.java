package work.luegg.baseball_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import work.luegg.baseball_boot.model.CurrentLeaderboard;
import work.luegg.baseball_boot.repository.CurrentLeaderboardRepository;


@Service
public class CurrentLeaderboardService {
	
	@Autowired
    private CurrentLeaderboardRepository repo;
	
	public List<CurrentLeaderboard> getByType(String type) {
    return repo.findByTypeOrderByRankAsc(type);
}

}
