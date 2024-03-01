package Servlets;

import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WebContent/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User oldUser = DBManager.checkUser(email);

        HttpSession session = req.getSession();
        session.setAttribute("exist", oldUser);

        if(oldUser !=null){
            if(oldUser.getEmail().equals(email) && oldUser.getPassword().equals(password)){
                resp.sendRedirect("/profile");
            } else {
                resp.sendRedirect("/login?wrongEmailOrPassword");
            }
        } else {
            resp.sendRedirect("/login?error");
        }
    }
}
