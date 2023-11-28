package com.groupseven.classscheduling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users_test")
public class User {

    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String role;

}