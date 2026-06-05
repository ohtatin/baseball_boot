package work.luegg.baseball_boot.dto;

public class QueryStatsDTO {
	
	private long id;
	private String type;   // 投手 / 打者
	private String date;
	private String opTeam;
	private Object data;   // 裡面放 BatterDTO 或 PitcherDTO

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

