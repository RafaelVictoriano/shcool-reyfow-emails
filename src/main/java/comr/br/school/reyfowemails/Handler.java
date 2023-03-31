package comr.br.school.reyfowemails;

import comr.br.school.reyfowemails.dto.EventDTO;
import comr.br.school.reyfowemails.dto.EventMessageDTO;
import comr.br.school.reyfowemails.service.NotifyStudentService;
import comr.br.school.reyfowemails.utils.JsonUtil;
import comr.br.school.reyfowemails.utils.MessageEmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.function.Function;

import static comr.br.school.reyfowemails.enums.EventTypeEnum.getEventTypeByEvent;

@Slf4j
@Component
public class Handler implements Function<String, Void> {

    @Autowired
    private NotifyStudentService service;

    @Override
    public Void apply(String eventJson) {
        log.info("Event received {}", eventJson);
        final var event = JsonUtil.toObject(eventJson, EventMessageDTO.class);

        event.getRecords().parallelStream().forEach(map -> {
            final var messageString = (String) map.get("body");
            final var message = JsonUtil.fileToObject(messageString, LinkedHashMap.class);
            final var attributes = (LinkedHashMap<String, Object>) map.get("messageAttributes");
            final var attributesEvent = (LinkedHashMap<String, String>) attributes.get("EVENT");
            final var eventStringValue = attributesEvent.get("stringValue");
            log.info("Message attributes");
            final var eventType = getEventTypeByEvent(eventStringValue);
            log.info("Message body {}, event:{}", message, eventType);
            final var eventDTO = JsonUtil.toObject(message, EventDTO.class);
            final var messageEmail = MessageEmailUtil.getMessageEvent(eventType, eventDTO);
            service.notify(eventDTO, messageEmail, eventType);
        });
        log.info("Processed messages");

        return null;
    }
}
