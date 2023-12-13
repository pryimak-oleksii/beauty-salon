package com.example.beautysaloneeservlets.web.commander;

import com.example.beautysaloneeservlets.model.DAO.OrderDAO;
import com.example.beautysaloneeservlets.model.DAO.ServiceDAO;
import com.example.beautysaloneeservlets.model.DAO.UserDAO;
import com.example.beautysaloneeservlets.model.entity.Order;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.model.entity.enums.Status;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.stream.Collectors;

import static com.example.beautysaloneeservlets.constants.globalConstants.USER_ATTRIBUTE;
import static com.example.beautysaloneeservlets.web.commander.utils.Operation.ORDER_DONE;

public class OrderCommand implements Command {

    public static final String SERVICE_PARAMETER = "service";

    public static final String MASTER_PARAMETER = "master";
    public static final String SERVICE_ATTRIBUTE = "service";
    public static final String MASTER_ATTRIBUTE = "master";
    public static final String VIEW_ORDER_DATE = "/view/order/date";
    public static final String VIEW_MAIN = "/view/main";
    public static final String VIEW_MAIN_MASTER = "/view/main/master";
    public static final String NOT_LOGGED_PARAMETER = "notLogged";
    public static final String ORDER_DATE_ATTRIBUTE = "orderDate";
    public static final String ORDER_TIME_ATTRIBUTE = "time";

    private final OrderDAO orderDAO;

    private final UserDAO userDAO;

    private final ServiceDAO serviceDAO;

    public OrderCommand(OrderDAO orderDAO, UserDAO userDAO, ServiceDAO serviceDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.serviceDAO = serviceDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(USER_ATTRIBUTE);
        String serviceName = req.getParameter(SERVICE_PARAMETER);
        String masterParameter = req.getParameter(MASTER_PARAMETER);
        String timeParameter = req.getParameter("time");

        if (timeParameter != null && masterParameter != null) {
            req.getSession().setAttribute("time", timeParameter);
            req.getSession().setAttribute(MASTER_ATTRIBUTE, masterParameter);

        }

        if (timeParameter != null && serviceName != null) {
            req.getSession().setAttribute("time", timeParameter);
            req.getSession().setAttribute(SERVICE_ATTRIBUTE, serviceName);

        }

        String dateAttribute = (String) req.getSession().getAttribute(ORDER_DATE_ATTRIBUTE);
        String timeAttribute = (String) req.getSession().getAttribute(ORDER_TIME_ATTRIBUTE);
        String serviceAttribute = (String) req.getSession().getAttribute(SERVICE_ATTRIBUTE);
        String masterAttribute = (String) req.getSession().getAttribute(MASTER_ATTRIBUTE);
        User userAttribute = (User) req.getSession().getAttribute(USER_ATTRIBUTE);


        if (dateAttribute != null && timeAttribute != null && serviceAttribute != null && masterAttribute != null && userAttribute != null) {
            Order order = new Order();
            order.setClientId(user.getId());
            order.setMasterId(userDAO.getUserByName(masterAttribute).getId());
            order.setServiceId(serviceDAO.getServiceByName(serviceAttribute).getId());

            Timestamp timestamp = Timestamp.valueOf(dateAttribute + " " + timeAttribute + ":00.0000000");
            order.setReservationTime(timestamp);
            order.setStatus(Status.CREATED);


            orderDAO.addOrder(order);
            req.setAttribute("order", orderDAO.findAllOrdersInfo().
                    stream().
                    filter(orderInlist -> userDAO.getUserByName(orderInlist.getMasterName()).getId() == order.getMasterId()
                            && userDAO.getUserByName(orderInlist.getClientName()).getId() == order.getClientId()
                    && orderInlist.getReservationTime().equals(timestamp)).collect(Collectors.toList()));

            req.getSession().setAttribute(ORDER_DATE_ATTRIBUTE, null );
            req.getSession().setAttribute(ORDER_TIME_ATTRIBUTE, null );
            req.getSession().setAttribute(SERVICE_ATTRIBUTE, null );
            req.getSession().setAttribute(masterAttribute, null );

            CommandUtil.goToPage(req, resp, ORDER_DONE);
        }

        if (user == null) {
            req.setAttribute(NOT_LOGGED_PARAMETER, true);
            if (serviceName != null) {
                CommandUtil.goToPage(req, resp, VIEW_MAIN);
            } else {
                CommandUtil.goToPage(req, resp, VIEW_MAIN_MASTER);
            }


        } else {

            if (serviceName != null) {
                req.getSession().setAttribute(SERVICE_ATTRIBUTE, serviceName);
                CommandUtil.goToPage(req, resp, VIEW_ORDER_DATE);
            } else if (masterParameter != null) {
                req.getSession().setAttribute(MASTER_ATTRIBUTE, masterParameter);
                CommandUtil.goToPage(req, resp, VIEW_ORDER_DATE);
            }
        }
    }
}
