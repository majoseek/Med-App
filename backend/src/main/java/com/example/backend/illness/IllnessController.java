package com.example.backend.illness;

import com.example.backend.exceptions.IllnessNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/illnesses")
public class IllnessController {
    private final IllnessService service;

    @Autowired
    public IllnessController(IllnessService service) {this.service = service;}

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<?> getAllIllnesses() {
        List<Illness> illnesses = service.getAllIllnesses();
        return ResponseEntity.ok(illnesses);
    }

    @GetMapping("/{illnessName}")
    @ResponseBody
    public ResponseEntity<?> getIllnessByName(@PathVariable String illnessName) {
        List<Illness> illnesses = service.getIllnessByName(illnessName);
        return ResponseEntity.ok(illnesses);
    }

    @GetMapping("/{illnessId}")
    @ResponseBody
    public ResponseEntity<?> getIllnessById(@PathVariable Long illnessId) {
        try{
            Illness illness = service.getIllnessById(illnessId);
            return ResponseEntity.ok(illness);
        } catch (IllnessNotFound illnessNotFound) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(illnessNotFound.getLocalizedMessage());
        }
    }


}
