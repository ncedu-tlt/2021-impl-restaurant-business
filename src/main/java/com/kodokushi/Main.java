package com.kodokushi;

import com.kodokushi.database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/restaurant";
    private static final String dbUser = "postgres";
    private static final String dbPassword = "qwerty123_";

    public static void operationCreate(Scanner scanner, Database database) {
        System.out.println("Выберите действие:");
        System.out.println("    (0) Выход");
        System.out.println("    (1) Добавить строку в таблицу");
        System.out.print(" > ");
        String operationCode = scanner.nextLine();
        switch (operationCode) {
            case "0" -> {  }
            case "1" -> {
                System.out.print("Введите название таблицы: ");
                String tableName = scanner.nextLine();
                System.out.print("Введите данные через запятую: ");
                String data = scanner.nextLine();
                try {
                    database.insertRow(tableName, data);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            default -> {
                System.out.println("Не верно введен код операции, попробуйте еще раз.");
                operationCreate(scanner, database);
            }
        }
    }

    public static void operationRead(Scanner scanner, Database database) {
        System.out.println("Выберите действие:");
        System.out.println("    (0) Выход");
        System.out.println("    (1) Считать значение столбца определенной строки");
        System.out.println("    (2) Считать строку из таблицы");
        System.out.println("    (3) Считать всю таблицу");
        System.out.print(" > ");
        String operationCode = scanner.nextLine();
        System.out.print("Введите название таблицы: ");
        String tableName = scanner.nextLine();
        switch (operationCode) {
            case "0" -> {  }
            case "1" -> {
                System.out.print("Введите номер строки: ");
                String rowId = scanner.nextLine();
                System.out.print("Введите название столбца: ");
                String columnName = scanner.nextLine();
                try {
                    ResultSet value = database.getValue(tableName, rowId, columnName);
                    while (value.next()) {
                        System.out.println(value.getString(1));
                    }
                } catch (SQLException e) {
                    System.err.println("Что то пошло не так, попробуйте еще раз.");
                }
            }
            case "2" -> {
                System.out.print("Введите номер строки: ");
                String rowId = scanner.nextLine();
                try {
                    ResultSet row = database.getRow(tableName, rowId);
                    for (int i = 1; row.next(); i++) {
                        System.out.print("    " + row.getString(i));
                    }
                    System.out.println();
                } catch (SQLException e) {
                    System.err.println("Что то пошло не так, попробуйте еще раз.");
                }
            }
            case "3" -> {
                try {
                    ResultSet columnNames = database.getColumnNames(tableName);
                    int columnsCounter = database.getColumnsCount(tableName);
                    while (columnNames.next()) {
                        System.out.print("    " + columnNames.getString(4));
                    }
                    System.out.println();
                    ResultSet data = database.getTable(tableName);
                    while (data.next()) {
                        for (int i = 1; i <= columnsCounter; i++) {
                            System.out.print("    " + data.getString(i));
                        }
                        System.out.println();
                    }
                } catch (SQLException e) {
                    System.err.println("Что то пошло не так, попробуйте еще раз.");
                }
            }
            default -> {
                System.out.println("Не верно введен код операции, попробуйте еще раз.");
            }
        }
    }

    public static void operationUpdate(Scanner scanner, Database database) {
        System.out.println("Выберите действие:");
        System.out.println("    (0) Выход");
        System.out.println("    (1) Обновить значение столбца определенной строки");
        System.out.println("    (2) Обновить строку");
        System.out.print(" > ");
        String operationCode = scanner.nextLine();
        System.out.print("Введите название таблицы: ");
        String tableName = scanner.nextLine();
        switch (operationCode) {
            case "0" -> {  }
            case "1" -> {
                System.out.print("Введите номер строки: ");
                String rowId = scanner.nextLine();
                System.out.print("Введите название столбца: ");
                String columnName = scanner.nextLine();
                System.out.print("Введите значение: ");
                String value = scanner.nextLine();
                try {
                    database.updateValue(tableName, rowId, columnName, value);
                } catch (SQLException e) {
                    System.err.println("Что то пошло не так, попробуйте еще раз.");
                }
            }
            case "2" -> {
                System.out.print("Введите номер строки: ");
                String rowId = scanner.nextLine();
                System.out.print("Введите данные, кроме id, через запятую: ");
                String data = scanner.nextLine();
                try {
                    database.updateRow(tableName, rowId, data);
                } catch (SQLException e) {
                    System.err.println("Что то пошло не так, попробуйте еще раз.");
                }
            }
            default -> {
                System.out.println("Не верно введен код операции, попробуйте еще раз.");
                operationRead(scanner, database);
            }
        }
    }

    public static void operationDelete(Scanner scanner, Database database) {
        System.out.println("Выберите действие:");
        System.out.println("    (0) Выход");
        System.out.println("    (1) Удалить строку в таблице");
        System.out.print(" > ");
        String operationCode = scanner.nextLine();
        switch (operationCode) {
            case "0" -> {   }
            case "1" -> {
                System.out.print("Введите название таблицы: ");
                String tableName = scanner.nextLine();
                System.out.print("Введите номер строки: ");
                String rowId = scanner.nextLine();
                try {
                    database.deleteRow(tableName, rowId);
                } catch (SQLException e) {
                    System.err.println("Что то пошло не так, попробуйте еще раз.");
                }
            }
            default -> {
                System.out.println("Не верно введен код операции, попробуйте еще раз.");
                operationDelete(scanner, database);
            }
        }
    }

    public static void operationGetTableList(Database database) {
        try {
            ResultSet tableList = database.getTableList();
            for (int i = 0; tableList.next(); i++) {
                System.out.println("    " + (i + 1) + ". " + tableList.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void commandHandler(String command, Scanner scanner, Database database) {
        switch (command) {
            case "1" -> operationCreate(scanner, database);
            case "2" -> operationRead(scanner, database);
            case "3" -> operationUpdate(scanner, database);
            case "4" -> operationDelete(scanner, database);
            case "5" -> operationGetTableList(database);
                default -> System.err.println("Не верно введена команда, попробуйте еще раз.");
        }
    }

    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database(dbUrl, dbUser, dbPassword);

        System.out.println("Список команд:");
        System.out.println("    (0) Выход");
        System.out.println("    (1) Создать");
        System.out.println("    (2) Считать");
        System.out.println("    (3) Обновить");
        System.out.println("    (4) Удалить");
        System.out.println("    (5) Список таблиц");

        while (true) {
            System.out.print(" >>> ");
            String command = scanner.nextLine();
            if (command.equals("0")) {
                break;
            }
            commandHandler(command, scanner, database);
        }
    }
}
