package work.luegg.baseball_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import work.luegg.baseball_boot.model.AlltimeLeaderboard;

public interface AlltimeLeaderboardRepository extends JpaRepository<AlltimeLeaderboard, Integer> {

    List<AlltimeLeaderboard> findByTypeOrderByRankAsc(String type);

}