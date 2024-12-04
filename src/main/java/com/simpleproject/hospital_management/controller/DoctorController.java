package com.simpleproject.hospital_management.controller;

import com.simpleproject.hospital_management.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/doctor/apis")

public class DoctorController {

    //we are using hashmap for now instead (Db)database operation (CRUD) we check that next project


    HashMap<Integer, Doctor> doctorHashMapDb = new  HashMap<>();

    //creating method saveDoctor

    @PostMapping ("/save")// we add annotation @PostMapping as we want to save/add data

    //@RequestBody is annotation which takes the input/ request from frontend/postman and assign its to the object
    public String saveDoctor(@RequestBody Doctor doctorRequest) {
        doctorHashMapDb.put(doctorRequest.getId(), doctorRequest);
        return " Doctor info saved successfully";

    }
        @GetMapping("/findAll")
                public HashMap<Integer,Doctor> getAllDoctor(){
            return doctorHashMapDb;
        }

        @GetMapping("/find/{doctorId}")
    public Doctor getDoctorById(@PathVariable("doctorId") int doctorId){
        Doctor doctor = doctorHashMapDb.get(doctorId);
        return doctor;
        }

        //Get doctor by name
    @GetMapping("/findByName")
    //@RequestParam: takes the input in form of a request parameter as a query
    public Doctor getDoctorByName(@RequestParam("doctorname") String doctorName){
        for(Doctor doctor : doctorHashMapDb.values()) {
            if (doctor.getName().equals(doctorName)) {
                return doctor;
            }
        }
    return null;

    }
    //Get doctor by Specialization
    //We use List as there can be more than one doctor with same specialization
    @GetMapping("/findBySpecialization")
    public List<Doctor> getDoctorBySpecialization(@RequestParam String specialization){
        List<Doctor> doctorList = new ArrayList<>();
        for(Doctor doctor: doctorHashMapDb.values()){
            if(doctor.getSpecialization().equalsIgnoreCase(specialization)){
               doctorList.add(doctor);
            }
        }
        return null;
    }

    // Get Doctor by 2 ways
    // Using doctor list NOTE: List can be used only when results are not unique
    @GetMapping("/findByAgeAndSpecialization")
    public List<Doctor> getDoctorByAgeAndSpecialization(@RequestParam int age,@RequestParam String specialization){
        List<Doctor> doctorList = new ArrayList<>();
        for(Doctor doctor: doctorHashMapDb.values()){
            if(doctor.getAge()==age && doctor.getSpecialization().equals(specialization)){
                doctorList.add(doctor);
            }
        }
        return doctorList;
    }

    //Getting doctor by ID or Specialization without taking fields
    @GetMapping("/findByAgeOrSpecializationOptional")
    public List<Doctor> getDoctorByIdOrSpecializationOptional(@RequestParam (required = false) Integer age,@RequestParam (required = false)String specialization){
        List<Doctor> doctorList = new ArrayList<>();
        for(Doctor doctor: doctorHashMapDb.values()){
            if(age!=null && doctor.getAge()==age){
                doctorList.add(doctor);
            } else if ( specialization != null  && doctor.getSpecialization().equals(specialization)) {
                doctorList.add(doctor);
            }

        }
        return doctorList;
    }

    //deleting or removing doctor
    @DeleteMapping("/delete/{id}")
    public String deleteDoctorById(@PathVariable int id){
        doctorHashMapDb.remove(id);
        return "Doctor with id: "+ id + " is deleted successfully";
    }

    //updating doctor
    @PutMapping("/update/{id}")
    public  String updateDoctor(@PathVariable int id, @RequestBody Doctor doctorRequest){
        //Check if doctor is present if present we update and if not we do not
        Doctor doctor = doctorHashMapDb.get(id);
        if(doctor!= null){
            doctorHashMapDb.put(id,doctorRequest);
            return "doctor updated successfully";
        }else{
            return "doctor not found with given id:" + id;
        }
    }
}

