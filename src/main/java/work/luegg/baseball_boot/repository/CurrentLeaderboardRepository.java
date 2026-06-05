package work.luegg.baseball_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import work.luegg.baseball_boot.model.CurrentLeaderboard;

public interface CurrentLeaderboardRepository extends JpaRepository<CurrentLeaderboard, Integer> {

    List<CurrentLeaderboard> findByTypeOrderByRankAsc(String type);

}