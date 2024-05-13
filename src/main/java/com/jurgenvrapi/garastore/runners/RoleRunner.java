package com.jurgenvrapi.garastore.runners;

import com.jurgenvrapi.garastore.entities.Role;
import com.jurgenvrapi.garastore.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleRunner(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create "CLIENT" role
        Optional<Role> clientRole = roleRepository.findByRoleName("CLIENT");
        if (clientRole.isEmpty()) {
            clientRole = Optional.of(new Role(null, "CLIENT", null));
            roleRepository.save(clientRole.get());
        }
    }
}