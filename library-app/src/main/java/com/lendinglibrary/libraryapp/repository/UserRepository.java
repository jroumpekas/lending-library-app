package com.lendinglibrary.libraryapp.repository;

import com.lendinglibrary.libraryapp.models.User;
import com.lendinglibrary.libraryapp.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail( String email);

    List<User> findByRole(Role role);

    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    boolean existsByEmailAndIdNot(String email, UUID id);
}
