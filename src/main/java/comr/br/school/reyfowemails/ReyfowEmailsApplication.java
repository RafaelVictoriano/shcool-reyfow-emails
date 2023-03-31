package comr.br.school.reyfowemails;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import comr.br.school.reyfowemails.dto.EventDTO;
import comr.br.school.reyfowemails.dto.EventMessageDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.SerializationHint;
import org.springframework.nativex.hint.TypeHint;
import software.amazon.awssdk.services.ses.SesClient;


@TypeHint(types = {EventMessageDTO.class, EventDTO.class, SQSEvent.class},
        typeNames = "comr.br.school.reyfowemails.utils.JsonUtil")
@NativeHint
@SerializationHint(types = {SQSEvent.class, EventMessageDTO.class})
@SpringBootApplication
public class ReyfowEmailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReyfowEmailsApplication.class, args);
        System.out.println("START");
    }

    @Bean
    public SesClient sesClient() {
        return SesClient.create();
    }


}
