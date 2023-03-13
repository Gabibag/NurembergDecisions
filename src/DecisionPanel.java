import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

        JLabel textDecision = new JLabel("<HTML> <h1> DECISION </h1> </HTML>");
        JLabel textFrame = new JLabel("<HTML> <h1>FRAME</h1> </HTML>");
        JLabel imageIcon = new JLabel(new ImageIcon(p.getImage()));
        image.setSize(10, 10);
        //add these labels to an arrayList
        name.setSize(DecisionFrame.width / 6, DecisionFrame.height / 9);
        crime.setSize(DecisionFrame.width / 6, DecisionFrame.height / 9);
        image.setSize(DecisionFrame.height / 6, DecisionFrame.width / 6);

        name.setUndecorated(true);
        name.setAlwaysOnTop(true);
        image.setUndecorated(true);
        crime.setUndecorated(true);
        crime.setAlwaysOnTop(true);
        image.setAlwaysOnTop(true);
        name.setOpacity(0.9f);
        crime.setOpacity(0.9f);
        image.setOpacity(0.9f);

        name.add(new JLabel("<HTML><h1>Name: " + p.getName() + " <h1/></HTML>"));
        crime.add(new JLabel("<HTML><span>Crime: " + p.getCrime() + " <span/></HTML>"));
        image.add(imageIcon);
//        image.pack();


        DecisionFrame.animateMoving(DecisionFrame.width / 3, DecisionFrame.height / 3 - name.getHeight(), "up", name);
        DecisionFrame.animateMoving(DecisionFrame.width / 3 + name.getWidth(),
                                    DecisionFrame.height / 3 - crime.getHeight(), "up", crime);
        DecisionFrame.animateMoving(DecisionFrame.width / 3 - image.getWidth(), DecisionFrame.height / 3, "left",
                                    image);
        if (p.getExcuse() != null) {

            //create a new frame for the excuse to the right of the decision frame similar to that of the one above
            JFrame excuseFrame = new JFrame();
            excuseFrame.setAlwaysOnTop(true);
            excuseFrame.add(new JLabel("<HTML><span>" + p.getExcuse() + "<span/> </HTML>", SwingConstants.CENTER));
            excuseFrame.setUndecorated(true);
            excuseFrame.setOpacity(0.9f);
            excuseFrame.setLocation(DecisionFrame.width / 3 + name.getWidth() + crime.getWidth(),
                                    DecisionFrame.height / 3);
            frames.add(excuseFrame);
            DecisionFrame.animateScale(DecisionFrame.width / 6, DecisionFrame.height / 9, excuseFrame);


        }

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
            //create a new frame with a drop down menu that has the options of 1 year, 5 years, 10 years, 20 years, 50 years, and life
            JFrame jailFrame = new JFrame();
            frames.add(jailFrame);
            jailFrame.setAlwaysOnTop(true);
            jailFrame.setUndecorated(true);
            jailFrame.setOpacity(0.9f);
            jailFrame.setLocation(DecisionFrame.width / 3, DecisionFrame.height / 3);
            //create a jlabel prompting the user as h1 text and style it as center aligned
            jailFrame.setLayout(new GridLayout(3, 1));
            //if p.getname is longer than 20 characters, make the font smaller
            if (p.getName().length() > 15) {
                jailFrame.add(new JLabel("<HTML><div style='text-align: center;'> <h2>How long should " + p.getName() +
                                         " have be imprisoned for?<h2/> <div/> </HTML>", SwingConstants.CENTER));
            }
            else {
                jailFrame.add(new JLabel("<HTML><div style='text-align: center;'> <h1>How long should " + p.getName() +
                                         " have be imprisoned for?<h1/> <div/> </HTML>", SwingConstants.CENTER));
            }
            String[] options = {"1 year", "5 years", "10 years", "20 years", "50 years", "Life"};
            JComboBox<String> jailTime = new JComboBox<>(options);
            //make the drop down menu the same size as the frame
            jailTime.setPreferredSize(new Dimension(DecisionFrame.width / 6, DecisionFrame.height / 9));
            jailFrame.add(jailTime);
            Button ok = new Button("OK");
            ok.addActionListener(e1 -> {
                //depending on the option selected, create a new verdict in the format of (false, int yearNum) with corresponding time and don't save it
                int yearNum = switch (jailTime.getSelectedIndex()) {
                    case 0 -> 1;
                    case 1 -> 5;
                    case 2 -> 10;
                    case 3 -> 20;
                    case 4 -> 50;
                    case 5 -> 99;
                    default -> 0;
                };
                Verdict verdict = new Verdict(false, yearNum);
                try {
                    Path path = Paths.get("src/verdicts.txt");
                    String verdictText = p.getName() + ": " + verdict.getSentence();
                    System.out.println(numOfOccurrence(verdictText));
                    Files.write(path, (verdictText).getBytes(), StandardOpenOption.APPEND);

                } catch (IOException ex) {
                    System.out.println("An error occurred.");
                }
                displayActualSentence(p);


            });
            jailFrame.add(ok);

            DecisionFrame.animateScale(DecisionFrame.width / 3, DecisionFrame.height / 3, jailFrame);


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
        JFrame sentenceFrame = new JFrame(
        );
        Button ok = new Button("OK");
        sentenceFrame.setAlwaysOnTop(true);
        sentenceFrame.add(new JLabel("<HTML><div style='text-align: center;'> <h1>" + p.getName() + " received " +
                                     p.getVerdict().getSentence() + "<h1/> <div/> </HTML>", SwingConstants.CENTER));
        ok.addActionListener(e -> {
            disposeAllFrames();
            DecisionPanel.frames.add(new DecisionFrame());
        });
        sentenceFrame.add(ok);
        sentenceFrame.setLayout(new GridLayout(2, 1));

        sentenceFrame.setUndecorated(true);
        sentenceFrame.setOpacity(0.9f);
        sentenceFrame.setLocation(DecisionFrame.width / 3, DecisionFrame.height / 3);
        frames.add(sentenceFrame);
        //create another Jframe telling the user how many times the verdict has been given using the numOfOccurrence method. only display if the verdict has been given more than 1 time, and give it in the format "This same verdict has been chosen (x) times"
        if (numOfOccurrence(p.getName() + ": " + p.getVerdict().getSentence()) > 1) {
            JFrame otherStats = new JFrame();
            otherStats.setOpacity(0.9f);
            otherStats.setAlwaysOnTop(true);
            otherStats.add(new JLabel("<HTML><div style='text-align: center;'> <h1>This same verdict has been chosen " +
                                      numOfOccurrence(p.getName() + ": " + p.getVerdict().getSentence()) +
                                      " times<h1/> <div/> </HTML>", SwingConstants.CENTER));
            //to the same location of the "excuse" frame

            otherStats.setLocation(DecisionFrame.width / 3 + DecisionFrame.width / 3,
                                   DecisionFrame.height / 3);
            otherStats.setUndecorated(true);
            DecisionFrame.animateScale(DecisionFrame.width / 6, DecisionFrame.height / 9, otherStats);
        }
        DecisionFrame.animateScale(DecisionFrame.width / 3, DecisionFrame.height / 3, sentenceFrame);

    }

    public static int numOfOccurrence(String verdict) {
        //cycle through the txt file verdicts.txt and count the number of times the verdict appears
        int count = 0;
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/verdicts.txt"));
            while ((line = reader.readLine()) != null) {
                if (line.contains(verdict)) {
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
