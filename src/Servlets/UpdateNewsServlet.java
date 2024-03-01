package Servlets;

import Classes.Categories;
import Classes.DBManager;
import Classes.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(value = "/update")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Long category_id = Long.valueOf(req.getParameter("category"));
        Long id = Long.valueOf(req.getParameter("id"));

        Categories categories = new Categories();
        categories.setId(category_id);

        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCategory(categories);
        news.setId(id);

        DBManager.updateNews(news);

        resp.sendRedirect("/detail");

    }
}
