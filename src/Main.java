import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Person> people = new ArrayList<>();
    public static File verdicts;
    public static float opacityAmt = 0.9f;
    static DecisionFrame frame;
    public static ArrayList<Person> unusedPeople = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //region crimes
        //check if there is a verdicts.txt file outside the jar
        verdicts = new File("verdicts.txt");
        if (verdicts.createNewFile()) {
            System.out.println("verdicts.txt does not exist, creating one now");
        }
        System.out.println("verdicts.txt exists");
        Crime Euthanasia = new Crime(
                "Involved the secret killing of the aged, insane, incurably ill, deformed children, and others, beginning at asylums in Germany and later in the camps and occupied territories.");
        Crime FreezingExperiment = new Crime(
                "Investigated treatments for persons who had been severely chilled, using prisoners at the Dachau camp as test subjects.");
        Crime SkeletonConstruction = new Crime(
                "Made a complete skeleton collection for an anatomical research project at the Reich University of Strasbourg using one hundred twelve Jews at Auschwitz.");
        Crime EpidemicJaundice = new Crime(
                "Conducted experiments for the benefit of the German armed forces to investigate causes of and inoculations against epidemic jaundice; experiments were conducted on Polish prisoners at Sachsenhausen and Natzweiler camps.");
        Crime HighAltitudeExperiment = new Crime(
                "Investigated the effect of high-altitude flying using a low-pressure chamber at the Dachau camp as test subjects.");
        Crime Important = new Crime("Played a major role in the extermination of Jews.");
        Crime MemberOfSS = new Crime("Membership in the criminal organization SS.");
        Crime MalariaExperiment = new Crime(
                "Experiments conducted to test immunization for and treatment of malaria; were conducted on more than 1000 prisoners at Dachau.");
        Crime MustardGasExperiment = new Crime(
                "Experiments conducted for the benefit of the German armed forces to investigate treatment of injuries caused by Lost (mustard) gas. These were conducted on camp inmates.");
        Crime SulfanilamideExperiment = new Crime(
                "Experiments conducted for the benefit of the German armed forces to test the effectiveness of sulfanilamide and other drugs as treatments for infected wounds. These were conducted on camp inmates.");
        Crime TransplantExperiments = new Crime(
                "Experiments conducted for benefit of German armed forces, using Polish inmates at the Ravensbrueck camp.");
        Crime SeawaterExperiments = new Crime(
                "Tested methods of making seawater drinkable, using prisoners at the Dachau camp as test subjects.");

        Crime EpidemicJaundiceExperiments = new Crime(
                "Investigated causes of and inoculations against epidemic jaundice, using Polish prisoners at Sachsenhausen and Natzweiler camps as test subjects.");

        Crime TyphusVaccineExperiments = new Crime(
                "Tested vaccines against typhus, smallpox, cholera, and other diseases, using prisoners at Buchenwald and Natzweiler camps as test subjects.");

        Crime PoisonExperiments = new Crime(
                "Investigated the effect of various poisons, including poison in food and poisoned bullets, using prisoners at Buchenwald and Sachsenhausen camps as test subjects.");

        Crime IncendiaryBombExperiments = new Crime(
                "Tested pharmaceutical treatments for phosphorus burns, using prisoners at Buchenwald as test subjects.");

        Crime SterilizationExperiments = new Crime(
                "Developed methods of rapid, large-scale sterilization, using drugs, x-rays, and surgery, and employed them on prisoners at Auschwitz, Ravensbrueck, and elsewhere.");

        Crime SkeletonCollection = new Crime(
                "Killed one hundred twelve Jews at Auschwitz to complete a skeleton collection for an anatomical research project at the Reich University of Strasbourg.");

        Crime TubercularPolishNationals = new Crime(
                "Imprisoned or killed Polish nationals alleged to have incurable tuberculosis on the pretext of protecting the health of Germans in Poland.");
        //endregion crimes
        /*people.add(new Person("Joachim Mrugowsky", new Verdict(true, "execution"), new Crime[]{EpidemicJaundice},
                              getImage("Mrugowsky")));
        people.add(new Person("Wolfram Sievers", new Verdict(false, 99), new Crime[]{MemberOfSS, HighAltitudeExperiment, FreezingExperiment, MalariaExperiment, }, getImage("sievers"),
                              "Claimed he was part of a plot to execute Hitler, and he was attempting to get close to him. All the things he did because of it were to kill hitler"));
        people.add(new Person("Hermann Becker-Freyseng", new Verdict(false, 20),
                              new Crime[]{FreezingExperiment, EpidemicJaundice}, getImage("becker")));
        people.add(new Person("Rudolf Hess", new Verdict(false, 99), new Crime[]{Important}, getImage("hess")));
        people.add(new Person("Karl Brandt", new Verdict(true, "hanging"),
                              new Crime[]{SkeletonConstruction, FreezingExperiment}, getImage("brandt")));
        people.add(new Person("Karl Gebhardt", new Verdict(true, "hanging"),
                              new Crime[]{FreezingExperiment, EpidemicJaundice}, getImage("gebhardt")));
        people.add(new Person("Siegfried Handloser", new Verdict(false, 99),
                              new Crime[]{FreezingExperiment, EpidemicJaundice}, getImage("handloser")));
        people.add(new Person("Helmut Poppendick", new Verdict(false, 10), new Crime[]{EpidemicJaundice},
                              getImage("poppendick")));
        */

        //region people
        people.add(new Person("Hermann Becker-Freyseng", new Verdict(true, "hanging"),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment, MalariaExperiment},
                              getImage("becker")));
        people.add(new Person("Gerhard Rose", new Verdict(false, 15), new Crime[]{EpidemicJaundice}, getImage("rose")));
        people.add(new Person("K. Brandt", new Verdict(false, 10),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment, MalariaExperiment},
                              getImage("brandt")));

        people.add(new Person("R. Brandt", new Verdict(true, "hanging"),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment},
                              getImage("rbrandt")));

        people.add(new Person("Karl Gebhardt", new Verdict(true, "hanging"),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment, MalariaExperiment, SulfanilamideExperiment},
                              getImage("gebhardt")));

        people.add(new Person("Siegfried Handloser", new Verdict(false, 20),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment},
                              getImage("handloser")));

        people.add(new Person("Joachim Mrugowsky", new Verdict(true, "execution"),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment, MalariaExperiment},
                              getImage("mrugowsky")));

        people.add(new Person("Helmut Poppendick", new Verdict(false, 10),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment, MalariaExperiment},
                              getImage("poppendick")));

        people.add(new Person("Siegfried Ruff", new Verdict(false, 10),
                              new Crime[]{HighAltitudeExperiment},
                              getImage("ruff")));

        people.add(new Person("Kurt Baron von Schröder", new Verdict(true, "hanging"),
                              new Crime[]{HighAltitudeExperiment, FreezingExperiment},
                              getImage("schroeder")));

        people.add(new Person("Wolfram Sievers", new Verdict(false, 99),
                              new Crime[]{MemberOfSS, HighAltitudeExperiment, FreezingExperiment, MalariaExperiment},
                              getImage("sievers"),
                              "Claimed he was part of a plot to execute Hitler, and he was attempting to get close to him. All the things he did because of it were to kill hitler"));
        people.add(new Person("Karl Genzken", new Verdict(false, 99),
                              new Crime[]{PoisonExperiments, TyphusVaccineExperiments, IncendiaryBombExperiments},
                              getImage("genzken")));
        people.add(new Person("Kurt Blome", new Verdict(false, 99),
                              new Crime[]{MalariaExperiment, MustardGasExperiment, SulfanilamideExperiment, TubercularPolishNationals, Euthanasia},
                              getImage("blome")));
        people.add(new Person("Viktor Brack", new Verdict(true, "hanging"),
                              new Crime[]{MemberOfSS, SterilizationExperiments, Euthanasia}, getImage("brack")));
        //endregion
        //add all persons in people to unused people
        unusedPeople.addAll(people);

        frame = new DecisionFrame();
        DecisionPanel.frames.add(frame);


    }

    public static Image getImage(String path) {
        URL imgURL = Main.class.getResource("images/" + path + ".jpg");
        if (imgURL == null) {
            System.out.println("You really should use.jpg files. file : src/images/" + path + ".png");
            imgURL = Main.class.getResource("images/" + path + ".png");
            if (imgURL == null) {
                System.out.println(
                        "There doesn't seem to be a file for " + path + " in the images folder. Using default image.");
                return getImage("noimage");
            }
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