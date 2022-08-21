package comr.br.school.reyfowemails.student;

import comr.br.school.reyfowemails.dto.DataSensitiveSchoolDTO;
import comr.br.school.reyfowemails.dto.StudentDTO;
import comr.br.school.reyfowemails.utils.ProducerEmailUtil;
import comr.br.school.reyfowemails.utils.SecretsManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.ses.model.*;

import java.text.MessageFormat;

@Component
public class NotifyStudentService {

    public static final String SUBJECT = "Welcome to School Reyfow";

    @Autowired
    private ProducerEmailUtil producerEmailUtil;

    @Autowired
    private SecretsManagerUtil secretsManagerUtil;

    @Value("${aws.secret-manager:prod-school-emails-company}")
    private String keySecretManager;

    private static final Logger log = LoggerFactory.getLogger(NotifyStudentService.class);

    public void notify(final StudentDTO student) {
        final var dataSensitiveSchoolDTO = secretsManagerUtil
                .getSecretAndConvert(keySecretManager, DataSensitiveSchoolDTO.class);
        producerEmailUtil.sendMailMessage(buildEmail(dataSensitiveSchoolDTO, student));
        log.info("Email sent success {}", student.getEmail());
    }

    private SendEmailRequest buildEmail(final DataSensitiveSchoolDTO dataSensitiveSchoolDTO, final StudentDTO student) {
        final var destination = Destination.builder()
                .toAddresses(student.getEmail())
                .build();

        final var content = Content.builder()
                .data(messageNotification(student))
                .build();

        final var sub = Content.builder()
                .data(SUBJECT)
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
                .source(dataSensitiveSchoolDTO.getEmailCompany())
                .build();
    }

    private String messageNotification(final StudentDTO studentDTO) {
        return MessageFormat.format(" Ola aluno {0}, sua matricula no curso {1} foi efetuado com sucesso. " +
                        "Seja bem vindo a Schooll Reyfow technologies!",
                studentDTO.getName(), studentDTO.getCourseName());
    }

}
