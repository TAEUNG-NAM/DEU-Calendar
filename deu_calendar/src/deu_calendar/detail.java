package deu_calendar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;

public class detail extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detail frame = new detail();
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
	public detail() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 503);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\uC77C\uC815 \uB4F1\uB85D");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {    
				new entry();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("\uACFC\uBAA9\uBA85");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(67, 123, 112, 77);
		contentPane.add(lblNewLabel_1);
		btnNewButton.setBounds(420, 60, 94, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("date");
		lblNewLabel.setBounds(260, 20, 65, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\uACFC\uC81C\uBA85");
		lblNewLabel_2.setBounds(191, 120, 307, 82);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC77C\uC815 \uC81C\uBAA9");
		lblNewLabel_1_1.setBounds(67, 241, 112, 77);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uC77C\uC815 \uBA54\uBAA8");
		lblNewLabel_2_1.setBounds(191, 238, 307, 82);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton_1 = new JButton("\uC218\uC815");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new change();
			} // ���� ��ư Ŭ��
		});
		btnNewButton_1.setBounds(538, 150, 65, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("\uC218\uC815");
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new change();
			} // ���� ��ư Ŭ��
		});
		btnNewButton_1_1.setBounds(538, 255, 65, 50);
		contentPane.add(btnNewButton_1_1);
	}

}
