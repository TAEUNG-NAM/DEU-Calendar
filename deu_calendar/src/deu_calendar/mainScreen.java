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

import javax.swing.BorderFactory;
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
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class mainScreen extends JFrame implements ItemListener, ActionListener{
	Font fnt = new Font("����ü", Font.BOLD, 15);
	Font fnt2 = new Font("����ü", Font.BOLD, 12);
	
	mainScreen_Control controller = new mainScreen_Control();
	int studentID=20215030;
	ArrayList<ArrayList<ArrayList<String>>> result = controller.ConnectDB(studentID);
	
	
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
	
	
	public mainScreen() {
		super("���� Ķ����");
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		date = Calendar.getInstance();//������ ��¥ �ð� ��ü ���� + ��ü�� �޾ƿ´�.
		year = date.get(Calendar.YEAR); // Ķ�������� ���� �޾ƿͼ� �̸������س��� year�� �����Ѵ�.
		month = date.get(Calendar.MONTH)+1; //���� �޾ƿͼ� month�� �����Ѵ�. +1�� �ϴ� ������ 0~11�̶�
		getContentPane().setLayout(null);
		selectPane.setBounds(701, 34, 387, 37);
		
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
		centerPane.setBounds(202, 89, 886, 539);
		titlePane.setBorder(new LineBorder(new Color(0, 0, 0)));
		titlePane.setBackground(new Color(220, 220, 220));
		centerPane.add(BorderLayout.NORTH, titlePane);	//�����г��� ���ʿ� title�� �ִ´�(�Ͽ�ȭ�������)
		getContentPane().add(centerPane);
		dayPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		dayPane.setBackground(Color.WHITE);
		
		//��¥�����
		centerPane.add(dayPane);
		lblNewLabel.setFont(new Font("���� ��� Semilight", Font.BOLD, 33));
		lblNewLabel.setBounds(464, 22, 226, 37);
		
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 17));
		lblNewLabel_1.setBounds(61, 46, 91, 37);
		
		getContentPane().add(lblNewLabel_1);
		
		JList list = new JList();
		// �ӽ� ������ �����ϱ� ���� �ڵ� (�����ؼ� ����ؾ� ��)
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"\uC77C\uC815 1", "\uC77C\uC815 2", "\uC77C\uC815 3", "\uC77C\uC815 4"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(27, 89, 150, 539);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // �׵θ� ��
		list.setFixedCellHeight(40);
		getContentPane().add(list);
		setDay();	//setDay()�޼ҵ带 ȣ���Ѵ�.
		
		
		//---------------------------����̺�Ʈ�� �߰�-------------------------------
		prevBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		//��� �̺�Ʈ �ٽõ��
		yearCombo.addItemListener(this);
		monthCombo.addItemListener(this);
		
		//JFrame�� ������
		setSize(1130, 691);
		setLocationRelativeTo(null); // ȭ�� �߾� ����
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
			  	JButton lbl = new JButton();
			    
			    lbl.setLayout(new BorderLayout()); // ��ư�� ���̾ƿ��� BorderLayout���� �����մϴ�.

			    JLabel dayLabel = new JLabel(String.valueOf(day));
			    
			    // ����ϴ� ��¥�� ���� ����
			    date.set(Calendar.DATE, day);
			    int w = date.get(Calendar.DAY_OF_WEEK);
			    if (w == 1)
			    	dayLabel.setForeground(Color.RED); // �Ͽ����� ���������� ǥ��
			    if (w == 7)
			    	dayLabel.setForeground(Color.BLUE); // ������� �Ķ������� ǥ��

			    dayPane.add(lbl);
			    
			    dayLabel.setHorizontalAlignment(SwingConstants.RIGHT); // ��¥�� ������ �����մϴ�.
			    lbl.add(dayLabel, BorderLayout.NORTH); // ��¥�� ��ư�� ��ܿ� �߰��մϴ�.

			    StringBuilder scheduleBuilder = new StringBuilder();
			    for (ArrayList<String> planInfo : result.get(0)) {
			        if (planInfo.get(2).equals(String.valueOf(year)+ String.valueOf(month)+String.valueOf(day))) {
			            String planTitle = planInfo.get(0);
			            scheduleBuilder.append("- ").append(planTitle).append("\n");
			     
			        }
			    }
			    for (ArrayList<String> subjectInfo : result.get(1)) {
			        if (subjectInfo.get(2).equals(String.valueOf(year)+ String.valueOf(month)+String.valueOf(day))) {
			            String subTitle = subjectInfo.get(0);
			            scheduleBuilder.append("+ ").append(subTitle).append("\n");
			        }
			    }

			    String scheduleText = scheduleBuilder.toString().trim();
			    JLabel scheduleLabel = new JLabel("<html>" + scheduleText.replace("\n", "<br>") + "</html>");
			    Font font = scheduleLabel.getFont();
			    Font smallerFont = font.deriveFont(font.getSize() - 2f); // ��Ʈ ũ�⸦ 2����Ʈ �۰� ����
			    scheduleLabel.setFont(smallerFont);
			    scheduleLabel.setForeground(Color.BLUE);	// �������� �۾� ������
			    lbl.add(scheduleLabel, BorderLayout.CENTER); // ������ ��ư�� �߾ӿ� �߰��մϴ�.

			    
			    lbl.setBackground(Color.white);
			    lbl.setFont(fnt); // ��ư�� ��Ʈ�� �����մϴ�.

			    
			    
			    lbl.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			            new detail();
			        }
			    });



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
