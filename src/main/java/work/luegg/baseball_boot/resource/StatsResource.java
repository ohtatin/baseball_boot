package work.luegg.baseball_boot.resource;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@Tag(
	    name = "球員成績管理",
	    description = "新增、查詢、修改、刪除投手與打者成績"
	)

public class StatsResource {

    private static final Logger log =
            LoggerFactory.getLogger(StatsResource.class);

    @Autowired
    private PitcherStatsService pitcherstatsService;

    @Autowired
    private BatterStatsService batterstatsService;


    @PostMapping("/pitcher")
    @Operation(
    	    summary = "新增投手成績",
    	    description = "新增一筆投手比賽成績資料"
    	)
    public String savePitcher(
    		@Valid
            @RequestBody PitcherStatsDTO dto,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        log.debug("收到新增投手 Request: team={}", loginTeam);

        

        pitcherstatsService.savePitcherStats(dto, loginTeam);

        return "投手資料已儲存";
    }


    @PostMapping("/batter")
    @Operation(
    	    summary = "新增打者成績",
    	    description = "新增一筆打者比賽成績資料"
    	)
    public String saveBatter(
    		
    		@Valid
            @RequestBody BatterStatsDTO dto,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        log.debug("收到新增打者 Request: team={}", loginTeam);

        

        batterstatsService.saveBatterStats(dto, loginTeam);

        return "打者資料已儲存";
    }


    @GetMapping("/stats")
    @Operation(
    	    summary = "依姓名查詢成績",
    	    description = "輸入球員姓名，查詢該球員所有成績"
    	)
    public List<QueryStatsDTO> getStats(
            @RequestParam String name,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        log.debug("收到球員查詢 Request: team={}, name={}",
                loginTeam, name);

        List<QueryStatsDTO> list = new ArrayList<>();

        list.addAll(
                batterstatsService.getStatsByName(name, loginTeam)
        );

        list.addAll(
                pitcherstatsService.getStatsByName(name, loginTeam)
        );

        return list;
    }


    @GetMapping("/pitcher/{id}")
    @Operation(
    	    summary = "依 ID 查詢投手資料",
    	    description = "查詢單筆投手資料，通常用於修改前載入資料"
    	)
    public PitcherStatsDTO getPitcher(
            @PathVariable Long id,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        return pitcherstatsService.getPitcherById(id, loginTeam);
    }


    @GetMapping("/batter/{id}")
    @Operation(
    	    summary = "依 ID 查詢打者資料",
    	    description = "查詢單筆打者資料，通常用於修改前載入資料"
    	)
    public BatterStatsDTO getBatter(
            @PathVariable Long id,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        return batterstatsService.getBatterById(id, loginTeam);
    }


    @PutMapping("/pitcher/{id}")
    @Operation(
    	    summary = "依 ID 修改投手資料",
    	    description = "修改單筆投手資料"
    	)
    public String updatePitcher(
            @PathVariable Long id,
            @Valid
            @RequestBody PitcherStatsDTO dto,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        pitcherstatsService.updatePitcher(id, dto, loginTeam);

        return "投手資料修改成功";
    }


    @PutMapping("/batter/{id}")
    @Operation(
    	    summary = "依 ID 修改打者資料",
    	    description = "修改單筆打者資料"
    	)
    public String updateBatter(
            @PathVariable Long id,
            @Valid
            @RequestBody BatterStatsDTO dto,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        batterstatsService.updateBatter(id, dto, loginTeam);

        return "打者資料修改成功";
    }


    @DeleteMapping("/pitcher/{id}")
    @Operation(
    	    summary = "依 ID 刪除投手資料",
    	    description = "刪除單筆投手資料"
    	)
    public String deletePitcher(
            @PathVariable Long id,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        pitcherstatsService.deletePitcher(id, loginTeam);

        return "投手資料刪除成功";
    }


    @DeleteMapping("/batter/{id}")
    @Operation(
    	    summary = "依 ID 刪除打者資料",
    	    description = "刪除單筆打者資料"
    	)
    public String deleteBatter(
            @PathVariable Long id,
            HttpServletRequest request) {

        String loginTeam =
                (String) request.getAttribute("team");

        batterstatsService.deleteBatter(id, loginTeam);

        return "打者資料刪除成功";
    }
}