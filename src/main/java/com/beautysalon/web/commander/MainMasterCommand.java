package com.example.beautysaloneeservlets.web.commander;

import com.example.beautysaloneeservlets.model.DAO.ServiceDAO;
import com.example.beautysaloneeservlets.model.DAO.UserDAO;
import com.example.beautysaloneeservlets.model.entity.Service;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.model.entity.enums.Role;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.beautysaloneeservlets.constants.globalConstants.*;


public class MainMasterCommand implements Command {

    public static final String NOT_LOGGED_USER_PARAMETER = "notLoggedUser";
    public static final String LOGGED_PARAMETER = "logged";


    private final UserDAO userDAO;

    private final ServiceDAO serviceDAO;

    public MainMasterCommand(UserDAO userDAO, ServiceDAO serviceDAO) {
        this.userDAO = userDAO;
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(USER_ATTRIBUTE);
        if (user == null) {
            req.setAttribute(NOT_LOGGED_USER_PARAMETER, true);
            req.setAttribute(LOGGED_PARAMETER, false);
        } else {
            req.setAttribute(LOGGED_PARAMETER, true);
            req.setAttribute(NOT_LOGGED_USER_PARAMETER, false);
        }

        List<User> mastersList = userDAO.findAllUsers().stream().filter(userInList -> userInList.getRole().equals(Role.MASTER)).collect(Collectors.toList());
        Map<User, List<Service>> masterServiceMap = mastersList.stream()
                .collect(Collectors.toMap(key -> userDAO.getUserById(key.getId()), value -> serviceDAO.findAllMasterServices(value.getId())));
        req.setAttribute(MASTERS_MAP_ATTRIBUTE, masterServiceMap);


        CommandUtil.goToPage(req, resp, MAIN_MASTER_PAGE_VIEW_PATH);

    }

}
