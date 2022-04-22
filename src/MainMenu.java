import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    private JButton Warehouse;
    private JButton citizensButton;
    private JButton statisticButton;
    private JButton exitButton;
    private JPanel mainMenuPanel;

    public MainMenu() {
        setContentPane(mainMenuPanel);
        setSize(500, 500);
        setTitle("Vaccination Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        statisticButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Hello, word!");
            }
        });
        citizensButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CitizenMenu citizenMenu = new CitizenMenu();
                citizenMenu.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}
