package ntduong.movieappserver;

import ntduong.movieappserver.constant.StaticValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieAppServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void checkRegexPassword(){
        String pass = "Duong123";
        Assertions.assertTrue(pass.matches(StaticValue.REGEX_PASSWORD));
    }
}
