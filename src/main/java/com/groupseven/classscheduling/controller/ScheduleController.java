package com.groupseven.classscheduling.controller;

import com.groupseven.classscheduling.model.Schedule;
import com.groupseven.classscheduling.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/schedules")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> save(@RequestBody Schedule schedule){
        return new ResponseEntity<>(scheduleService.save(schedule), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllScheduleByUsernameAndCourse(
            @RequestParam String instructorName,
            @RequestParam String course){
        return new ResponseEntity<>(scheduleService.getAllScheduleByUsernameAndCourse(instructorName, course), HttpStatus.OK);
    }

}
