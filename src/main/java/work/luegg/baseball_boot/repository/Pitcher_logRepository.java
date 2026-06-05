package work.luegg.baseball_boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import work.luegg.baseball_boot.model.Pitcher_log;

public interface Pitcher_logRepository extends JpaRepository<Pitcher_log, Long> {
	List<Pitcher_log> findByNameAndTeam(String name, String team);
	Optional<Pitcher_log> findByIdAndTeam(Long id, String team);
	
}