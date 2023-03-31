package comr.br.school.reyfowemails.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum EventTypeEnum {

    STUDENT("STUDENT_CREATED", "Bem vindo a Schooll Reyfow technologies", " " +
            "Ola aluno {0}, sua matricula na Schooll Reyfow technologies foi efetuado com sucesso. " +
            "Para acessar a plataforma utilize seu email e essa senha: {1}. \n" +
            "Seja bem vindo a Schooll Reyfow technologies!"),
    COURSE("REGISTERED_COURSE", "Parabéns por se matricular em um novo curso", " Ola aluno {0}, sua matricula no curso {1} foi efetuado com sucesso. Bons estudos! ");

    private final String eventType;

    private final String subject;
    private final String messageEmail;


    public static EventTypeEnum getEventTypeByEvent(final String event) {
        return Arrays.stream(EventTypeEnum.values())
                .filter(eventTypeEnum -> eventTypeEnum.eventType.equals(event))
                .findFirst()
                .orElseThrow();
    }

}
