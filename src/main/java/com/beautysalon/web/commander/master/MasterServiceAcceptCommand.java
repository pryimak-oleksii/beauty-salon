package com.example.beautysaloneeservlets.web.commander.master;

import com.example.beautysaloneeservlets.model.DAO.OrderDAO;
import com.example.beautysaloneeservlets.model.entity.enums.Status;
import com.example.beautysaloneeservlets.web.commander.Command;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServiceAcceptCommand implements Command {

    public final String ACCEPT_PARAMETER = "accept";


    private final OrderDAO orderDAO;

    public MasterServiceAcceptCommand(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
    // TODO add logic with letter
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String accept = req.getParameter(ACCEPT_PARAMETER);



        if (accept != null) {
            orderDAO.changeOrderStatus(Integer.parseInt(accept), Status.DONE);
        }


        CommandUtil.goToPage(req, resp, "/view/master");

    }
}
