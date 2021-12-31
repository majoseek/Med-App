package com.example.backend.doctor;

import com.example.backend.exceptions.InvalidRole;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.user.UserDataDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @Autowired
    DoctorController(DoctorService doctorService, ModelMapper modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    private DoctorDto convertToDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorDto.class);
    }

    @GetMapping(path = "/all")
    @ResponseBody
    ResponseEntity<?> getAllDoctors() {
        List<DoctorDto> doctorList = doctorService.getListOfDoctors().stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(doctorList);
    }

    @GetMapping(path = "/allGetBySpec")
    @ResponseBody
    ResponseEntity<?> getDoctorsBySpecialization(@RequestParam String specialization) {
        List<DoctorDto> doctorList = doctorService.getAllDoctorsBySpecialization(specialization).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(doctorList);
    }

    @GetMapping(path = "/allGetByName")
    @ResponseBody
    ResponseEntity<?> getDoctorsByNames(@RequestParam String name) {
        List<DoctorDto> doctorList = doctorService.getAllDoctorsByNameOrSurname(name).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(doctorList);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        try {
            DoctorDto doctor = convertToDto(doctorService.getDoctorById(id));
            return ResponseEntity.ok(doctor);
        } catch (UserNotFound userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFound.getLocalizedMessage());
        }
    }

    @PutMapping(path="/update/{id}")
    @ResponseBody
    ResponseEntity<?> editSpecialization(@PathVariable Long id,
                                         @RequestParam String specialization) {
        try {
            final UserDataDto.DoctorData editDoctor = doctorService.editDoctorData(id, specialization);
            return ResponseEntity.ok(editDoctor);
        } catch (UserNotFound | InvalidRole userNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", userNotFound.getLocalizedMessage()));
        }
    }
}
