package com.example.backend.illness;

import com.example.backend.patient.Patient;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PAP_ILLNESS", schema = "Z14")
public class Illness {
    private Long id;
    private String name;
    private Collection<Patient> patientsByIllnessId;

    @Id
    @Column(name = "ILLNESS_ID", nullable = false)
    @SequenceGenerator(name = "ILLNESS", sequenceName = "ILLNESS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ILLNESS")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Illness that = (Illness) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToMany(mappedBy = "illnessesByUserId")
    public Collection<Patient> getPatientsByIllnessId() {
        return patientsByIllnessId;
    }

    public void setPatientsByIllnessId(Collection<Patient> patientsByIlnessId) {
        this.patientsByIllnessId = patientsByIlnessId;
    }
}
