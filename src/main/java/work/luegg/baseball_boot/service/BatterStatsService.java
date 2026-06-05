package work.luegg.baseball_boot.service;

import java.util.List;

import work.luegg.baseball_boot.dto.BatterStatsDTO;
import work.luegg.baseball_boot.dto.QueryStatsDTO;
import work.luegg.baseball_boot.model.Batter_log;

public interface BatterStatsService {
	String saveBatterStats(BatterStatsDTO dto,String team);
	BatterStatsDTO convertToDTO(Batter_log b);
	List<QueryStatsDTO> getStatsByName(String name,String team);
	void deleteBatter(Long id,String team);
	BatterStatsDTO getBatterById(Long id,String team);
	void updateBatter(Long id, BatterStatsDTO dto,String team);
}
