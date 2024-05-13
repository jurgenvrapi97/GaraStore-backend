package com.jurgenvrapi.garastore.repositories;

import com.jurgenvrapi.garastore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}