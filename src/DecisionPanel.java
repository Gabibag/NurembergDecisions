import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
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

    static Random r = new Random();
    public static ArrayList<JFrame> frames = new ArrayList<>();

    public DecisionPanel() {


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

        JLabel textDecision = new JLabel("<HTML> <h1> DECISION </h1> </HTML>", JLabel.CENTER);
        JLabel textFrame = new JLabel("<HTML> <h1>FRAME</h1> </HTML>", JLabel.CENTER);
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
        Color popout = new Color(218, 215, 205, 100);


        name.setOpacity(Main.opacityAmt);
        crime.setOpacity(Main.opacityAmt);
        image.setOpacity(Main.opacityAmt);
        /*
        * Area shape2 = new Area(
                new RoundRectangle2D.Double(0, this.getHeight() / 2 - 30, this.getWidth(), this.getHeight() / 2, 18,
                                            18));
        Area shape1 = new Area(new Rectangle(0, 0, this.getWidth(), this.getHeight() / 2));
        shape1.add(shape2);
        this.setShape(shape1);
        * */
        //the code above makes the bottom two corners of the panel rounded, using this code, make the left corners rounded of the image frame, and the top corners of the name and crime frames rounded
        Area shape2 = new Area(
                new RoundRectangle2D.Double(0, 0, image.getWidth(), image.getHeight(), 18, 18));
        Area shape1 = new Area(new Rectangle(image.getWidth() / 2, 0, image.getWidth() / 2, image.getHeight()));
        shape1.add(shape2);
        image.setShape(shape1);
        Area shape3 = new Area(
                new RoundRectangle2D.Double(0, 0, name.getWidth(), name.getHeight(), 18, 18));
        Area shape4 = new Area(new Rectangle(0, name.getHeight() / 2, name.getWidth(), name.getHeight() / 2));
        Area shape7 = new Area(new Rectangle(name.getHeight() / 2, 0, name.getWidth(), name.getHeight() / 2));
        shape4.add(shape3);
        shape4.add(shape7);
        name.setShape(shape4);
        Area shape5 = new Area(
                new RoundRectangle2D.Double(0, 0, crime.getWidth(), crime.getHeight(), 18, 18));
        Area shape6 = new Area(new Rectangle(0, crime.getHeight() / 2, crime.getWidth(), crime.getHeight() / 2));
        Area shape8 = new Area(new Rectangle(0, 0, name.getWidth() / 2, name.getHeight() / 2));
        shape6.add(shape5);
        shape6.add(shape8);
        crime.setShape(shape6);


        name.add(new JLabel("<HTML><div style='text-align:center; font-size: 20px'> <span>Name: " + p.getName() +
                            " <span/> <div/></HTML>"));
        if (p.getCrime().toString().length() < 166) {
            crime.add(new JLabel(
                    "<HTML><div style='text-align:center'><span>Crime: " + p.getCrime() + " <span/><div/></HTML>"));
        }
        else {
            crime.add(new JLabel(
                    "<HTML><div style='text-align:center; font-size: 10px'><span>Crime: " + p.getCrime() +
                    " <span/><div/></HTML>"));
        }
        image.add(imageIcon);
//        image.pack();


        DecisionFrame.animateMoving(DecisionFrame.width / 3, (DecisionFrame.height / 3) - crime.getHeight(), "up",
                                    name);
        DecisionFrame.animateMoving(DecisionFrame.width / 3 + name.getWidth(),
                                    (DecisionFrame.height / 3) - name.getHeight(), "up", crime);
        DecisionFrame.animateMoving(DecisionFrame.width / 3 - image.getWidth(), DecisionFrame.height / 3, "left",
                                    image);
        if (p.getExcuse() != null) {

            //create a new frame for the excuse to the right of the decision frame similar to that of the one above
            JFrame excuseFrame = new JFrame();
            excuseFrame.setAlwaysOnTop(true);
            excuseFrame.add(new JLabel("<HTML><span>" + p.getExcuse() + "<span/> </HTML>", SwingConstants.CENTER));
            excuseFrame.setUndecorated(true);
            excuseFrame.setOpacity(Main.opacityAmt);
            excuseFrame.setLocation(DecisionFrame.width / 3 + name.getWidth() + crime.getWidth(),
                                    DecisionFrame.height / 3);
            frames.add(excuseFrame);
            Area shape9 = new Area(
                    new RoundRectangle2D.Double(0, 0, DecisionFrame.width / 6, DecisionFrame.width / 6, 18, 18));
            Area shape10 = new Area(new Rectangle(0, 0, DecisionFrame.width / 12, DecisionFrame.width / 12));
            shape9.add(shape10);
            excuseFrame.setShape(shape9);
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
            Verdict verdict = new Verdict();
            try {
                Path path = Paths.get("src/verdicts.txt");
                String verdictText = p.getName() + ": a death sentence\n";
                System.out.println(numOfOccurrence(p.getName() + ": a death sentence") + " [" + p.getName() +
                                   ": a death sentence]");
                Files.write(path, (verdictText).getBytes(), StandardOpenOption.APPEND);
                otherStat(verdictText);

            } catch (IOException ex) {
                System.out.println("An error occurred.");
            }
            displayActualSentence(p);
        });
        jail.addActionListener(e -> {
            //dispose all frames
            //create a new frame with a drop down menu that has the options of 1 year, 5 years, 10 years, 20 years, 50 years, and life
            JFrame jailFrame = new JFrame();
            frames.add(jailFrame);
            jailFrame.setAlwaysOnTop(true);
            jailFrame.setUndecorated(true);
            jailFrame.setOpacity(Main.opacityAmt);
            jailFrame.setLocation(DecisionFrame.width / 3, DecisionFrame.height / 3 + DecisionFrame.height / 3);
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
            Button ok = new Button("Submit");
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
                    String verdictText = p.getName() + ": " + verdict.getSentence() + "\n";
                    System.out.println(numOfOccurrence(verdictText) + " [" + verdictText + "]");
                    Files.write(path, (verdictText).getBytes(), StandardOpenOption.APPEND);
                    otherStat(verdictText);

                } catch (IOException ex) {
                    System.out.println("An error occurred.");
                }
                displayActualSentence(p);


            });
            jailFrame.add(ok);
            //add rounded bottom corners to the frame
            Area shape11 = new Area(
                    new RoundRectangle2D.Double(0, 0, DecisionFrame.width / 3, DecisionFrame.width / 3, 18, 18));
            Area shape12 = new Area(new Rectangle(0, 0, DecisionFrame.width / 3, DecisionFrame.width / 3));
            shape11.add(shape12);
            jailFrame.setShape(shape11);
            DecisionFrame.animateScale(DecisionFrame.width / 3, DecisionFrame.height / 3, jailFrame);


        });
        //add death and jail buttons to the panel, with death on the left, and jail on the right

        this.add(death);
        this.add(jail);


    }

    private void otherStat(String verdictText) {
        if (numOfOccurrence(verdictText) >= 1) {
            JFrame otherStats = new JFrame();
            frames.add(otherStats);
            otherStats.setOpacity(Main.opacityAmt);
            otherStats.setAlwaysOnTop(true);
            otherStats.add(new JLabel("<HTML><div style='text-align: center;'> <h1>This same verdict has been chosen " +
                                      numOfOccurrence(p.getName() + ": " + p.getVerdict().getSentence()) +
                                      " times<h1/> <div/> </HTML>", SwingConstants.CENTER));
            //to the same location of the "excuse" frame

            otherStats.setLocation(DecisionFrame.width / 3 + DecisionFrame.width / 3,
                                   DecisionFrame.height / 3);
            otherStats.setUndecorated(true);
            otherStats.setVisible(true);
            DecisionFrame.animateScale(DecisionFrame.width / 6, DecisionFrame.height / 9, otherStats);
        }
    }


    public static Person getPerson() {
        //get a random person from the list of people
        int randomPerson;
        if (Main.unusedPeople.size() == 0) {
            Main.unusedPeople = Main.people;
            System.out.println(Main.people.size());
        }
        if (Main.unusedPeople.size() == 1) {
            randomPerson = 0;
        }
        else {
            randomPerson = r.nextInt(0, Main.unusedPeople.size() - 1);
        }
        Person person = Main.unusedPeople.get(randomPerson);
        //remove the person from the list of unused people
        Main.unusedPeople.remove(randomPerson);
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
        sentenceFrame.setOpacity(Main.opacityAmt);
        sentenceFrame.setLocation(DecisionFrame.width / 3, DecisionFrame.height / 3);
        frames.add(sentenceFrame);
        //create another Jframe telling the user how many times the verdict has been given using the numOfOccurrence method. only display if the verdict has been given more than 1 time, and give it in the format "This same verdict has been chosen (x) times"

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
