package epicode.u2w2d5BEdispositivi.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.exceptions.BadRequestException;
import epicode.u2w2d5BEdispositivi.exceptions.NotFoundException;
import epicode.u2w2d5BEdispositivi.payload.NewUserDTO;
import epicode.u2w2d5BEdispositivi.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<User> getUsers(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return userDAO.findAll(pageable);
    }

    public User save(NewUserDTO user) {
        userDAO.findByUsername(user.username()).ifPresent(found -> {
            throw new BadRequestException("Username " + found.getUsername() + " is already exsist!");
        });
        User u = new User();
        u.setUsername(user.username());
        u.setLastName(user.lastName());
        u.setFirstName(user.firstName());
        u.setEmail(user.email());
        u.setAvatar(user.avatar());
        return userDAO.save(u);
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
        found.setUsername(body.getUsername());
        found.setEmail(body.getEmail());
        found.setFirstName(body.getFirstName());
        found.setLastName(body.getLastName());
        found.setAvatar(body.getAvatar());

        return userDAO.save(found);
    }

    // set user avatar using multipart/form
    public User uploadAvatar(Long id, MultipartFile file) throws IOException {
        User found = this.findById(id);
        String avatarURL = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(avatarURL);
        return userDAO.save(found);
    }

}
