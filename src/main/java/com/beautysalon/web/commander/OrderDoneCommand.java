package com.beautysalon.web.commander;


import com.beautysalon.model.DAO.OrderDAO;
import com.beautysalon.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class OrderDoneCommand implements Command{

    public static final String ORDER_DONE_VIEW_PATH = "/WEB-INF/view/order_done.jsp";

    public static final String ORDER_ATTRIBUTE = "order";

    private OrderDAO orderDAO;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

//        List<Order> orderList = (List<Order>) req.getSession().getAttribute(ORDER_ATTRIBUTE);
        CommandUtil.goToPage(req, resp, ORDER_DONE_VIEW_PATH);

    }
}
