package com.yaseenworks.supermarket.user.records;

import com.yaseenworks.supermarket.user.enums.Role;
import com.yaseenworks.supermarket.user.models.Name;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
public record UserResponse(Integer id, Name name, String email, String phoneNumber,
                           @Enumerated(EnumType.STRING) Role role) {
}
