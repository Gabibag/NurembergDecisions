import javax.swing.*;
import java.awt.*;


public class DecisionFrame extends JFrame {
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public DecisionFrame() {
        //get dimensions of screen

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width/3, height/3);
        setLocation(width/3, height/3);
        setTitle("Decision Frame");
        setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.add(new DecisionPanel());
        setVisible(true);
    }
}
