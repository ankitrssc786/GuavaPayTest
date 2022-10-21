package com.test.application.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.application.model.ERole;
import com.test.application.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
  
  @Query( value = "SELECT * FROM roles",  nativeQuery = true)
  Set<String> findRoles();
}
