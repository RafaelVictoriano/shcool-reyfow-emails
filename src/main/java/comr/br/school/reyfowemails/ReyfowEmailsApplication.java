package comr.br.school.reyfowemails;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import comr.br.school.reyfowemails.student.NotifyStudentService;
import comr.br.school.reyfowemails.student.StudentDTO;
import comr.br.school.reyfowemails.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class ReyfowEmailsApplication {

	@Autowired
	private NotifyStudentService notifyStudentService;

	public static void main(String[] args) {
		SpringApplication.run(ReyfowEmailsApplication.class, args);
	}

	@Bean
	public Function<SQSEvent, Consumer<SQSEvent>> sqsEventVoidFunction() {
		Consumer<SQSEvent> sqsEventConsumer = (SQSEvent sqsEvent) -> sqsEvent.getRecords().forEach(message -> {
			final var student = JsonUtil.toObject(message.getBody(), StudentDTO.class);
			notifyStudentService.notify(student);
		});
		return null;
	}

}
