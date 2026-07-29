package work.luegg.baseball_boot.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import io.swagger.v3.oas.annotations.media.Schema;

public class PitcherStatsDTO {
	@Schema(
		    description = "資料 ID",
		    example = "15",
		    accessMode = Schema.AccessMode.READ_ONLY
		)
	private long id;	
	
	@NotBlank(message = "球員姓名不能空白")
	@Schema(
		    description = "球員姓名",
		    example = "Shohei Ohtani"
		)
    private String name;
	
	@NotNull(message = "日期不能空白")
	@PastOrPresent(message = "日期不能是未來")
	@Schema(
		    description = "比賽日期",
		    example = "2026-07-22"
		)
    private LocalDate date;
	
    @NotBlank(message = "對手球隊不能空白")
    @Schema(
    	    description = "對戰球隊",
    	    example = "Yankees"
    	)
    private String opTeam;
    
    @NotNull(message = "局數不能空白")
    @Min(value = 0, message = "局數不能小於0")
    @Schema(
    	    description = "投球局數",
    	    example = "7"
    	)
    private Integer innings;
    
    @NotNull(message = "責失不能空白")
    @Min(value = 0, message = "責失不能小於0")
    @Schema(
    	    description = "責失分",
    	    example = "2"
    	)
    private Integer er;
    
    @NotNull(message = "三振不能空白")
    @Min(value = 0, message = "三振不能小於0")
    @Schema(
    	    description = "奪三振數",
    	    example = "9"
    	)
    private Integer strikeouts;
    
    @NotNull(message = "保送不能空白")
    @Min(value = 0, message = "保送不能小於0")
    @Schema(
    	    description = "保送數",
    	    example = "1"
    	)
    private Integer walks;
    
    @Schema(hidden = true)
    private String team;

    
    // getter / setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getOpTeam() { return opTeam; }
    public void setOpTeam(String opTeam) { this.opTeam = opTeam; }

    public Integer getInnings() { return innings; }
    public void setInnings(Integer innings) { this.innings = innings; }

    public Integer getEr() { return er; }
    public void setEr(Integer er) { this.er = er; }

    public Integer getStrikeouts() { return strikeouts; }
    public void setStrikeouts(Integer strikeouts) { this.strikeouts = strikeouts; }

    public Integer getWalks() { return walks; }
    public void setWalks(Integer walks) { this.walks = walks; }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
}