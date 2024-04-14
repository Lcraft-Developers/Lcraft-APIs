package de.lcraft.lapi.configurationSystem.sql.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class MySQLServer extends MySQLSendConnector {

    private MysqlDataSource mysqlServerOptions;

    public MySQLServer(String host, Integer port, String userName, String password) {
        init(host, port, userName, password);
    }
    public MySQLServer(Integer port, String userName, String password) {
        init(null, port, userName, password);
    }
    public MySQLServer(String host, String userName, String password) {
        init(host, null, userName, password);
    }
    public MySQLServer(String userName, String password) {
        init(null, null, userName, password);
    }
    private void init(String host, Integer port, String userName, String password) {
        MysqlDataSource mysqlServerOptions = new MysqlDataSource();

        if(host != null) mysqlServerOptions.setServerName(host);
        else mysqlServerOptions.setServerName("localhost");

        if(port != null) mysqlServerOptions.setPort(port);
        else mysqlServerOptions.setPort(3306);

        if(userName != null) mysqlServerOptions.setUser(userName);
        if(password != null) mysqlServerOptions.setPassword(password);

        setMySQLServerOptions(mysqlServerOptions);
    }

    public void connect() throws SQLException {
        super.connect(getMySQLServerOptions());
    }

    public MysqlDataSource getMySQLServerOptions() {
        return mysqlServerOptions;
    }
    private void setMySQLServerOptions(MysqlDataSource mysqlServerOptions) {
        this.mysqlServerOptions = mysqlServerOptions;
    }

}