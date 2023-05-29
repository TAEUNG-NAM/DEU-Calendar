package deu_calendar;

public class registration {
	
	private String planName; // 일정 제목
	private String memoTextArea; // 일정 메모
	private int Repetiton; // 반복 값
	private String dayValue; // 날짜 값
	
	// 일정 제목 가져오는 메소드
	public String birngTitle(String planName) { 
		planName = entry.textField.getText();
		
		return planName;
	}
	
	// 일정 메모 가져오는 메소드
	public String bringMemo(String memoText) {
		memoTextArea = entry.textArea.getText();
		
		return memoTextArea;	
	}
	
	// 반복 값 가져오는 메소드
	public int bringRepeat() {
		
		return Repetiton;
	}
	
	// 날짜 값 받아오는 메소드
	public String bringDayValue() {
		
		return dayValue;
	}
	
	// 일정 등록 기능 메소드
	public void regist() {
		
	}
	
	// 경고 메시지 메소드
	public void showErrorMsg() {
		
	}

}
