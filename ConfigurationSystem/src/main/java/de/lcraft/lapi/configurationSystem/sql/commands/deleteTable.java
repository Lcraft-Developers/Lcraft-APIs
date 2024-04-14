package de.lcraft.lapi.configurationSystem.sql.commands;

import de.lcraft.lapi.configurationSystem.api.sql.*;

// Website: https://www.dataquest.io/blog/sql-commands/
public class deleteTable implements SQLCommand {

    private String tableName;

    public deleteTable(String tableName) {
        init(tableName);
    }
    private void init(String tableName) {
        if(tableName != null) setTableName(tableName);
    }
    @Override
    public String[] createSQL(SQLCommandSender SQLCommandSender) {
        return new String[]{"DROP TABLE " + getTableName()};
    }

    public String getTableName() {
        return tableName;
    }
    private void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
