package comr.br.school.reyfowemails.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class EventMessageDTO {

    private List<Map<String, Object>> Records;

}
