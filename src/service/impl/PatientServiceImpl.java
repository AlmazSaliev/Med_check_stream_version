package service.impl;

import dao.impl.PatientDaoImpl;
import models.Patient;
import service.GenericService;
import service.PatientService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PatientServiceImpl implements PatientService, GenericService<Patient> {
    private final PatientDaoImpl patientDao = new PatientDaoImpl();

    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            return patientDao.add(hospitalId, patient);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on added!";
    }

    @Override
    public void removeById(Long id) {
        try {
            patientDao.removeById(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        try {
            return patientDao.updateById(id, patient);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on updated!";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            return patientDao.addPatientsToHospital(id, patients);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on added!";
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            return patientDao.getPatientById(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        try {
            return patientDao.getPatientByAge();
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return Map.of();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        try {
            return patientDao.sortPatientsByAge(ascOrDesc);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return List.of();
    }
}
