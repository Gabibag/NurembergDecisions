public class Verdict {
    boolean isDeathSentence;
    int yearsInJail = 0;
    boolean isGuilty = true;
    String wayKilled;
    public Verdict(boolean isDeathSentence, int yearsInJail) {
        this.isDeathSentence = isDeathSentence;
        this.yearsInJail =yearsInJail;
    }public Verdict(boolean isDeathSentence, String wayKilled) {
        this.isDeathSentence = isDeathSentence;
        this.wayKilled = wayKilled;
    }
    public Verdict() {
        this.isDeathSentence = true;
    }

    public String getSentence(){//don't add space before
        return (this.isDeathSentence) ?
                "a death sentence by " + wayKilled : (this.yearsInJail == 99 ? "a lifetime sentence" :
                "sentence of " + this.yearsInJail + (this.yearsInJail == 1 ? " year" : " years")) + " in jail";
    }
}
