package comr.br.school.reyfowemails.utils;

import comr.br.school.reyfowemails.dto.EventDTO;
import comr.br.school.reyfowemails.enums.EventTypeEnum;

import java.text.MessageFormat;

public class MessageEmailUtil {

    public static String getMessageEvent(final EventTypeEnum eventType, final EventDTO eventDTO) {
        if (eventType.equals(EventTypeEnum.COURSE)) {
            return MessageFormat.format(eventType.getMessageEmail(), eventDTO.getStudentName(), eventDTO.getCourseName());
        }
        return MessageFormat.format(eventType.getMessageEmail(), eventDTO.getStudentName(), eventDTO.getPassword());
    }
}
