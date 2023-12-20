package com.groupseven.classscheduling.repository;

import com.groupseven.classscheduling.model.Schedule;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    List<Schedule> findByInstructorNameAndCourse(String instructorName, String course);


    List<Schedule> findByInstructorNameOrRoomNumberAndYearSectionAndCourseAndSemesterAndDaysAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            String instructorName,
            String roomNumber,
            String yearAndSection,
            String course,
            String semester,
            String days,
            LocalTime startTime,
            LocalTime endTime);

//    boolean findByInstructorNameOrRoomNumberAndYearSectionAndCourseAndSemesterAndDaysAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
//            String instructorName,
//            Integer roomNumber,
//            String yearSection,
//            String course,
//            String semester,
//            String days,
//            LocalTime endTime,
//            LocalTime startTime
//
//    );

    List<Schedule> findByInstructorNameOrRoomNumberAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            String instructorName,
            String roomNumber,
            LocalTime endTime,
            LocalTime startTime

    );


}
