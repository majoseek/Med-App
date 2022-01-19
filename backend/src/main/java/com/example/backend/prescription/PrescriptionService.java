package com.example.backend.prescription;

import com.example.backend.doctor.Doctor;
import com.example.backend.doctor.DoctorRepository;
import com.example.backend.exceptions.MedicationNotFound;
import com.example.backend.exceptions.PrescriptionNotFound;
import com.example.backend.exceptions.UserNotFound;
import com.example.backend.medication.Medication;
import com.example.backend.medication.MedicationRepository;
import com.example.backend.patient.Patient;
import com.example.backend.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository repository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MedicationRepository medicationRepository;

    @Autowired
    PrescriptionService(PrescriptionRepository repository,
                        PatientRepository patientRepository,
                        DoctorRepository doctorRepository,
                        MedicationRepository medicationRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.medicationRepository = medicationRepository;
    }

    public List<Prescription> getPatientPrescription(Long patientId) {
        return repository.findAllByPatientId(patientId);
    }

    public List<Prescription> getDoctorPrescription(Long doctorId) throws UserNotFound {
        return repository.findAllByDoctorByDoctorUserId(doctorId);
    }

    public List<Prescription> getUserPrescriptions(Long userId) {
        return repository.findAllByUserId(userId);
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

    private List<Medication> getMedications(List<Long> ids) throws MedicationNotFound {
        List<Medication> meds = new java.util.ArrayList<>();
        for (Long id : ids) {
            meds.add(medicationRepository.findById(id).orElseThrow(() -> new MedicationNotFound(String.format("Medication with id: %d not found", id))));
        }
        return meds;
    }

    public Prescription createPrescription(CreatePrescriptionDto prescriptionDto, Long doctorId) throws UserNotFound, MedicationNotFound {
        final Prescription newPrescription = new Prescription();
        newPrescription.setMedicationsByPrescriptId(getMedications(prescriptionDto.getMedications()));
        newPrescription.setAmount(prescriptionDto.getAmount());
        newPrescription.setDoctorByDoctorUserId(doctorRepository.findById(doctorId).orElseThrow(() -> new UserNotFound("Doctor with this id was not found")));
        newPrescription.setPatientByPatientUserId(patientRepository.findById(prescriptionDto.getPatientId()).orElseThrow(() -> new UserNotFound("Patient with this id was not found")));
        repository.save(newPrescription);
        return newPrescription;
    }

//    public Prescription createByPatientPesel(CreateByPeselPrescriptionDto prescriptionDto)
//            throws UserNotFound, MedicationNotFound {
//        final Prescription newPrescription = new Prescription();
//        final Patient newPatient = patientRepository.findPatientByPesel(prescriptionDto.getPesel())
//                .orElseThrow(() -> new UserNotFound(String.format("Patient with pesel %s doesn't exist", prescriptionDto.getPesel())));
//        final Doctor newDoctor = doctorRepository.findById(prescriptionDto.getDoctorId())
//                .orElseThrow(() -> new UserNotFound(String.format("Doctor with id %d doesn't exist", prescriptionDto.getDoctorId())));
//        final List<Medication> medications = getMedications(prescriptionDto.getMedications());
//        newPrescription.setPatientByPatientUserId(newPatient);
//        newPrescription.setDoctorByDoctorUserId(newDoctor);
//        newPrescription.setAmount(prescriptionDto.getAmount());
//        newPrescription.setMedicationsByPrescriptId(medications);
//        repository.save(newPrescription);
//        return newPrescription;
//    }
}
