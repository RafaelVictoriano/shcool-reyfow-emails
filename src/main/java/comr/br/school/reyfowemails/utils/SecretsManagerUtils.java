package comr.br.school.reyfowemails.utils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;

import java.util.Map;

@Component
public class SecretsManagerUtils {

    @Autowired
    private SecretsManagerClient secretsManagerClient;
    private final Gson gson = new Gson();

    public String getEmail(String secretName) {
        var getValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        var secretValue = secretsManagerClient.getSecretValue(getValueRequest).secretString();
        var mapValues = this.gson.fromJson(secretValue, Map.class);
        var email = (String) mapValues.get("email_company");
        return email;
    }
}
