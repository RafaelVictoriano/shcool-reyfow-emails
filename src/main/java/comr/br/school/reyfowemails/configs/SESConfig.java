package comr.br.school.reyfowemails.configs;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.profile.path.cred.CredentialsDefaultLocationProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import io.awspring.cloud.ses.SimpleEmailServiceMailSender;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class SESConfig {

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService(ClientConfiguration clientConfiguration) {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

    @Bean
    public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
    }


    @Bean
    public ClientConfiguration clientConfiguration() {
        final var clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProxyPort(0);
        clientConfiguration.setProxyHost(null);
        return clientConfiguration;
    }

}
