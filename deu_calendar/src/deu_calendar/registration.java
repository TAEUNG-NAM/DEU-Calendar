package deu_calendar;

public class registration {
	
	private String planName; // ���� ����
	private String memoTextArea; // ���� �޸�
	private int Repetiton; // �ݺ� ��
	private String dayValue; // ��¥ ��
	
	// ���� ���� �������� �޼ҵ�
	public String birngTitle(String planName) { 
		planName = entry.textField.getText();
		
		return planName;
	}
	
	// ���� �޸� �������� �޼ҵ�
	public String bringMemo(String memoText) {
		memoTextArea = entry.textArea.getText();
		
		return memoTextArea;	
	}
	
	// �ݺ� �� �������� �޼ҵ�
	public int bringRepeat() {
		
		return Repetiton;
	}
	
	// ��¥ �� �޾ƿ��� �޼ҵ�
	public String bringDayValue() {
		
		return dayValue;
	}
	
	// ���� ��� ��� �޼ҵ�
	public void regist() {
		
	}
	
	// ��� �޽��� �޼ҵ�
	public void showErrorMsg() {
		
	}

}
