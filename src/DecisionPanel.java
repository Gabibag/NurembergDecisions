import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Random;

public class DecisionPanel extends JPanel {
    Person p;
    public static ArrayList<Person> unusedPeople = new ArrayList<>();
    static Random r = new Random();
    public DecisionPanel() {
        unusedPeople.addAll(Main.people);

        //create a panel that has two buttons, a place for an image, and a place for text
        this.setLayout(new GridLayout(4, 3));
        p = getPerson();
        //create a new label for each piece of information
        JFrame name = new JFrame();
        JFrame verdict = new JFrame();
        JFrame crime = new JFrame();
        JLabel textDecision = new JLabel("<HTML> <h1> DECISION </h1> </HTML>");
        JLabel textFrame = new JLabel("<HTML> <h1>FRAME</h1> </HTML>");
        JLabel image = new JLabel(new ImageIcon(p.getImage()));
        image.setSize(10, 10);
        //add these labels to an arrayList
        name.setSize(DecisionFrame.width/6, DecisionFrame.height/9);
        name.setLocation(DecisionFrame.width/3, ((DecisionFrame.height/3)-name.getHeight()));

        name.setUndecorated(true);
        name.setAlwaysOnTop(true);
        name.add(new JLabel("<HTML><h1>Name: " + p.getName() + " <h1/></HTML>"));
        name.setVisible(true);

        textDecision.setHorizontalAlignment(JLabel.RIGHT);
        textFrame.setHorizontalAlignment(JLabel.LEFT);
        textDecision.setFont(new Font("Arial", Font.BOLD, 10));
        textFrame.setFont(new Font("Arial", Font.BOLD, 10));
        this.add(textDecision);
        this.add(textFrame);
        this.add(image);





        //create buttons
        JButton death = new JButton("Death Sentence");
        JButton jail = new JButton("Imprisonment");
        //add an on click listener to the buttons
        death.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JDialog dialog = new JDialog();
                dialog.setSize(Main.frame.getSize().width, Main.frame.getSize().height);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setResizable(false);
                dialog.setLayout(new GridLayout(1, 1));
                dialog.setLocationRelativeTo(Main.frame);
                dialog.setLocation(Main.frame.getLocation().x, Main.frame.getLocation().y+10);
                dialog.add(new Label(p.getSentence()));
                dialog.setVisible(true);

            }
        } );
        jail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.newFrame();
            }
        } );
        //add death and jail buttons to the panel, with death on the left, and jail on the right
        this.add(death);
        this.add(jail);




    }
    public static Person getPerson() {
        //get a random person from the list of people
        int randomPerson;
        if (unusedPeople.size() == 0) {
            unusedPeople = Main.people;
            System.out.println(Main.people.size());
        }
        if (unusedPeople.size() == 1) {
            randomPerson = 0;
        }else {
            randomPerson = r.nextInt(0, unusedPeople.size()-1);
        }
        Person person = unusedPeople.get(randomPerson);
        //remove the person from the list of unused people
        unusedPeople.remove(randomPerson);
        return person;
    }
}
