package comr.br.school.reyfowemails;

import comr.br.school.reyfowemails.dto.EventMessageDTO;
import comr.br.school.reyfowemails.dto.StudentDTO;
import comr.br.school.reyfowemails.student.NotifyStudentService;
import comr.br.school.reyfowemails.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component
public class Handler implements Function<String, Void> {

    @Autowired
    private NotifyStudentService service;

    @Override
    public Void apply(String eventJson) {
        log.info("Event received {}", eventJson);
        final var event = JsonUtil.toObject(eventJson, EventMessageDTO.class);

        event.getRecords().forEach(map -> {
            final var message = (String) map.get("body");
            log.info("Message body {}", message);
            final var student = JsonUtil.toObject(message, StudentDTO.class);
            service.notify(student);
        });
        log.info("Processed messages");
        return null;
    }
}
