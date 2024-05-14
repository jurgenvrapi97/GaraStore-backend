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

    Optional<Role> clientRole = roleRepository.findByRoleName("CLIENT");
    if (clientRole.isEmpty()) {
        Role role = new Role();
        role.setRoleName("CLIENT");
        roleRepository.save(role);
    }
}
}