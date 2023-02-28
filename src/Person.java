import java.awt.*;

public class Person {
    private final String name;
    private final String verdict;
    private final String sentence;
    private final String crime;
    private Image image = null;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Person(String name, String verdict, String sentence, String crime, Image image) {
        this.name = name;
        this.verdict = verdict;
        this.sentence = sentence;
        this.crime = crime;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getVerdict() {
        return verdict;
    }

    public String getSentence() {
        return sentence;
    }

    public String getCrime() {
        return crime;
    }
}

