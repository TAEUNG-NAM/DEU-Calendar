package deu_calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MainScreen_B extends JFrame implements ItemListener, ActionListener{
	Font fnt = new Font("굴림체", Font.BOLD, 15);
	Font fnt2 = new Font("굴림체", Font.BOLD, 12);
	
	Schedule_C controller = new Schedule_C();
	int studentID=20215030;
	ArrayList<ArrayList<ArrayList<String>>> result = controller.ConnectDB(studentID);
	 ArrayList<ArrayList<String>> subjectList = result.get(1); // 과목 정보를 가져옵니다.  (추가)
	 ArrayList<ArrayList<String>> planList = result.get(1);
	
	//상단
	JPanel selectPane = new JPanel(); //패널생성
		JButton prevBtn = new JButton("◀"); //이전버튼
		JButton nextBtn = new JButton("▶"); //다음버튼
		JComboBox<Integer> yearCombo = new JComboBox<Integer>(); //년도 콤보박스추가
		JComboBox<Integer> monthCombo = new JComboBox<Integer>(); //월 콤보박스 추가
		JLabel yearLBl = new JLabel("년");  //"년"을 표시할 라벨 추가
		JLabel monthLBl = new JLabel("월"); //"월"을 표시할 라벨추가
		
	//가운데
	JPanel centerPane = new JPanel(new BorderLayout()); //가운데 패널을 생성하고 borderLayout으로 잡아준다. 
														//borderLayout : 상하좌우 가운데로 나뉘어서 layout을 잡는것
		JPanel titlePane = new JPanel(new GridLayout(1, 7));// 타이틀을 생성시킬 패널을 생성하고 GridLayout으로 잡아준다.
															// GridLayout: 지정된 수의 행과 열을 생성하는 레이아웃이다 1행 7열이므로 일,월,화,수,목,금,토 가 들어가게된다.
		String[] title = {"일", "월", "화", "수", "목", "금", "토"};
		JPanel dayPane = new JPanel(new GridLayout(0, 7)); // 위와 동일하며 날짜가 나오게 된다. 
	
	//달력관련 데이터
	Calendar date; //달력주입
	int year; //년과, 월 주입
	int month;
	private final JLabel lblNewLabel = new JLabel("\uB3D9\uC758 \uCE98\uB9B0\uB354");
	private final JLabel lblNewLabel_1 = new JLabel("\uC77C\uC815 \uB9AC\uC2A4\uD2B8");
	private final JScrollPane scrollPane = new JScrollPane();
	
	
	public MainScreen_B() {
		super("동의 캘린더");
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		date = Calendar.getInstance();//현재의 날짜 시간 객체 생성 + 객체를 받아온다.
		year = date.get(Calendar.YEAR); // 캘린더에서 년을 받아와서 미리생성해놓은 year에 주입한다.
		month = date.get(Calendar.MONTH)+1; //월을 받아와서 month에 대입한다. +1을 하는 이유는 0~11이라
		getContentPane().setLayout(null);
		selectPane.setBounds(701, 34, 387, 37);
		
		//상단
		selectPane.setBackground(Color.WHITE); //백그라운드의 배경을 주입한다.
		selectPane.add(prevBtn); prevBtn.setFont(fnt);  
		selectPane.add(yearCombo); yearCombo.setFont(fnt);
		selectPane.add(yearLBl); yearLBl.setFont(fnt);
		selectPane.add(monthCombo); monthCombo.setFont(fnt);
		selectPane.add(monthLBl); monthLBl.setFont(fnt);
		selectPane.add(nextBtn); nextBtn.setFont(fnt);  //패널에 대입한후 작성한 font를 주입한다.
		
		getContentPane().add(selectPane); // borderLayout : 상하좌우 가운데로 나뉘어서 layout을 잡는것
											// 위에 대입시키고 selectPane을 넣는다.
		
		//현재 년, 월 세팅
		setYear();	
		setMonth();
		
		//title호출
		setCalendarTitle();		//일월화수목금토를 만들어놓은 메소드 setCalendarTitle을 호출한다.
		centerPane.setBounds(202, 89, 886, 539);
		titlePane.setBorder(new LineBorder(new Color(0, 0, 0)));
		titlePane.setBackground(new Color(220, 220, 220));
		centerPane.add(BorderLayout.NORTH, titlePane);	//센터패널의 위쪽에 title을 넣는다(일월화수목금토)
		getContentPane().add(centerPane);
		dayPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		dayPane.setBackground(Color.WHITE);
		
		//날짜만들기
		centerPane.add(dayPane);
		lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 33));
		lblNewLabel.setBounds(464, 22, 226, 37);
		
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblNewLabel_1.setBounds(61, 46, 91, 37);
		
		getContentPane().add(lblNewLabel_1);
		
		setDay();	//setDay()메소드를 호출한다.

		//---------------------------기능이벤트를 추가-------------------------------
		prevBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		//년월 이벤트 다시등록
		yearCombo.addItemListener(this);
		monthCombo.addItemListener(this);
		
		//JFrame의 설정들
		setSize(1130, 691);
		setLocationRelativeTo(null); // 화면 중앙 정렬
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	//날짜셋팅
	public void setDay() {
		//요일
		date.set(year, month-1, 1); //date를 세팅하는데, 일(day)를 1로 세팅한다.
		int week = date.get(Calendar.DAY_OF_WEEK); //DAY_OF_WEEK는 일월화수목금토이며 이데이터를 받아와서 week에 넣는다.
		//마지막날
		int lastDay = date.getActualMaximum(Calendar.DATE); //getActualMaximum 는 날짜가 셋팅 된 Calender 가 가질수 있는 값
															//getMaximum 는 Calender 자체가 최대로 가질수 있는 값
															//마지막날을 불러온다.
		//공백
		for(int s=1; s<week; s++) {  //반복문을 돌린다.
			JLabel lbl = new JLabel(" "); //들여쓰기
			dayPane.add(lbl);
		}
		//날짜추가
		DefaultListModel<String> listModel = new DefaultListModel<>(); //
		for(int day=1; day<=lastDay; day++) {			
			  	JButton lbl = new JButton();
			    
			    lbl.setLayout(new BorderLayout()); // 버튼의 레이아웃을 BorderLayout으로 설정합니다.
			    JLabel dayLabel = new JLabel(String.valueOf(day));			    
			    // 출력하는 날짜에 대한 요일
			    date.set(Calendar.DATE, day);
			    int w = date.get(Calendar.DAY_OF_WEEK);
			    if (w == 1)
			    	dayLabel.setForeground(Color.RED); // 일요일은 빨간색으로 표시
			    if (w == 7)
			    	dayLabel.setForeground(Color.BLUE); // 토요일은 파란색으로 표시

			    dayPane.add(lbl);
			    
			    dayLabel.setHorizontalAlignment(SwingConstants.RIGHT); // 날짜를 오른쪽 정렬합니다.
			    lbl.add(dayLabel, BorderLayout.NORTH); // 날짜를 버튼의 상단에 추가합니다.

			    StringBuilder scheduleBuilder = new StringBuilder();
			    
			    ArrayList<ArrayList<String>> sendlist = new ArrayList<>(); //detail로 보낼 정보들
			    
			    ArrayList<Integer> ddaylist = new ArrayList<>();
			    
			    // DB에서 일정 데이터 가져오기
			    for (ArrayList<String> planInfo : result.get(0)) {
			        if (planInfo.get(2).equals(String.valueOf(year)+ String.valueOf(month)+String.valueOf(day))) {
			        	sendlist.add(planInfo);
			            String planTitle = planInfo.get(0);
			            scheduleBuilder.append("- ").append(planTitle).append("\n");
			            ddaylist.add(day);
			        }
			    }
			    // DB에서 과목 데이터 가져오기
			    for (ArrayList<String> subjectInfo : result.get(1)) {
			        if (subjectInfo.get(2).equals(String.valueOf(year)+ String.valueOf(month)+String.valueOf(day))) {
			            sendlist.add(subjectInfo);
			            String subTitle = subjectInfo.get(0);
			            scheduleBuilder.append("+ ").append(subTitle).append("\n");
			            ddaylist.add(day);
			        }
			    }
			    
			    String scheduleText = scheduleBuilder.toString().trim();
			    JLabel scheduleLabel = new JLabel("<html>" + scheduleText.replace("\n", "<br>") + "</html>");
			    Font font = scheduleLabel.getFont();
			    Font smallerFont = font.deriveFont(font.getSize() -2f); // 글씨 크기 조절 
			    scheduleLabel.setFont(smallerFont);
			    scheduleLabel.setForeground(Color.BLUE);	// 일정내용 글씨 색설정
			    lbl.add(scheduleLabel, BorderLayout.CENTER); // 일정을 버튼의 중앙에 추가합니다. 

			    
			    int dDayFormattedMonth = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("M")));
			    int dDayFormattedDay = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("d")));

			    // 일정리스트에 D-day와 일정 이름, 과목명 추가
			    if (!scheduleText.isEmpty()) {
			        ArrayList<Integer> distinctDdays = new ArrayList<>(new LinkedHashSet<>(ddaylist));
			        for (int dday : distinctDdays) {
			            int dDayMonthDiff = month - dDayFormattedMonth;
			            int dDayDayDiff = dday - dDayFormattedDay;
			            int dDayDiff = dDayMonthDiff * lastDay + dDayDayDiff;
			            if(dDayDiff<0) 		dDayDiff =0; // dday가 지났을경우 [D-0] 으로 출력 
			            String dDayInfo = ("<html>" + scheduleText.replace("\n", "<br>") + "") + " [ D - " + dDayDiff + " ]";
			            listModel.addElement(dDayInfo);
			        }
			    }

			    lbl.setBackground(Color.white);
			    lbl.setFont(fnt); // 버튼에 폰트를 설정합니다.
			    
			 // ActionListener 내부에서 day 변수를 사용하기 위해 클래스 멤버 변수로 변경
			    final int selectedDay = day;
			   
			    lbl.addActionListener(new ActionListener() {
			        @Override
			        public void actionPerformed(ActionEvent e) {
			        	 String select_day= (String.valueOf(selectedDay));
			        	 new Detail_B(sendlist, String.valueOf(year) , String.valueOf(month),select_day );
			        }
			    });

			}
				scrollPane.setBounds(27, 89, 150, 539);
				
				getContentPane().add(scrollPane);
				JList<String> list = new JList<>(listModel);
				scrollPane.setViewportView(list);
				list.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 테두리 선
				list.setFixedCellHeight(40);
				
	}
	
	//월화수목금토일 설정
	public void setCalendarTitle() { //메소드
		for(int i =0; i <title.length; i++) { //만들어준 배열의 수만큼 돌린다.
			JLabel lbl = new JLabel(title[i], JLabel.CENTER); //만들어준 배열의 수만큼 label에 주입시키고 가운데로 오게한다.
			lbl.setFont(fnt); //폰트적용
			if(i ==0) lbl.setForeground(Color.red); //setForeground폰트속성을 변경해주는데 쓰는것,
			if(i ==6) lbl.setForeground(Color.blue);
			titlePane.add(lbl); //타이틀패널에 라벨을 추가시킨다.
		}
	}
	//년도세팅
	public void setYear() {
		for(int i= year-50; i<year+6; i++) { //해당구문을 반복문을돌려서
			yearCombo.addItem(i); //yearCombo박스에 담는다.
		}
		yearCombo.setSelectedItem(year); //콤보박스에 담지만 이벤트와 연동시켜주기위해 선언
	}
	//월세팅
	public void setMonth() {
		for(int i=1; i<=12; i++) {
			monthCombo.addItem(i);
		}
		monthCombo.setSelectedItem(month); //위와동
	}
	
	//콤보박스클릭이벤트
	public void itemStateChanged(ItemEvent e) { //콤보박스를 변경하였을때에 선택되는 이벤트이다.
		year = (int)yearCombo.getSelectedItem(); //형변환한것을볼수 있으며 yearCombo바뀌었을때 yearCombo의 값을 getSelected 가져와서 찾는것을 볼수 있다.
		month = (int)monthCombo.getSelectedItem();
		
		dayPane.setVisible(false); //패널을 닫는다.
		dayPane.removeAll(); //원래있는 날짜 지우기
		setDay(); //날짜 처리 함수 호출
		dayPane.setVisible(true); //패널을 볼수있게 처리한다.
		
									//여기서 닫고 지웠다가 호출하고, 다시 보여주는 이유는  안그러면 화면이 지워지지않기 때문이다.
		
	}
	//버튼이벤트 
	public void actionPerformed(ActionEvent ae) {  //액션이벤트(버튼이벤트)
		Object obj = ae.getSource(); //Obejct에 액션이벤트의 소스를 가져온다.
		if(obj == prevBtn) {//이전버튼을 눌렀을때
			//이전월을 눌렀을때
			prevMonth(); //이전버튼메소드호출
			setDayReset(); //Day를 Reset해주는 메소드 호출
		}else if(obj == nextBtn) { //이후 버튼을 눌렀을때
			//다음월을 눌렀을떄
			nextMonth(); //위와동
			setDayReset(); //위와동
		}
		
	}
	private void setDayReset() {
		//년월 이벤트 등록해제
		yearCombo.removeItemListener(this); //등록이벤트를 해제시켜주고
		monthCombo.removeItemListener(this);
		
		yearCombo.setSelectedItem(year); //yearCombo의 year에 해당되는 값을 가져온다.
		monthCombo.setSelectedItem(month);
		
		dayPane.setVisible(false); //패널을 보여주기를 숨킨다.
		dayPane.removeAll(); //전부지운다.
		setDay(); //해당메소드를 호출한다.
		dayPane.setVisible(true); //다시보여준다.
		
		yearCombo.addItemListener(this); //다시 이벤트를 등록시킨다.
		monthCombo.addItemListener(this); //다시 이벤트 등록
		
	}
	public void prevMonth() { //월
		if(month==1) { //21.01월 일때에 12월로 떨어지면서 year를 전년도로 바꾼다.
			year--;
			month=12;
		}else { //그외의 경우
			month--; 
		}
	}
	public void nextMonth() {
		if(month==12){ //12월일때에는 년도를 추가시키고 월을 1로바꾼다.
			year++; 
			month=1;
		}else{ //그외의 경우
			month++;
		}
	}
	
	//시작메소드
	public static void main(String[] args) {
		new MainScreen_B();
	}
}
