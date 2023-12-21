package com.groupseven.classscheduling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("schedules")
@CompoundIndexes({
        @CompoundIndex(name = "instructor_time_room_semester_days",
                def = "{'instructorName' : 1, 'startTime' : 1, 'endTime' : 1, 'roomNumber' : 1, 'semester' : 1, 'days' : 1}",
                unique = true)
})
public class Schedule {

    @Id
    private String id;
    @Indexed(unique = true)
    private String instructorName;
    @Indexed(unique = true)
    private String subject;
    @Indexed(unique = true)
    private String yearSection;
    @Indexed(unique = true)
    private String phoneNumber;
    @Indexed(unique = true)
    private String semester;
    @Indexed(unique = true)
    private String roomNumber;
    @Indexed(unique = true)
    private String days;
    @Indexed(unique = true)
    private String course;
    private LocalTime startTime;
    private LocalTime endTime;
}
