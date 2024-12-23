package comr.br.school.reyfowemails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSensitiveSchoolDTO implements Serializable {

    private String emailCompany;

}
