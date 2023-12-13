package com.groupseven.classscheduling.service;

import com.groupseven.classscheduling.model.Schedule;
import com.groupseven.classscheduling.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
        List<Schedule> existingSchedules = scheduleRepository
                .findByInstructorNameOrRoomNumberAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                        newSchedule.getInstructorName(),
                        newSchedule.getRoomNumber(),
                        newSchedule.getStartTime(),
                        newSchedule.getEndTime()

                );

        existingSchedules.forEach(System.out::println);
        log.info("Existing Schedules: {}", existingSchedules.size());

        return existingSchedules.stream()
                .anyMatch(existingSchedule ->
                                newSchedule.getStartTime().isBefore(existingSchedule.getEndTime()) &&
                                newSchedule.getEndTime().isAfter(existingSchedule.getStartTime()) &&
                                newSchedule.getSemester().equals(existingSchedule.getSemester()) ||
                                newSchedule.getInstructorName().equals(existingSchedule.getInstructorName()) &&
                                newSchedule.getRoomNumber().equals(existingSchedule.getRoomNumber()) &&
                                newSchedule.getDays().equals(existingSchedule.getDays())
                );
    }

    @Transactional
    private boolean isScheduleConflict(Schedule newSchedule, List<Schedule> existingSchedules) {
        for (Schedule existingSchedule : existingSchedules) {
            if (newSchedule.getStartTime().isBefore(existingSchedule.getEndTime()) &&
                    newSchedule.getEndTime().isAfter(existingSchedule.getStartTime())) {
                if (newSchedule.getRoomNumber().equals(existingSchedule.getRoomNumber()) &&
                        newSchedule.getSemester().equals(existingSchedule.getSemester()) &&
                        newSchedule.getDays().equals(existingSchedule.getDays())) {
                    return true;
                }
            }
        }
        return false; // No conflict
    }

}
