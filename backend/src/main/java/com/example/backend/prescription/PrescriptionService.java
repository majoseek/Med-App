package com.example.backend.prescription;

import com.example.backend.doctor.Doctor;
import com.example.backend.doctor.DoctorRepository;
import com.example.backend.exceptions.PrescriptionNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientRepository;
import com.example.backend.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository repository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    PrescriptionService(PrescriptionRepository repository,
                        PatientRepository patientRepository,
                        DoctorRepository doctorRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Prescription> getPatientPrescription(Long patientId) {
        return repository.findAllByPatientId(patientId);
    }

    public List<Prescription> getDoctorPrescription(Long doctorId) {
        return repository.findAllByDoctorByDoctorUserId(doctorId);
    }

    public Prescription getPrescriptionById(Long prescriptionId) throws PrescriptionNotFound {
        return repository.findById(prescriptionId).orElseThrow(() -> new PrescriptionNotFound("Could not find prescription " + prescriptionId));
    }

    public Prescription save(Prescription newPrescription) {
        return repository.save(newPrescription);
    }

    public void delete(Long prescriptionId) {
        if (repository.existsById(prescriptionId)) {
            repository.deleteById(prescriptionId);
        }
    }


    public Prescription createPrescription(CreatePrescriptionDto prescriptionDto) throws UserNotFound {
        final Prescription newPrescription = new Prescription();
//        newPrescription.setMedicationName(prescriptionDto.getMedicationName()); #### Replace to setMedication
        newPrescription.setAmount(prescriptionDto.getAmount());
        newPrescription.setDoctorByDoctorUserId(doctorRepository.findById(prescriptionDto.getDoctorId()).orElseThrow(() -> new UserNotFound("Doctor with this id was not found")));
        newPrescription.setPatientByPatientUserId(patientRepository.findById(prescriptionDto.getPatientId()).orElseThrow(() -> new UserNotFound("Patient with this id was not found")));
        repository.save(newPrescription);
        return newPrescription;
    }

    public Prescription createByPatientPesel(CreateByPeselPrescriptionDto prescriptionDto)
            throws UserNotFound {
        final Prescription newPrescription = new Prescription();
        final Patient newPatient = patientRepository.findPatientByPesel(prescriptionDto.getPesel())
                .orElseThrow(() -> new UserNotFound(String.format("Patient with pesel %s doesn't exist", prescriptionDto.getPesel())));
        final Doctor newDoctor = doctorRepository.findById(prescriptionDto.getDoctorId())
                .orElseThrow(() -> new UserNotFound(String.format("Doctor with id %d doesn't exist", prescriptionDto.getDoctorId())));

        newPrescription.setPatientByPatientUserId(newPatient);
        newPrescription.setDoctorByDoctorUserId(newDoctor);
        newPrescription.setAmount(prescriptionDto.getAmount());
//        newPrescription.setMedicationName(prescriptionDto.getMedicationName()); #### Replace to setMedication
        repository.save(newPrescription);
        return newPrescription;
    }
}
