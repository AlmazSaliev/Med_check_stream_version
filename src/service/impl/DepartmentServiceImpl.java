package service.impl;

import dao.impl.DepartmentDaoImpl;
import models.Department;
import service.DepartmentService;
import service.GenericService;

import java.util.List;
import java.util.NoSuchElementException;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    private final DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        try {
            return departmentDao.getAllDepartmentByHospital(id);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return List.of();
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            return departmentDao.findDepartmentByName(name);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        try {
            return departmentDao.add(hospitalId, department);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on added!";
    }

    @Override
    public void removeById(Long id) {
        try {
            departmentDao.removeById(id);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        try {
            return departmentDao.updateById(id, department);
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return "Error on updated!";
    }
}
