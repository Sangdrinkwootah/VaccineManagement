import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputCitizen extends JFrame {
    private JTextField NameField;
    private JTextField CitizenIDField;
    private JFormattedTextField dateOfBirthField;
    private JComboBox sexBox;
    private JPanel InputCitizen;
    private JButton Submit;

    public InputCitizen() {
//        Set date format for formattedTextField1
        setContentPane(InputCitizen);
        setTitle("Input Citizen");
//        Add Male/Female option for sexBox

        setSize(500, 500);
        setVisible(true);
        Submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = NameField.getText();
                String citizenID = CitizenIDField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                String sex = (String) sexBox.getSelectedItem();
// Check date of birth is in format dd/mm/yyyy
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if (name.equals("") || citizenID.equals("") || dateOfBirth.equals("")) {
                    if (name.equals("")) {
                        NameField.setBorder(new LineBorder(Color.RED, 2));
                    } else {
                        NameField.setBorder(new JTextField().getBorder());
                    }
                    if (citizenID.equals("")) {
                        CitizenIDField.setBorder(new LineBorder(Color.RED, 2));
                    } else {
                        CitizenIDField.setBorder(new JTextField().getBorder());
                    }
                    if (dateOfBirth.equals("")) {
                        dateOfBirthField.setBorder(new LineBorder(Color.RED, 2));
                    } else {
                        dateOfBirthField.setBorder(new JTextField().getBorder());
                    }
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                    return;
                }
                Date date;
                try {
                    date = sdf.parse(dateOfBirth);
                } catch (ParseException ex) {
                    dateOfBirthField.setBorder(new LineBorder(Color.RED, 2));
                    return;
                }
                CitizenDatabase citizenDatabase = new CitizenDatabase();
                citizenDatabase.insertCitizen(citizenID, name, date, sex);
                JOptionPane.showMessageDialog(null, "Add citizen successfully");
                NameField.setText("");
                CitizenIDField.setText("");
                dateOfBirthField.setText("");
            }
        });
    }
}
