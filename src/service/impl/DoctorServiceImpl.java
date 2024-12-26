package service.impl;

import dao.impl.DoctorDaoImpl;
import models.Doctor;
import service.DoctorService;
import service.GenericService;

import java.util.List;
import java.util.NoSuchElementException;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {
    private final DoctorDaoImpl doctorDao = new DoctorDaoImpl();

    @Override
    public Doctor findDoctorById(Long id) {
        try {
            return doctorDao.findDoctorById(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        try {
            return doctorDao.assignDoctorToDepartment(departmentId, doctorsId);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on assigned!";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            return doctorDao.getAllDoctorsByHospitalId(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
            return doctorDao.getAllDoctorsByDepartmentId(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        try {
            return doctorDao.add(hospitalId, doctor);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on added!";
    }

    @Override
    public void removeById(Long id) {
        try {
            doctorDao.removeById(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        try {
           return doctorDao.updateById(id, doctor);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on updated!";
    }
}
