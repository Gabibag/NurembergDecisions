import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DecisionPanel extends JPanel {
    Person p;
    public static ArrayList<Person> unusedPeople = new ArrayList<>();
    static Random r = new Random();
    public static ArrayList<JFrame> frames = new ArrayList<>();

    public DecisionPanel() {
        unusedPeople.addAll(Main.people);


        //create a panel that has two buttons, a place for an image, and a place for text
        this.setLayout(new GridLayout(2, 2));
        p = getPerson();
        //create a new label for each piece of information
        JFrame name = new JFrame();
        JFrame crime = new JFrame();
        JFrame image = new JFrame();
        frames.add(name);
        frames.add(crime);
        frames.add(image);
        System.out.println("a");

        JLabel textDecision = new JLabel("<HTML> <h1> DECISION </h1> </HTML>");
        JLabel textFrame = new JLabel("<HTML> <h1>FRAME</h1> </HTML>");
        JLabel imageIcon = new JLabel(new ImageIcon(p.getImage()));
        image.setSize(10, 10);
        //add these labels to an arrayList
        name.setSize(DecisionFrame.width/6, DecisionFrame.height/9);
        crime.setSize(DecisionFrame.width/6, DecisionFrame.height/9);
        image.setSize(DecisionFrame.height/6, DecisionFrame.width/6);

        name.setUndecorated(true);
        name.setAlwaysOnTop(true);
        image.setUndecorated(true);
        crime.setUndecorated(true);
        crime.setAlwaysOnTop(true);
        image.setAlwaysOnTop(true);

        name.add(new JLabel("<HTML><h1>Name: " + p.getName() + " <h1/></HTML>"));
        crime.add(new JLabel("<HTML><span>Crime: " + p.getCrime() + " <span/></HTML>"));
        image.add(imageIcon);
//        image.pack();
        DecisionFrame.animateMoving(DecisionFrame.width / 3, DecisionFrame.height / 3 - name.getHeight(), "up", name);
        DecisionFrame.animateMoving(DecisionFrame.width / 3 + name.getWidth(), DecisionFrame.height / 3 - crime.getHeight(), "up", crime);
        DecisionFrame.animateMoving(DecisionFrame.width / 3 - image.getWidth(), DecisionFrame.height / 3, "left", image);

        textDecision.setHorizontalAlignment(JLabel.RIGHT);
        textFrame.setHorizontalAlignment(JLabel.LEFT);
        textDecision.setFont(new Font("Arial", Font.BOLD, 10));
        textFrame.setFont(new Font("Arial", Font.BOLD, 10));
        this.add(textDecision);
        this.add(textFrame);





        //create buttons
        JButton death = new JButton("Death Sentence");
        JButton jail = new JButton("Imprisonment");
        //add an on click listener to the buttons
        death.addActionListener(e -> {
            displayActualSentence(p);
        });
        jail.addActionListener(e -> {
            //dispose all frames
            displayActualSentence(p);


        });
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
    public static void disposeAllFrames() {
        for (JFrame frame : frames) {
            frame.dispose();
        }
    }
    public static void displayActualSentence(Person p) {
        JFrame sentenceFrame = new JFrame();
        Button ok = new Button("OK");
        sentenceFrame.setAlwaysOnTop(true);
        sentenceFrame.add(new JLabel("<HTML><div style='text-align: center;'> <h1>" +p.getName() + " received " + p.getVerdict().getSentence() + "<h1/> <div/> </HTML>",SwingConstants.CENTER));
        ok.addActionListener(e -> {
            disposeAllFrames();
            DecisionPanel.frames.add(new DecisionFrame());
        });
        sentenceFrame.add(ok);
        sentenceFrame.setLayout(new GridLayout(2, 1));
        sentenceFrame.setUndecorated(true);
        sentenceFrame.setLocation(DecisionFrame.width/3, DecisionFrame.height/3);
        frames.add(sentenceFrame);
        DecisionFrame.animateScale(DecisionFrame.width / 3, DecisionFrame.height / 3, sentenceFrame);

    }
}
