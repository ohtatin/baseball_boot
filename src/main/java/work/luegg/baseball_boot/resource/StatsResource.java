package work.luegg.baseball_boot.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import work.luegg.baseball_boot.dto.BatterStatsDTO;
import work.luegg.baseball_boot.dto.PitcherStatsDTO;
import work.luegg.baseball_boot.dto.QueryStatsDTO;
import work.luegg.baseball_boot.service.BatterStatsService;
import work.luegg.baseball_boot.service.PitcherStatsService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StatsResource {

    @Autowired
    private PitcherStatsService pitcherstatsService;

    @Autowired
    private BatterStatsService batterstatsService;
    
    @PostMapping("/pitcher")
    public String savePitcher(@RequestBody PitcherStatsDTO dto,
    		                  HttpServletRequest request) {
    	String loginTeam =
    	        (String) request.getAttribute("team");

    	    dto.setTeam(loginTeam);

        pitcherstatsService.savePitcherStats(dto,loginTeam);
        return "投手資料已儲存";
    }

    @PostMapping("/batter")
    public String saveBatter(@RequestBody BatterStatsDTO dto,
    		                 HttpServletRequest request) {
    	String loginTeam =
    	        (String) request.getAttribute("team");

    	    dto.setTeam(loginTeam);
    	    
        batterstatsService.saveBatterStats(dto,loginTeam);
        return "打者資料已儲存";
    }
    
    
    @GetMapping("/stats")
    public List<QueryStatsDTO> getStats(@RequestParam String name,HttpServletRequest request) {
    	String loginTeam = (String) request.getAttribute("team");
    	List<QueryStatsDTO> list = new ArrayList<>();

        list.addAll(batterstatsService.getStatsByName(name,loginTeam));
        list.addAll(pitcherstatsService.getStatsByName(name,loginTeam));

        return list;
    }
    
    
    
    @GetMapping("/pitcher/{id}")
    public PitcherStatsDTO getPitcher(@PathVariable Long id,HttpServletRequest request) {
    	String loginTeam = (String) request.getAttribute("team");
        return pitcherstatsService.getPitcherById(id,loginTeam);
    }
    
    @GetMapping("/batter/{id}")
    public BatterStatsDTO getBatter(@PathVariable Long id,HttpServletRequest request) {
    	String loginTeam = (String) request.getAttribute("team");
        return batterstatsService.getBatterById(id,loginTeam);
    }
    
    
    
    @PutMapping("/pitcher/{id}")
    public String updatePitcher(
            @PathVariable Long id,
            @RequestBody PitcherStatsDTO dto,HttpServletRequest request) {
    	
    	String loginTeam = (String) request.getAttribute("team");
        pitcherstatsService.updatePitcher(id, dto,loginTeam);

        return "投手資料修改成功";
    }
    
    @PutMapping("/batter/{id}")
    public String updateBatter(
            @PathVariable Long id,
            @RequestBody BatterStatsDTO dto,HttpServletRequest request) {
    	String loginTeam = (String) request.getAttribute("team");
        batterstatsService.updateBatter(id, dto,loginTeam);

        return "打者資料修改成功";
    }
    
    
    
    
    @DeleteMapping("/pitcher/{id}")
    public String deletePitcher(@PathVariable Long id,HttpServletRequest request) {
    	String loginTeam = (String) request.getAttribute("team");
        pitcherstatsService.deletePitcher(id,loginTeam);

        return "投手資料刪除成功";
    }      
    
    
    @DeleteMapping("/batter/{id}")
    public String deleteBatter(@PathVariable Long id, HttpServletRequest request) {
    	String loginTeam = (String) request.getAttribute("team");
        batterstatsService.deleteBatter(id,loginTeam);

        return "打者資料刪除成功";
    }
    
    
}   










    
