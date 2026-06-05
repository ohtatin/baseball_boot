package work.luegg.baseball_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import work.luegg.baseball_boot.model.AlltimeLeaderboard;
import work.luegg.baseball_boot.repository.AlltimeLeaderboardRepository;

@Service
public class AlltimeLeaderboardService {

    @Autowired
    private AlltimeLeaderboardRepository repo;

    public List<AlltimeLeaderboard> getByType(String type) {
        return repo.findByTypeOrderByRankAsc(type);
    }
}