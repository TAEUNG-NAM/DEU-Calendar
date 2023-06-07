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
		setLocationRelativeTo(null); // ȭ�� �߾� ����
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC77C\uC815 \uB4F1\uB85D");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(303, 20, 95, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC77C\uC815 \uC774\uB984");
		lblNewLabel_1.setBounds(138, 98, 69, 34);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("���� ���", Font.PLAIN, 16));
		textField.setBounds(219, 97, 330, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uBA54\uBAA8");
		lblNewLabel_1_1.setBounds(138, 169, 69, 34);
		contentPane.add(lblNewLabel_1_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("���� ���", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		textArea.setBounds(138, 213, 411, 111);
		contentPane.add(textArea);
		 
		// ������� �߰�
		int maxLength = 50; // �ִ� ���� ��
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
        }); // �������
        
        // ���� ���� 50�� ����
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
		
		// ���� �׷� ��ü ����
		ButtonGroup BtnGroup = new ButtonGroup();
		
		// ���� ��ư���� �׷����� �߰�
		BtnGroup.add(rdbtnNewRadioButton);
		BtnGroup.add(rdbtnNewRadioButton_1);
		BtnGroup.add(rdbtnNewRadioButton_2);
		BtnGroup.add(rdbtnNewRadioButton_3);
		BtnGroup.add(rdbtnNewRadioButton_4);
		
		// registration Ŭ������ �ν��Ͻ� ����
		Regis_C reg = new Regis_C();
		
		JButton btnNewButton = new JButton("\uB4F1\uB85D");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ���� ���� �� �޾ƿ���
				reg.birngTitle();
				
				// ���� �޸� �� �޾ƿ���
				reg.bringMemo();
				
				// ���õ� ������ư �� ��������
				Repetiton = null;
		        for (Enumeration<AbstractButton> buttons = BtnGroup.getElements(); buttons.hasMoreElements();) {
		            JRadioButton button = (JRadioButton) buttons.nextElement();
		            if (button.isSelected()) {
		            	Repetiton = button;
		                break;
		            }
		        }
		        if(reg.birngTitle() == null || reg.birngTitle().isEmpty()) { // ���� �̸��� �� ���
		        	reg.showErrorMsg();
		        }else {
		        	reg.regist(year, month, select_day); // DB�� �Է� �� ����

					Window[] windows = Window.getWindows();
			        for (Window window : windows) {
			            window.dispose(); // �����ִ� ��� â ����
			        }
			        MainScreen_B NewmainScreen = new MainScreen_B();
			        NewmainScreen.setVisible(true); // Ķ���� ȭ�� ���
		        }
		        
			} // ��� ��ư Ŭ��
		});
		btnNewButton.setBounds(217, 528, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uCDE8\uC18C");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
				
			} // ��� ��ư Ŭ��
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
