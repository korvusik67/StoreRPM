package com.store.util;

import org.flywaydb.core.Flyway;

public class DatabaseMigration {


    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        DatabaseConnection.URL,
                        DatabaseConnection.USER,
                        DatabaseConnection.PASSWORD
                )
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)  // если таблица существует, создает baseline
                .baselineVersion("1")     // версия для baseline
                .load();

        flyway.migrate();
        System.out.println("миграция выполнена успешно");
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
        System.out.println("база данных очищена");
    }


    public static void reset() {
        System.out.println("пересоздание базы данных...");
        clean();
        migrate();
        System.out.println("база данных пересоздана");
    }

    public static void info() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        DatabaseConnection.URL,
                        DatabaseConnection.USER,
                        DatabaseConnection.PASSWORD
                )
                .load();



    }
}