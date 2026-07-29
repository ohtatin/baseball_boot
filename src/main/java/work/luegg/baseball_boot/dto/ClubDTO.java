package work.luegg.baseball_boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClubDTO {
    
	 @Schema(
		        description = "球隊名稱",
		        example = "Yankees"
		    )
    private String team;
	 
	 @Schema(
		        description = "登入密碼",
		        accessMode = Schema.AccessMode.WRITE_ONLY,
		        example = "123456"
		    )
    private String password;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}