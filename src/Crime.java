public class Crime {
    public String getDescription() {
        return description;
    }

    private final String description;

    public Crime(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
