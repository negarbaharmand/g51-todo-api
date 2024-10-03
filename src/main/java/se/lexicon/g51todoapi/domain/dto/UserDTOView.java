package se.lexicon.g51todoapi.domain.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class UserDTOView {
    private String email;
    private Set<RoleDTOView> roles;
}
