package ro.iteahome.nhs.adminui.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Admin implements UserDetails {

// FIELDS: -------------------------------------------------------------------------------------------------------------

    private static final String ROLE_PREFIX = "ROLE_";

    private int id;

    @NotNull(message = "EMAIL CANNOT BE EMPTY.")
    @Email(regexp = ".+@.+\\.\\w+", message = "INVALID EMAIL ADDRESS")
    private String email;

    @NotNull(message = "PASSWORD CANNOT BE EMPTY.")
    @Pattern(regexp = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,32})", message = "INVALID PASSWORD")
    private String password;

    @NotNull(message = "FIRST NAME CANNOT BE EMPTY.")
    private String firstName;

    @NotNull(message = "LAST NAME CANNOT BE EMPTY.")
    private String lastName;

    @NotNull(message = "PHONE NUMBER NAME CANNOT BE EMPTY.")
    @Pattern(regexp = "^0040\\d{9}$", message = "INVALID PHONE NUMBER")
    private String phoneNoRo;

    private int status;

    private Set<Role> roles;

// METHODS: ------------------------------------------------------------------------------------------------------------

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // "getPassword()" IS PART OF THE "UserDetails" OVERRIDDEN METHODS.

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNoRo() {
        return phoneNoRo;
    }

    public void setPhoneNoRo(String phoneNoRo) {
        this.phoneNoRo = phoneNoRo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()))
                .collect(Collectors.toList());
    }

// OVERRIDDEN METHODS FROM "UserDetails" INTERFACE: --------------------------------------------------------------------

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status != 3;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != 3;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != 4;
    }
}
