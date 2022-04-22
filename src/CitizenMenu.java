import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class CitizenMenu extends JFrame {
    private JButton listCitizensButton;
    private JButton inputCitizenButton;
    private JPanel CitizenMenu;
    private JPanel citizenMenuPanel;

    public CitizenMenu() {
        setContentPane(CitizenMenu);
        setTitle("Citizen Menu");
        setSize(400, 400);
        setVisible(true);
        inputCitizenButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InputCitizen inputCitizen = new InputCitizen();
                inputCitizen.setVisible(true);

            }
        });
        listCitizensButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ListCitizen listCitizens = new ListCitizen();
                listCitizens.setVisible(true);
            }
        });
    }
}
