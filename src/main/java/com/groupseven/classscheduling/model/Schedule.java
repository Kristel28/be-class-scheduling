package com.groupseven.classscheduling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("schedules")
@CompoundIndexes({
        @CompoundIndex(name = "instructor_time", def = "{'instructorName' : 1, 'startTime' : 1, 'endTime' : 1}", unique = true)
})
public class Schedule {

    @Id
    private String id;
    private String instructorName;
    private String subject;
    private String yearSection;
    private String semester;
    private int roomNumber;
    private String days;
    private String course;
    private LocalTime startTime;
    private LocalTime endTime;
}
