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
    @GetMapping("")
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) Long userId) {
        if(userId != null) return deviceService.findByUserToPage(userId, PageRequest.of(page, size, Sort.by(sortBy)));
        else return deviceService.getDevicesToPage(PageRequest.of(page, size, Sort.by(sortBy)));
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
