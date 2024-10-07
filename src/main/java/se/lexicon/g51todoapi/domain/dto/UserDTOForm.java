package se.lexicon.g51todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class UserDTOForm {
    private String email;
    private String password;
    private Set<RoleDTOForm> roles;
}
