package org.js.azdanov.depositservice.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<Long> bills;
    private String phone;
    private OffsetDateTime creationDate;
}
