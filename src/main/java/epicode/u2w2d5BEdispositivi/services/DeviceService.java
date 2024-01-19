package epicode.u2w2d5BEdispositivi.services;

import epicode.u2w2d5BEdispositivi.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;
}
