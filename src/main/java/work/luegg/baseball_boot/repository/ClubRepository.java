package work.luegg.baseball_boot.repository;



import work.luegg.baseball_boot.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, String> {
    Optional<Club> findByTeam(String team);
    boolean existsByTeam(String team);
}