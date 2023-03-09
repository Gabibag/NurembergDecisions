import java.awt.*;
import java.util.Random;

public class Person {
    private final String name;
    private final Verdict verdict;
    private final Crime[] crime;
    Random Random = new Random();
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Person(String name, Verdict verdict, Crime[] crime, Image image) {
        this.name = name;
        this.verdict = verdict;
        this.crime = crime;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public Crime getCrime() {
        return crime[Random.nextInt(crime.length)];
    }
}

