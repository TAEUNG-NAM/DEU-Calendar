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
        setTitle("일정 상세");
        int tableWidth = 550; // JTable의 가로 크기
        int tableHeight = 300; // JTable의 세로 크기
        int tableX = 50; // JTable의 x 좌표
        int tableY = 110; // JTable의 y 좌표
        int contentPaneWidth = Math.max(tableX + tableWidth + 20, 668); // JPanel의 가로 크기 계산 (20은 여백)
        int contentPaneHeight = Math.max(tableY + tableHeight + 20, 503); // JPanel의 세로 크기 계산 (20은 여백)
        setBounds(100, 100, contentPaneWidth, contentPaneHeight);
        setLocation(610, 200);
        setVisible(true);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.window);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("일정 등록");
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
        
        Font font = new Font("Arial", Font.BOLD, 17); // 15은 폰트 크기를 나타냅니다.
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
        model.addColumn("수정");

        table.getColumnModel().getColumn(2).setCellRenderer(new TableCell());
        table.getColumnModel().getColumn(2).setCellEditor(new TableCell());

        // Add rows to the table using the data ArrayList
        for (ArrayList<String> row : data) {
            model.addRow(new Object[] { row.get(0), row.get(1), "수정" });
        }

        // 열의 너비 조절
        table.getColumnModel().getColumn(0).setPreferredWidth(200);  // 첫 번째 열의 너비를 100으로 설정
        table.getColumnModel().getColumn(1).setPreferredWidth(370);  // 두 번째 열의 너비를 200으로 설정
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        // 행의 높이 조절
        table.setRowHeight(30);  // 첫 번째 행의 높이를 30으로 설정

        contentPane.add(scrollPane);
    }
    
    class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

        JButton jb;

        public TableCell() {
            jb = new JButton("수정");

            jb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 클릭 이벤트 처리 로직을 작성하세요
                    // 수정 버튼이 클릭되었을 때 수행할 동작을 여기에 추가합니다
                    // 예시: 새로운 change 인스턴스를 생성하여 변경 작업을 수행
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
