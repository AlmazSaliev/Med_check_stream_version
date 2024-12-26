package dao.impl;

import dao.GenericDao;
import dao.HospitalDao;
import dao.PatientDao;
import models.Hospital;
import models.Patient;

import java.util.*;

public class PatientDaoImpl implements PatientDao, GenericDao<Patient> {
    private final HospitalDaoImpl hospitalDao = new HospitalDaoImpl();

    @Override
    public String add(Long hospitalId, Patient patient) {
        Hospital hospitalById = hospitalDao.findHospitalById(hospitalId);
        if (hospitalById.getPatients() == null) {
            hospitalById.setPatients(new ArrayList<>());
            hospitalById.getPatients().add(patient);
            return "Patient successfully added to hospital by id: " + hospitalId;
        } else if (hospitalById.getPatients() != null) {
            hospitalById.getPatients().add(patient);
            return "Patient successfully added to hospital by id: " + hospitalId;
        }
        return "Error on patient added to hospital by id: " + hospitalId;
    }

    @Override
    public void removeById(Long id) {
        hospitalDao.getAllHospital().forEach(x -> x.getPatients().removeIf(c -> c.getId().equals(id)));
        System.out.println("Patient by id: " + id + " successfully recovered!, and go out!");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        Patient patient1 = hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getPatients().stream())
                .map(x -> {
                    if (x.getId().equals(id)) {
                        x.setLastName(patient.getLastName());
                        x.setGender(patient.getGender());
                        x.setAge(patient.getAge());
                        x.setId(id);
                        x.setFirstName(patient.getFirstName());
                        return x;
                    }
                    return x;
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Patient by id: " + id + " error updated!"));
        if (patient1 != null) {
            return "Patient by id: " + id + " successfully updated!";
        }
        return "Patient by id: " + id + " error updated!";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        Hospital hospitalById = hospitalDao.findHospitalById(id);
        if (hospitalById.getPatients() == null) {
            hospitalById.setPatients(patients);
            return "Patients successfully added!, to hospital by id: " + id;
        } else if (hospitalById.getPatients() != null) {
            hospitalById.getPatients().addAll(patients);
            return "Patients successfully added!, to hospital by id: " + id;
        }
        return "Error on patients added!, to hospital by id: " + id;
    }

    @Override
    public Patient getPatientById(Long id) {
        return hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getPatients().stream())
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Patient by id: " + id + " not found!"));
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        Map<Integer, Patient> patientMap = new HashMap<>();
        hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getPatients().stream())
                .sorted((x, y) -> x.getAge() - y.getAge())
                .toList()
                .forEach(x -> patientMap.put(x.getAge(), x));
        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return hospitalDao.getAllHospital()
                .stream()
                .flatMap(x -> x.getPatients().stream())
                .sorted((x, y) -> {
                    if (ascOrDesc.equals("0")) {
                        return x.getAge() - y.getAge();
                    }
                    return y.getAge() - x.getAge();
                }).toList();
    }
}
