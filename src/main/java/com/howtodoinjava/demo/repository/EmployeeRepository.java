package com.howtodoinjava.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.howtodoinjava.demo.model.EmployeeEntity;
 
@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> {
	List<EmployeeEntity> findByEmail(String email);
	List<EmployeeEntity> findByFirstName(String name);

}
