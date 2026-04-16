package com.lab.worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.Duration;
import redis.clients.jedis.Jedis;

public class Worker {
    public static void main(String[] args) throws Exception {
        String redisHost = System.getenv().getOrDefault("REDIS_HOST", "redis");
        String dbHost = System.getenv().getOrDefault("DB_HOST", "db");
        String dbName = System.getenv().getOrDefault("DB_NAME", "votesdb");
        String dbUser = System.getenv().getOrDefault("DB_USER", "postgres");
        String dbPassword = System.getenv().getOrDefault("DB_PASSWORD", "postgres");

        String jdbcUrl = "jdbc:postgresql://" + dbHost + ":5432/" + dbName;

        while (true) {
            try (Jedis jedis = new Jedis(redisHost, 6379);
                 Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS votes (choice TEXT PRIMARY KEY, total INTEGER NOT NULL)"
                    );
                }

                while (true) {
                    var result = jedis.blpop(0, "votes");
                    if (result != null && result.size() > 1) {
                        String choice = result.get(1);
                        try (PreparedStatement statement = connection.prepareStatement(
                            "INSERT INTO votes(choice, total) VALUES (?, 1) " +
                            "ON CONFLICT (choice) DO UPDATE SET total = votes.total + 1"
                        )) {
                            statement.setString(1, choice);
                            statement.executeUpdate();
                        }
                    }
                }
            } catch (Exception error) {
                System.out.println("Worker retrying after failure: " + error.getMessage());
                Thread.sleep(Duration.ofSeconds(5).toMillis());
            }
        }
    }
}
