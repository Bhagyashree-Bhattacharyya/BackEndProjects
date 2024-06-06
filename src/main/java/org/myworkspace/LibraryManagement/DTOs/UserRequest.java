package org.myworkspace.LibraryManagement.DTOs;

import lombok.*;
import org.myworkspace.LibraryManagement.Entities.User.User;
import jakarta.validation.constraints.NotBlank;
import org.myworkspace.LibraryManagement.Entities.User.UserStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserRequest {
    private String userName;

    @NotBlank(message = "user phoneNo should not be blank")
    private String phoneNo;

    private String email;

    private String  address;

    public User toUser() {
        return User.
                builder().
                name(this.userName).
                email(this.email).
                phoneNo(this.phoneNo).
                address(this.address).
                userStatus(UserStatus.ACTIVE).
                build();
    }

}
