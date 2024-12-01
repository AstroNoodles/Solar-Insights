public class NewsItem {

    private String imagePath, headline, primaryText, sourceURL;
    private boolean isFavorite = false;

    public NewsItem(String imagePath, String headline, String primaryText, String sourceURL, boolean isFavorite) {
        this.imagePath = imagePath;
        this.headline = headline;
        this.primaryText = primaryText;
        this.sourceURL = sourceURL;
        this.isFavorite = isFavorite;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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