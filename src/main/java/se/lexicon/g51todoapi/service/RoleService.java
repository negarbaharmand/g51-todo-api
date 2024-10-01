package se.lexicon.g51todoapi.service;

import se.lexicon.g51todoapi.domain.dto.RoleDTOView;
import se.lexicon.g51todoapi.domain.entity.Role;

import java.util.List;

public interface RoleService {
    List<RoleDTOView> getAll();
}
