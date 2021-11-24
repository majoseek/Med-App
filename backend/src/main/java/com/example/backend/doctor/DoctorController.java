package com.example.backend.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "/doctor")
@Transactional
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    ResponseEntity<?> getAllDoctors() {
        List<Doctor> doctorList = doctorService.getListOfDoctors();
        return ResponseEntity.ok(doctorList);
    }

    @GetMapping(path = "/allBySpec")
    @ResponseBody
    ResponseEntity<?> getDoctorsBySpecialization(@RequestParam String specialization) {
        List<Doctor> doctorList = doctorService.getAllDoctorsBySpecialization(specialization);
        return ResponseEntity.ok(doctorList);
    }

}
