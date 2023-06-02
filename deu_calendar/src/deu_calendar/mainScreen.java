package deu_calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class mainScreen extends JFrame implements ItemListener, ActionListener{
	Font fnt = new Font("����ü", Font.BOLD, 15);
	Font fnt2 = new Font("����ü", Font.BOLD, 12);
	
	
	//���
	JPanel selectPane = new JPanel(); //�гλ���
		JButton prevBtn = new JButton("��"); //������ư
		JButton nextBtn = new JButton("��"); //������ư
		JComboBox<Integer> yearCombo = new JComboBox<Integer>(); //�⵵ �޺��ڽ��߰�
		JComboBox<Integer> monthCombo = new JComboBox<Integer>(); //�� �޺��ڽ� �߰�
		JLabel yearLBl = new JLabel("��");  //"��"�� ǥ���� �� �߰�
		JLabel monthLBl = new JLabel("��"); //"��"�� ǥ���� ���߰�
		
	//���
	JPanel centerPane = new JPanel(new BorderLayout()); //��� �г��� �����ϰ� borderLayout���� ����ش�. 
														//borderLayout : �����¿� ����� ����� layout�� ��°�
		JPanel titlePane = new JPanel(new GridLayout(1, 7));// Ÿ��Ʋ�� ������ų �г��� �����ϰ� GridLayout���� ����ش�.
															// GridLayout: ������ ���� ��� ���� �����ϴ� ���̾ƿ��̴� 1�� 7���̹Ƿ� ��,��,ȭ,��,��,��,�� �� ���Եȴ�.
		String[] title = {"��", "��", "ȭ", "��", "��", "��", "��"};
		JPanel dayPane = new JPanel(new GridLayout(0, 7)); // ���� �����ϸ� ��¥�� ������ �ȴ�. 
	
	//�޷°��� ������
	Calendar date; //�޷�����
	int year; //���, �� ����
	int month;
	private final JLabel lblNewLabel = new JLabel("\uB3D9\uC758 \uCE98\uB9B0\uB354");
	private final JLabel lblNewLabel_1 = new JLabel("\uC77C\uC815 \uB9AC\uC2A4\uD2B8");
	private final JTextArea txtrTestTest = new JTextArea();
	
	
	
	public mainScreen() {
		super("���� Ķ����");
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		date = Calendar.getInstance();//������ ��¥ �ð� ��ü ���� + ��ü�� �޾ƿ´�.
		year = date.get(Calendar.YEAR); // Ķ�������� ���� �޾ƿͼ� �̸������س��� year�� �����Ѵ�.
		month = date.get(Calendar.MONTH)+1; //���� �޾ƿͼ� month�� �����Ѵ�. +1�� �ϴ� ������ 0~11�̶�
		getContentPane().setLayout(null);
		selectPane.setBounds(202, 46, 729, 37);
		
		//���
		selectPane.setBackground(Color.WHITE); //��׶����� ����� �����Ѵ�.
		selectPane.add(prevBtn); prevBtn.setFont(fnt);  
		selectPane.add(yearCombo); yearCombo.setFont(fnt);
		selectPane.add(yearLBl); yearLBl.setFont(fnt);
		selectPane.add(monthCombo); monthCombo.setFont(fnt);
		selectPane.add(monthLBl); monthLBl.setFont(fnt);
		selectPane.add(nextBtn); nextBtn.setFont(fnt);  //�гο� �������� �ۼ��� font�� �����Ѵ�.
		
		getContentPane().add(selectPane); // borderLayout : �����¿� ����� ����� layout�� ��°�
											// ���� ���Խ�Ű�� selectPane�� �ִ´�.
		
		//���� ��, �� ����
		setYear();	
		setMonth();
		
		//titleȣ��
		setCalendarTitle();		//�Ͽ�ȭ������並 �������� �޼ҵ� setCalendarTitle�� ȣ���Ѵ�.
		centerPane.setBounds(202, 89, 729, 380);
		titlePane.setBorder(new LineBorder(new Color(0, 0, 0)));
		titlePane.setBackground(new Color(220, 220, 220));
		centerPane.add(BorderLayout.NORTH, titlePane);	//�����г��� ���ʿ� title�� �ִ´�(�Ͽ�ȭ�������)
		getContentPane().add(centerPane);
		dayPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		dayPane.setBackground(Color.WHITE);
		
		//��¥�����
		centerPane.add(dayPane);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 33));
		lblNewLabel.setBounds(407, 5, 226, 37);
		
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 17));
		lblNewLabel_1.setBounds(61, 46, 91, 37);
		
		getContentPane().add(lblNewLabel_1);
		txtrTestTest.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtrTestTest.setBackground(new Color(255, 255, 255));
		txtrTestTest.setEditable(false);
		txtrTestTest.setRows(10);
		txtrTestTest.setText("TEST1\r\nTEST2");
		txtrTestTest.setBounds(12, 89, 178, 380);
		
		getContentPane().add(txtrTestTest);
		setDay();	//setDay()�޼ҵ带 ȣ���Ѵ�.
		
		
		//---------------------------����̺�Ʈ�� �߰�-------------------------------
		prevBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		//��� �̺�Ʈ �ٽõ��
		yearCombo.addItemListener(this);
		monthCombo.addItemListener(this);
		
		//JFrame�� ������
		setLocation(450,200);
		setSize(959, 518);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	//��¥����
	public void setDay() {
		//����
		date.set(year, month-1, 1); //date�� �����ϴµ�, ��(day)�� 1�� �����Ѵ�.
		int week = date.get(Calendar.DAY_OF_WEEK); //DAY_OF_WEEK�� �Ͽ�ȭ��������̸� �̵����͸� �޾ƿͼ� week�� �ִ´�.
		//��������
		int lastDay = date.getActualMaximum(Calendar.DATE); //getActualMaximum �� ��¥�� ���� �� Calender �� ������ �ִ� ��
															//getMaximum �� Calender ��ü�� �ִ�� ������ �ִ� ��
															//���������� �ҷ��´�.
		//����
		for(int s=1; s<week; s++) {  //�ݺ����� ������.
			JLabel lbl = new JLabel(" "); //�鿩����
			dayPane.add(lbl);
		}
		//��¥�߰�
		for(int day=1; day<=lastDay; day++) {
			/*JButton lbl = new JButton(String.valueOf(day)); //�󺧼������ִµ� String.value �� ����ȯ�̴�. JLabel�� ����� �Է��ϰԵд�.
//			lbl.setHorizontalAlignment(SwingConstants.TOP);		//// ��� ����
//			lbl.setEditable(false);
			lbl.setBackground(Color.WHITE);
			lbl.setFont(fnt); //�󺧿� ��Ʈ�� �����Ѵ�.
			
			lbl.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new detail();
				}
			});
			
			//����ϴ� ��¥�� ���� ����
			date.set(Calendar.DATE, day); // 19 ->1
			int w = date.get(Calendar.DAY_OF_WEEK); //����
			if(w ==1) lbl.setForeground(Color.red); //�Ͽ�ȭ������� (1~7) 1�� �Ͽ����̹Ƿ� �Ͽ��Ͽ� red����
			if(w ==7) lbl.setForeground(Color.blue); //7�̹Ƿ� blue����
			dayPane.add(lbl);
		}
	}*/
			JButton lbl = new JButton();
		    lbl.setLayout(new BorderLayout()); // ��ư�� ���̾ƿ��� BorderLayout���� �����մϴ�.

		    JLabel dayLabel = new JLabel(String.valueOf(day));
		    dayLabel.setHorizontalAlignment(SwingConstants.RIGHT); // ��¥�� ������ �����մϴ�.
		    lbl.add(dayLabel, BorderLayout.NORTH); // ��¥�� ��ư�� ��ܿ� �߰��մϴ�.

		    mainScreen_Control main_con = new mainScreen_Control();
		    ArrayList<String> dbData = main_con.ConnectDB(year, month, day);
		    StringBuilder sb = new StringBuilder();
		    for (String item : dbData) {
		        sb.append(item).append("\n");
		    }

		    String[] lines = sb.toString().split("\n");
		    StringBuilder labelTextBuilder = new StringBuilder();
		    for (String line : lines) {
		    	if (line.length() > 8) {
		            line = line.substring(0, 8) + "..";
		        }
		        labelTextBuilder.append(line).append("\n");
		    }
		    String labelText = labelTextBuilder.toString().trim();

		    JLabel scheduleLabel = new JLabel("<html>" + labelText.replace("\n", "<br>") + "</html>");
		    System.out.println(labelText);
		    Font font = scheduleLabel.getFont();
		    Font smallerFont = font.deriveFont(font.getSize() - 2f); // ��Ʈ ũ�⸦ 2����Ʈ �۰� ����
		    scheduleLabel.setFont(smallerFont);
		    lbl.add(scheduleLabel, BorderLayout.CENTER); // ������ ��ư�� �߾ӿ� �߰��մϴ�.
		    
		    
		    lbl.setBackground(Color.WHITE); 
		    lbl.setFont(fnt); //�󺧿� ��Ʈ�� �����Ѵ�.
		    
		    lbl.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new detail();
				}
			});
			
			
			//����ϴ� ��¥�� ���� ����
			date.set(Calendar.DATE, day); // 19 ->1
			int w = date.get(Calendar.DAY_OF_WEEK); //����
			if(w ==1) lbl.setForeground(Color.red); //�Ͽ�ȭ������� (1~7) 1�� �Ͽ����̹Ƿ� �Ͽ��Ͽ� red����
			if(w ==7) lbl.setForeground(Color.blue); //7�̹Ƿ� blue����
			dayPane.add(lbl);
			
		}
	}
	
	//��ȭ��������� ����
	public void setCalendarTitle() { //�޼ҵ�
		for(int i =0; i <title.length; i++) { //������� �迭�� ����ŭ ������.
			JLabel lbl = new JLabel(title[i], JLabel.CENTER); //������� �迭�� ����ŭ label�� ���Խ�Ű�� ����� �����Ѵ�.
			lbl.setFont(fnt); //��Ʈ����
			if(i ==0) lbl.setForeground(Color.red); //setForeground��Ʈ�Ӽ��� �������ִµ� ���°�,
			if(i ==6) lbl.setForeground(Color.blue);
			titlePane.add(lbl); //Ÿ��Ʋ�гο� ���� �߰���Ų��.
		}
	}
	//�⵵����
	public void setYear() {
		for(int i= year-50; i<year+20; i++) { //�ش籸���� �ݺ�����������
			yearCombo.addItem(i); //yearCombo�ڽ��� ��´�.
		}
		yearCombo.setSelectedItem(year); //�޺��ڽ��� ������ �̺�Ʈ�� ���������ֱ����� ����
	}
	//������
	public void setMonth() {
		for(int i=1; i<=12; i++) {
			monthCombo.addItem(i);
		}
		monthCombo.setSelectedItem(month); //���͵�
	}
	
	//�޺��ڽ�Ŭ���̺�Ʈ
	public void itemStateChanged(ItemEvent e) { //�޺��ڽ��� �����Ͽ������� ���õǴ� �̺�Ʈ�̴�.
		year = (int)yearCombo.getSelectedItem(); //����ȯ�Ѱ������� ������ yearCombo�ٲ������ yearCombo�� ���� getSelected �����ͼ� ã�°��� ���� �ִ�.
		month = (int)monthCombo.getSelectedItem();
		
		dayPane.setVisible(false); //�г��� �ݴ´�.
		dayPane.removeAll(); //�����ִ� ��¥ �����
		setDay(); //��¥ ó�� �Լ� ȣ��
		dayPane.setVisible(true); //�г��� �����ְ� ó���Ѵ�.
		
									//���⼭ �ݰ� �����ٰ� ȣ���ϰ�, �ٽ� �����ִ� ������  �ȱ׷��� ȭ���� ���������ʱ� �����̴�.
		
	}
	//��ư�̺�Ʈ 
	public void actionPerformed(ActionEvent ae) {  //�׼��̺�Ʈ(��ư�̺�Ʈ)
		Object obj = ae.getSource(); //Obejct�� �׼��̺�Ʈ�� �ҽ��� �����´�.
		if(obj == prevBtn) {//������ư�� ��������
			//�������� ��������
			prevMonth(); //������ư�޼ҵ�ȣ��
			setDayReset(); //Day�� Reset���ִ� �޼ҵ� ȣ��
		}else if(obj == nextBtn) { //���� ��ư�� ��������
			//�������� ��������
			nextMonth(); //���͵�
			setDayReset(); //���͵�
		}
		
	}
	private void setDayReset() {
		//��� �̺�Ʈ �������
		yearCombo.removeItemListener(this); //����̺�Ʈ�� ���������ְ�
		monthCombo.removeItemListener(this);
		
		yearCombo.setSelectedItem(year); //yearCombo�� year�� �ش�Ǵ� ���� �����´�.
		monthCombo.setSelectedItem(month);
		
		dayPane.setVisible(false); //�г��� �����ֱ⸦ ��Ų��.
		dayPane.removeAll(); //���������.
		setDay(); //�ش�޼ҵ带 ȣ���Ѵ�.
		dayPane.setVisible(true); //�ٽú����ش�.
		
		yearCombo.addItemListener(this); //�ٽ� �̺�Ʈ�� ��Ͻ�Ų��.
		monthCombo.addItemListener(this); //�ٽ� �̺�Ʈ ���
		
	}
	public void prevMonth() { //��
		if(month==1) { //21.01�� �϶��� 12���� �������鼭 year�� ���⵵�� �ٲ۴�.
			year--;
			month=12;
		}else { //�׿��� ���
			month--; 
		}
	}
	public void nextMonth() {
		if(month==12){ //12���϶����� �⵵�� �߰���Ű�� ���� 1�ιٲ۴�.
			year++; 
			month=1;
		}else{ //�׿��� ���
			month++;
		}
	}
	
	//���۸޼ҵ�
	public static void main(String[] args) {
		new mainScreen();
	}
}
