package work.luegg.baseball_boot.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public class QueryStatsDTO {

    @Schema(
        description = "資料ID",
        accessMode = Schema.AccessMode.READ_ONLY,
        example = "15"
    )
    private long id;

    @Schema(
        description = "資料類型",
        example = "投手"
    )
    private String type;

    @Schema(
        description = "比賽日期",
        example = "2026-07-23"
    )
    private String date;

    @Schema(
        description = "對手球隊",
        example = "Dodgers"
    )
    private String opTeam;

    @Schema(
        description = "詳細資料（PitcherStatsDTO 或 BatterStatsDTO）"
    )
    private Object data;


	    // getter / setter

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getDate() {
	        return date;
	    }

	    public void setDate(String date) {
	        this.date = date;
	    }

	    public String getOpTeam() {
	        return opTeam;
	    }

	    public void setOpTeam(String opTeam) {
	        this.opTeam = opTeam;
	    }

	    public Object getData() {
	        return data;
	    }

	    public void setData(Object data) {
	        this.data = data;
	    }

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	}

