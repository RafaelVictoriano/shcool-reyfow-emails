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

    public String getEmail(String secretName) {
        var gson = new Gson();
        var getValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        var secretValue = secretsManagerClient.getSecretValue(getValueRequest).secretString();
        var dataSensitiveSchoolDTO = gson.fromJson(secretValue, DataSensitiveSchoolDTO.class);
        return dataSensitiveSchoolDTO.getEmailCompany();
    }
}
