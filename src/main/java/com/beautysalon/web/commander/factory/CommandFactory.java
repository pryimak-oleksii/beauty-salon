package com.example.beautysaloneeservlets.web.commander.factory;

import com.example.beautysaloneeservlets.model.DAO.OrderDAO;
import com.example.beautysaloneeservlets.model.DAO.ServiceDAO;
import com.example.beautysaloneeservlets.model.DAO.UserDAO;
import com.example.beautysaloneeservlets.web.commander.*;
import com.example.beautysaloneeservlets.web.commander.admin.AdminChangeStatusOfOrderCommand;
import com.example.beautysaloneeservlets.web.commander.admin.AdminChangeTimeOfOrderAcceptCommand;
import com.example.beautysaloneeservlets.web.commander.admin.AdminChangeTimeOfOrderCommand;
import com.example.beautysaloneeservlets.web.commander.admin.AdminCommand;
import com.example.beautysaloneeservlets.web.commander.master.MasterCommand;
import com.example.beautysaloneeservlets.web.commander.master.MasterServiceAcceptCommand;
import com.example.beautysaloneeservlets.web.commander.utils.Operation;

import java.util.HashMap;
import java.util.Map;


public class CommandFactory {

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        UserDAO userDAO = new UserDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        OrderDAO orderDAO = new OrderDAO(userDAO, serviceDAO);
        commandMap.put(Operation.MAIN_PAGE, new MainPageCommand(serviceDAO));
        commandMap.put(Operation.MAIN_MASTER_PAGE, new MainMasterCommand(userDAO, serviceDAO));
        commandMap.put(Operation.LOGIN, new LoginCommand(userDAO));
        commandMap.put(Operation.LOGOUT, new LogoutCommand());
        commandMap.put(Operation.REGISTRATION, new RegistrationCommand(userDAO));
        commandMap.put(Operation.MASTER_MENU, new MasterCommand(orderDAO));
        commandMap.put(Operation.MASTER_CHANGE_STATUS, new MasterServiceAcceptCommand(orderDAO));
        commandMap.put(Operation.ADMIN_MENU, new AdminCommand(orderDAO));
        commandMap.put(Operation.ADMIN_CHANGE_STATUS, new AdminChangeStatusOfOrderCommand(orderDAO));
        commandMap.put(Operation.ADMIN_CHANGE_TIME, new AdminChangeTimeOfOrderCommand(orderDAO));
        commandMap.put(Operation.ADMIN_CHANGE_TIME_ACCEPT, new AdminChangeTimeOfOrderAcceptCommand(userDAO, orderDAO));
        commandMap.put(Operation.ORDER, new OrderCommand(orderDAO, userDAO, serviceDAO));
        commandMap.put(Operation.ORDER_DATE, new OrderDateCommand());
        commandMap.put(Operation.ORDER_MASTER, new OrderMasterCommand(userDAO, serviceDAO, orderDAO));
        commandMap.put(Operation.ORDER_SERVICE, new OrderServiceCommand(userDAO, serviceDAO, orderDAO));
        commandMap.put(Operation.ORDER_DONE, new OrderDoneCommand());
    }


    public static Command getCommand(String url) throws Exception {
        Command command = commandMap.get(url);

        if (command == null) {
            throw new Exception();
        }

        return command;
    }
}