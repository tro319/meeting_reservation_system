package dto;

public class ReseSituDTO {
	
	// 各値初期化
	
	private int id;
	
	private Boolean status;
	
	private int interID;
	
	private int timeID;
	
	private String weekday;
	
	private int startTime;
	
	private int orderInt;
	
	
	// コンストラクタ
	
	
	public ReseSituDTO(int id, Boolean status, int interID, int timeID, String weekday, int startTime, int orderInt) {
		
		this.id = id;
		
		this.status = status;
		
		this.interID = interID;
		
		this.timeID = timeID;
		
		this.weekday = weekday;
		
		this.startTime = startTime;
		
		this.orderInt = orderInt;
		
	}
	
	
	/*
	 * 
	 * ゲッター・セッター
	 * 
	 * @param ユーザーが指定する値 (セッター)
	 * 
	 * @return ユーザーが取得する値（ゲッター)
	 * 
	 */
	
	
	public int getID() {
		
		return id;
		
	}
	
	public void setID(int id) {
		
		this.id = id;
		
	}
	
	
	public Boolean getStatus() {
		
		return status;
		
	}
	
	public void setStatus(Boolean status) {
		
		this.status = status;
		
	}
	
	
	public int getInterID() {
		
		return interID;
		
	}
	
	public void setInterID(int interID) {
		
		this.interID = interID;
		
	}
	
	
	public int getTimeID() {
		
		return timeID;
		
	}
	
	public void setTimeID(int timeID) {
		
		this.timeID = timeID;
		
	}
	
	
	public String getWeekday() {
		
		return weekday;
		
	}
	
	public void setWeekday(String weekday) {
		
		this.weekday = weekday;
		
	}
	
	public int getStartTime() {
		
		return startTime;
		
	}
	
	public void setStartTime(int startTime) {
		
		this.startTime = startTime;
		
	}
	
	public int getOrderInt() {
		
		return orderInt;
		
	}
	

}
