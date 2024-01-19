package epicode.u2w2d5BEdispositivi.repositories;

import epicode.u2w2d5BEdispositivi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
}
