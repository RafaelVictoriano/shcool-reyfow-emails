package comr.br.school.reyfowemails;

import comr.br.school.reyfowemails.utils.ProducerEmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;

@SpringBootTest
class ReyfowEmailsApplicationTests {

	@Autowired
	private ProducerEmailUtil producerEmailUtil;

	@Test
	void testSendMail() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("rafavicsk8life@gmail.com");
		simpleMailMessage.setTo("rafaelvictoriano05@hotmail.com");
		simpleMailMessage.setSubject("Enrollment completed");
		simpleMailMessage.setText("");

		this.producerEmailUtil.sendMailMessage(simpleMailMessage);
	}

}
