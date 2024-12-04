public class NewsItem {

    private String imageURL, headline, primaryText, sourceURL;
    private boolean isFavorite = false;

    public NewsItem(String imageURL, String headline, String primaryText, String sourceURL, boolean isFavorite) {
        this.imageURL = imageURL;
        this.headline = headline;
        this.primaryText = primaryText;
        this.sourceURL = sourceURL;
        this.isFavorite = isFavorite;
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

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}