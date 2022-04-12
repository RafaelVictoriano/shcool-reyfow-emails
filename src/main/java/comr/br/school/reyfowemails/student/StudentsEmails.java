package comr.br.school.reyfowemails.student;

import com.google.gson.Gson;
import comr.br.school.reyfowemails.configs.SendingEmailNewStudentEnrolled;
import comr.br.school.reyfowemails.utils.SecretsManagerUtil;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;

@Component
public class StudentsEmails {

    @Autowired
    private SendingEmailNewStudentEnrolled sendingEmailNewStudentEnrolled;
    @Autowired
    private SecretsManagerUtil secretsManagerUtil;
    @Value("${aw.secret-manager}")
    private String KEY_SECRET_MANAGER;
    private static final Logger logger = LoggerFactory.getLogger(StudentsEmails.class);


    @SqsListener(value = "${aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(String message, @Header(value = "TransactionId", required = false) String transactionId) {
        var gson = new Gson();
        var args = gson.fromJson(message, Map.class);
        var messageJson = (String) args.get("Message");
        this.buildEmail(gson.fromJson(messageJson, StudentDTO.class), transactionId);
    }

    private void buildEmail(StudentDTO student, String transactionId) {
        var simpleMailMessage = new SimpleMailMessage();
        final var fromEmail = secretsManagerUtil.getEmail(KEY_SECRET_MANAGER);

        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Student enrolled");
        simpleMailMessage.setText(this.messageStudent(student));

        this.sendingEmailNewStudentEnrolled.sendMailMessage(simpleMailMessage);
        logger.info("Email sent success {}", transactionId);
    }

    private String messageStudent(StudentDTO studentDTO) {
        return MessageFormat.format("""
                Ola aluno {0}, sua matricula no curso {1} foi efetuado com sucesso.
                Seja bem vindo a Schooll Reyfow technologies!""", studentDTO.getName(), studentDTO.getCourseName());
    }

}
