package org.myworkspace.LibraryManagement.Entities.User;

import jakarta.persistence.*;
import lombok.*;
import org.myworkspace.LibraryManagement.Entities.BaseEntity;
import org.myworkspace.LibraryManagement.Entities.Book.Book;
import org.myworkspace.LibraryManagement.Entities.Transaction.Txn;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    private String password;

    private String authorities;

    @Column(nullable = false, unique = true, length = 15)
    private String phoneNo;

    @Column(unique = true, length = 50)
    private String email;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Enumerated//(value = EnumType.STRING) // could not add the constraint to already present table
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
    private List<Book> bookList;

    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
    private List<Txn> txnList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] auth = authorities.split(",");
        return Arrays.stream(auth).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
