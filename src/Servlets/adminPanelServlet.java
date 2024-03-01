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
import java.util.List;

@WebServlet (value = "/detail")
public class adminPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> news = DBManager.getNews();
        req.setAttribute("novosti", news);

        List<Categories> categories = DBManager.getCategories();
        req.setAttribute("category", categories);

        req.getRequestDispatcher("/WebContent/detailAdmin.jsp").forward(req,resp);
    }
}
