package comr.br.school.reyfowemails.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@Slf4j
@Service
public class ProducerEmailUtil {


    @Autowired
    private SesClient sesClient;

    public void sendMailMessage(SendEmailRequest sendEmailRequest) {
        sesClient.sendEmail(sendEmailRequest);
    }
}
