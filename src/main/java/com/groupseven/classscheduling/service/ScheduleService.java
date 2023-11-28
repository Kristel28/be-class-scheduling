package com.groupseven.classscheduling.service;

import com.groupseven.classscheduling.model.Schedule;
import com.groupseven.classscheduling.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllScheduleByUsernameAndCourse(String instructorName, String course){
        return scheduleRepository.findByInstructorNameAndCourse(instructorName, course);
    }


}
