package epicode.u2w2d5BEdispositivi.services;

import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers() {
        return userDAO.findAll();
    }
}
