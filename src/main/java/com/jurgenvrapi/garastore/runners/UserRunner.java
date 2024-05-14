package com.jurgenvrapi.garastore.runners;

import com.jurgenvrapi.garastore.entities.Role;
import com.jurgenvrapi.garastore.entities.User;
import com.jurgenvrapi.garastore.repositories.RoleRepository;
import com.jurgenvrapi.garastore.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRunner.class);

    public UserRunner(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

  @Override
public void run(String... args) throws Exception {
    if (userRepository.count() == 0) {
        Optional<Role> adminRole = roleRepository.findByRoleName("ADMIN");
        if (adminRole.isEmpty()) {
            Role role = new Role();
            role.setRoleName("ADMIN");
            roleRepository.save(role);
            adminRole = Optional.of(role);
        }

        String encodedPassword = passwordEncoder.encode("adminPassword");
        User adminUser = new User(null, "Admin", "Admin", "admin@garastore.com", "1234567890", encodedPassword, LocalDate.now(), adminRole.get());
        userRepository.save(adminUser);
    }
}
}