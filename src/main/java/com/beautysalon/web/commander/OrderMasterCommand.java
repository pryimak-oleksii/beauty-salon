package com.example.beautysaloneeservlets.web.commander;

import com.example.beautysaloneeservlets.model.DAO.OrderDAO;
import com.example.beautysaloneeservlets.model.DAO.ServiceDAO;
import com.example.beautysaloneeservlets.model.DAO.UserDAO;
import com.example.beautysaloneeservlets.model.entity.Order;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.model.entity.enums.Role;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class OrderMasterCommand implements Command {
    public static final String VIEW_ORDER_MASTER = "/view/order/master";
    public static final String VIEW_ORDER_SERVICE = "/view/order/service";
    public static final String VIEW_ORDER_DATE = "/view/order/date";
    public static final String DATE_ATTRIBUTE = "date";

    public static final String SERVICE_PARAMETER = "service";
    public static final String SERVICE_ATTRIBUTE = "service";
    public static final String ORDER_DATE_ATTRIBUTE = "orderDate";

    public static final String MASTER_PARAMETER = "master";
    public static final String ORDER_MASTER_VIEW_PATH = "/WEB-INF/view/order_master.jsp";

    public static final String NO_MASTER_FOR_THIS_DAY_PARAMETER = "noMasterForDay";


    private final UserDAO userDAO;
    private final ServiceDAO serviceDao;
    private final OrderDAO orderDAO;

    public OrderMasterCommand(UserDAO userDAO, ServiceDAO serviceDao, OrderDAO orderDAO) {
        this.userDAO = userDAO;
        this.serviceDao = serviceDao;
        this.orderDAO = orderDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String orderDay = (String) req.getSession().getAttribute("orderDate");
        String orderedService = (String) req.getSession().getAttribute(SERVICE_ATTRIBUTE);

        List<User> mastersList = filterMastersToList(orderDay);


        Map<User, List<String>> masterTimeslotMap = getMastersTimeslot(mastersList, orderDay);

        if (masterTimeslotMap.isEmpty()) {
            req.setAttribute(NO_MASTER_FOR_THIS_DAY_PARAMETER, true);
            req.setAttribute("orderDate", null);
            CommandUtil.goToPage(req, resp, "/view/main");

        } else {
            req.setAttribute("masterTimeslotMap", masterTimeslotMap);

            CommandUtil.goToPage(req, resp, ORDER_MASTER_VIEW_PATH);
        }
    }

    public Map<User, List<String>> getMastersTimeslot(List<User> masterList, String date) {
        Map<User, List<String>> mastersReservedTimeMap = new HashMap<>();
        String dateOfOrder = date.substring(0, 10);

        for (User users : masterList) {
            List<String> masterTimestampsString = orderDAO.findAllOrders().stream()
                    .filter(order -> order.getMasterId() == users.getId())
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

            mastersReservedTimeMap.put(users, defaultTimeslots);
        }

        return mastersReservedTimeMap;
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

    private List<User> filterMastersToList(String orderDay) {
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(orderDay.substring(0, 4));
        int month = Integer.parseInt(orderDay.substring(5, 7));
        int day = Integer.parseInt(orderDay.substring(8, 10));
        calendar.set(year, month - 1, day);

        //TODO add masterservice chek
        return userDAO.findAllUsers().stream()
                .filter(user -> user.getRole().equals(Role.MASTER))
                .filter(master -> userDAO.getMasterWorkingDaysNumbers(master.getId()).contains(calendar.get(Calendar.DAY_OF_WEEK)))
//                .filter(master -> serviceDao.findAllMasterServices(master.getId()).contains(serviceDao.getServiceByName((String) req.getSession().getAttribute(SERVICE_ATTRIBUTE))))
                .collect(Collectors.toList());

    }


}
