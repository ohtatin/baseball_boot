package work.luegg.baseball_boot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import work.luegg.baseball_boot.dto.PitcherStatsDTO;
import work.luegg.baseball_boot.dto.QueryStatsDTO;
import work.luegg.baseball_boot.model.Pitcher_log;
import work.luegg.baseball_boot.repository.Pitcher_logRepository;
import work.luegg.baseball_boot.service.PitcherStatsService;

@Service
public class PitcherStatsServiceImpl implements PitcherStatsService {

    private static final Logger log =
            LoggerFactory.getLogger(PitcherStatsServiceImpl.class);

    @Autowired
    private Pitcher_logRepository pitcherRepo;


    @Override
    public String savePitcherStats(
            PitcherStatsDTO dto,
            String team) {

        Pitcher_log p = new Pitcher_log();

        p.setName(dto.getName());
        p.setDate(dto.getDate());
        p.setOpTeam(dto.getOpTeam());
        p.setInnings(dto.getInnings());
        p.setEr(dto.getEr());
        p.setStrikeouts(dto.getStrikeouts());
        p.setWalks(dto.getWalks());

        // 直接使用登入者的 team
        p.setTeam(team);

        pitcherRepo.save(p);

        log.info("投手資料新增成功: team={}, id={}, name={}",
                team, p.getId(), p.getName());

        return "投手資料已儲存";
    }


    @Override
    public PitcherStatsDTO convertToDTO(Pitcher_log p) {

        PitcherStatsDTO dto = new PitcherStatsDTO();

        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDate(p.getDate());
        dto.setOpTeam(p.getOpTeam());
        dto.setInnings(p.getInnings());
        dto.setEr(p.getEr());
        dto.setStrikeouts(p.getStrikeouts());
        dto.setWalks(p.getWalks());

        return dto;
    }


    @Override
    public List<QueryStatsDTO> getStatsByName(
            String name,
            String team) {

        List<QueryStatsDTO> list = new ArrayList<>();

        for (Pitcher_log p :
                pitcherRepo.findByNameAndTeam(name, team)) {

            QueryStatsDTO dto = new QueryStatsDTO();

            dto.setId(p.getId());
            dto.setType("投手");
            dto.setDate(p.getDate().toString());
            dto.setOpTeam(p.getOpTeam());
            dto.setData(convertToDTO(p));

            list.add(dto);
        }

        log.debug("投手查詢完成: team={}, name={}, count={}",
                team, name, list.size());

        return list;
    }


    @Override
    public PitcherStatsDTO getPitcherById(
            Long id,
            String team) {

        Pitcher_log p = pitcherRepo.findByIdAndTeam(id, team)
                .orElseThrow(() -> {

                    log.warn("找不到投手或沒有權限: team={}, id={}",
                            team, id);

                    return new RuntimeException(
                            "找不到資料或沒有權限"
                    );
                });

        return convertToDTO(p);
    }


    @Override
    public void updatePitcher(
            Long id,
            PitcherStatsDTO dto,
            String team) {

        Pitcher_log p = pitcherRepo.findByIdAndTeam(id, team)
                .orElseThrow(() -> {

                    log.warn("投手修改失敗，找不到資料或沒有權限: team={}, id={}",
                            team, id);

                    return new RuntimeException(
                            "找不到資料或沒有權限修改"
                    );
                });

        p.setName(dto.getName());
        p.setDate(dto.getDate());
        p.setOpTeam(dto.getOpTeam());
        p.setInnings(dto.getInnings());
        p.setEr(dto.getEr());
        p.setStrikeouts(dto.getStrikeouts());
        p.setWalks(dto.getWalks());

        pitcherRepo.save(p);

        log.info("投手資料修改成功: team={}, id={}",
                team, id);
    }


    @Override
    public void deletePitcher(
            Long id,
            String team) {

        Pitcher_log p = pitcherRepo.findByIdAndTeam(id, team)
                .orElseThrow(() -> {

                    log.warn("投手刪除失敗，找不到資料或沒有權限: team={}, id={}",
                            team, id);

                    return new RuntimeException(
                            "找不到資料或沒有權限刪除"
                    );
                });

        pitcherRepo.delete(p);

        log.info("投手資料刪除成功: team={}, id={}",
                team, id);
    }
}