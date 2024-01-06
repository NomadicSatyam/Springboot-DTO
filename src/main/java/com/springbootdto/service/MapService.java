package com.springbootdto.service;

import com.springbootdto.dto.LocationDTO;
import com.springbootdto.dto.UserLocationDTO;
import com.springbootdto.model.Location;
import com.springbootdto.model.User;
import com.springbootdto.repository.LocationRepository;
import com.springbootdto.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserLocationDTO getUserLocation(Long id) {
        return convertToUserLocationDTO(userRepository.findById(id).get());
    }


    public List<UserLocationDTO> getAllUsersLocation() {
        return ((List<User>) userRepository.findAll()).stream().map(this::convertToUserLocationDTO)
                .collect(Collectors.toList());
    }

    public List<UserLocationDTO> getAllUsersLocation_V2() {
        return ((List<User>) userRepository.findAll()).stream().map(this::convertToUserLocationDTO_V2)
                .collect(Collectors.toList());
    }

    private UserLocationDTO convertToUserLocationDTO(User user) {
        UserLocationDTO userLocationDTO = new UserLocationDTO();
        userLocationDTO.setUserId(user.getId());
        userLocationDTO.setUsername(user.getUserName());
        Location location = user.getLocation();
        userLocationDTO.setLat(location.getLat());
        userLocationDTO.setLng(location.getLng());
        userLocationDTO.setPlace(location.getPlace());
        return userLocationDTO;
    }

    private UserLocationDTO convertToUserLocationDTO_V2(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserLocationDTO userLocationDTO = modelMapper.map(user, UserLocationDTO.class);
        return userLocationDTO;
    }


    public void addLocation(LocationDTO locationDto) {
        Location location = new ModelMapper().map(locationDto, Location.class);
        locationRepository.save(location);
    }

    public List<LocationDTO> getAllLocations() {
        return ((List<Location>) locationRepository.findAll())
                .stream()
                .map(obj -> modelMapper.map(obj, LocationDTO.class))
                .collect(Collectors.toList());
    }


}
