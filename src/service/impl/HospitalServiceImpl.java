package service.impl;

import dao.impl.HospitalDaoImpl;
import models.Hospital;
import models.Patient;
import service.HospitalService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HospitalServiceImpl implements HospitalService {
    private final HospitalDaoImpl hospitalDao = new HospitalDaoImpl();

    @Override
    public String addHospital(Hospital hospital) {
        return hospitalDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            return hospitalDao.findHospitalById(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        try {
            return hospitalDao.getAllHospital();
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
            return hospitalDao.getAllPatientFromHospital(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            return hospitalDao.deleteHospitalById(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on deleted!";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            return hospitalDao.getAllHospitalByAddress(address);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return Map.of();
    }
}
