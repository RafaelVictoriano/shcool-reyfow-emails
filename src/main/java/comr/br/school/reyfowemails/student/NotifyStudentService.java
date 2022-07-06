package comr.br.school.reyfowemails.student;

import comr.br.school.reyfowemails.utils.ProducerEmailUtil;
import comr.br.school.reyfowemails.utils.SecretsManagerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

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

    public void notify(StudentDTO student) {
        var simpleMailMessage = new SimpleMailMessage();
        final var dataSensitiveSchoolDTO = secretsManagerUtil
                .getSecretAndConvert(keySecretManager, DataSensitiveSchoolDTO.class);

        simpleMailMessage.setFrom(dataSensitiveSchoolDTO.getEmailCompany());
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject(SUBJECT);
        simpleMailMessage.setText(this.messageNotification(student));

        this.producerEmailUtil.sendMailMessage(simpleMailMessage);
        log.info("Email sent success {}", student.getEmail());
    }

    private String messageNotification(StudentDTO studentDTO) {
        return MessageFormat.format(" Ola aluno {0}, sua matricula no curso {1} foi efetuado com sucesso. " +
                        "Seja bem vindo a Schooll Reyfow technologies!",
                studentDTO.getName(), studentDTO.getCourseName());
    }

}
