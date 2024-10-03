package se.lexicon.g51todoapi.converter;

import se.lexicon.g51todoapi.domain.dto.RoleDTOForm;
import se.lexicon.g51todoapi.domain.dto.RoleDTOView;
import se.lexicon.g51todoapi.domain.entity.Role;

public class RoleConverterImpl implements RoleConverter {
    @Override
    public RoleDTOView toRoleDTO(Role entity) {
        return new RoleDTOView(entity.getId(), entity.getName());
    }

    @Override
    public Role toEntity(RoleDTOForm dto) {
        return new Role(dto.getId(), dto.getName());
    }
}
