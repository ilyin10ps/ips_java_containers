package containers;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;
public class PostgreSQLTestContainer extends PostgreSQLContainer<PostgreSQLTestContainer> {
    public static final String SERVICE_NAME = "POSTGRESQL_CONTAINER";
    public final int postgresPort = 5432;
    public PostgreSQLTestContainer(String postgresUser, String postgresPassword, String postgresDb, String scriptName) {
        DockerImageName.parse("postgres:13.3");
        this.withReuse(true);
        this.withCreateContainerCmdModifier(cmd -> cmd.withName(SERVICE_NAME));
        this.withCopyFileToContainer(MountableFile.forClasspathResource(scriptName), "/docker-entrypoint-initdb.d/init_db.sql");
        this.withEnv("POSTGRES_USER", postgresUser);
        this.withEnv("POSTGRES_PASSWORD", postgresPassword);
        this.withEnv("POSTGRES_DB", postgresDb);
        this.waitingFor(Wait.forLogMessage("БД Postgres готова к поднятию", 2));
        System.out.println("POSTGRES_USER: " + postgresUser);
        System.out.println("POSTGRES_PASSWORD: " + postgresPassword);
        System.out.println("POSTGRES_DB: " + postgresDb);
    }
    
    public void start(){
        super.addFixedExposedPort(postgresPort, postgresPort);
        super.start();
    }
}