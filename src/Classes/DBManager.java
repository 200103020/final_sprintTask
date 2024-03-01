package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bitlabnews",
                    "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<News> getNews() {
        List<News> news = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT n.id, n.postdate, n.title, n.content, c.id, c.name from news " +
                    "AS n INNER JOIN news_categories c on n.categoryid = c.id");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                News newsSee = new News();
                newsSee.setId(resultSet.getLong("id"));
                newsSee.setPostDate(resultSet.getTimestamp("postDate"));
                newsSee.setTitle(resultSet.getString("title"));
                newsSee.setContent(resultSet.getString("content"));

                Categories category = new Categories();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                newsSee.setCategory(category);

                news.add(newsSee);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return news;
    }


    public static User checkUser(String email) {
        User user = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * from users where email = ?");
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("fullname"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleID(resultSet.getString("roleid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    public static boolean registerUser(User user) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (email, password, fullname,roleid)" +
                    "VALUES (?,?,?,'2')");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullName());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean updateUser(User user) {
        int rows = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE users SET email = ?, fullname= ?, password = ?, roleid = ? where id = ?;");
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRoleID());
            stmt.setLong(5, user.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rows > 0;
    }

    public static boolean addNews(News news) {
        int row = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO news (postdate, categoryid, title, content) " +
                    "values (Now(),?,?,?)");
            stmt.setLong(1, news.getCategory().getId());
            stmt.setString(2, news.getTitle());
            stmt.setString(3, news.getContent());
            row = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row>0;
    }

    public static News getNewsById(Long id){
        News news1 = null;
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement("SELECT n.id, n.postdate, n.title, n.content, c.id, c.name from news" +"\n" +
                                       "AS n INNER JOIN news_categories c on n.categoryid = c.id where n.id = ?");
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                news1 = new News();
                news1.setId(resultSet.getLong("id"));
                news1.setPostDate(resultSet.getTimestamp("postDate"));
                news1.setTitle(resultSet.getString("title"));
                news1.setContent(resultSet.getString("content"));

                Categories category = new Categories();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));
                news1.setCategory(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return news1;
    }

    public static void updateNews(News news){
        try{
            PreparedStatement stmt = connection.prepareStatement("UPDATE news SET postdate = Now(),title = ?,content = ?,categoryid = ? FROM news_categories " +
                    "AS c WHERE news.id = ? AND news.categoryid = c.id;");
//            stmt.setTimestamp(1,news.getPostDate());
            stmt.setString(1,news.getTitle());
            stmt.setString(2,news.getContent());
            stmt.setLong(3, news.getCategory().getId());
            stmt.setLong(4,news.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteNews(long id){
        try{
            PreparedStatement stmt = connection.prepareStatement("DELETE from news where id = ?");
            stmt.setLong(1,id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean addComment(Comment comment){
        int row = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO comments(comment, postdate, userid, newsid)\n" +
                    "VALUES(?, Now(),?, ?)");
            stmt.setString(1,comment.getComment());
            stmt.setLong(2,comment.getUser().getId());
            stmt.setLong(3,comment.getNews().getId());
            row = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return row>0;
    }

    public static List<Comment> getComments(Long id){
        List<Comment> comments = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT c.id, c.comment, c.postdate, u.id, u.email\n" +
                    "from comments AS c INNER JOIN users u on u.id = c.userid where c.newsid = ?");
            stmt.setLong(1,id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("postdate"));

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                comment.setUser(user);
                comments.add(comment);
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comments;
    }

    public static List<Categories> getCategories(){
        List<Categories> categories = new ArrayList<>();
        PreparedStatement stmt = null;
        try {
             stmt = connection.prepareStatement("select * from news_categories");
             ResultSet resultSet = stmt.executeQuery();
             while (resultSet.next()){
                 Categories categories1 = new Categories();
                 categories1.setId(resultSet.getLong("id"));
                 categories1.setName(resultSet.getString("name"));
                 categories.add(categories1);
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

}
