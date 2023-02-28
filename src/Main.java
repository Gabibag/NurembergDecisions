import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static DecisionFrame frame;
    public static ArrayList<Person> people = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        people.add(new Person("testName", "testVerdict", "testSentence", "testCrime", ImageIO.read(new File("src/images/test-image.jpg"))));
        people.add(new Person("Rudolf Hess", "Guilty", "Life in Prison", "Top man in Nazi Party", ImageIO.read(new File("src/images/hess.jpg"))));
        people.add(new Person("Rudolf Hess", "Guilty", "Life in Prison", "Top man in Nazi Party", ImageIO.read(new File("src/images/hess.jpg"))));
        frame = new DecisionFrame();

    }
    public static void newFrame() {
        frame.dispose();
        frame = new DecisionFrame();
    }
}