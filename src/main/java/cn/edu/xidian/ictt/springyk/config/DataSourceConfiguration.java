package cn.edu.xidian.ictt.springyk.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * Created by heart_sunny on 2019/1/18
 */
@Configuration
@MapperScan("cn.edu.xidian.ictt.springyk.dao")
public class DataSourceConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {

        ComboPooledDataSource cpds = new ComboPooledDataSource();

        cpds.setDriverClass(jdbcDriver);
        cpds.setJdbcUrl(jdbcUrl);
        cpds.setUser(jdbcUsername);
        cpds.setPassword(jdbcPassword);

        cpds.setMaxPoolSize(30);
        cpds.setMinPoolSize(10);
        cpds.setInitialPoolSize(10);
        cpds.setAutoCommitOnClose(false);
        cpds.setCheckoutTimeout(10000);
        cpds.setAcquireRetryAttempts(2);

        return cpds;
    }
}
