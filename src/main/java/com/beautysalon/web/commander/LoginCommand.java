package com.beautysalon.web.commander;

import com.beautysalon.model.DAO.UserDAO;
import com.beautysalon.model.entity.User;
import com.beautysalon.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.beautysalon.constants.globalConstants.*;

public class LoginCommand implements Command {




    private final UserDAO userDAO;


    public LoginCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String email = req.getParameter(EMAIL_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        if (Objects.nonNull(email) && Objects.nonNull(password)) {

            try {

                User user = userDAO.getUserByEmail(email);

                if (user == null) {
                    req.setAttribute(NOTFOUND_PARAMETER, true);
                    CommandUtil.goToPage(req, resp, LOGIN_VIEW_PATH);
                }

                if (!password.equals(user.getPassword())) {
                    req.setAttribute(WRONG_PASSWORD_PARAMETER, true);
                    CommandUtil.goToPage(req, resp, LOGIN_VIEW_PATH);
                }

                if (user.getRole() != null) {
                    req.getSession().setAttribute(USER_ATTRIBUTE, user);

                    String page = CommandUtil.getPersonPageByRole(user);

                    CommandUtil.goToPage(req, resp, page);
                } else {
                    req.setAttribute(ERROR_ATTRIBUTE, true);
                }

            } catch (Exception e) {

                req.setAttribute(NOTFOUND_PARAMETER, true);
                CommandUtil.goToPage(req, resp, LOGIN_VIEW_PATH);
            }
        }
        CommandUtil.goToPage(req, resp, LOGIN_VIEW_PATH);
    }
}
