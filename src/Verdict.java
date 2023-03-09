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
        this.isDeathSentence = false;
        this.isGuilty = false;
    }

    public String getSentence(){
        return (this.isDeathSentence) ? "Death Sentence by " + wayKilled : (this.yearsInJail==99 ? "Lifetime" : this.yearsInJail + " years") + " in jail";
    }
}
