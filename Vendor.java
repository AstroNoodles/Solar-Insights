
public class Vendor {
    
    private String businessName, location;
    private int numReviews;
    private double rating;

    public Vendor(String businessName, String location, int numReviews, double rating) {
        this.businessName = businessName;
        this.location = location;
        this.numReviews = numReviews;
        this.rating = rating;
    }

    public Vendor(String businessName, String location, double rating) {
        this.businessName = businessName;
        this.location = location;
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

    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }

}
