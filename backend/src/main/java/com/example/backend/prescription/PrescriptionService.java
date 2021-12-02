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

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private PrescriptionRepository repository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    @Autowired
    PrescriptionService(PrescriptionRepository repository,
                        PatientRepository patientRepository,
                        DoctorRepository doctorRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Prescription> getPatientPrescription (Long patientId) {
        return repository.findAllByPatientByPatientUserId(patientId);
    }

    public List<Prescription> getDoctorPrescription(Long doctorId) {
        return repository.findAllByDoctorByDoctorUserId(doctorId);
    }

    public Prescription getPrescriptionById(Long prescriptionId) throws PrescriptionNotFound {
        return repository.findById(prescriptionId).orElseThrow(()-> new PrescriptionNotFound("Could not find prescription " + prescriptionId));
    }

    public Prescription save(Prescription newPrescription) {
        return repository.save(newPrescription);
    }

    public void delete(Long prescriptionId){
        if(repository.existsById(prescriptionId)) {
            repository.deleteById(prescriptionId);
        }
    }


    public Prescription createPrescription(String medicationName, Long amount, Doctor doctor, Patient patient) {
        final Prescription newPrescription = new Prescription();
        newPrescription.setMedicationName(medicationName);
        newPrescription.setAmount(amount);
        newPrescription.setDoctorByDoctorUserId(doctor);
        newPrescription.setPatientByPatientUserId(patient);
        repository.save(newPrescription);
        return newPrescription;
    }

    public Prescription createByPatientPesel(String pesel, String medicationName, Long amount, Long doctorId)
            throws UserNotFound {
        final Prescription newPrescription = new Prescription();
        final Optional<Patient> newPatient = patientRepository.findPatientByPesel(pesel);
        if (newPatient.isEmpty())
            throw new UserNotFound(String.format("Patient with pesel %s doesn't exist", pesel));
        final Optional<Doctor> newDoctor = doctorRepository.findById(doctorId);
        if (newDoctor.isEmpty())
            throw new UserNotFound(String.format("Doctor with id %d doesn't exist", doctorId));

        newPrescription.setPatientByPatientUserId(newPatient.get());
        newPrescription.setDoctorByDoctorUserId(newDoctor.get());
        newPrescription.setAmount(amount);
        newPrescription.setMedicationName(medicationName);
        return newPrescription;
    }
}
