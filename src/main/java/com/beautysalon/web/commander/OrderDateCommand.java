package com.example.beautysaloneeservlets.web.commander;

import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDateCommand implements Command {
    public static final String VIEW_ORDER_MASTER = "/view/order/master";
    public static final String VIEW_ORDER_SERVICE = "/view/order/service";

    public static final String DATE_PARAMETER = "date";

    public static final String SERVICE_PARAMETER = "service";
    public static final String ORDER_DATE_ATTRIBUTE = "orderDate";

    public static final String MASTER_PARAMETER = "master";
    public static final String INVALID_DATE_PARAMETER = "dateIsInvalid";


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String orderDate = req.getParameter(DATE_PARAMETER);


        if (orderDate != null && req.getSession().getAttribute(SERVICE_PARAMETER) != null) {
            if (!checkDateIsValid(orderDate)) {
                req.setAttribute(INVALID_DATE_PARAMETER, true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/order_day.jsp");
            } else {
                req.getSession().setAttribute(ORDER_DATE_ATTRIBUTE, orderDate);
                CommandUtil.goToPage(req, resp, VIEW_ORDER_MASTER);
            }
        } else if (orderDate != null && req.getSession().getAttribute(MASTER_PARAMETER) != null) {
            if (!checkDateIsValid(orderDate)) {
                req.setAttribute(INVALID_DATE_PARAMETER, true);
                CommandUtil.goToPage(req, resp, "/WEB-INF/view/order_day.jsp");
            } else {
                req.getSession().setAttribute(ORDER_DATE_ATTRIBUTE, orderDate);
                CommandUtil.goToPage(req, resp, VIEW_ORDER_SERVICE);
            }
        } else {
            CommandUtil.goToPage(req, resp, "/WEB-INF/view/order_day.jsp");
        }

    }


    private Boolean checkDateIsValid(String dateParam) {

        String dateOfOrder = dateParam + ":23-59";
        Date currentDate = null;
        try {
            currentDate = new SimpleDateFormat("yyyy-MM-dd:HH-mm").parse(dateOfOrder);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        return currentTime.before(currentDate);
    }


}
