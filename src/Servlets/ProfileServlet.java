package Servlets;

import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("exist");
        req.getRequestDispatcher("/WebContent/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String roleId = req.getParameter("role");

        if(password.equals(repassword)){
            User user = new User();
            user.setEmail(email);
            user.setFullName(fullname);
            user.setPassword(password);
            user.setId(id);
            user.setRoleID(roleId);

            if(DBManager.updateUser(user)){
                resp.sendRedirect("/profile");
            }  else {
                resp.sendRedirect("/home?error");
            }

        }
    }
}
