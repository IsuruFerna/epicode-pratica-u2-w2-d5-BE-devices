package epicode.u2w2d5BEdispositivi.repositories;

import epicode.u2w2d5BEdispositivi.entities.Device;
import epicode.u2w2d5BEdispositivi.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDAO extends JpaRepository<Device, Long> {
    List<Device> findByUser(User user);

    Page<Device> findByUserToPage(User user, Pageable pageable);

    Page<Device> findAllToPage(Pageable pageable);
}
