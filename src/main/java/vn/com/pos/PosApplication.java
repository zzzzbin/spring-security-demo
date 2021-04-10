package vn.com.pos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import vn.com.pos.rest.model.Tutorial;
import vn.com.pos.rest.repository.TutorialRepository;

import java.util.List;

@SpringBootApplication
public class PosApplication implements WebMvcConfigurer, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
    }

    @Autowired
    TutorialRepository tutorialRepository;

    @Override
    public void run(String... args) throws Exception {
        Tutorial tutorial1 = new Tutorial("TEST 1", "Test 1 description", true);
        Tutorial tutorial2 = new Tutorial("TEST 2", "Test 2 description", true);
        List<Tutorial> tutorials = List.of(tutorial1, tutorial2);
        tutorialRepository.saveAll(tutorials);

    }
}
