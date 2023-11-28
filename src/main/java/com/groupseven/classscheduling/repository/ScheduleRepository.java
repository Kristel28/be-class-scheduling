package com.groupseven.classscheduling.repository;

import com.groupseven.classscheduling.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    List<Schedule> findByInstructorNameAndCourse(String instructorName, String course);

}
