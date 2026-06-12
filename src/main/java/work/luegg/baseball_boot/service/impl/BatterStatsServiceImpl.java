package work.luegg.baseball_boot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import work.luegg.baseball_boot.dto.BatterStatsDTO;
import work.luegg.baseball_boot.dto.QueryStatsDTO;
import work.luegg.baseball_boot.model.Batter_log;
import work.luegg.baseball_boot.repository.Batter_logRepository;
import work.luegg.baseball_boot.service.BatterStatsService;

@Service
public class BatterStatsServiceImpl implements BatterStatsService {
	@Autowired
    private Batter_logRepository batterRepo;

    @Override
    public String saveBatterStats(BatterStatsDTO dto,String team) {

        Batter_log b = new Batter_log();
        
        b.setName(dto.getName());
        b.setDate(dto.getDate());
        b.setOpTeam(dto.getOpTeam());
        b.setAtBats(dto.getAtBats());
        b.setHits(dto.getHits());
        b.setStrikeouts(dto.getStrikeouts());
        b.setWalks(dto.getWalks());
        b.setTeam(team);

        batterRepo.save(b);

        return "打者資料已儲存";
    }
    
    
    @Override
    public BatterStatsDTO convertToDTO(Batter_log b) {
        BatterStatsDTO dto = new BatterStatsDTO();
        dto.setId(b.getId());
        dto.setName(b.getName());       
        dto.setDate(b.getDate());        
        dto.setOpTeam(b.getOpTeam()); 
        dto.setAtBats(b.getAtBats());
        dto.setHits(b.getHits());
        dto.setStrikeouts(b.getStrikeouts());
        dto.setWalks(b.getWalks());
        dto.setTeam(b.getTeam());
        return dto;
    }
    
    
    @Override
    public List<QueryStatsDTO> getStatsByName(String name,String team) {

        List<QueryStatsDTO> list = new ArrayList<>();

        
        for (Batter_log b : batterRepo.findByNameAndTeam(name,team)) {

            QueryStatsDTO dto = new QueryStatsDTO();
            dto.setId(b.getId());
            dto.setType("打者");
            dto.setDate(b.getDate().toString());
            dto.setOpTeam(b.getOpTeam());
            dto.setData(convertToDTO(b));

            list.add(dto);
        }
        return list;
    }    
    

    @Override
    public BatterStatsDTO getBatterById(Long id,String team) {

    	Batter_log b = batterRepo.findByIdAndTeam(id, team)
                .orElseThrow(() -> new RuntimeException("找不到資料或沒有權限"));
       
    	return convertToDTO(b);
    }
    
    
    @Override
    public void updateBatter(Long id, BatterStatsDTO dto,String team) {

    	Batter_log b = batterRepo.findByIdAndTeam(id, team)
                .orElseThrow(() -> new RuntimeException("找不到資料或沒有權限修改"));

            b.setName(dto.getName());
            b.setDate(dto.getDate());
            b.setOpTeam(dto.getOpTeam());
            b.setAtBats(dto.getAtBats());
            b.setHits(dto.getHits());
            b.setStrikeouts(dto.getStrikeouts());
            b.setWalks(dto.getWalks());
            

            batterRepo.save(b);
        }
    
    
    
  
    @Override
    public void deleteBatter(Long id,String team) {
    	
    	Batter_log b = batterRepo.findByIdAndTeam(id, team)
                .orElseThrow(() -> new RuntimeException("找不到資料或沒有權限刪除"));

        batterRepo.delete(b);
    }
    
    
    
    
    
}
	

