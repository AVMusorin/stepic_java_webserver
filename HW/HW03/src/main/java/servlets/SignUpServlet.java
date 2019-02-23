package servlets;

import accounts.AccountService;
import accounts.Profile;
import com.google.gson.Gson;
import dbService.DBException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    final private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Получить пользователя
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        String login = request.getParameter("login");
        Profile mayBeUser = null;
        try {
            mayBeUser = accountService.getUserByLogin(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (mayBeUser == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Добавить пользоывателя
     */
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || password == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Profile newUser = Profile.builder()
                    .login(login)
                    .password(password)
                    .build();
            try {
                accountService.addUser(newUser);
            } catch (DBException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            String json = gson.toJson(newUser);
            response.getWriter().println(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    /**
     * Изменить пользователя
     */
    public void doPut(HttpServletRequest request,
                      HttpServletResponse response) {

    }

    /**
     * Удалить пользователя
     */
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) {

    }

}
