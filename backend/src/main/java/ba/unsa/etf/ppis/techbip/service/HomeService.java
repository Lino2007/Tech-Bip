package ba.unsa.etf.ppis.techbip.service;

import ba.unsa.etf.ppis.techbip.exception.ResourceNotFoundException;
import ba.unsa.etf.ppis.techbip.model.Employee;
import ba.unsa.etf.ppis.techbip.model.Role;
import ba.unsa.etf.ppis.techbip.model.RoleName;
import ba.unsa.etf.ppis.techbip.repository.EmployeeRepository;
import ba.unsa.etf.ppis.techbip.repository.RoleRepository;
import ba.unsa.etf.ppis.techbip.request.LoginRequest;
import ba.unsa.etf.ppis.techbip.request.RegisterRequest;
import ba.unsa.etf.ppis.techbip.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HomeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public String register(RegisterRequest registerRequest) {
        Optional<Employee> employee = employeeRepository.findByUsername(registerRequest.getUsername());
        if(employee.isPresent()) return "User already exists with this username.";

        Optional<Employee> employee1 = employeeRepository.findByEmail(registerRequest.getEmail());
        if(employee1.isPresent()) return "User already exists with this email.";

        Employee e = new Employee(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPhoneNumber(),
                registerRequest.getCountry(),
                registerRequest.getCity(),
                passwordEncoder.encode(registerRequest.getPassword()));

        if(registerRequest.getRoleName().equals(RoleName.ROLE_ADMIN)){
            List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_ADMIN));
            e.setRoles(new HashSet<>(roles));

        }
        else{
            List<Role> roles = Collections.singletonList(roleRepository.findByRolename(RoleName.ROLE_EMPLOYEE));
            e.setRoles(new HashSet<>(roles));
        }
        employeeRepository.save(e);
        return "Registration is completed.";
    }

    public String authentication(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    public Employee getEmployeeByUsername(String username) {
        String errorMessage = "User with username " + username + " does not exist.";
        return employeeRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(errorMessage));
    }
}