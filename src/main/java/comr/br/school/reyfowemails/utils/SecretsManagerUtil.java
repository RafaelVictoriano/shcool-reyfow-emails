package comr.br.school.reyfowemails.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

@Slf4j
@Component
public class SecretsManagerUtil {

    @Autowired
    private SecretsManagerClient secretsManagerClient;

    public <T> T getSecretAndConvert(String secretName, Class<T> valueType) {
        var getValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        var secretValue = secretsManagerClient.getSecretValue(getValueRequest).secretString();
        log.info("Secret {}", secretValue);
        return JsonUtil.toObject(secretValue, valueType);
    }
}
