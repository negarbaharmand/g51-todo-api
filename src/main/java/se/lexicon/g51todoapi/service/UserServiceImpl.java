package se.lexicon.g51todoapi.service;

import org.springframework.stereotype.Service;
import se.lexicon.g51todoapi.domain.dto.UserDTOForm;
import se.lexicon.g51todoapi.domain.dto.UserDTOView;

@Service
public class UserServiceImpl implements UserService {

    //todo Add the required dependencies

    @Override
    public UserDTOView register(UserDTOForm userDTOForm) {
        //todo Implement the method
        return null;
    }

    @Override
    public UserDTOView getByEmail(String email) {
        //todo Implement the method

        return null;
    }

    @Override
    public void disableByEmail(String email) {
        //todo Implement the method

    }

    @Override
    public void enableByEmail(String email) {
        //todo Implement the method


    }
}
