package se.lexicon.g51todoapi.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmailDTO {
    private String to;
    private String subject;
    private String html;
}
