package com.example.beautysaloneeservlets.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DBHelper {

    private static volatile DBHelper instance;

    private final HikariDataSource ds;

    private DBHelper() {

        //TODO xml-> HDS tomcat jndi
        //configuring db connection and pooling with recommended params
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("qw12er34");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();

    }

    public void shutdown() {
        ds.close();
    }

    public static DBHelper getInstance() {
        DBHelper localInstance = instance;
        if (localInstance == null) {
            synchronized (DBHelper.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new DBHelper();
            }
        }
        return localInstance;
    }
//
//    private static DBHelper instance;
//    private Connection connection;
//
//    public static synchronized DBHelper getInstance() {
//
//        if (instance == null) {
//            instance = new DBHelper();
//        } else {
//
//            try {
//                boolean connectionClosed = instance.getConnection().isClosed();
//                if (connectionClosed) {
//                    instance = new DBHelper();
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException("Connection is closed: ", e);
//            }
//
//        }
//
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return connection;
//    }
//
//    private DBHelper() {
//        try {
//            this.connection = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "qw12er34");
//        } catch (SQLException e) {
//            throw new RuntimeException("Database Connection Creation Failed : " + e.getMessage());
//        }
//
//    }


}
//
//    public boolean insertUser(User user) throws DBException {
//
//        if (findAllUsers().contains(user)) {
//            return false;
//        }
//
//
//        try {
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(login) VALUES(?)");
//            statement.setString(1, user.getLogin());
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            throw new DBException("insertUser failed", e);
//
//        }
//    }
//
//    public boolean deleteUsers(User... users) throws DBException {
//
//        if (users.length == 0) {
//            return false;
//        }
//
//        StringBuilder sql = new StringBuilder("DELETE FROM users WHERE users.id IN(");
//        for (int i = 0; i < users.length; i++) {
//            if (findAllUsers().contains(users[i])) {
//                sql.append(users[i].getId());
//            }
//            if (i != users.length - 1) {
//                sql.append(", ");
//            }
//        }
//        sql.append(")");
//
//
//        try {
//            PreparedStatement statement = connection.prepareStatement(sql.toString());
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            throw new DBException("deleteUsers failed", e);
//        }
//    }
//
//    public User getUser(String login) throws DBException {
//        User resultUser = new User();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
//            ResultSet rs = statement.executeQuery();
//
//
//            while (rs.next()) {
//                if (rs.getString("login").equals(login)) {
//                    resultUser.setId(rs.getInt("id"));
//                    resultUser.setLogin(rs.getString("login"));
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            throw new DBException("getUser failed", e);
//        }
//        return resultUser;
//
//    }
//
//    public Team getTeam(String name) throws DBException {
//        Team resultTeam = new Team();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams");
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                if (rs.getString("name").equals(name)) {
//                    resultTeam.setId(rs.getInt("id"));
//                    resultTeam.setName(rs.getString("name"));
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            throw new DBException("getTeam failed", e);
//        }
//        return resultTeam;
//
//    }
//
//    public List<Team> findAllTeams() throws DBException {
//        List<Team> teamList = new ArrayList<>();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams");
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                Team team = new Team();
//                team.setId(rs.getInt("id"));
//                team.setName(rs.getString("name"));
//                teamList.add(team);
//
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return teamList;
//    }
//
//    public boolean insertTeam(Team team) throws DBException {
//        try {
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO teams(name) VALUES(?)");
//            statement.setString(1, team.getName());
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }
//    }
//
//    public boolean setTeamsForUser(User user, Team... teams) throws DBException {
//        if (teams.length == 0 || user == null) {
//            System.out.println("teams length is zero our user is null");
//            return false;
//        }
//        List<Team> userTeams = getUserTeams(user);
//
//
//        for (Team team : teams) {
//            if (userTeams.contains(team)) {
//                throw new DBException();
//            }
//        }
//
//
//        List<Team> teamList = findAllTeams();
//        List<User> userList = findAllUsers();
//
//
//        if (user.getId() == 0) {
//            if (userList.stream().map(User::getLogin).collect(Collectors.toList()).contains(user.getLogin())) {
//                for (User userInList : userList) {
//                    if (userInList.getLogin().equals(user.getLogin())) {
//                        user.setId(userInList.getId());
//                    }
//                }
//            } else {
//                insertUser(user);
//                user = getUser(user.getLogin());
//            }
//        }
//
//
//        for (int i = 0; i < teams.length; i++) {
//            if (teams[i].getId() == 0) {
//                if (teamList.stream().map(Team::getName).collect(Collectors.toList()).contains(teams[i].getName())) {
//                    for (Team teamInList : teamList) {
//                        if (teamInList.getName().equals(teams[i].getName())) {
//                            teams[i].setId(teamInList.getId());
//                        }
//                    }
//                } else {
//                    insertTeam(teams[i]);
//                    teams[i] = getTeam(teams[i].getName());
//                }
//            }
//        }
//
//        try {
//
//            connection.setAutoCommit(false);
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO users_teams (user_id, team_id) VALUES (?, ?)");
//            for (Team team : teams) {
//                statement.setInt(1, user.getId());
//                statement.setInt(2, team.getId());
//                statement.executeUpdate();
//            }
//            connection.commit();
//            return true;
//        } catch (SQLException e) {
//            try {
//                connection.rollback();
//                connection.close();
//            } catch (SQLException ex) {
//                throw new DBException("roolback failed", ex);
//            }
//            throw new DBException("setTeamsForUser failed", e);
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//    public List<Team> getUserTeams(User user) throws DBException {
//
//        if (user.getId() == 0) {
//            user = getUser(user.getLogin());
//        }
//
//        List<Team> userTeamList = new ArrayList<>();
//        try {
//
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users_teams");
//            ResultSet resultSetUserTeams = statement.executeQuery();
//
//            while (resultSetUserTeams.next()) {
//                if (user.getId() == resultSetUserTeams.getInt("user_id")) {
//                    Team team = new Team();
//                    team.setId(resultSetUserTeams.getInt("team_id"));
//                    List<Team> teamList = findAllTeams();
//
//                    for (Team teamInLoop : teamList) {
//                        if (teamInLoop.getId() == resultSetUserTeams.getInt("team_id")) {
//                            team.setName(teamInLoop.getName());
//                            break;
//                        }
//                    }
//                    userTeamList.add(team);
//                }
//
//            }
//        } catch (SQLException e) {
//            throw new DBException("getUserTeams failed", e);
//        }
//
//        return userTeamList;
//    }
//
//    public boolean deleteTeam(Team team) throws DBException {
//        if (team == null) {
//            return false;
//        }
//
//        try {
//
//            PreparedStatement statementForTeams = connection.prepareStatement("DELETE FROM teams WHERE name =  ?");
//            statementForTeams.setString(1, team.getName());
//            statementForTeams.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            throw new DBException("deleteTeam failed", e);
//        }
//
//    }
//
//    public boolean updateTeam(Team team) throws DBException {
//        try {
//            PreparedStatement statement = connection.prepareStatement("UPDATE teams SET name = ? WHERE  id = ?");
//            statement.setString(1, team.getName());
//            statement.setInt(2, team.getId());
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            throw new DBException("updateTeam failed", e);
//        }
//
//    }



