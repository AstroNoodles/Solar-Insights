public class Review {
    private String name, description;
    private double numStars;
    private boolean haveBought = false;

    public Review(String name, String description, double numStars, boolean haveBought) {
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

    public void setNumStars(double numStars) {
        this.numStars = numStars;
    }

    public double getNumStars() {
        return numStars;
    }

    public void setHaveBought(boolean haveBought) {
        this.haveBought = haveBought;
    }

    public boolean didReviewerBuy() {
        return haveBought;
    }
}
