package com.example.beautysaloneeservlets.web.commander.admin;

import com.example.beautysaloneeservlets.model.DAO.OrderDAO;
import com.example.beautysaloneeservlets.model.DAO.UserDAO;
import com.example.beautysaloneeservlets.model.entity.Order;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.web.commander.Command;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.beautysaloneeservlets.web.commander.OrderServiceCommand.NO_MASTER_FOR_THIS_DAY_PARAMETER;


public class AdminChangeTimeOfOrderAcceptCommand implements Command {

    private final UserDAO userDAO;
    private final OrderDAO orderDAO;

    public AdminChangeTimeOfOrderAcceptCommand(UserDAO userDAO, OrderDAO orderDAO) {
        this.userDAO = userDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String orderId = (String) req.getSession().getAttribute("orderNumber");
        Integer orderIdInt = Integer.parseInt(orderId);

        String dateToChangeTime = (String) req.getSession().getAttribute("dateToChangeTime");

        String newTimeParam = req.getParameter("newTimeForOrder");

        User filteredMaster = filterMasterWorkingDay(dateToChangeTime, userDAO.getUserById(orderDAO.getOrderById(orderIdInt).getMasterId()));

        List<String> mastersTimeslotList = getMasterTimeslot(dateToChangeTime, filteredMaster);


        if (mastersTimeslotList.isEmpty()) {
            req.setAttribute(NO_MASTER_FOR_THIS_DAY_PARAMETER, true);
            req.getSession().setAttribute("orderDate", null);
            req.getSession().setAttribute("orderNumber", null);
            req.getSession().setAttribute("dateToChangeTime", null);
            CommandUtil.goToPage(req, resp, "/view/admin");

        } else {
            if (newTimeParam != null) {
                req.setAttribute("dateToChangeTime", null);
                req.setAttribute("orderToChangeTime", null);

                Timestamp timestamp = Timestamp.valueOf(dateToChangeTime + " " + newTimeParam + ":00.0000000");
                orderDAO.changeOrderTime(orderIdInt, timestamp);
                CommandUtil.goToPage(req, resp, "/view/admin");

            } else {

                req.setAttribute("mastersTimeslotList", mastersTimeslotList);


                CommandUtil.goToPage(req, resp, "/WEB-INF/view/admin/change_time_accept.jsp");
            }
        }

    }

    public List<String> getMasterTimeslot(String date, User user) {

        String dateOfOrder = date.substring(0, 10);

        List<String> masterTimestampsString = orderDAO.findAllOrders().stream()
                .filter(order -> order.getMasterId() == user.getId())
                .map(Order::getReservationTime)
                .map(Timestamp::toString)
                .collect(Collectors.toList());

        List<String> sortedMasterTimestampsString = new ArrayList<>();
        for (String timestamp : masterTimestampsString) {
            String listDate = timestamp.substring(0, 10);
            if (listDate.equals(dateOfOrder)) {
                sortedMasterTimestampsString.add(timestamp);
            }

        }

        List<String> defaultTimeslots = getListOfTimeslots();

        for (String time : sortedMasterTimestampsString) {
            String cutTimestamp = time.substring(11, 16);
            defaultTimeslots.remove(cutTimestamp);
        }


        return defaultTimeslots;
    }


    public List<String> getListOfTimeslots() {
        List<String> result = new ArrayList<>();
        result.add("10:00");
        result.add("11:00");
        result.add("12:00");
        result.add("13:00");
        result.add("14:00");
        result.add("15:00");
        result.add("16:00");
        result.add("17:00");
        result.add("18:00");
        result.add("19:00");
        result.add("20:00");
        return result;
    }

    private User filterMasterWorkingDay(String orderDay, User user) {
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(orderDay.substring(0, 4));
        int month = Integer.parseInt(orderDay.substring(5, 7));
        int day = Integer.parseInt(orderDay.substring(8, 10));
        calendar.set(year, month - 1, day);

        if (userDAO.getMasterWorkingDaysNumbers(user.getId()).contains(calendar.get(Calendar.DAY_OF_WEEK))) {
            return user;

        }
        return null;

    }


}
