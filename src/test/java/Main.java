import containers.PostgreSQLTestContainer;
import clients.DBClient;
import models.db.test_schema.students.Tables;
import models.db.test_schema.students.tables.records.StudentsRecord;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {
        PostgreSQLTestContainer postgres_test = new PostgreSQLTestContainer(
                "test_user",
                "qwerty",
                "test_db",
                "init_postgresql.sql"
        );
        postgres_test.start();
        DBClient db_client = new DBClient();
        db_client.openDBConnection();
        var recordHead = new StudentsRecord(1, "Car", "Ford");
        db_client.insertIntoTable(Tables.STUDENTS, recordHead);
//        Thread.sleep(10000000);
        db_client.closeDBConnection();
    }
}