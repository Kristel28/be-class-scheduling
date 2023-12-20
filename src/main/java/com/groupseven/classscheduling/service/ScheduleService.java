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
                                newSchedule.getCourse().equals(existingSchedule.getCourse()) &&
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


    public Schedule getScheduleById(String id){

        return scheduleRepository.
                findById(id).orElseThrow(() -> new IllegalStateException("Cannot find schedule with id of : " + id));
    }

    public Schedule updateSchedule(String id, Schedule schedule){

        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id of : " + id));

        existingSchedule.setInstructorName(schedule.getInstructorName());
        existingSchedule.setSubject(schedule.getSubject());
        existingSchedule.setYearSection(schedule.getYearSection());
        existingSchedule.setSemester(schedule.getSemester());
        existingSchedule.setRoomNumber(schedule.getRoomNumber());
        existingSchedule.setDays(schedule.getDays());
        existingSchedule.setCourse(schedule.getCourse());
        existingSchedule.setStartTime(schedule.getStartTime());
        existingSchedule.setEndTime(schedule.getEndTime());

        scheduleRepository.save(existingSchedule);
        return existingSchedule;

    }

    public void deleteScheduleById(String id){
        scheduleRepository.deleteById(id);
    }

}
