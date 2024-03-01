package Servlets;

import Classes.Categories;
import Classes.DBManager;
import Classes.News;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addNewsServlet")
public class addNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("exist");
        if(user!=null && user.getRoleID().equals("1")){
            resp.sendRedirect("/WebContent/detailPage.jsp");
        } else {
            resp.sendRedirect("/login?error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        Long categoryStr = Long.valueOf(req.getParameter("categories"));

        Categories categories = new Categories();
        categories.setId(categoryStr);

        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setCategory(categories);

        DBManager.addNews(news);

        resp.sendRedirect("/detail");
    }
}
