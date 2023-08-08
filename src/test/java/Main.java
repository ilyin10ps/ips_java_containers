import containers.PostgreSQLTestContainer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PostgreSQLTestContainer postgres_test = new PostgreSQLTestContainer(
                "test_user",
                "qwerty",
                "test_db",
                "init_postgresql.sql"
        );
        postgres_test.start();
//        Thread.sleep(1000000);
    }
}