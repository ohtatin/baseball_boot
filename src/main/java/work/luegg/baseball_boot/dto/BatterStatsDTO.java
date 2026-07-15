package work.luegg.baseball_boot.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


public class BatterStatsDTO {
	
	private long id;
	@NotBlank(message = "球員姓名不能空白")
    private String name;
	@NotNull(message = "日期不能空白")
	@PastOrPresent(message = "日期不能是未來")
    private LocalDate date;
    @NotBlank(message = "對手球隊不能空白")
    private String opTeam;
    @NotNull(message = "打數不能空白")
    @Min(value = 0, message = "打數不能小於0")
    private Integer atBats;
    @NotNull(message = "安打不能空白")
    @Min(value = 0, message = "安打不能小於0")
    private Integer hits;
    @NotNull(message = "三振不能空白")
    @Min(value = 0, message = "三振不能小於0")
    private Integer strikeouts;
    @NotNull(message = "保送不能空白")
    @Min(value = 0, message = "保送不能小於0")
    private Integer walks;
    private String team;

    // getter / setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getOpTeam() { return opTeam; }
    public void setOpTeam(String opTeam) { this.opTeam = opTeam; }

    public Integer getAtBats() { return atBats; }
    public void setAtBats(Integer atBats) { this.atBats = atBats; }

    public Integer getHits() { return hits; }
    public void setHits(Integer hits) { this.hits = hits; }

    public Integer getStrikeouts() { return strikeouts; }
    public void setStrikeouts(Integer strikeouts) { this.strikeouts = strikeouts; }

    public Integer getWalks() { return walks; }
    public void setWalks(Integer walks) { this.walks = walks; }
    
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
	public String getTeam() {
		return team; 
		}
	public void setTeam(String team) { 
		this.team = team; 
		}
}


