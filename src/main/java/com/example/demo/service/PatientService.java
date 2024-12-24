package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.exception.AlreadyException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) throws AlreadyException {
        if (patientRepository.existsById(patient.getId())) {
            throw new AlreadyException("Пациент с id: " + patient.getId() + " уже существует");
        }
        return patientRepository.save(patient);
    }

    public void deletePatientById(Integer id) throws NotFoundException {

        if (!patientRepository.existsById(id)) {
            throw new NotFoundException("Пациент с id: " + id + " не был найден");
        }

        patientRepository.deleteById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) throws NotFoundException {
        Patient result = null;
        try {
            Optional<Patient> patient = patientRepository.findById(id);
            if (patient.isPresent()) {
                result = patient.get();
            } else {
                throw new NotFoundException("Пациент с id: " + id + " не был найден");
            }
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    public void updatePatientById(Integer id, Patient updatePatient) throws NotFoundException {
        try {
            Optional<Patient> patient = patientRepository.findById(id);
            if (patient.isPresent()) {
                Patient existingPatient = patient.get();

//                existingPatient.setId(id);
                existingPatient.setName(updatePatient.getName());
                existingPatient.setSurname(updatePatient.getSurname());
                existingPatient.setPolicy(updatePatient.getPolicy());
                existingPatient.setDiagnosis(updatePatient.getDiagnosis());
                existingPatient.setPlaceResidence(updatePatient.getPlaceResidence());

                patientRepository.save(existingPatient);
            } else {
                throw new NotFoundException("Пациент с id: " + id + " не был найден");
            }
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
