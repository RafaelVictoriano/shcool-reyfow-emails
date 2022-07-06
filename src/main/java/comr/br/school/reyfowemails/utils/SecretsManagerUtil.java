package comr.br.school.reyfowemails.utils;

import com.google.gson.Gson;
import comr.br.school.reyfowemails.student.DataSensitiveSchoolDTO;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

@Component
public class SecretsManagerUtil {

    private final SecretsManagerClient secretsManagerClient;

    public SecretsManagerUtil(SecretsManagerClient secretsManagerClient) {
        this.secretsManagerClient = secretsManagerClient;
    }

    public <T> T getSecretAndConvert(String secretName, Class<T> valueType) {
        var getValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        var secretValue = secretsManagerClient.getSecretValue(getValueRequest).secretString();
        return JsonUtil.toObject(secretValue, valueType);
    }
}
