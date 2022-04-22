import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class ListCitizen extends JFrame {
    private JPanel ListCitizenPane;
    private JTable table1;
    private JScrollPane scrollPanel;
    private JPanel FilterPane;
    private JTextField textField1;
    private JButton filterButton;
    private JButton exportToFileButton;

    public ListCitizen() {
        DefaultTableModel modelTable = new DefaultTableModel();
        CitizenDatabase citizenDatabase = new CitizenDatabase();
        List<Citizen> citizens = citizenDatabase.getAllCitizen();
        modelTable.addColumn("ID");
        modelTable.addColumn("Name");
        modelTable.addColumn("Date of Birth");
        modelTable.addColumn("Sex");
        modelTable.addColumn("Vaccines");
        modelTable.addColumn("Vaccine Dose Count");
        table1.setModel(modelTable);
        SimpleDateFormat spd = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < citizens.size(); i++) {
            Vector<String> row = new Vector<String>();
            String dateString = spd.format(citizens.get(i).getDateOfBirth());
            row.add(citizens.get(i).getId());
            row.add(citizens.get(i).getName());
            row.add(dateString);
            row.add(citizens.get(i).getSex());
            row.add(citizens.get(i).getDoses().toString());
            row.add(String.valueOf(citizens.get(i).getDoses().size()));
            modelTable.addRow(row);
        }
        table1.setAutoCreateRowSorter(true);
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelTable);
        table1.setRowSorter(sorter);
        scrollPanel.setBounds(0, 0, 800, 600);
        scrollPanel.setVisible(true);
        setSize(800, 600);
        setVisible(true);
        setContentPane(ListCitizenPane);
        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String text = textField1.getText();
                if (text.equals("")) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = textField1.getText();
                    if (text.equals("")) {
                        sorter.setRowFilter(null);
                    } else {
                        try {
                            sorter.setRowFilter(RowFilter.regexFilter(text));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        exportToFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JFileChooser fchoose = new JFileChooser();
                int option = fchoose.showSaveDialog(ListCitizen.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String name = fchoose.getSelectedFile().getName();
                    String path = fchoose.getSelectedFile().getParentFile().getPath();
                    File exportFile = new File(path, name + ".tsv");
                    exportToTSVFile(table1, exportFile);
                }

            }
        });
    }

    public void exportToTSVFile(JTable table, File file) {
        try {
            TableModel m = table.getModel();
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < m.getColumnCount(); i++) {
                fw.write(m.getColumnName(i) + "\t");
            }
            fw.write("\n");
            for (int i = 0; i < m.getRowCount(); i++) {
                for (int j = 0; j < m.getColumnCount(); j++) {
                    fw.write(m.getValueAt(i, j).toString() + "\t");
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}



