package de.lcraft.lapi.configurationSystem.sql.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.SQLException;

public class MySQLDataBase extends MySQLSendConnector {

    private MysqlDataSource mysqlServerOptions;

    public MySQLDataBase(String dataBaseName, MysqlDataSource mysqlServerOptions) {
        init(dataBaseName, mysqlServerOptions);
    }
    private void init(String dataBaseName, MysqlDataSource mysqlServerOptions) {
        mysqlServerOptions.setDatabaseName(dataBaseName);
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
