package com.example.backend.doctor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/doctor")
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

    @GetMapping(path = "/allBySpec")
    @ResponseBody
    ResponseEntity<?> getDoctorsBySpecialization(@RequestParam String specialization) {
        List<DoctorDto> doctorList = doctorService.getAllDoctorsBySpecialization(specialization).stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(doctorList);
    }

}
