package dto;

public class TimeSlotDTO {
	
	private int id;
	
	private int startTime;
	
	
	public TimeSlotDTO(int id, int startTime) {
		
		this.id = id;
		
		this.startTime = startTime;
		
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
	
	
	public int getStartTime() {
		
		return startTime;
		
	}
	
	public void setStartTime(int startTime) {
		
		this.startTime = startTime;
		
	}
	

}
