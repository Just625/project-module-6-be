package com.example.casestudy.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(max=12)
    @Column(unique=true)
    private String username;
//    @Length(min = 6, max = 8)
    @NotEmpty
    private String password;
    @NotEmpty
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b")
    private String phoneNumber;
    private String name;
    private String address;
    private String email;
    private String avatarUrl;
    //De fetch mac dinh Lazy thi se ko load duoc cac roles ra
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
