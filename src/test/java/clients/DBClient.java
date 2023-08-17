package clients;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;

public class DBClient {
    private static final String dbName = "test_db";
    private static final String user = "test_user";
    private static final String password = "qwerty";
    private static String url = "jdbc:postgresql://localhost:5432/" + dbName;
    private static Connection connection;

    public static Connection openDBConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Подключение к PostgreSQL создано");
        return connection;
    }

    public void closeDBConnection() {
        try {
            connection.close();
        } catch (Exception exception) {
            System.out.println("При запуске тестов не выполнялось подключение к БД");
            System.out.println(exception);
        }
        System.out.println("Соединение с PostgreSQL успешно закрыто");
    }

    public static Result<Record> insertIntoTable(Table table, Record record) {
        DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
        String sql = create
                .insertInto(table)
                .set(record)
                .getSQL(ParamType.INLINED);
        System.out.println("Запрос: " + sql);
        Result<Record> result = create.fetch(sql);
        System.out.println("\n" + result);
        return result;
    }
}