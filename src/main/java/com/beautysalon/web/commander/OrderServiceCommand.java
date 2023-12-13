package com.example.beautysaloneeservlets.web.commander;

import com.example.beautysaloneeservlets.model.DAO.OrderDAO;
import com.example.beautysaloneeservlets.model.DAO.ServiceDAO;
import com.example.beautysaloneeservlets.model.DAO.UserDAO;
import com.example.beautysaloneeservlets.model.entity.Order;
import com.example.beautysaloneeservlets.model.entity.Service;
import com.example.beautysaloneeservlets.model.entity.User;
import com.example.beautysaloneeservlets.web.commander.utils.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class OrderServiceCommand implements Command {
    public static final String VIEW_ORDER_MASTER = "/view/order/master";
    public static final String VIEW_ORDER_SERVICE = "/view/order/service";
    public static final String VIEW_ORDER_DATE = "/view/order/date";
    public static final String DATE_ATTRIBUTE = "date";

    public static final String SERVICE_PARAMETER = "service";
    public static final String SERVICE_ATTRIBUTE = "service";
    public static final String MASTER_ATTRIBUTE = "master";
    public static final String ORDER_DATE_ATTRIBUTE = "orderDate";

    public static final String MASTER_PARAMETER = "master";
    public static final String ORDER_SERVICE_VIEW_PATH = "/WEB-INF/view/order_service.jsp";

    public static final String NO_MASTER_FOR_THIS_DAY_PARAMETER = "noMasterForDay";


    private UserDAO userDAO;
    private ServiceDAO serviceDao;
    private final OrderDAO orderDAO;

    public OrderServiceCommand(UserDAO userDAO, ServiceDAO serviceDao, OrderDAO orderDAO) {
        this.userDAO = userDAO;
        this.serviceDao = serviceDao;
        this.orderDAO = orderDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String orderDay = (String) req.getSession().getAttribute(ORDER_DATE_ATTRIBUTE);
        String orderedMaster = (String) req.getSession().getAttribute(MASTER_ATTRIBUTE);
        User filteredMaster = filterMasterWorkingDay(orderDay, userDAO.getUserByName(orderedMaster));
        List<String> mastersTimeslot = getMasterTimeslot(orderDay, filteredMaster);
        if (Objects.requireNonNull(mastersTimeslot).isEmpty()) {
            req.setAttribute(NO_MASTER_FOR_THIS_DAY_PARAMETER, true);
            req.setAttribute("orderDate", null);
            CommandUtil.goToPage(req, resp, "/view/main/master");

        } else {

            List<Service> masterServices = serviceDao.findAllMasterServices(Objects.requireNonNull(filteredMaster).getId());

            Map<Service, List<String>> serviceTimeSlotMap = getServicesWithTimeslots(masterServices, mastersTimeslot);

            if (filteredMaster == null || mastersTimeslot == null) {
                //TODO JSP
                req.setAttribute("masterNotWorkingDay", true);
                CommandUtil.goToPage(req, resp, "/view/main/master");
            }

            req.setAttribute("serviceTimeSlotMap", serviceTimeSlotMap);
            CommandUtil.goToPage(req, resp, ORDER_SERVICE_VIEW_PATH);
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

    private Map<Service, List<String>> getServicesWithTimeslots(List<Service> serviceList, List<String> timeslotList) {
        Map<Service, List<String>> result = new HashMap<>();
        for (int i = 0; i < serviceList.size(); i++) {
            result.put(serviceList.get(i), timeslotList);
        }
        return result;
    }
}
