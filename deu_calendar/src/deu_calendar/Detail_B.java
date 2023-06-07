package deu_calendar;

import java.awt.Font;
import java.awt.EventQueue;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class Detail_B extends JFrame {
    private ArrayList<ArrayList<String>> data;
    private JPanel contentPane;
    private String select_day;
    private JTable table;
    private DefaultTableModel model; //jtable

    public static void main(String[] args) {
        final ArrayList<ArrayList<String>> data = new ArrayList<>();
        String select_day="0";
        String year="0";
        String month="0";

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Detail_B frame = new Detail_B(data, year, month, select_day);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Detail_B(ArrayList<ArrayList<String>> data, String year, String month, String select_day) {
        this.data = data;
        this.select_day = select_day;
        System.out.println(data);
        setResizable(false);
        setTitle("���� ��");
        int tableWidth = 550; // JTable�� ���� ũ��
        int tableHeight = 300; // JTable�� ���� ũ��
        int tableX = 50; // JTable�� x ��ǥ
        int tableY = 110; // JTable�� y ��ǥ
        int contentPaneWidth = Math.max(tableX + tableWidth + 20, 668); // JPanel�� ���� ũ�� ��� (20�� ����)
        int contentPaneHeight = Math.max(tableY + tableHeight + 20, 503); // JPanel�� ���� ũ�� ��� (20�� ����)
        setBounds(100, 100, contentPaneWidth, contentPaneHeight);
        setLocation(610, 200);
        setVisible(true);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("���� ���");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {    
                new Regis_B(year, month, select_day);
            }
        });
        btnNewButton.setBounds(513, 60, 87, 28);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel();
        String dayString = select_day;
        lblNewLabel.setText(dayString);
        lblNewLabel.setBounds(325, 20, 65, 37);
        
        Font font = new Font("Arial", Font.BOLD, 17); // 15�� ��Ʈ ũ�⸦ ��Ÿ���ϴ�.
        lblNewLabel.setFont(font);
        contentPane.add(lblNewLabel);

        
        System.out.println(Integer.parseInt(Crawling_C.student_id));
        model = new DefaultTableModel();
        table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(tableX, tableY, tableWidth, tableHeight);
        
        table.setBorder(null);
        table.setBounds(tableX, tableY, tableWidth, tableHeight);

        // Add columns to the table
        model.addColumn("Plan");
        model.addColumn("Memo");
        model.addColumn("����");

        table.getColumnModel().getColumn(2).setCellRenderer(new TableCell());
        table.getColumnModel().getColumn(2).setCellEditor(new TableCell());

        // Add rows to the table using the data ArrayList
        for (ArrayList<String> row : data) {
            model.addRow(new Object[] { row.get(0), row.get(1), "����" });
        }

        // ���� �ʺ� ����
        table.getColumnModel().getColumn(0).setPreferredWidth(200);  // ù ��° ���� �ʺ� 100���� ����
        table.getColumnModel().getColumn(1).setPreferredWidth(370);  // �� ��° ���� �ʺ� 200���� ����
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        // ���� ���� ����
        table.setRowHeight(30);  // ù ��° ���� ���̸� 30���� ����

        contentPane.add(scrollPane);
    }
    
    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

        JButton jb;

        public TableCell() {
            jb = new JButton("����");

            jb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Ŭ�� �̺�Ʈ ó�� ������ �ۼ��ϼ���
                    // ���� ��ư�� Ŭ���Ǿ��� �� ������ ������ ���⿡ �߰��մϴ�
                    // ����: ���ο� change �ν��Ͻ��� �����Ͽ� ���� �۾��� ����
                    new Modify_B();
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            return jb;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
            return jb;
        }
    }
}
