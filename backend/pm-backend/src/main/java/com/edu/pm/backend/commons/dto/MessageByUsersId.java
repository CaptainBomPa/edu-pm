package com.edu.pm.backend.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageByUsersId {

    private Integer user1Id;
    private Integer user2Id;
}
