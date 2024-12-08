
public class Vendor implements Comparable<Vendor> {

    private String businessName, location, imageSrc;
    private int numReviews;
    private double rating;

    public Vendor(String businessName, String location, String imageSrc, int numReviews, double rating) {
        this.businessName = businessName;
        this.location = location;
        this.numReviews = numReviews;
        this.imageSrc = imageSrc;
        this.rating = rating;
    }

    public Vendor(String businessName, String location, String imageSrc, double rating) {
        this.businessName = businessName;
        this.location = location;
        this.imageSrc = imageSrc;
        this.rating = rating;
    }

    public Vendor(String businessName, String location, String imageSrc) {
        this.businessName = businessName;
        this.location = location;
        this.imageSrc = imageSrc;
    }

    @Override
    public int compareTo(Vendor otherVendor) {
        return Double.compare(rating, otherVendor.rating);
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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

}
