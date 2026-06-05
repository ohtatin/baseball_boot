package work.luegg.baseball_boot.model;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "batter_log")
public class Batter_log {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // ⭐ 主鍵
	
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String opTeam;

    private Integer atBats;
    private Integer hits;
    private Integer strikeouts;
    private Integer walks;
    private String team;

    // Getter / Setter
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOpTeam() {
        return opTeam;
    }

    public void setOpTeam(String opTeam) {
        this.opTeam = opTeam;
    }

    public Integer getAtBats() {
        return atBats;
    }

    public void setAtBats(Integer atBats) {
        this.atBats = atBats;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getStrikeouts() {
        return strikeouts;
    }

    public void setStrikeouts(Integer strikeouts) {
        this.strikeouts = strikeouts;
    }

    public Integer getWalks() {
        return walks;
    }

    public void setWalks(Integer walks) {
        this.walks = walks;
    }

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
}