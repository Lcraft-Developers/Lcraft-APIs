package de.lcraft.lapi.configurationSystem.api.sql;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;

public interface MySQLConnector {

    void connect(MysqlDataSource mysqlServerOptions) throws SQLException;
    void endConnection() throws SQLException;
    boolean isConnected() throws SQLException;

}
