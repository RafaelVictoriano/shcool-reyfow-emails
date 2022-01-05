package comr.br.school.reyfowemails.student;

import com.google.gson.Gson;
import comr.br.school.reyfowemails.enrollstudent.SendingEmailNewStudentEnrolled;
import comr.br.school.reyfowemails.utils.SecretsManagerUtils;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class StudentsEmails {

    @Autowired
    private SendingEmailNewStudentEnrolled sendingEmailNewStudentEnrolled;
    @Autowired
    private SecretsManagerUtils secretsManagerUtils;
    private final Gson gson = new Gson();
    private final String KEY_SECRET_MANAGER = "prod-school-emails-company";
    private final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    private static final Logger logger = LoggerFactory.getLogger(StudentsEmails.class);


    @SqsListener(value = "${aws.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(String message, @Header("TransactionId") String transactionId) {
        this.logger.debug("Received message {}", transactionId);
        var student = this.gson.fromJson(message, StudentDTO.class);
        this.buildEmail(student, transactionId);
    }

    private void buildEmail(StudentDTO student, String transactionId) {
        final var fromEmail = secretsManagerUtils.getEmail(KEY_SECRET_MANAGER);

        this.simpleMailMessage.setFrom(fromEmail);
        this.simpleMailMessage.setTo(student.getEmail());
        this.simpleMailMessage.setSubject("Student enrolled");
        this.simpleMailMessage.setText(this.messageStudent(student));

        this.sendingEmailNewStudentEnrolled.sendMailMessage(simpleMailMessage);
        this.logger.info("Email sent success {}", transactionId);
    }

    private String messageStudent(StudentDTO studentDTO) {
        return MessageFormat.format("""
                Ola aluno {0}, sua matricula no curso {1} foi efetuado com sucesso.
                 Seja bem vindo a Schooll Reyfow technologies!""", studentDTO.getName(), studentDTO.getCourseName());
    }

}
