package comr.br.school.reyfowemails;

import comr.br.school.reyfowemails.enrollstudent.SendingEmailNewStudentEnrolled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

@SpringBootTest
class ReyfowEmailsApplicationTests {

	@Autowired
	private SendingEmailNewStudentEnrolled sendingEmailNewStudentEnrolled;

	@Test
	void testSendMail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("rafavicsk8life@gmail.com");
		simpleMailMessage.setTo("rafaelvictoriano05@hotmail.com");
		simpleMailMessage.setSubject("Enrollment completed");
		simpleMailMessage.setText("");

		this.sendingEmailNewStudentEnrolled.sendMailMessage(simpleMailMessage);
	}

}
