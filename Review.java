public class Review {
    private String name, description;
    private int numStars;
    private boolean haveBought = false;

    public Review(String name, String description, int numStars, boolean haveBought) {
        this.name = name;
        this.description = description;
        this.numStars = numStars;
        this.haveBought = haveBought;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public int getNumStars() {
        return numStars;
    }

    public void setHaveBought(boolean haveBought) {
        this.haveBought = haveBought;
    }

    public boolean didReviewerBuy() {
        return haveBought;
    }
}
