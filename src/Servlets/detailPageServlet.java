package Servlets;

import Classes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(value = "/currentPage")
public class detailPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("idDetail"));
        News news = DBManager.getNewsById(id);
        req.setAttribute("novosti",news);

        List<Comment> comments = DBManager.getComments(id);
        req.setAttribute("comment",comments);

        List<Categories> categories = DBManager.getCategories();
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("/WebContent/detailPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Long categoryStr = Long.valueOf(req.getParameter("categories"));
        Timestamp postdate = Timestamp.valueOf(req.getParameter("postdate"));

        Categories categories = new Categories();
        categories.setId(categoryStr);

        News news = new News();
        news.setId(id);
        news.setTitle(title);
        news.setContent(content);
        news.setCategory(categories);
        news.setPostDate(postdate);

        DBManager.getNewsById(news.getId());

    }
}
