package io.pivotal.pal.paluserprovidedservices;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
//@Configuration
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class})
@SpringBootApplication
public class PalUserProvidedServicesApplication {

    private Logger logger = LoggerFactory.getLogger(PalUserProvidedServicesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PalUserProvidedServicesApplication.class, args);

    }

    @Bean
    DatabaseServiceCredentials serviceCredentials(@Value("${vcap.services}") String vcapServices) {
        return new DatabaseServiceCredentials(vcapServices);
    }

    @Bean
    public DataSource externaldatabase(DatabaseServiceCredentials serviceCredentials) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(serviceCredentials.jdbcUrl("externaldatabase", "user-provided"));
        logger.info("MysqlDataSource:" + dataSource);
        System.out.println("MysqlDataSource:" + dataSource);
        return dataSource;
    }
}
