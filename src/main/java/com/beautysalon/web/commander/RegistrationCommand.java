package com.example.beautysaloneeservlets.web.commander;

//import controller.command.utils.CommandUtil;
//import controller.command.utils.ValidationData;
//import model.entity.Person;
//import model.exception.AlreadyExistPersonException;
//import model.exception.ServiceException;
//import model.exception.WrongDataException;
//import service.PersonService;
//import service.factory.ServiceFactory;

import com.example.beautysaloneeservlets.model.DAO.UserDAO;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.model.entity.enums.Role;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.example.beautysaloneeservlets.security.Security.isEmailValid;
import static com.example.beautysaloneeservlets.security.Security.isPasswordValid;


public class RegistrationCommand implements Command {

    public static final String REGISTRATION_VIEW_PATH = "/WEB-INF/view/registration.jsp";
    public static final String USER_ATTRIBUTE = "user";
    public static final String NAME_PARAMETER = "name";
    public static final String SURNAME_PARAMETER = "surname";
    public static final String EMAIL_PARAMETER = "email";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String REPEAT_PASSWORD_PARAMETER = "repeatPassword";

    public static final String WRONG_EMAIL_PARAMETER = "wrongEmail";

    public static final String WRONG_EMAIL_OR_PASSWORD_PARAMETER = "incorrectEmailOrPass";
    public static final String ERROR_ATTRIBUTE = "errorMessage";

    private final UserDAO userDAO;

    public RegistrationCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String name = req.getParameter(NAME_PARAMETER);
        String surname = req.getParameter(SURNAME_PARAMETER);
        String email = req.getParameter(EMAIL_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        String repeatPassword = req.getParameter(REPEAT_PASSWORD_PARAMETER);

        if (Objects.nonNull(email) && Objects.nonNull(password)) {
            //PersonService personService = factory.getPersonService();

            try {
                //var encrypt = CommandUtil.encrypt(password);
                // Person person = personService.getByLoginAndPass(login, encrypt.orElseThrow(() -> new Exception()));
                // TODO name surname check

                if (!isEmailValid(email)) {
                    req.setAttribute(WRONG_EMAIL_OR_PASSWORD_PARAMETER, true);
                    CommandUtil.goToPage(req, resp, REGISTRATION_VIEW_PATH);
                }

                if (!isPasswordValid(password)) {
                    req.setAttribute(WRONG_EMAIL_OR_PASSWORD_PARAMETER, true);
                    CommandUtil.goToPage(req, resp, REGISTRATION_VIEW_PATH);
                }

                if (!password.equals(repeatPassword)) {
                    req.setAttribute(WRONG_EMAIL_OR_PASSWORD_PARAMETER, true);
                    CommandUtil.goToPage(req, resp, REGISTRATION_VIEW_PATH);
                }

                if (userDAO.getUserByEmail(email) != null) {
                    req.setAttribute(WRONG_EMAIL_PARAMETER, true);
                    CommandUtil.goToPage(req, resp, REGISTRATION_VIEW_PATH);
                }

                User user = new User(name, surname, password, email, Role.CLIENT);

                userDAO.addUser(user);

                String page = CommandUtil.getPersonPageByRole(user);

                CommandUtil.goToPage(req, resp, page);
//            } catch (NotFoundPersonException e) {
//                req.setAttribute("notFound", true);
//                CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
//            } catch (WrongDataException e) {
//                req.setAttribute("wrongData", true);
//                CommandUtil.goToPage(req, resp, "/WEB-INF/view/login.jsp");
            } catch (Exception e) {
                req.setAttribute(ERROR_ATTRIBUTE, true);
                CommandUtil.goToPage(req, resp, REGISTRATION_VIEW_PATH);
            }
        } else {
            CommandUtil.goToPage(req, resp, REGISTRATION_VIEW_PATH);
        }
    }
}
