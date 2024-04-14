package de.lcraft.lapi.configurationSystem.sql.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import de.lcraft.lapi.configurationSystem.api.sql.*;
import java.sql.*;

public class MySQLSendConnector implements MySQLConnector, SQLCommandSender {

    private Connection sqlConnection;

    @Override
    public void connect(MysqlDataSource mysqlServerOptions) throws SQLException {
        if(!isConnected()) setSQLConnection(mysqlServerOptions.getConnection());
    }
    @Override
    public void endConnection() throws SQLException {
        if(isConnected()) getSQLConnection().close();
    }

    @Override
    public void executeCommand(SQLCommand cmd) throws SQLException {
        execute(cmd.createSQL(this));
    }
    @Override
    public void execute(String[] sqlArray) throws SQLException {
        for(String c : sqlArray) execute(c);
    }
    @Override
    public void execute(String sql) throws SQLException {
        if(isConnected()) getSQLConnection().createStatement().execute(sql);
    }

    @Override
    public boolean isConnected() throws SQLException {
        return getSQLConnection() != null && !getSQLConnection().isClosed();
    }
    public Connection getSQLConnection() {
        return sqlConnection;
    }
    private void setSQLConnection(Connection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

}
