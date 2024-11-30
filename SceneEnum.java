public enum SceneEnum {
    ABOUT_US("/views/about_us.fxml", "About Us"),
    COST_ESTIMATES("/views/cost_estimates.fxml", "Cost Estimates"),
    LOCAL_NEWS("/views/local_news.fxml", "Local News"),
    FAQ("/views/faq.fxml", "FAQ"),
    VENDORS("/views/vendor_list.fxml", "Vendors"),
    ZIPCODE_PAGE("/views/zipcode_page.fxml", "Zipcodes"),
    RATINGS("views/vendor_review.fxml", "Ratings");

    private String resourceName;
    private String sceneName;

    private SceneEnum(String resourceName, String sceneName) {
        this.resourceName = resourceName;
        this.sceneName = sceneName;
    }

    public String getResourceName(){
        return resourceName;
    }

    public String getSceneName(){
        return sceneName;
    }
}
