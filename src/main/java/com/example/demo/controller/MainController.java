package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.exception.AlreadyException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/clinic")
public class MainController {
    @Autowired
    private PatientService patientService;

    @PostMapping("patient")
    @Operation(summary = "Создание пациента")
    public ResponseEntity<Object> createPatient(@RequestBody Patient patient) {
        try {
            Patient newPatient = patientService.createPatient(patient);
            return ResponseEntity.status(200).body(newPatient);
        } catch (AlreadyException already) {
            return ResponseEntity.status(400).body(already.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @DeleteMapping("patient")
    @Operation(summary = "Удаление пациента")
    public ResponseEntity<String> deletePatientById(@RequestParam Integer id) {
        try {
            patientService.deletePatientById(id);
            return ResponseEntity.status(200).body("Пациент с id: " + id + " успешно удален");
        } catch (NotFoundException notFound) {
            return ResponseEntity.status(404).body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("patient")
    @Operation(summary = "Получение всех пациентов")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("patient/{id}")
    @Operation(summary = "Получение информации о пациенте по id")
    public ResponseEntity<Object> getPatientById(@PathVariable Integer id) {
        try {
            Patient patient = patientService.getPatientById(id);
            return ResponseEntity.status(200).body(patient);
        } catch (NotFoundException notFound) {
            return ResponseEntity.status(404).body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("patient")
    @Operation(summary = "Обновление информации о пациенте по id")
    public ResponseEntity<String> updatePatientById(@RequestParam Integer id, @RequestBody Patient patient) {
        try {
            patientService.updatePatientById(id, patient);
            return ResponseEntity.status(200).body("Пациент с id: " + id + " успешно изменен");
        } catch (NotFoundException notFound) {
            return ResponseEntity.status(404).body(notFound.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
