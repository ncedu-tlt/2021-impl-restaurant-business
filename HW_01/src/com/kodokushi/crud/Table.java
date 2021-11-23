package com.kodokushi.crud;

import java.util.*;

public class Table {
    private final ArrayList<ArrayList<String>> table = new ArrayList<>();
    private final LinkedHashMap<String, Integer> columnNames = new LinkedHashMap<>();
    private int rowCounter = 0;

    public Table(ArrayList<String> columnNames) {
        for (int i = 0; i < columnNames.size(); i++) {
            this.columnNames.put(columnNames.get(i), i);
        }
    }

    public ArrayList<String> getColumnNames() {
        return new ArrayList<>(this.columnNames.keySet());
    }

    public int getRowCounter() {
        return rowCounter;
    }

    // ================================== CREATE ================================== //
    public void createRow(ArrayList<String> row) {
        rowCounter++;
        this.table.add(row);
    }

    // ================================== READ ================================== //
    public String readValue(int rowId, String columnName) {
        return this.table.get(rowId).get(columnNames.get(columnName));
    }
    public ArrayList<String> readRow(int rowId) {
        return this.table.get(rowId);
    }
    public ArrayList<ArrayList<String>> readTable() {
        return this.table;
    }

    // ================================== UPDATE ================================== //
    public void updateValue(int rowId, String columnName, String value) {
        this.table.get(rowId).set(columnNames.get(columnName), value);
    }
    public void updateRow(int rowId, ArrayList<String> row) {
        this.table.set(rowId, row);
    }

    // ================================== DELETE ================================== //
    public void deleteRow(int rowId) {
        this.table.remove(rowId);
    }
}
