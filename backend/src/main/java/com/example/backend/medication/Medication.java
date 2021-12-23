package com.example.backend.medication;

import com.example.backend.prescription.Prescription;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PAP_MEDICATION", schema = "Z14")
public class Medication {
    private Long medicationId;
    private String name;
    private String dosage;
    private Collection<Prescription> prescriptionsByMedicationId;

    @Basic
    @Column(name = "NAME", nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DOSAGE", nullable = false, length = 40)
    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Id
    @Column(name = "MEDICATION_ID", nullable = false)
    @SequenceGenerator(name = "MEDICATION", sequenceName = "MEDICATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICATION")
    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Objects.equals(medicationId, that.medicationId) && Objects.equals(name, that.name) && Objects.equals(dosage, that.dosage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.medicationId, this.name, this.dosage);
    }

    @ManyToMany(mappedBy = "medicationsByPrescriptId")
    Collection<Prescription> getPrescriptionsByMedicationId() {
        return prescriptionsByMedicationId;
    }

    public void setPrescriptionsByMedicationId(Collection<Prescription> prescriptionsByMedicationId) {
        this.prescriptionsByMedicationId = prescriptionsByMedicationId;
    }
}
