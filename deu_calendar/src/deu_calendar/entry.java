package deu_calendar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class entry extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;
	public static JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entry frame = new entry();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public entry() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 624);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC77C\uC815 \uB4F1\uB85D");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBounds(303, 20, 95, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC77C\uC815 \uC774\uB984");
		lblNewLabel_1.setBounds(138, 98, 69, 34);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(283, 97, 275, 37);
		contentPane.add(textField);
		textField.setColumns(10);
			
		JLabel lblNewLabel_1_1 = new JLabel("\uBA54\uBAA8");
		lblNewLabel_1_1.setBounds(138, 169, 69, 34);
		contentPane.add(lblNewLabel_1_1);
		
		textArea = new JTextArea();
		textArea.setBounds(138, 213, 411, 111);
		contentPane.add(textArea);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uBC18\uBCF5 \uC5C6\uC74C");
		rdbtnNewRadioButton.setBounds(138, 363, 121, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\uB9E4\uC6D4 \uAC19\uC740 \uC77C");
		rdbtnNewRadioButton_1.setBounds(361, 363, 121, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("\uB9E4\uC77C");
		rdbtnNewRadioButton_2.setBounds(138, 412, 121, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("\uB9E4\uB144 \uAC19\uC740 \uC6D4\uC77C");
		rdbtnNewRadioButton_3.setBounds(361, 412, 121, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("\uB9E4\uC8FC \uAC19\uC740 \uC694\uC77C");
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
		registration reg = new registration();
		
		JButton btnNewButton = new JButton("등록");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 일정 제목 값 받아오기
				reg.birngTitle(textField.getText());
				System.out.println(reg.birngTitle(textField.getText()));
				
				// 일정 메모 값 받아오기
				reg.bringMemo(textArea.getText());
				System.out.println(reg.bringMemo(textArea.getText()));
				
				// 선택된 라디오버튼 값 가져오기
				JRadioButton selectBtn = null;
		        for (Enumeration<AbstractButton> buttons = BtnGroup.getElements(); buttons.hasMoreElements();) {
		            JRadioButton button = (JRadioButton) buttons.nextElement();
		            if (button.isSelected()) {
		            	selectBtn = button;
		                break;
		            }
		        }
				System.out.println(selectBtn.getActionCommand());
				
				dispose();
			} // 등록 버튼 클릭
		});
		
		btnNewButton.setBounds(217, 528, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				
			} // 취소 버튼 클릭
		});
		btnNewButton_1.setBounds(373, 528, 97, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
}
