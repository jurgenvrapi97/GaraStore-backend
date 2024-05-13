package com.jurgenvrapi.garastore.repositories;

import com.jurgenvrapi.garastore.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}