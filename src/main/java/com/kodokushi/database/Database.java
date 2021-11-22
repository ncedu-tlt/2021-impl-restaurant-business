package com.kodokushi.database;

import java.sql.*;

public class Database {

    private Connection connection;
    private Statement statement;

    public Database(String dbUrl, String dbUser, String dbPassword) {
        try {
            this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getColumnsCount(String tableName) throws SQLException {
        ResultSet counter = statement.executeQuery("SELECT COUNT(*) FROM information_schema.columns WHERE " +
                "table_catalog = 'restaurant' AND table_name = '" + tableName + "'");
        return counter.next() ? counter.getInt(1) : 0;
    }

    public ResultSet getColumnNames(String tableName) throws SQLException {
        return connection.getMetaData().getColumns(null, null, tableName, null);
    }

    public ResultSet getTableList() throws SQLException {
        return statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'");
    }

    // CREATE
    public void insertRow(String tableName, String data) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        ResultSet columnNames = getColumnNames(tableName);
        int columnsCounter = getColumnsCount(tableName);
        for (int i = 1; columnNames.next(); i++) {
            query.append(columnNames.getString(4)).append(i == columnsCounter ? ") VALUES (default, " : ", ");
        }
        query.append(data).append(")");
        statement.executeUpdate(String.valueOf(query));
    }

    // READ
    public ResultSet getTable(String tableName) throws SQLException {
        return statement.executeQuery("SELECT * FROM " + tableName + " ORDER BY id");
    }

    public ResultSet getRow(String tableName, String rowId) throws SQLException {
        return statement.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + rowId);
    }

    public ResultSet getValue(String tableName, String rowId, String columnName) throws SQLException {
        return statement.executeQuery("SELECT " + columnName + " FROM " + tableName + " WHERE id = " + rowId);
    }

    // UPDATE
    public void updateValue(String tableName, String rowId, String columnName, String value) throws SQLException {
        statement.executeUpdate("UPDATE " + tableName + " SET " + columnName + " = " + value + " WHERE id = " + rowId);
    }

    public void updateRow(String tableName, String rowId, String data) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET (");
        ResultSet columnNames = getColumnNames(tableName);
        int columnsCounter = getColumnsCount(tableName);
        for (int i = 1; columnNames.next(); i++) {
            query.append(columnNames.getString(4)).append(i == columnsCounter ? ") = (default, " : ", ");
        }
        query.append(data).append(") WHERE id = ").append(rowId);
        statement.executeUpdate(String.valueOf(query));
    }

    // DELETE
    public void deleteRow(String tableName, String rowId) throws SQLException {
        statement.executeQuery("DELETE FROM " + tableName + " WHERE id = " + rowId);
    }
}
