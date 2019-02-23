package servlets;

import accounts.AccountService;
import accounts.Profile;
import dbService.DBException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    final private AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setContentType("text/html;charset=utf-8");

        if (login == null || password == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println("Unauthorized");
        } else {
            Profile mayBeUser = null;
            try {
                mayBeUser = accountService.getUserByLogin(login);
            } catch (DBException e) {
                e.printStackTrace();
            }
            if (mayBeUser != null) {
                if (mayBeUser.getPassword().equals(password)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().println("Authorized: " + login);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Unauthorized");
            }
        }
    }
}
