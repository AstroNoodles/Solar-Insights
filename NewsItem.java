public class NewsItem {

    private String imageURL, headline, primaryText, sourceSite, category, sourceURL;
    private boolean isFavorite = false;

    public NewsItem(String imageURL, String headline, String primaryText, String sourceSite, String sourceURL,
            String category, boolean isFavorite) {
        this.imageURL = imageURL;
        this.headline = headline;
        this.primaryText = primaryText;
        this.sourceURL = sourceURL;
        this.category = category;
        this.isFavorite = isFavorite;
    }

    public NewsItem(String imageURL, String headline, String sourceSite, String sourceURL, String category,
            boolean isFavorite) {
        this.imageURL = imageURL;
        this.headline = headline;
        this.primaryText = "Click the button to be redirected to the source website!";
        this.sourceURL = sourceURL;
        this.category = category;
        this.isFavorite = isFavorite;
    }

    public NewsItem(String imageURL, String headline, String sourceSite, String category, String sourceURL) {
        this.imageURL = imageURL;
        this.headline = headline;
        this.primaryText = "Click the button to be redirected to the source website!";
        this.sourceSite = sourceSite;
        this.sourceURL = sourceURL;
        this.category = category;
        this.isFavorite = false;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imagePath) {
        this.imageURL = imagePath;
    }

    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public String getSourceSite() {
        return sourceSite;
    }

    public void setSourceSite(String sourceSite) {
        this.sourceSite = sourceSite;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}