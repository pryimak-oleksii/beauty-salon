package com.beautysalon.web.commander.master;



import com.beautysalon.model.DAO.OrderDAO;
import com.beautysalon.model.entity.Order;
import com.beautysalon.model.entity.User;
import com.beautysalon.web.commander.Command;
import com.beautysalon.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class MasterCommand implements Command {
    public static final String MASTER_PAGE_VIEW_PATH = "/WEB-INF/view/master/master.jsp";
    public final String DATE_PARAMETER = "date";
    private final OrderDAO orderDAO;

    public MasterCommand(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
       String date = req.getParameter(DATE_PARAMETER);

        User master = (User) req.getSession().getAttribute("user");

        System.out.println(master);
        List<Order> masterOrders = sortListByDateParameter(date, master.getId());

//                orderDAO.findAllOrdersInfo().stream()
//                .filter(order -> order.getMasterId() == master.getId())
//                .collect(Collectors.toList());
        System.out.println(masterOrders);
        req.setAttribute("masterList", masterOrders);


        CommandUtil.goToPage(req, resp, MASTER_PAGE_VIEW_PATH);
    }


    private List<Order> sortListByDateParameter(String dateParameter, Integer masterId ) {

        List<Order> result;
        if (dateParameter == null){
                result =   orderDAO.findAllOrdersInfo().stream()
                .filter(order -> order.getMasterId() == masterId)
                .collect(Collectors.toList());
        } else{
            result =  orderDAO.findAllOrdersInfo().stream()
                    .filter(order -> order.getMasterId() == masterId)
                    .filter(order -> order.getReservationTime().toString().substring(0, 10).equals(dateParameter))
                    .collect(Collectors.toList());


//                    orderDAO.findAllOrdersInfo().stream()
//                    .filter(order -> order.getMasterId() == masterId)
//                    .filter(order -> order.getReservationTime().toLocalDateTime().equals())
//                    .collect(Collectors.toList());
        }


        return result;
    }

}
