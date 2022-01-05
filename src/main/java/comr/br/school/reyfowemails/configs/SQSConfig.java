package comr.br.school.reyfowemails.configs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {


    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(
            AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }


    @Bean
    public AmazonSQSAsync amazonSQS() {
        var amazonSQSAsync = AmazonSQSAsyncClientBuilder.standard().defaultClient();
        return amazonSQSAsync;
    }
}
