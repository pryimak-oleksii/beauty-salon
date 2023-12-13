package com.example.beautysaloneeservlets.web.commander.utils;

public interface Operation {
    String LOGIN = "/view/login";
    String MAIN_PAGE = "/view/main";

    String MAIN_MASTER_PAGE = "/view/main/master";
    //    String MASTER_ORDER_PAGE = "/view/main/masterOrder";
    String REGISTRATION = "/view/registration";
    String LOGOUT = "/view/logout";
    String ADMIN_MENU = "/view/admin";
    String ADMIN_CHANGE_STATUS = "/view/admin/changeStatus";
    String ADMIN_CHANGE_TIME = "/view/admin/changeTime";
    String ADMIN_CHANGE_TIME_ACCEPT = "/view/admin/changeTime/accept";

    String MASTER_MENU = "/view/master";
    String MASTER_CHANGE_STATUS = "/view/master/changeStatus";
    String ORDER = "/view/order";
    String ORDER_DATE = "/view/order/date";
    String ORDER_MASTER = "/view/order/master";
    String ORDER_SERVICE = "/view/order/service";

    String ORDER_DONE = "/view/order/done";


}
