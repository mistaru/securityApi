package org.example.argen.repository;

import org.example.argen.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Long, Todo> {

}
