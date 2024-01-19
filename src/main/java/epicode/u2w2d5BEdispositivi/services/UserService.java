package epicode.u2w2d5BEdispositivi.services;

import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.exceptions.NotFoundException;
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

    public User save(User user) {
        return userDAO.save(user);
    }

    public User findById(Long id) {
        return userDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id) {
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByIdAndUpdate(Long id, User body) {
        User found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setFirstName(body.getFirstName());
        found.setLastName(body.getLastName());

        return userDAO.save(found);
    }


}
