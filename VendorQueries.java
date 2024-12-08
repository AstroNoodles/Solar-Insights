import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class VendorQueries implements AutoCloseable {

    private Connection conn;
    public static String SECRETS_FILE = "secrets.properties";

    public VendorQueries(String secretsFilePath) {
        try (FileInputStream fis = new FileInputStream(secretsFilePath)) {
            Properties databaseProperties = new Properties();
            databaseProperties.load(fis);

            String dbURL = databaseProperties.getProperty("db.url");
            String dbUser = databaseProperties.getProperty("db.root");
            String dbPassword = databaseProperties.getProperty("db.password");

            this.conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // this function is just an example of how to use the queries! delete/modify as
    // needed
    public void initVendors() throws SQLException {
        HashMap<String, Vendor> allVendors = getAllVendors();
        HashMap<String, Double> avgRatings = getAvgRatings();
        for (String name : allVendors.keySet()) {
            // names, addresses, average ratings for each vendor
            System.out.println("VENDOR");
            System.out.println(name);
            System.out.println(allVendors.get(name).getLocation());
            System.out.print("rating: ");
            if (avgRatings.get(name) == null) {
                System.out.println("- / 5.0");
            } else {
                System.out.println(avgRatings.get(name) + " / 5.0");
                System.out.println("\nREVIEWS");
                getVendorReviews(name); // all reviews per vendor
                System.out.println("END OF REVIEW\n");
            }

            System.out.println("-------------------------------");
        }
    }

    // return hashmap of all vendors, format {name : address}
    public HashMap<String, Vendor> getAllVendors() throws SQLException {
        HashMap<String, Vendor> allVendors = new HashMap<>();
        String query = "select * from vendors order by name asc";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String vendorName = rs.getString("name");
                // may switch to StringBuilder, this is too slow
                StringBuilder sb = new StringBuilder();
                sb.append(rs.getString("street"));
                sb.append(", ");
                sb.append(rs.getString("city"));
                sb.append(", ");
                sb.append(rs.getString("state"));
                sb.append(", ");
                sb.append(rs.getInt("zip_code"));
                String vendorAddress = sb.toString();

                String imageSrc = rs.getString("image_src");
                System.out.println(vendorAddress);
                Vendor vendorInfo = new Vendor(vendorName, vendorAddress, imageSrc);

                allVendors.put(vendorName, vendorInfo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allVendors;
    }

    // return hashmap of vendors that have >= 1 review, format {name : avgRating}
    public HashMap<String, Double> getAvgRatings() throws SQLException {
        HashMap<String, Double> avgRatings = new HashMap<>();
        String query = "select name, round(avg(rating), 2) as avg_rating from reviews, vendors where reviews.vendorid = vendors.vendorid group by reviews.vendorid";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String vendorName = rs.getString("name");
                double vendorAvg = rs.getDouble("avg_rating");
                avgRatings.put(vendorName, vendorAvg);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return avgRatings;
    }

    public List<NewsItem> getResources() {
        String query = "select * from resources order by date_published desc";
        List<NewsItem> newsItems = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String resourceTitle = rs.getString("title");
                String resourceSource = rs.getString("source");
                String resourceDate = rs.getString("date_published");
                String resourceUrl = rs.getString("url");
                String resourceImgUrl = rs.getString("img_url");
                String category = rs.getString("category");
                // display and/or pass data

                System.out.println(resourceTitle);
                System.out.println(resourceSource);
                System.out.println(resourceDate);
                System.out.println(resourceUrl);
                System.out.println(resourceImgUrl);

                NewsItem item = new NewsItem(resourceImgUrl, resourceTitle, resourceSource, category, resourceUrl);
                newsItems.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return newsItems;
    }

    public void getResourcesByCategory(String c) {
        String query = "select * from resources where category = ? order by date_published desc";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, c);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String resourceTitle = rs.getString("title");
                String resourceSource = rs.getString("source");
                String resourceDate = rs.getString("date_published");
                String resourceUrl = rs.getString("url");
                String resourceImgUrl = rs.getString("img_url");
                // display and/or pass data

                System.out.println(resourceTitle);
                System.out.println(resourceSource);
                System.out.println(resourceDate);
                System.out.println(resourceUrl);
                System.out.println(resourceImgUrl);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // print all reviews for a vendor by vendor's name, no output formatted
    public List<Review> getVendorReviews(String v) throws SQLException {
        List<Review> vendorReviews = new ArrayList<>();
        String query = "select rating, description, have_bought, reviewer_name from reviews join vendors on reviews.vendorid = vendors.vendorid where name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, v);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String reviewerName = rs.getString("reviewer_name");
                Double rating = rs.getDouble("rating");
                String description = rs.getString("description");
                Boolean have_bought = rs.getBoolean("have_bought");
                System.out.printf("Rating: %f / 5\n", rating);
                System.out.println(description);
                System.out.printf("Have Bought: %b\n", have_bought);
                System.out.println();

                Review review = new Review(reviewerName, description, rating, have_bought);
                vendorReviews.add(review);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return vendorReviews;
    }

    public List<Integer> getResidentialIncentive() {
        String query = "select incentive_value from incentives where building_type = \"residential\"";
        List<Integer> incentiveValues = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Integer incentive = rs.getInt("incentive_value");
                incentiveValues.add(incentive);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return incentiveValues;
    }

    public List<Double> getDaylightHours() {
        String query = "select hours from Avg_Daylight_Hrs_States where state = \"New Jersey\"";
        List<Double> hoursValues = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Double incentive = rs.getDouble("hours");
                hoursValues.add(incentive);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return hoursValues;
    }

    public List<Integer> getIncentiveViaFilter(String buildingType, String buildingSize, String installedLocation,
            String entity) {
        String query = "select incentive_value from incentives where building_type = ? AND system_size = ? AND installed_location = ? AND entity = ?";
        List<Integer> incentiveValues = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, buildingType);
            stmt.setString(2, buildingSize);
            stmt.setString(3, installedLocation);
            stmt.setString(4, entity);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Integer incentive = rs.getInt("incentive_value");
                incentiveValues.add(incentive);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return incentiveValues;
    }

    public List<Integer> getInstallationCost5KW() {
        String query = "select KW5 from Avg_Installation_Cost where state = \"NJ\"";
        List<Integer> incentiveValues = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Integer incentive = rs.getInt("KW5");
                incentiveValues.add(incentive);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return incentiveValues;
    }

    public List<Integer> getInstallationCost6KW() {
        String query = "select KW6 from Avg_Installation_Cost where state = \"NJ\"";
        List<Integer> incentiveValues = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Integer incentive = rs.getInt("KW6");
                incentiveValues.add(incentive);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return incentiveValues;
    }

    public static void main(String[] args) throws SQLException {
        // use as needed
        try (VendorQueries vq = new VendorQueries("secrets.properties")) {
            vq.initVendors();
        }
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }
}