package comr.br.school.reyfowemails;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import comr.br.school.reyfowemails.configs.Config;
import comr.br.school.reyfowemails.student.NotifyStudentService;
import comr.br.school.reyfowemails.student.StudentDTO;
import comr.br.school.reyfowemails.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Handler extends AbstractHandler<Config> implements RequestHandler<SQSEvent, Void> {

    private static final Logger log = LoggerFactory.getLogger(Handler.class);

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        log.info("Event received {}", sqsEvent.getRecords());
        final var notifyStudentService = getApplicationContext().getBean(NotifyStudentService.class);

        sqsEvent.getRecords().forEach(message -> {
            log.info("Message body {}", message.getBody());
            final var student = JsonUtil.toObject(message.getBody(), StudentDTO.class);
            notifyStudentService.notify(student);
        });
        log.info("Processed message");
        return null;
    }

}
