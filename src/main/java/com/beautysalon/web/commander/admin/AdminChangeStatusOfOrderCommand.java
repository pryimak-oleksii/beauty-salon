package com.beautysalon.web.commander.admin;


import com.beautysalon.model.DAO.OrderDAO;
import com.beautysalon.model.entity.enums.Status;
import com.beautysalon.web.commander.Command;
import com.beautysalon.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminChangeStatusOfOrderCommand implements Command {

    public final String DECLINE_PARAMETER = "decline";
    public final String PAYMENT_PARAMETER = "payment";

    private final OrderDAO orderDAO;

    public AdminChangeStatusOfOrderCommand(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String decline = req.getParameter(DECLINE_PARAMETER);
        String payment = req.getParameter(PAYMENT_PARAMETER);


        if (decline != null) {
            orderDAO.changeOrderStatus(Integer.parseInt(decline), Status.CANCELED);
        }

        if (payment != null) {
            orderDAO.changeOrderStatus(Integer.parseInt(payment), Status.PAID);
        }



        CommandUtil.goToPage(req, resp, "/view/admin");

    }
}
