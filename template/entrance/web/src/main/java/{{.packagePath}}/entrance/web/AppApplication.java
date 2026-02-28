package {{ .package }}.entrance.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = {"{{.groupId}}", "com.vs"})
@MapperScan("{{.groupId}}.**.persist.mapper.mybatis")
@EnableScheduling
public class AppApplication{

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AppApplication.class);
        application.run(args);
    }
}
