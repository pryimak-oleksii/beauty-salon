package com.beautysalon.web.commander.admin;



import com.beautysalon.model.DAO.OrderDAO;
import com.beautysalon.model.entity.Order;
import com.beautysalon.web.commander.Command;
import com.beautysalon.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class AdminCommand implements Command {
    public static final String ADMIN_PAGE_VIEW_PATH = "/WEB-INF/view/admin/admin.jsp";
    private final OrderDAO orderDAO;

    public AdminCommand(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        List<Order> orders = orderDAO.findAllOrdersInfo().stream().
//                sorted().
                collect(Collectors.toList());

        req.setAttribute("ordersList", orders);

        CommandUtil.goToPage(req, resp, ADMIN_PAGE_VIEW_PATH);
    }
}
