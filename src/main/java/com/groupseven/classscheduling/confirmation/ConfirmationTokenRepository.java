package com.groupseven.classscheduling.confirmation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
