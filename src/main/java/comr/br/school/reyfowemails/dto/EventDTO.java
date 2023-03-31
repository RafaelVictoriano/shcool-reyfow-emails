package comr.br.school.reyfowemails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter @Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO implements Serializable {

    private String name;
    private String email;
    private String courseName;

}
