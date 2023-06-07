package deu_calendar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class Regis_B extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;
	public static JTextArea textArea;
	public static JRadioButton Repetiton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		String select_day="0";
		String year="0";
		String month="0";
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regis_B frame = new Regis_B(year, month, select_day);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param select_day 
	 * @param month 
	 */
	public Regis_B(String year, String month, String select_day) {
		setTitle("\uC77C\uC815 \uB4F1\uB85D");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 724, 624);
		setLocationRelativeTo(null); // 화면 중앙 정렬
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC77C\uC815 \uB4F1\uB85D");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(303, 20, 95, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC77C\uC815 \uC774\uB984");
		lblNewLabel_1.setBounds(138, 98, 69, 34);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField.setBounds(219, 97, 330, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uBA54\uBAA8");
		lblNewLabel_1_1.setBounds(138, 169, 69, 34);
		contentPane.add(lblNewLabel_1_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		textArea.setBounds(138, 213, 411, 111);
		contentPane.add(textArea);
		 
		// 여기부터 추가
		int maxLength = 50; // 최대 글자 수
		AbstractDocument doc = (AbstractDocument) textArea.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int overLimit = (currentLength + text.length()) - maxLength - length;
                if (overLimit > 0) {
                    text = text.substring(0, text.length() - overLimit);
                }
                if (text.length() > 0) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        }); // 여기까지
        
        // 일정 제목 50자 제한
        AbstractDocument doc2 = (AbstractDocument) textField.getDocument();
        doc2.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws javax.swing.text.BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int overLimit = (currentLength + text.length()) - maxLength - length;
                if (overLimit > 0) {
                    text = text.substring(0, text.length() - overLimit);
                }
                if (text.length() > 0) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uBC18\uBCF5 \uC5C6\uC74C");
		rdbtnNewRadioButton.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton.setBounds(138, 363, 121, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\uB9E4\uC6D4 \uAC19\uC740 \uC77C");
		rdbtnNewRadioButton_1.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton_1.setBounds(361, 363, 121, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("\uB9E4\uC77C");
		rdbtnNewRadioButton_2.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton_2.setBounds(138, 412, 121, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("\uB9E4\uB144 \uAC19\uC740 \uC6D4\uC77C");
		rdbtnNewRadioButton_3.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton_3.setBounds(361, 412, 121, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("\uB9E4\uC8FC \uAC19\uC740 \uC694\uC77C");
		rdbtnNewRadioButton_4.setBackground(new Color(255, 255, 255));
		rdbtnNewRadioButton_4.setBounds(138, 467, 121, 23);
		contentPane.add(rdbtnNewRadioButton_4);
		
		// 라디오 그룹 객체 생성
		ButtonGroup BtnGroup = new ButtonGroup();
		
		// 라디오 버튼들을 그룹으로 추가
		BtnGroup.add(rdbtnNewRadioButton);
		BtnGroup.add(rdbtnNewRadioButton_1);
		BtnGroup.add(rdbtnNewRadioButton_2);
		BtnGroup.add(rdbtnNewRadioButton_3);
		BtnGroup.add(rdbtnNewRadioButton_4);
		
		// registration 클래스의 인스턴스 생성
		Regis_C reg = new Regis_C();
		
		JButton btnNewButton = new JButton("\uB4F1\uB85D");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 일정 제목 값 받아오기
				reg.birngTitle();
				
				// 일정 메모 값 받아오기
				reg.bringMemo();
				
				// 선택된 라디오버튼 값 가져오기
				Repetiton = null;
		        for (Enumeration<AbstractButton> buttons = BtnGroup.getElements(); buttons.hasMoreElements();) {
		            JRadioButton button = (JRadioButton) buttons.nextElement();
		            if (button.isSelected()) {
		            	Repetiton = button;
		                break;
		            }
		        }
		        if(reg.birngTitle() == null || reg.birngTitle().isEmpty()) { // 일정 이름이 빈 경우
		        	reg.showErrorMsg();
		        }else {
		        	reg.regist(year, month, select_day); // DB에 입력 값 삽입

					Window[] windows = Window.getWindows();
			        for (Window window : windows) {
			            window.dispose(); // 열려있는 모든 창 종료
			        }
			        MainScreen_B NewmainScreen = new MainScreen_B();
			        NewmainScreen.setVisible(true); // 캘린더 화면 출력
		        }
		        
			} // 등록 버튼 클릭
		});
		btnNewButton.setBounds(217, 528, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uCDE8\uC18C");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
				
			} // 취소 버튼 클릭
		});
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(137, 212, 413, 113);
		contentPane.add(panel);
		btnNewButton_1.setBounds(373, 528, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}
