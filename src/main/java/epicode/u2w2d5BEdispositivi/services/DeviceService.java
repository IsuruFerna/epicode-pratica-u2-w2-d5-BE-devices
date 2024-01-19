package epicode.u2w2d5BEdispositivi.services;

import epicode.u2w2d5BEdispositivi.entities.Device;
import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.exceptions.NotFoundException;
import epicode.u2w2d5BEdispositivi.payload.NewDeviceDTO;
import epicode.u2w2d5BEdispositivi.payload.NewDeviceResponse;
import epicode.u2w2d5BEdispositivi.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
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

//        if(body.user() != null) {
//            User user = userService.findById(body.user());
//            found.setUser(user);
//        }

        return deviceDAO.save(found);
    }
}
