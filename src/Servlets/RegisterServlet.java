package Servlets;

import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WebContent/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if(password.equals(repassword) && fullname!=null){
            User user = new User();
            user.setEmail(email);
            user.setFullName(fullname);
            user.setPassword(password);
            if(DBManager.registerUser(user)){
                resp.sendRedirect("/login");
            } else {
                resp.sendRedirect("/register?error");
            }
        }
    }
}
