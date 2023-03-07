import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    static DecisionFrame frame;
    public static ArrayList<Person> people = new ArrayList<>();
    public static void main(String[] args)  {
        //save the images file and add the images to an arraylist called images

        people.add(new Person("testName", "testVerdict", "testSentence", "testCrime", getImage("test-image")));
        people.add(new Person("Rudolf Hess", "Guilty", "Life in Prison", "Top man in Nazi Party", getImage("hess")));
        frame = new DecisionFrame();

    }
    public static void newFrame() {
        for (JFrame f :DecisionPanel.frames) {
            f.dispose();
        }
        frame = new DecisionFrame();
    }
    public static Image getImage(String path) {
        URL imgURL = Main.class.getResource("images/"+path+".jpg");
        if (imgURL == null) {
            System.out.println("You really should use.jpg files. file : src/images/" + path + ".png");
            imgURL = Main.class.getResource("images/"+path+".png");
        }

        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            return tk.getImage(imgURL);
        } catch (Exception e) {
            System.out.println("hey that errored.");
            return null;
        }
        /*try {
            return ImageIO.read(
                    (Objects.requireNonNull(Main.class.getResource("../src/images/hess.jpg"))));
        } catch (Exception e) {
            System.out.println("You really should use.jpg files. file : src/images/" + path + ".png");
            try {
                return ImageIO.read(
                        Objects.requireNonNull(Main.class.getResource("src/images/" + path + ".png")));
            } catch (Exception ex) {
                System.out.println("hey that errored.");
            }
        }*/
    }
}