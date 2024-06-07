package org.myworkspace.LibraryManagement.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TxnRequest {
    @NotBlank(message = "mandatory field !!")
    private String userPhoneNo;
    @NotBlank(message = "mandatory field !!")
    private String bookNo;
}
