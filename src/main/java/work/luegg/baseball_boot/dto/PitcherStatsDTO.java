package work.luegg.baseball_boot.dto;

import java.time.LocalDate;

public class PitcherStatsDTO {

	private long id;
    private String name;
    private LocalDate date;
    private String opTeam;
    private Integer innings;
    private Integer er;
    private Integer strikeouts;
    private Integer walks;
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