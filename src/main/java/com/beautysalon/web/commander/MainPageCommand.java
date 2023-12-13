package com.example.beautysaloneeservlets.web.commander;

import com.example.beautysaloneeservlets.model.DAO.ServiceDAO;
import com.example.beautysaloneeservlets.model.entity.Service;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.beautysaloneeservlets.constants.globalConstants.*;


public class MainPageCommand implements Command {

    public static final String NOT_LOGGED_USER_PARAMETER = "notLoggedUser";
    public static final String LOGGED_PARAMETER = "logged";

    private final ServiceDAO serviceDAO;

    public MainPageCommand(ServiceDAO serviceDAO) {
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


        List<Service> serviceList = sortServiceListBySortingParameter(req.getParameter(SORT_PARAMETER));
        req.setAttribute(SERVICES_LIST_ATTRIBUTE, serviceList);


        CommandUtil.goToPage(req, resp, MAIN_PAGE_VIEW_PATH);

    }

    private List<Service> sortServiceListBySortingParameter(String sortParameter) {
        List<Service> serviceList = serviceDAO.findAllService();
        List<Service> result;


        if (sortParameter == null){
            return serviceList;
        }

        switch (sortParameter) {
            case SORT_PARAMETER_PRICE -> result = serviceList.stream().sorted(Comparator.comparing(Service::getPrice)).
                    collect(Collectors.toList());
            case SORT_PARAMETER_NAME -> result = serviceList.stream().sorted(Comparator.comparing(Service::getName)).
                    collect(Collectors.toList());
            case SORT_PARAMETER_DURATION -> result = serviceList.stream().sorted(Comparator.comparing(Service::getDuration)).
                    collect(Collectors.toList());
            default -> result = serviceList;
        }
        return result;
    }



}