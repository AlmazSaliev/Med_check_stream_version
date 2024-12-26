package dao.impl;

import dao.HospitalDao;
import db.DataBase;
import models.Hospital;
import models.Patient;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        DataBase.hospitals.add(hospital);
        return "Successfully added!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return getAllHospital()
                .stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Hospital by id: " + id + " not found!"));
    }

    @Override
    public List<Hospital> getAllHospital() {
        if (DataBase.hospitals.isEmpty()) throw new NullPointerException("Hospital is empty!");
        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        Hospital hospitalById = findHospitalById(id);
        if (hospitalById.getPatients() != null) return hospitalById.getPatients();
        throw new NullPointerException("Patient in hospital by id: " + id + " not found!");
    }

    @Override
    public String deleteHospitalById(Long id) {
        Hospital hospitalById = findHospitalById(id);
        boolean remove = getAllHospital().remove(hospitalById);
        if (remove) {
            return "Successfully deleted!, hospital by id: " + id;
        }
        throw new NoSuchElementException("Hospital by id: " + id + " not found!");
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Hospital hospital = getAllHospital()
                .stream()
                .filter(x -> x.getAddress().equals(address))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Hospital by address: " + address + " not found!"));
        return Map.of(address, hospital);
    }
}
