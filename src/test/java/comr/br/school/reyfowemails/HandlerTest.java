package comr.br.school.reyfowemails;

import comr.br.school.reyfowemails.utils.SecretsManagerUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class HandlerTest {

    @Autowired
    private Handler handler;

    @Autowired
    private SecretsManagerUtil secretsManagerUtil;

    private String JSON_PATH = "src/test/java/comr/br/school/reyfowemails/sqs-event.json";

//    @Test
//    void shouldDoesNotThrowExceptionWhenSendEmailTest() throws Exception {
//          final String json = Files.readString(Path.of(JSON_PATH));
//        //final var json = JsonUtil.toObject(JSON_PATH, String.class);
//        //final var student = new StudentDTO("Rafa", "rafaelvictoriano05@hotmail.com", "Python");
//        assertDoesNotThrow(() -> handler.apply(json));
//    }

    //    @Test
//    void shouldDoesNotThrowExceptionWhenGetSecrtManager() {
//        assertDoesNotThrow(() -> secretsManagerUtil
//                .getSecretAndConvert("prod-school-emails-company", DataSensitiveSchoolDTO.class));
//    }
//
//    @Test
//    void shouldEqualsEmailWhenConvertJsonToStudent(String path) throws IOException {
//        final String json = "{\"name\": \"rafael\", \"email\": \"rafaelvictoriano05@hotmail.com\", \"courseName\": \"Programação Web\"}";
//        final var student = JsonUtil.toObject(json, StudentDTO.class);
//        assertEquals("rafaelvictoriano05@hotmail.com", student.getEmail());
//    }

}
