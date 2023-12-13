package com.example.beautysaloneeservlets.web.commander.admin;

import com.example.beautysaloneeservlets.model.DAO.OrderDAO;
import com.example.beautysaloneeservlets.model.entity.Order;
import com.example.beautysaloneeservlets.web.commander.Command;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.beautysaloneeservlets.web.commander.OrderDateCommand.INVALID_DATE_PARAMETER;

public class AdminChangeTimeOfOrderCommand implements Command {

    public static final String ADMIN_PAGE_VIEW_PATH = "/WEB-INF/view/admin/admin.jsp";
    public static final String CHANGE_TIME_PAGE_VIEW_PATH = "/WEB-INF/view/admin/change_time.jsp";
    public final String ORDER_NUMBER_PARAMETER = "orderNumber";

    public final String ORDER_NUMBER_ATTRIBUTE = "orderNumber";
    private final OrderDAO orderDAO;

    public AdminChangeTimeOfOrderCommand(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter(ORDER_NUMBER_PARAMETER);
        if (id != null) {
            req.getSession().setAttribute(ORDER_NUMBER_ATTRIBUTE, id);
        }

        if (req.getAttribute(ORDER_NUMBER_ATTRIBUTE) != null) {
            Order order = orderDAO.getOrderById(Integer.parseInt(id));
            req.getSession().setAttribute("orderToChangeTime", order);
        }


        String newDate = req.getParameter("date");
        if (!checkDateIsValid(newDate) && newDate != null) {
            req.getSession().setAttribute(INVALID_DATE_PARAMETER, true);
        } else if (newDate != null) {
            req.getSession().setAttribute("dateToChangeTime", newDate);
        }

        String dateToChangeTime = (String) req.getSession().getAttribute("dateToChangeTime");
        String idOfOrder = (String) req.getSession().getAttribute("orderNumber");
        if (dateToChangeTime != null && idOfOrder != null) {
            CommandUtil.goToPage(req, resp, "/view/admin/changeTime/accept");

        } else {
            CommandUtil.goToPage(req, resp, CHANGE_TIME_PAGE_VIEW_PATH);
        }
    }


    private Boolean checkDateIsValid(String dateParam) {
        if (dateParam != null) {
            String dateOfOrder = dateParam + ":23-59";
            Date currentDate = null;
            try {
                currentDate = new SimpleDateFormat("yyyy-MM-dd:HH-mm").parse(dateOfOrder);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            return currentTime.before(currentDate);
        } else {
            return true;
        }
    }

}