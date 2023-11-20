package com.yaseenworks.supermarket.user.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "first",
                column = @Column(name = "first_name")
        ),
        @AttributeOverride(
                name = "last",
                column = @Column(name = "last_name")
        )
})
public class Name {
    @NotBlank(message = "First Name is required")
    private String first;
    @NotBlank(message = "Last Name is required")
    private String last;
}
