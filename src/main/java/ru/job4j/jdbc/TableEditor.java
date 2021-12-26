package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;


/**
 * Класс для работы с табоицами БД. Реализованы базовые функции:
 * создание таблицы, удаление таблицы, добавление колонки,
 * удаление колонки, переименование колонки.
 * @author Aleksandr kuzentsov.
 * @version 1.0
 */
public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    private void sqlQuery(String query) throws Exception {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"),
                properties.getProperty("password"));
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }

    }

    /**
     * Метод создает таблицу с указанным именем.
     * @param tableName имя таблицы.
     * @throws Exception
     */
    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                   "create table if not exists %s()",
                   tableName
        );
        sqlQuery(sql);
        System.out.println("Table created.");
    }

    /**
     * Метод удаляет таблицу с указанным именем.
     * @param tableName имя таблицы.
     */
    public void dropTable(String tableName) throws Exception {
        String sql= String.format(
                "drop table if exists %s",
                tableName
        );
        sqlQuery(sql);
        System.out.println("Table dropped.");
    }

    /**
     * Метод добавляет столбец с таблицу.
     * @param tableName имя таблицы.
     * @param columnName имя столбца.
     * @param type тип данных столбца.
     */
    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add column if not exists %s %s",
                tableName,
                columnName,
                type
        );
        sqlQuery(sql);
        System.out.println("Column added.");
    }

    /**
     * Метод удаляет столбец из таблицы.
     * @param tableName имя таблицы.
     * @param columnName имя столбца.
     */
    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column if exists %s",
                tableName,
                columnName
        );
        sqlQuery(sql);
        System.out.println("Column dropped.");
    }

    /**
     * Метод переименовывает столбец в таблице.
     * @param tableName имя таблицы.
     * @param columnName имя столбца.
     * @param newColumnName новое имя столбца.
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName
        );
        sqlQuery(sql);
        System.out.println("Column renamed.");
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("./data/app.properties"));
        TableEditor table = new TableEditor(properties);
        table.createTable("empty");
        System.out.println(getTableScheme(table.connection, "empty"));
        table.addColumn("empty", "col_1", "varchar(255)");
        System.out.println(getTableScheme(table.connection, "empty"));
        table.renameColumn("empty", "col_1", "col_2");
        System.out.println(getTableScheme(table.connection, "empty"));
        table.dropColumn("empty", "col_2");
        System.out.println(getTableScheme(table.connection, "empty"));
        table.dropTable("empty");

    }
}