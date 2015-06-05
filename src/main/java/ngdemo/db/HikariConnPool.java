package ngdemo.db;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnPool {

    private static HikariConnPool instance;
    private HikariDataSource dataSource;

    private HikariConnPool() {
        HikariConfig config = new HikariConfig();
        //config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
        config.setDriverClassName("org.h2.Driver");
        config.setJdbcUrl("jdbc:h2:file:./base");
        config.setUsername("");
        config.setPassword("");

        this.dataSource = new HikariDataSource(config);
        new DataBaseManager(this.dataSource).createTables();
    }

    public static HikariConnPool getInstance() {
        if (instance == null) {
            synchronized (HikariConnPool.class) {
                if (instance == null) {
                    instance = new HikariConnPool();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
