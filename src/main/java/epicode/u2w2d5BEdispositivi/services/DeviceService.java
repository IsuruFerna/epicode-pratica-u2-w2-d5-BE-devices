package epicode.u2w2d5BEdispositivi.services;

import epicode.u2w2d5BEdispositivi.entities.Device;
import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.exceptions.BadRequestException;
import epicode.u2w2d5BEdispositivi.exceptions.NotFoundException;
import epicode.u2w2d5BEdispositivi.payload.NewDeviceDTO;
import epicode.u2w2d5BEdispositivi.payload.NewDeviceResponse;
import epicode.u2w2d5BEdispositivi.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private UserService userService;

    public List<Device> getDevices() {
        return deviceDAO.findAll();
    }

//    public Page<Device> getDevicesToPage(PageRequest pageRequest) {
//        return deviceDAO.findAllToPage(pageRequest);
//    }

    public Device save(NewDeviceDTO body) {
        Device device = new Device();
        device.setAvailability(body.availability());
        device.setType(body.type());

        if(body.user() != null) {
            User user = userService.findById(body.user());
            device.setUser(user);
        }
        return deviceDAO.save(device);
    }

    public Device findById(Long id) {
        return deviceDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public void findByIdAndDelete(Long id) {
        Device found = this.findById(id);
        deviceDAO.delete(found);
    }

    public Device findByIdAndUpdate(Long id, NewDeviceDTO body) {
        Device found = this.findById(id);
        found.setType(body.type());
        found.setAvailability(body.availability());

        System.out.println("this is user: " + body.user());

        if(body.user() != null && found.getUser() == null) {
            User user = userService.findById(body.user());
            found.setUser(user);
        } else {
            // throw exception
            System.out.println("Device already in use");
        }
        return deviceDAO.save(found);
    }

    public List<Device> findByUser(Long userId) {
        User user = userService.findById(userId);
        return deviceDAO.findByUser(user);
    }

    public List<Device> findByType(String type) {
        return deviceDAO.findByType(type);
    }

    public List<Device> findByAvailability(Integer availability) {
        if(3 <= availability && availability <= 0) {
            throw new BadRequestException(availability + " must be a value between 0 and 3");
        }
        return deviceDAO.findByAvailability(availability);
    }

//    public Page<Device> findByUserToPage(Long userId, Pageable pageable) {
//        User user = userService.findById(userId);
//        return deviceDAO.findByUserToPage(user, pageable);
//    }
}
