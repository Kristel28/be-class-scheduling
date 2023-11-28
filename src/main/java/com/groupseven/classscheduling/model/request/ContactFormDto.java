package com.groupseven.classscheduling.model.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactFormDto {

    private String firstName;
    private String lastName;
    private String message;
    private String email;

}
