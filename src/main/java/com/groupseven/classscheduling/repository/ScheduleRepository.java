package com.groupseven.classscheduling.repository;

import com.groupseven.classscheduling.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    List<Schedule> findByInstructorNameAndCourse(String instructorName, String course);


    List<Schedule> findByInstructorNameAndRoomNumberAndYearSectionAndCourseAndSemesterOrDaysAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            String instructorName,
            int roomNumber,
            String yearAndSection,
            String course,
            String semester,
            String days,
            LocalTime startTime,
            LocalTime endTime);


}
