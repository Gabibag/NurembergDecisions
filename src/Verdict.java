public class Verdict {
    boolean isDeathSentence;
    int yearsInJail = 0;
    boolean isGuilty = true;
    public Verdict(boolean isDeathSentence, int yearsInJail) {
        this.isDeathSentence = isDeathSentence;
        this.yearsInJail =yearsInJail;
    }public Verdict(boolean isDeathSentence) {
        this.isDeathSentence = isDeathSentence;
    }
    public Verdict() {
        this.isDeathSentence = false;
        this.isGuilty = false;
    }

    public String getSentence(){
        return (this.isDeathSentence) ? "Death Sentence" : (this.yearsInJail==99 ? "Lifetime" : this.yearsInJail + " years") + " in jail";
    }
}
