package comr.br.school.reyfowemails;

import comr.br.school.reyfowemails.dto.EventDTO;
import comr.br.school.reyfowemails.enums.EventTypeEnum;
import comr.br.school.reyfowemails.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HandlerTest {

//    @Autowired
//    private Handler handler;
//    private String JSON_PATH = "src/test/java/comr/br/school/reyfowemails/sqs-event.json";

//    @Test
//    void shouldDoesNotThrowExceptionWhenSendEmailTest() throws Exception {
//          final String json = Files.readString(Path.of(JSON_PATH));
//      //  final var json = JsonUtil.toObject(JSON_PATH, String.class);
//       // final var student = new EventDTO("Rafa", "rafaelvictoriano05@hotmail.com", "Python");
//        assertDoesNotThrow(() -> handler.apply(json));
//    }
//
//    @Test
//    void shouldEqualsEmailWhenConvertJsonToStudent(String path) throws IOException {
//        final String json = "{\"name\": \"rafael\", \"email\": \"rafaelvictoriano05@hotmail.com\", \"courseName\": \"Programação Web\"}";
//        final var student = JsonUtil.toObject(json, EventDTO.class);
//        assertEquals("rafaelvictoriano05@hotmail.com", student.getEmail());
//    }

}
