package com.beautysalon.constants;

public class globalConstants {


    /**
     * Database constants
     */


    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "qw12er34";
    public static final String DB_JDBCURL = "jdbc:postgresql://localhost:5432/postgres";

    public static final String DB_DRIVER = "org.postgresql.Driver";




    /**
     * Order DAO
     */
    public static final String SQL_ADD_ORDER = "INSERT INTO orders" +
            "(client_id, master_id, service_id, status_id, reservation_time) VALUES(?,?,?,?,?)";
    public static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    public static final String SQL_SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE order_id=(?)";
    public static final String SQL_UPDATE_ORDER_STATUS_BY_id = "UPDATE orders SET status_id=(?) WHERE order_id=(?)";
    public static final String SQL_UPDATE_TIME_STATUS_BY_id = "UPDATE orders SET reservation_time=(?) WHERE order_id=(?)";

    /**
     * Service DAO
     */

    public static final String SQL_SELECT_SERVICE_BY_ID = "SELECT * FROM services WHERE service_id = (?)";
    public static final String SQL_SELECT_SERVICE_BY_NAME = "SELECT * FROM services WHERE service_name =(?)";

    // TODO fill in all constants and make comments

    public static final String USER_ATTRIBUTE = "user";
    public static final String EMAIL_PARAMETER = "email";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String NOTFOUND_PARAMETER = "notfound";
    public static final String WRONG_PASSWORD_PARAMETER = "wrongPassword";
    public static final String ERROR_ATTRIBUTE = "errorMessage";

    public static final String LOGIN_VIEW_PATH = "/WEB-INF/view/login.jsp";
    public static final String MAIN_PAGE_VIEW_PATH = "/WEB-INF/view/main_page.jsp";
    public static final String MAIN_MASTER_PAGE_VIEW_PATH = "/WEB-INF/view/main_master_page.jsp";

    public static final String SORT_PARAMETER = "sort";

    public static final String SERVICES_LIST_ATTRIBUTE = "servicesList";

    public static final String MASTERS_MAP_ATTRIBUTE = "mastersMap";

    public static final  String SORT_PARAMETER_PRICE = "price";
    public static final  String SORT_PARAMETER_NAME = "name";
    public static final  String SORT_PARAMETER_DURATION = "duration";

    public static final  String SORT_PARAMETER_RATING = "rating";
}
