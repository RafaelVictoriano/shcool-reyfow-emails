package comr.br.school.reyfowemails.service;

import comr.br.school.reyfowemails.dto.EventDTO;
import comr.br.school.reyfowemails.enums.EventTypeEnum;
import comr.br.school.reyfowemails.utils.ProducerEmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.ses.model.*;

import java.text.MessageFormat;

@Component
public class NotifyStudentService {

    public static final String SUBJECT = "Welcome to School Reyfow";

    @Autowired
    private ProducerEmailUtil producerEmailUtil;

    private static final String COMPANY_EMAIL = "rafaelvictoriano05@hotmail.com";

    private static final Logger log = LoggerFactory.getLogger(NotifyStudentService.class);

    public void notify(final EventDTO eventDTO, final String messageEmail, final EventTypeEnum eventType) {
        producerEmailUtil.sendMailMessage(buildEmail(eventDTO, messageEmail, eventType));
        log.info("Email sent success {}", eventDTO.getEmail());
    }

    private SendEmailRequest buildEmail(final EventDTO eventDTO, final String messageEmail, final EventTypeEnum eventType) {
        final var destination = Destination.builder()
                .toAddresses(eventDTO.getEmail())
                .build();

        final var content = Content.builder()
                .data(messageEmail)
                .build();

        final var sub = Content.builder()
                .data(eventType.getSubject())
                .build();

        final var body = Body.builder()
                .text(content)
                .build();

        final var msg = Message.builder()
                .subject(sub)
                .body(body)
                .build();

        return SendEmailRequest.builder()
                .destination(destination)
                .message(msg)
                .source(COMPANY_EMAIL)
                .build();
    }
}
