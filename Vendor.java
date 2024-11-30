
public class Vendor {
    
    private String businessName, location;
    private int numReviews;
    private int rating;

    public Vendor(String businessName, String location, int numReviews, int rating) {
        this.businessName = businessName;
        this.location = location;
        this.numReviews = numReviews;
        this.rating = rating;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }

}
