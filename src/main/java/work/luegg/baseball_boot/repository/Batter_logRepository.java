package work.luegg.baseball_boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import work.luegg.baseball_boot.model.Batter_log;

public interface Batter_logRepository extends JpaRepository<Batter_log, Long> {
	List<Batter_log> findByNameAndTeam(String name, String team);
	Optional<Batter_log> findByIdAndTeam(Long id,String name);
}


