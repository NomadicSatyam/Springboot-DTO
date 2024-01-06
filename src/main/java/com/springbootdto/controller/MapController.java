package com.springbootdto.controller;

import com.springbootdto.dto.LocationDTO;
import com.springbootdto.dto.UserLocationDTO;
import com.springbootdto.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/map")
public class MapController {
    @Autowired
    private MapService mapService;

    @GetMapping
    @ResponseBody
    public List<UserLocationDTO> getAllUsersLocation() {
        List <UserLocationDTO> usersLocation = mapService.getAllUsersLocation();
        return usersLocation;
    }


    @GetMapping("/v2")
    @ResponseBody
    public List<UserLocationDTO> getAllUsersLocation_Mapper() {
        List <UserLocationDTO> usersLocation = mapService.getAllUsersLocation_V2();
        return usersLocation;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public UserLocationDTO getUserLocation(@PathVariable("id") Long id) {
        return mapService.getUserLocation(id);
    }

    @PostMapping(value = "/addLocation")
    @ResponseStatus(HttpStatus.OK)
    public void addLocation(@RequestBody LocationDTO locationDTO) {
        mapService.addLocation(locationDTO);
    }

    @GetMapping(value = "/allLocations")
    @ResponseBody
    public List<LocationDTO> getAllLocations() {
        List <LocationDTO> locations = mapService.getAllLocations();
        return locations;
    }

}
