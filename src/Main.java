import config.CheckValue;
import enums.Gender;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import service.impl.DepartmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.HospitalServiceImpl;
import service.impl.PatientServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String strPattern = "^[a-zA-Z\\s]+";
    static String numPattern = "\\d+";

    public static void main(String[] args) {
        HospitalServiceImpl hospitalService = new HospitalServiceImpl();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        DoctorServiceImpl doctorService = new DoctorServiceImpl();
        PatientServiceImpl patientService = new PatientServiceImpl();
        while (true) {
            System.out.println("""
                    1. Hospital options
                    2. Department options
                    3. Doctor options
                    4. Patient options
                    5. Exit
                    """);
            System.out.print("Choice by under number: ");
            switch (CheckValue.checkValue("choice", numPattern)) {
                case "1" -> {
                    boolean loop = true;
                    while (loop) {
                        System.out.println("""
                                1. Add new hospital
                                2. Find hospital by id
                                3. Get all hospital by address
                                4. Get all hospital
                                5. Delete hospital by id
                                6. Get all patients from hospital by id
                                7. Exit
                                """);
                        switch (CheckValue.checkValue("choice", numPattern)) {
                            case "1" -> {
                                System.out.println(hospitalService.addHospital(addNewHospital()));
                            }
                            case "2" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(hospitalService.findHospitalById(Long.parseLong(hospitalId)));
                            }
                            case "3" -> {
                                String address = CheckValue.checkValue("address", strPattern);
                                System.out.println(hospitalService.getAllHospitalByAddress(address));
                            }
                            case "4" -> {
                                System.out.println(hospitalService.getAllHospital());
                            }
                            case "5" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(hospitalService.deleteHospitalById(Long.parseLong(hospitalId)));
                            }
                            case "6" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(hospitalService.getAllPatientFromHospital(Long.parseLong(hospitalId)));
                            }
                            case "7" -> {
                                loop = false;
                            }
                            default -> {
                                System.out.println("Invalid value!");
                            }
                        }
                    }
                }
                case "2" -> {
                    boolean loop = true;
                    while (loop) {
                        System.out.println("""
                                1. Add new department to hospital by id
                                2. Find department by name
                                3. Get all departments in hospital by id
                                4. Update department by id
                                5. Delete department by id
                                6. Exit
                                """);
                        switch (CheckValue.checkValue("choice", numPattern)) {
                            case "1" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(departmentService.add(Long.parseLong(hospitalId), addNewDepartment()));
                            }
                            case "2" -> {
                                String departmentName = CheckValue.checkValue("department name", strPattern);
                                System.out.println(departmentService.findDepartmentByName(departmentName));
                            }
                            case "3" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(departmentService.getAllDepartmentByHospital(Long.parseLong(hospitalId)));
                            }
                            case "4" -> {
                                String departmentId = CheckValue.checkValue("department Id", numPattern);
                                System.out.println(departmentService.updateById(Long.parseLong(departmentId), addNewDepartment()));
                            }
                            case "5" -> {
                                String departmentId = CheckValue.checkValue("department Id", numPattern);
                                departmentService.removeById(Long.parseLong(departmentId));
                            }
                            case "6" -> {
                                loop = false;
                            }
                            default -> {
                                System.out.println("Invalid value!");
                            }
                        }
                    }
                }
                case "3" -> {
                    boolean loop = true;
                    while (loop) {
                        System.out.println("""
                                1. Add new doctor to hospital by id
                                2. Find doctor by id
                                3. Get all doctors in hospital by id
                                4. Get all doctors in department by id
                                5. Assign doctors to department
                                6. Update doctor by id
                                7. Delete doctor by id
                                8. Exit
                                """);
                        switch (CheckValue.checkValue("choice", numPattern)) {
                            case "1" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(doctorService.add(Long.parseLong(hospitalId), addNewDoctor()));
                            }
                            case "2" -> {
                                String doctorId = CheckValue.checkValue("doctor Id", numPattern);
                                System.out.println(doctorService.findDoctorById(Long.parseLong(doctorId)));
                            }
                            case "3" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(doctorService.getAllDoctorsByHospitalId(Long.parseLong(hospitalId)));
                            }
                            case "4" -> {
                                String departmentId = CheckValue.checkValue("department Id", numPattern);
                                System.out.println(doctorService.getAllDoctorsByDepartmentId(Long.parseLong(departmentId)));
                            }
                            case "5" -> {
                                String departmentId = CheckValue.checkValue("department Id", numPattern);
                                System.out.println(doctorService.assignDoctorToDepartment(Long.parseLong(departmentId), getDoctorsId()));
                            }
                            case "6" -> {
                                String doctorId = CheckValue.checkValue("doctor Id", numPattern);
                                System.out.println(doctorService.updateById(Long.parseLong(doctorId), addNewDoctor()));
                            }
                            case "7" -> {
                                String doctorId = CheckValue.checkValue("doctor Id", numPattern);
                                doctorService.removeById(Long.parseLong(doctorId));
                            }
                            case "8" -> {
                                loop = false;
                            }
                            default -> {
                                System.out.println("Invalid value!");
                            }
                        }
                    }
                }
                case "4" -> {
                    boolean loop = true;
                    while (loop) {
                        System.out.println("""
                                1. Add new patient to hospital by id
                                2. Add more patients to hospital by id
                                3. Get patient by id
                                4. Get patients by age
                                5. Update patient by id
                                6. Delete patient by id
                                7. Sort patient by age
                                8. Exit
                                """);
                        switch (CheckValue.checkValue("choice", numPattern)) {
                            case "1" -> {
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(patientService.add(Long.parseLong(hospitalId), addNewPatient()));
                            }
                            case "2" -> {
                                List<Patient> patients = new ArrayList<>();
                                boolean loopInner = true;
                                while (loopInner) {
                                    System.out.println("""
                                            Add more patient:
                                            1. Add
                                            2. Done
                                            """);
                                    switch (CheckValue.checkValue("choice", numPattern)) {
                                        case "1" -> {
                                            patients.add(addNewPatient());
                                        }
                                        case "2" -> {
                                            loopInner = false;
                                        }
                                    }
                                }
                                String hospitalId = CheckValue.checkValue("hospital Id", numPattern);
                                System.out.println(patientService.addPatientsToHospital(Long.parseLong(hospitalId), patients));
                            }
                            case "3" -> {
                                String patientId = CheckValue.checkValue("patient Id", numPattern);
                                System.out.println(patientService.getPatientById(Long.parseLong(patientId)));
                            }
                            case "4" -> {
                                System.out.println(patientService.getPatientByAge());
                            }
                            case "5" -> {
                                String patientId = CheckValue.checkValue("patient Id", numPattern);
                                System.out.println(patientService.updateById(Long.parseLong(patientId), addNewPatient()));
                            }
                            case "6" -> {
                                String patientId = CheckValue.checkValue("patient Id", numPattern);
                                patientService.removeById(Long.parseLong(patientId));
                            }
                            case "7" -> {
                                System.out.println("""
                                        Sort patients by age:
                                        1. ->
                                        2. <-
                                        """);
                                switch (CheckValue.checkValue("choice", numPattern)) {
                                    case "1" -> {
                                        System.out.println(patientService.sortPatientsByAge("0"));
                                    }
                                    case "2" -> {
                                        System.out.println(patientService.sortPatientsByAge("1"));
                                    }
                                }
                            }
                            case "8" -> {
                                loop = false;
                            }
                            default -> {
                                System.out.println("Invalid value!");
                            }
                        }
                    }
                }
                case "5" -> {
                    return;
                }
                default -> {
                    System.out.println("Invalid value!");
                }
            }
        }
    }

    public static Hospital addNewHospital() {
        Hospital hospital = new Hospital();
        String hospitalName = CheckValue.checkValue("hospital name", strPattern);
        String hospitalAddress = CheckValue.checkValue("hospital address", strPattern);
        hospital.setHospitalName(hospitalName);
        hospital.setAddress(hospitalAddress);
        return hospital;
    }

    public static Department addNewDepartment() {
        Department department = new Department();
        String departmentName = CheckValue.checkValue("department name", strPattern);
        department.setDepartmentName(departmentName);
        return department;
    }

    public static Doctor addNewDoctor() {
        Doctor doctor = new Doctor();
        String firstName = CheckValue.checkValue("first name", strPattern);
        String lastName = CheckValue.checkValue("last name", strPattern);
        Gender gender = addGender();
        String experienceYear = CheckValue.checkValue("experience year", numPattern);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setGender(gender);
        doctor.setExperienceYear(Integer.parseInt(experienceYear));
        return doctor;
    }

    public static Gender addGender() {
        System.out.println("Please add your gender: ");
        for (int i = 0; i < Gender.values().length; i++) {
            System.out.println((i + 1) + ". " + Gender.values()[i].toString().toLowerCase());
        }
        String choiceGender = CheckValue.checkValue("choice gender", numPattern);
        Gender gender = null;
        if (Integer.parseInt(choiceGender) <= Gender.values().length) {
            gender = Gender.values()[Integer.parseInt(choiceGender) - 1];
        } else {
            System.out.println("Invalid choice!");
        }
        if (gender != null) return gender;
        return addGender();
    }

    public static List<Long> getDoctorsId() {
        List<Long> idDoctors = new ArrayList<>();
        boolean loop = true;
        while (loop) {
            System.out.println("""
                    Add doctors by id to department
                    1. Write doctor ID
                    2. Done
                    """);
            switch (CheckValue.checkValue("choice", numPattern)) {
                case "1" -> {
                    System.out.println("Write the doctor's id with space or one by one");
                    String ides = new Scanner(System.in).nextLine();
                    if (ides.length() > 1) {
                        String[] id = ides.split(" ");
                        for (String s : id) {
                            idDoctors.add(Long.parseLong(s));
                        }
                    } else {
                        idDoctors.add(Long.parseLong(ides));
                    }
                }
                case "2" -> {
                    loop = false;
                }
                default -> {
                    System.out.println("Invalid value!");
                }
            }
        }
        return idDoctors;
    }

    public static Patient addNewPatient() {
        Patient patient = new Patient();
        String firstName = CheckValue.checkValue("first name", strPattern);
        String lastName = CheckValue.checkValue("last name", strPattern);
        String age = CheckValue.checkValue("age", numPattern);
        Gender gender = addGender();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(Integer.parseInt(age));
        patient.setGender(gender);
        return patient;
    }
}