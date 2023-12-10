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
        return scheduleRepository.insert(schedule);
    }

    public List<Schedule> getAllScheduleByUsernameAndCourse(String instructorName, String course){
        return scheduleRepository.findByInstructorNameAndCourse(instructorName, course);
    }

    public boolean isScheduleConflict(Schedule newSchedule) {
        List<Schedule> existingSchedules = scheduleRepository.findByInstructorNameAndYearSectionAndCourse(
                newSchedule.getInstructorName(),
                newSchedule.getYearSection(),
                newSchedule.getCourse()
        );

        // Check for conflicts with existing schedules
        for (Schedule existingSchedule : existingSchedules) {
            if (isTimeConflict(newSchedule, existingSchedule)) {
                return true; // Conflict found
            }
        }

        return false; // No conflicts
    }

    private boolean isTimeConflict(Schedule newSchedule, Schedule existingSchedule) {
        // Check if the new schedule's start time is before the existing schedule's end time
        // and the new schedule's end time is after the existing schedule's start time
        return newSchedule.getStartTime().isBefore(existingSchedule.getEndTime()) &&
                newSchedule.getEndTime().isAfter(existingSchedule.getStartTime());
    }


}
