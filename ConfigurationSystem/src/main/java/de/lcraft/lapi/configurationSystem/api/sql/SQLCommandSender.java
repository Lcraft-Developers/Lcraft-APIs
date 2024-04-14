package de.lcraft.lapi.configurationSystem.api.sql;

import java.sql.SQLException;

public interface SQLCommandSender {

    void executeCommand(SQLCommand cmd) throws SQLException;
    void execute(String[] sqlArray) throws SQLException;
    void execute(String sql) throws SQLException;

}
