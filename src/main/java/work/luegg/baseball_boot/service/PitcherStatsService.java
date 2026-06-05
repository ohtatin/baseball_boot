package work.luegg.baseball_boot.service;

import java.util.List;

import work.luegg.baseball_boot.dto.PitcherStatsDTO;
import work.luegg.baseball_boot.dto.QueryStatsDTO;
import work.luegg.baseball_boot.model.Pitcher_log;

public interface PitcherStatsService {
	String savePitcherStats(PitcherStatsDTO dto,String team);
	PitcherStatsDTO convertToDTO(Pitcher_log p);
	List<QueryStatsDTO> getStatsByName(String name,String team);
	void deletePitcher(Long id,String team);
	PitcherStatsDTO getPitcherById(Long id,String team);
	void updatePitcher(Long id, PitcherStatsDTO dto,String team);
}
