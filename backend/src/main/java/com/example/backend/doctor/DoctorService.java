package com.example.backend.doctor;

import com.example.backend.exceptions.InvalidRole;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.user.UserDataDto;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserService userService;

    @Autowired
    DoctorService(DoctorRepository doctorRepository, UserService userService) {
        this.doctorRepository = doctorRepository;
        this.userService = userService;
    }

    List<Doctor> getListOfDoctors() {
        return doctorRepository.findAllBy();
    }

    List<Doctor> getAllDoctorsBySpecialization(String specialization) {
        return doctorRepository.findAllBySpecialization(specialization);
    }

    List<Doctor> getAllDoctorsByNameOrSurname(String text) {
        return doctorRepository.findDoctorsByNames(text.toUpperCase());
    }

    public Doctor getDoctorById(Long id) throws UserNotFound {
        return doctorRepository.findById(id).orElseThrow(() -> new UserNotFound(String.format("User with id=%d does not exists", id)));
    }

    public UserDataDto.DoctorData editDoctorData(Long id, String specialization) throws UserNotFound, InvalidRole {
        final Doctor doctor = doctorRepository.findById(id).orElseThrow(
                ()-> new UserNotFound(String.format("User with id=%d does not exist", id))
        );
        doctor.setSpecialization(specialization);
        doctorRepository.save(doctor);
        return (UserDataDto.DoctorData) userService.getUserData(id);
    }
}
