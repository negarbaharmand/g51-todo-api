package se.lexicon.g51todoapi.service;

import se.lexicon.g51todoapi.domain.dto.UserDTOForm;
import se.lexicon.g51todoapi.domain.dto.UserDTOView;

public interface UserService {

    UserDTOView register(UserDTOForm userDTOForm);

    UserDTOView getByEmail(String email);

    void disableByEmail(String email);

    void enableByEmail(String email);
}
