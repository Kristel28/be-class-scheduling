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
@CrossOrigin(origins = "*")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping()
    public ResponseEntity<String> addSchedule(@RequestBody Schedule newSchedule) {
        if (scheduleService.isScheduleConflict(newSchedule)) {
            return ResponseEntity.badRequest().body("Schedule conflict detected");
        }

        // Save the schedule since there are no conflicts
        scheduleService.save(newSchedule);
        return ResponseEntity.ok("Schedule saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllScheduleByUsernameAndCourse(
            @RequestParam String instructorName,
            @RequestParam String course){
        return new ResponseEntity<>(scheduleService.getAllScheduleByUsernameAndCourse(instructorName, course), HttpStatus.OK);
    }

}
