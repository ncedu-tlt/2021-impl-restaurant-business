package com.kodokushi;

import com.kodokushi.crud.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    /**
     * Заполняет хэшлист таблиц
     * @param tables хэшлист таблиц
     */
    public static void initializeTablesByDefault(HashMap<String, Table> tables) {
        Table employees = new Table(new ArrayList<>() {{ add("id"); add("surname"); add("name"); add("patronymic"); }});
        employees.createRow(new ArrayList<>() {{ add("0"); add("Иванов"); add("Иван"); add("Иванович"); }});
        employees.createRow(new ArrayList<>() {{ add("1"); add("Петров"); add("Петр"); add("Петрович"); }});
        employees.createRow(new ArrayList<>() {{ add("2"); add("Сергеев"); add("Сергей"); add("Сергеевич"); }});
        tables.put("Сотрудники", employees);

        Table menu = new Table(new ArrayList<>() {{ add("id"); add("name"); add("cost"); }});
        menu.createRow(new ArrayList<>() {{ add("0"); add("Блюдо1"); add("1111"); }});
        menu.createRow(new ArrayList<>() {{ add("1"); add("Блюдо2"); add("2222"); }});
        menu.createRow(new ArrayList<>() {{ add("2"); add("Блюдо3"); add("2222"); }});
        tables.put("Блюда", menu);
    }

    /**
     * Конвертирует строку в array list
     * @param string строка
     * @return array list
     */
    public static ArrayList<String> stringToArrayList(String string) {
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuilder column = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) != ' ') {
                column.append(string.charAt(i));
            } else {
                arrayList.add(column.toString());
                column = new StringBuilder();
            }
        }
        return arrayList;
    }

    /**
     * Считывает и валидирует имя таблицы
     * @param tables хэшлист таблиц
     * @param scanner сканнер
     * @return название таблицы
     */
    public static String getTableName(HashMap<String, Table> tables, Scanner scanner) {
        while (true) {
            System.err.print("Введите имя таблицы: ");
            String tableName = scanner.next();
            if (tables.containsKey(tableName)) {
                return tableName;
            }
            System.err.println("    Неверные данные, попробуйте еще раз.");
        }
    }

    /**
     * Считывает и валидирует номер столбца для определенной таблицы
     * @param tables хэшлист таблиц
     * @param scanner сканнер
     * @param tableName название таблицы
     * @return номер столбца
     */
    public static int getRowId(HashMap<String, Table> tables, Scanner scanner, String tableName) {
        while (true) {
            System.err.print("Введите номер строки: ");
            scanner.nextLine();
            if (scanner.hasNextInt()) {
                int rowId = scanner.nextInt();
                if (rowId >= 0 && rowId <= tables.get(tableName).getRowCounter()) {
                    return rowId;
                }
            }
            System.err.println("    Неверные данные, попробуйте еще раз.");
        }
    }

    /**
     * Проводит операцию "Добавление данных" в таблицы, или добавление самих таблиц
     * @param tables хэшлист таблиц
     * @param scanner сканнер
     */
    public static void operationCreate(HashMap<String, Table> tables,  Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.err.println("Выберите, что вы хотите создать: ");
            System.err.println("    (0) Выйти");
            System.err.println("    (1) Новую таблицу");
            System.err.println("    (2) Новую строку в уже существующей таблице");
            System.err.print(" > ");
            int operationCode = scanner.nextInt();
            switch (operationCode) {
                case 0 ->
                        exit = true;
                case 1 -> {
                    System.err.print("Введите имя таблицы: ");
                    String tableName = scanner.next();
                    System.err.print("Введите названия столбцов через пробел: ");
                    scanner.nextLine();
                    String columnNames = scanner.nextLine();
                    tables.put(tableName, new Table(stringToArrayList(columnNames + " ")));
                    exit = true;
                }
                case 2 -> {
                    String tableName = getTableName(tables, scanner);
                    System.err.print("Введите значения столбцов через пробел: ");
                    scanner.nextLine();
                    String columnValues = scanner.nextLine();
                    tables.get(tableName).createRow(stringToArrayList(columnValues + " "));
                    exit = true;
                }
                default ->
                        System.err.println("    Неверный код операции, попробуйте еще раз.");
            }
        }
    }

    /**
     * Проводит операцию "Считываение данных" из таблиц
     * @param tables хэшлист таблиц
     * @param scanner сканнер
     */
    public static void operationRead(HashMap<String, Table> tables,  Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.err.println("Выберите, что вы хотите вывести: ");
            System.err.println("    (0) Выйти");
            System.err.println("    (1) Значение определенного столбца таблицы");
            System.err.println("    (2) Определенную строку в таблице");
            System.err.println("    (3) Всю таблицу целиком");
            System.err.print(" > ");
            switch (scanner.nextInt()) {
                case 0 -> exit = true;
                case 1 -> {
                    String tableName = getTableName(tables, scanner);
                    int rowId = getRowId(tables, scanner, tableName);
                    System.err.print("Введите названия столбца: ");
                    String columnName = scanner.next();
                    System.err.println("    " + tables.get(tableName).readValue(rowId, columnName));
                    exit = true;
                }
                case 2 -> {
                    String tableName = getTableName(tables, scanner);
                    int rowId = getRowId(tables, scanner, tableName);
                    for (String column : tables.get(tableName).readRow(rowId)) {
                        System.err.print( "    " + column);
                    }
                    System.err.println();
                    exit = true;
                }
                case 3 -> {
                    String tableName = getTableName(tables, scanner);
                    for (String columnName : tables.get(tableName).getColumnNames()) {
                        System.err.print("    " + columnName);
                    }
                    System.err.println();
                    for (ArrayList<String> row : tables.get(tableName).readTable()) {
                        for (String column : row) {
                            System.err.print("    " + column);
                        }
                        System.err.println();
                    }
                    exit = true;
                }
                default -> System.err.println("    Неверный код операции, попробуйте еще раз.");
            }
        }
    }

    /**
     * Проводит операцию "Обновление данных" в таблицах
     * @param tables хэшлист таблиц
     * @param scanner сканнер
     */
    public static void operationUpdate(HashMap<String, Table> tables,  Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.err.println("Выберите, что вы хотите обновить: ");
            System.err.println("    (0) Выйти");
            System.err.println("    (1) Значение определенного столбца таблицы");
            System.err.println("    (2) Определенную строку в таблице");
            System.err.print(" > ");
            switch (scanner.nextInt()) {
                case 0 -> exit = true;
                case 1 -> {
                    String tableName = getTableName(tables, scanner);
                    int rowId = getRowId(tables, scanner, tableName);
                    System.err.print("Введите названия столбца: ");
                    String columnName = scanner.next();
                    System.err.print("Введите значение столбца: ");
                    String columnValue = scanner.next();
                    tables.get(tableName).updateValue(rowId, columnName, columnValue);
                    exit = true;
                }
                case 2 -> {
                    String tableName = getTableName(tables, scanner);
                    int rowId = getRowId(tables, scanner, tableName);
                    System.err.print("Введите значения столбцов через пробел: ");
                    scanner.nextLine();
                    String columnValues = scanner.nextLine();
                    tables.get(tableName).updateRow(rowId, stringToArrayList(columnValues + " "));
                    exit = true;
                }
                default -> System.err.println("    Неверный код операции, попробуйте еще раз.");
            }
        }
    }
    /**
     * Проводит операцию "Удаление данных" в таблицах, или удаление самих таблиц
     * @param tables хэшлист таблиц
     * @param scanner сканнер
     */
    public static void operationDelete(HashMap<String, Table> tables,  Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.err.println("Выберите, что вы хотите удалить: ");
            System.err.println("    (0) Выйти");
            System.err.println("    (1) Определенную строку в таблице");
            System.err.println("    (2) Определенную таблицу");
            System.err.print(" > ");
            switch (scanner.nextInt()) {
                case 0 -> exit = true;
                case 1 -> {
                    String tableName = getTableName(tables, scanner);
                    int rowId = getRowId(tables, scanner, tableName);
                    tables.get(tableName).deleteRow(rowId);
                    exit = true;
                }
                case 2 -> {
                    String tableName = getTableName(tables, scanner);
                    tables.remove(tableName);
                    exit = true;
                }
                default -> System.err.println("Неверный код операции, попробуйте еще раз.");
            }
        }
    }

    /**
     * Обрабатывает поступившие команды
     * @param tables хэшлист таблиц
     * @param command команда
     */
    public static void commandHandler(HashMap<String, Table> tables, String command) {
        Scanner scanner = new Scanner(System.in);
        switch (command) {
            case "create" -> operationCreate(tables, scanner);
            case "read" -> operationRead(tables, scanner);
            case "update" -> operationUpdate(tables, scanner);
            case "delete" -> operationDelete(tables, scanner);
            case "list" -> {
                for (String tableName : tables.keySet()) {
                    System.err.println("    " + tableName);
                }
            }
            case "help" -> {
                System.err.println("    Создать - \"create\"");
                System.err.println("    Считать - \"read\"");
                System.err.println("    Обновить - \"update\"");
                System.err.println("    Удалить - \"delete\"");
                System.err.println("    Список таблиц - \"list\"");
                System.err.println("    Выйти - \"exit\"");
            }
            default ->
                System.err.println("    Такой команды не существует. Для того, чтобы узнать доступные команды введите \"help\".");
        }
    }

    public static void main(String[] args) {
        System.err.println("\nДля того, чтобы узнать доступные команды введите \"help\"");
	    Scanner scanner = new Scanner(System.in);
        HashMap<String, Table> tables = new HashMap<>();
        initializeTablesByDefault(tables);
        while (true) {
            System.err.print(" >>> ");
            String command = scanner.next();
            if (command.equals("exit")) {
                break;
            }
            commandHandler(tables, command);
        }
    }
}
