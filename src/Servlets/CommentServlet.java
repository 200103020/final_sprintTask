package Servlets;

import Classes.Comment;
import Classes.DBManager;
import Classes.News;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/comment")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comment = req.getParameter("comment");
        Long user_id = Long.valueOf(req.getParameter("user_id"));
        Long news_id = Long.valueOf(req.getParameter("news_id"));

        Comment comment1 = new Comment();
        comment1.setComment(comment);

        News news = new News();
        news.setId(news_id);
        comment1.setNews(news);

        User user = new User();
        user.setId(user_id);
        comment1.setUser(user);

        DBManager.addComment(comment1);

        resp.sendRedirect("/home");
    }
}
