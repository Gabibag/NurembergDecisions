import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DecisionPanel extends JPanel {
    Person p;
    public static ArrayList<Person> unusedPeople = new ArrayList<>();
    static Random r = new Random();
    public static ArrayList<JFrame> frames = new ArrayList<>();
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(p.getImage(), 0, 0, this);
    }

    public DecisionPanel() {
        unusedPeople.addAll(Main.people);


        //create a panel that has two buttons, a place for an image, and a place for text
        this.setLayout(new GridLayout(4, 3));
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
        image.setSize(DecisionFrame.width/9, DecisionFrame.height/6);

        name.setUndecorated(true);
        name.setAlwaysOnTop(true);
        image.setUndecorated(true);
        crime.setUndecorated(true);
        crime.setAlwaysOnTop(true);
        image.setAlwaysOnTop(true);

        name.add(new JLabel("<HTML><h1>Name: a" + p.getName() + " <h1/></HTML>"));
        crime.add(new JLabel("<HTML><h1>Crime: " + p.getCrime() + " <h1/></HTML>"));
        image.add(imageIcon);
//        image.pack();
        DecisionFrame.animate(DecisionFrame.width/3, DecisionFrame.height/3 - name.getHeight(), "up", name);
        DecisionFrame.animate(DecisionFrame.width/3 + name.getWidth(), DecisionFrame.height/3 - crime.getHeight(), "up", crime);
        DecisionFrame.animate(DecisionFrame.width/3 - image.getWidth(), DecisionFrame.height/3, "left", image);

        textDecision.setHorizontalAlignment(JLabel.RIGHT);
        textFrame.setHorizontalAlignment(JLabel.LEFT);
        textDecision.setFont(new Font("Arial", Font.BOLD, 10));
        textFrame.setFont(new Font("Arial", Font.BOLD, 10));
        this.add(textDecision);
        this.add(textFrame);
        this.add(imageIcon);





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
        JFrame frame = new JFrame();
        Button ok = new Button("OK");
        frame.setSize(DecisionFrame.width/3, DecisionFrame.height/3);
        frame.setAlwaysOnTop(true);
        frame.add(new JLabel("<HTML><h1>Actual Punishment: " + p.getSentence() + " <h1/></HTML>"));
        ok.addActionListener(e -> disposeAllFrames());
        frame.add(ok);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2, 1));
        frames.add(frame);
        DecisionFrame.animate(Main.frame.getLocation().x, Main.frame.getLocation().y+frame.getHeight(), "bottom", frame);

    }
}
