package com.groupseven.classscheduling.service;

import com.groupseven.classscheduling.model.Schedule;
import com.groupseven.classscheduling.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule){
        return scheduleRepository.insert(schedule);
    }

    public List<Schedule> getAllScheduleByUsernameAndCourse(String instructorName, String course){
        return scheduleRepository.findByInstructorNameAndCourse(instructorName, course);
    }

    public boolean isScheduleConflict(Schedule newSchedule) {
        List<Schedule> existingSchedules = scheduleRepository.findByInstructorNameAndYearSectionAndCourseOrSemesterOrDaysAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                newSchedule.getInstructorName(),
                newSchedule.getYearSection(),
                newSchedule.getCourse(),
                newSchedule.getSemester(),
                newSchedule.getDays(),

                newSchedule.getEndTime(),
                newSchedule.getStartTime()
        );

        log.info("Existing Schedules: {}", existingSchedules.size());

        if (isScheduleConflict(newSchedule, existingSchedules)) {
            // Handle conflict, maybe throw an exception or return an error
            return true;
        } else {
            // Save the new schedule
            scheduleRepository.save(newSchedule);
        }
        return false; // No conflicts
    }

    private boolean isScheduleConflict(Schedule newSchedule, List<Schedule> existingSchedules) {
        for (Schedule existingSchedule : existingSchedules) {
            if (newSchedule.getStartTime().isBefore(existingSchedule.getEndTime()) &&
                    newSchedule.getEndTime().isAfter(existingSchedule.getStartTime())) {
                return true; // Conflict found
            }
        }
        return false; // No conflicts
    }


}
