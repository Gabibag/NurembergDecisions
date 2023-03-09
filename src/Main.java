import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    static DecisionFrame frame;
    public static ArrayList<Person> people = new ArrayList<>();
    public static void main(String[] args)  {
        Crime FreezingExperiment = new Crime("Investigated treatments for persons who had been severely chilled, using prisoners at the Dachau camp as test subjects.");
        Crime SkeletonConstruction = new Crime("Made a complete skeleton collection for an anatomical research project at the Reich University of Strasbourg using one hundred twelve Jews at Auschwitz.");
        Crime EpidemicJaundice = new Crime("Conducted experiments for the benefit of the German armed forces to investigate causes of and inoculations against epidemic jaundice; experiments were conducted on Polish prisoners at Sachsenhausen and Natzweiler camps");
        Crime Important = new Crime("Played a major role in the extermination of Jews.");
        //save the images file and add the images to an arraylist called images
        people.add(new Person("Hermann Becker-Freyseng", new Verdict(false, 20), new Crime[]{FreezingExperiment,EpidemicJaundice}, getImage("becker")));
        people.add(new Person("Rudolf Hess", new Verdict(false, 99), new Crime[]{Important}, getImage("hess")));
        people.add(new Person("Karl Brandt", new Verdict(true, "hanging"), new Crime[]{SkeletonConstruction,FreezingExperiment}, getImage("brandt")));
        people.add(new Person("Karl Gebhardt", new Verdict(true, "hanging"), new Crime[]{FreezingExperiment, EpidemicJaundice}, getImage("gebhardt")));
        frame = new DecisionFrame();
        DecisionPanel.frames.add(frame);


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