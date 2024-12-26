package dao.impl;

import dao.DepartmentDao;
import dao.GenericDao;
import models.Department;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    private final HospitalDaoImpl hospitalDao = new HospitalDaoImpl();

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return hospitalDao.findHospitalById(id)
                .getDepartments();
    }

    @Override
    public Department findDepartmentByName(String name) {
        return hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getDepartments().stream())
                .filter(x -> x.getDepartmentName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Department by name: " + name + " not found!"));
    }

    @Override
    public String add(Long hospitalId, Department department) {
        Hospital hospitalById = hospitalDao.findHospitalById(hospitalId);
        if (hospitalById.getDepartments() == null) {
            hospitalById.setDepartments(new ArrayList<>());
            hospitalById.getDepartments().add(department);
            return "Department successfully added to hospital by id: " + hospitalId;
        } else if (hospitalById.getDepartments() != null) {
            hospitalById.getDepartments().add(department);
            return "Department successfully added to hospital by id: " + hospitalId;
        }
        throw new NoSuchElementException("Hospital by id: " + hospitalId + " not found!");
    }

    @Override
    public void removeById(Long id) {
        hospitalDao.getAllHospital().forEach(x -> x.getDepartments().removeIf(c -> c.getId().equals(id)));
        System.out.println("Successfully deleted!, department by id: " + id);
    }

    @Override
    public String updateById(Long id, Department department) {
        Department department1 = hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getDepartments().stream())
                .map(x -> {
                    if (x.getId().equals(id)) {
                        x.setId(id);
                        x.setDepartmentName(department.getDepartmentName());
                        x.setDoctors(department.getDoctors());
                        return x;
                    }
                    return x;
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Not found!, department by id: " + id));
        if (department1 != null) {
            return "Successfully updated!, department by id: " + id;
        }
        return "Error on updated!, department by id: " + id;
    }

    public Department findDepartmentById(Long id) {
        return hospitalDao.getAllHospital()
                .stream()
                .flatMap(x ->
                        x.getDepartments()
                                .stream()
                                .filter(c -> c.getId().equals(id)))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Department by id: " + id + " not found!"));
    }
}
