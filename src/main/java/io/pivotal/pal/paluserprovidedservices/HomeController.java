package io.pivotal.pal.paluserprovidedservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private DataSource dataSource;

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @PostConstruct
    private void init() {
        logger.info("DataSource:" + dataSource);
        System.out.println("DataSource:" + dataSource);
    }

    @GetMapping("/")
    public String home() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.execute("update cohort set nickname='Murali' where id = 8");
        //jdbcTemplate.execute("update cohort set nickname='Murthy' where id = 11");
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from cohort where id in (8,11)");
//serviceCredentials.jdbcUrl("externaldatabase", "user-provided") +
        return "<br/>Hello PALs from Wells Fargo in Des Moines<br/>" + list;
    }


    @GetMapping("/update")
    public void update() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("update cohort set nickname='Murali1' where id = 8");
        jdbcTemplate.execute("update cohort set nickname='Murthy1' where id = 11");

    }

}
