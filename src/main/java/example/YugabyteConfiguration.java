package example;

import com.datastax.oss.driver.api.core.CqlSession;
import com.yugabyte.data.jdbc.datasource.YugabyteTransactionManager;
import com.yugabyte.data.jdbc.repository.config.EnableYsqlRepositories;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.TransactionManager;

import java.net.InetSocketAddress;

import javax.sql.DataSource;

/**
 * TODO: Use this config until the Yugabyte "starter" is available
 */
@Configuration
@EnableYsqlRepositories
public class YugabyteConfiguration {

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.url}") String jdbcUrl,
                          @Value("${spring.datasource.driver-class-name}") String driverClassName,
                          @Value("${yugabyte.datasource.load-balance:false}") String loadBalance,
                          @Value("${spring.datasource.username}") String username,
                          @Value("${spring.datasource.password}") String password) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("load-balance", loadBalance);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public CqlTemplate cqlTemplate(CqlSession session) {
        return new CqlTemplate(session);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public CqlSession cqlSession(@Value("${yugabyte.cql.hostname}") String hostname,
                                 @Value("${yugabyte.cql.port}") int port) {
        return CqlSession.builder()
                .addContactPoint(new InetSocketAddress(hostname, port))
                .withKeyspace("example")
                .withLocalDatacenter("datacenter1")
                .build();
    }

//    @Bean
//    public TransactionManager transactionManager(DataSource dataSource) {
//        return new YugabyteTransactionManager(dataSource);
//    }

}
