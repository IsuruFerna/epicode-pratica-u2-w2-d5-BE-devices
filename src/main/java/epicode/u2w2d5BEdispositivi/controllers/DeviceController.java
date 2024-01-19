package epicode.u2w2d5BEdispositivi.controllers;

import epicode.u2w2d5BEdispositivi.entities.Device;
import epicode.u2w2d5BEdispositivi.exceptions.BadRequestException;
import epicode.u2w2d5BEdispositivi.payload.NewDeviceDTO;
import epicode.u2w2d5BEdispositivi.payload.NewDeviceResponse;
import epicode.u2w2d5BEdispositivi.repositories.DeviceDAO;
import epicode.u2w2d5BEdispositivi.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

//    get: /devices
// get: /device?userId=2

// get: /devices?availability=1
    // availability is an in between 0 and 3
    // 0: Available, 1: In Maintain, 2: In use, 3: Removed

// get: /devices?deviceType=computer
    // types can be various like(mobile, laptop,...) can not be definitive
    @GetMapping("")
    public List<Device> getDevices(
            @RequestParam(required = false) String deviceType,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer availability) {
        if(userId != null && deviceType == null && availability == null) {
            return deviceService.findByUser(userId);
        } else if (userId == null && deviceType != null && availability == null) {
            return deviceService.findByType(deviceType);
        } else if (userId == null && deviceType == null && availability != null) {
            return deviceService.findByAvailability(availability);
        } else {
            return deviceService.getDevices();
        }
    }

//    post: /devices
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewDeviceResponse saveDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation) {
        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Device device = deviceService.save(body);
        return new NewDeviceResponse(device.getId());
    }

//    get: /devices/id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Device findById(@PathVariable Long id){
        return deviceService.findById(id);
    }

//    put: /devices/id
    @PutMapping("/{id}")
    public Device findByIdAndUpdate(@PathVariable Long id, @RequestBody NewDeviceDTO body) {
        return deviceService.findByIdAndUpdate(id, body);
    }

//    detele: /devices/id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long id) {
        deviceService.findByIdAndDelete(id);
    }
}
