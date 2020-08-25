package com.listening.listening.repository;

import com.listening.listening.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
}
