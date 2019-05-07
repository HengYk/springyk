package cn.edu.xidian.ictt.springyk.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by heart_sunny on 2019/1/18
 */
@Configuration
public class SessionFactoryConfiguration {

    @Autowired
    private DataSource dataSource;

    private static String mybatisConfigFile;
    @Value("${mybatis_config_path}")
    public void setMybatisConfigFile(String mybatisConfigFile) {
        SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
    }

    private static String mybatisPath;
    @Value("${mapper_path}")
    public void setMybatisPath(String mybatisPath) {
        SessionFactoryConfiguration.mybatisPath = mybatisPath;
    }

    @Value("${type_aliases_package}")
    private String typeAliasesPackage;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {

        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();

        // 设置数据库连接池
        ssfb.setDataSource(dataSource);

        // 设置mybatis的全局配置文件
        ssfb.setConfigLocation(new ClassPathResource(mybatisConfigFile));

        // 设置mapper的扫描路径
        PathMatchingResourcePatternResolver prpr = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mybatisPath;
        ssfb.setMapperLocations(prpr.getResources(packageSearchPath));

        // 设置aliases的扫描路径
        ssfb.setTypeAliasesPackage(typeAliasesPackage);

        return ssfb;
    }
}
