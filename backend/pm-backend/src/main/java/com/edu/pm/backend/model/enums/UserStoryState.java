package com.edu.pm.backend.model.enums;

public enum UserStoryState {
    NEW, //just created
    DEFINED, //goal and scope of this user story has been defined, and developer can take it
    IN_PROGRESS, //developer is working on a story
    READY, //ready to be taken by qa tester if necessary
    TEST, // qa - automatic / manual tests if necessary
    TEST_READY, // test completed with positive result. Waiting for Product Manager to be accepted
    ACCEPTED, // story accepted by PM
    CLOSED, // story closed, no there will be no need for any additional work on that.
}
