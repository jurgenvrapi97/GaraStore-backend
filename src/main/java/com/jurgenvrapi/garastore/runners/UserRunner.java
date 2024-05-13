package com.jurgenvrapi.garastore.runners;

import com.jurgenvrapi.garastore.entities.Role;
import com.jurgenvrapi.garastore.entities.User;
import com.jurgenvrapi.garastore.repositories.RoleRepository;
import com.jurgenvrapi.garastore.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserRunner(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findByRoleName("ADMIN");
            if (adminRole == null) {
                adminRole = new Role(null, "ADMIN", null);
                roleRepository.save(adminRole);
            }

            User adminUser = new User(null, "Admin", "Admin", "admin@garastore.com", "1234567890", "adminPassword", LocalDate.now(), adminRole);
            userRepository.save(adminUser);
        }
    }
}