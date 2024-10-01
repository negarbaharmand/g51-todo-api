package se.lexicon.g51todoapi.converter;

import org.springframework.stereotype.Component;
import se.lexicon.g51todoapi.domain.dto.RoleDTOView;
import se.lexicon.g51todoapi.domain.entity.Role;

@Component
public interface RoleConverter {
    RoleDTOView toRoleDTO(Role entity);

    Role toEntity(RoleDTOView dto);
}
