package comr.br.school.reyfowemails;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import comr.br.school.reyfowemails.dto.EventMessageDTO;
import comr.br.school.reyfowemails.utils.JsonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.SerializationHint;
import org.springframework.nativex.hint.TypeHint;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.ses.SesClient;


@TypeHint(types = EventMessageDTO.class, typeNames = "comr.br.school.reyfowemails.utils.JsonUtil")
@NativeHint
@SerializationHint(types = {SQSEvent.class})
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


    @Bean
    public SecretsManagerClient secretsManagerClient() {
        return SecretsManagerClient.builder()
                .region(Region.US_EAST_2)
                .build();
    }


}
