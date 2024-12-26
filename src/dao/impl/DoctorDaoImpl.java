package dao.impl;

import dao.DoctorDao;
import dao.GenericDao;
import models.Department;
import models.Doctor;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DoctorDaoImpl implements DoctorDao, GenericDao<Doctor> {
    private final HospitalDaoImpl hospitalDao = new HospitalDaoImpl();
    private final DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalDao.findHospitalById(hospitalId);
        if (hospital.getDoctors() == null) {
            hospital.setDoctors(new ArrayList<>());
            hospital.getDoctors().add(doctor);
            return "Doctor successfully added to hospital by id: " + hospitalId;
        } else if (hospital.getDoctors() != null) {
            hospital.getDoctors().add(doctor);
            return "Doctor successfully added to hospital by id: " + hospitalId;
        }
        throw new NoSuchElementException("Hospital by id: " + hospitalId + " not found!");
    }

    @Override
    public void removeById(Long id) {
        hospitalDao.getAllHospital().forEach(x -> x.getDoctors().removeIf(c -> c.getId().equals(id)));
        System.out.println("Successfully deleted!");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        Doctor doctor1 = hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getDoctors().stream())
                .map(x -> {
                    if (x.getId().equals(id)) {
                        x.setGender(doctor.getGender());
                        x.setExperienceYear(doctor.getExperienceYear());
                        x.setLastName(doctor.getLastName());
                        x.setFirstName(doctor.getFirstName());
                        return x;
                    }
                    return x;
                }).findFirst()
                .orElse(null);
        if (doctor1 != null) {
            return "Doctor by id: " + id + " successfully updated!";
        }
        throw new NoSuchElementException("Doctor by id: " + id + " not found!");
    }


    @Override
    public Doctor findDoctorById(Long id) {
        return hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getDoctors().stream())
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Doctor by id: " + id + " not found!"));
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        Department departmentById = departmentDao.findDepartmentById(departmentId);
        List<Doctor> doctors = new ArrayList<>();
        doctorsId.forEach(x -> doctors.add(findDoctorById(x)));
        if (departmentById.getDoctors() == null) {
            departmentById.setDoctors(doctors);
            return "Doctors successfully assigned to department by id: " + departmentId;
        } else if (departmentById.getDoctors() != null) {
            departmentById.getDoctors().addAll(doctors);
            return "Doctors successfully assigned to department by id: " + departmentId;
        }
        return "Error on assigned!";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        Hospital hospitalById = hospitalDao.findHospitalById(id);
        if (hospitalById.getDoctors() == null)
            throw new NullPointerException("Doctors not found in hospital by id: " + id);
        return hospitalById.getDoctors();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        Department departmentById = departmentDao.findDepartmentById(id);
        if (departmentById.getDoctors() == null)
            throw new NullPointerException("Doctors not found in department by id: " + id);
        return departmentById.getDoctors();
    }
}
