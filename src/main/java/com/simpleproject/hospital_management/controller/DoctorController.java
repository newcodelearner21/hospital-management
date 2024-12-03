package com.simpleproject.hospital_management.controller;

import com.simpleproject.hospital_management.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
}

