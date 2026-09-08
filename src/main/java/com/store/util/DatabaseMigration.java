package com.store.util;

import org.flywaydb.core.Flyway;
import com.store.util.DatabaseConnection;

public class DatabaseMigration {

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        DatabaseConnection.URL,
                        DatabaseConnection.USER,
                        DatabaseConnection.PASSWORD
                )
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }

    public static void clean() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        DatabaseConnection.URL,
                        DatabaseConnection.USER,
                        DatabaseConnection.PASSWORD
                )
                .load();

        flyway.clean();
    }
}