package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;
 
@Service
public class EmployeeService {
     
    @Autowired
    EmployeeRepository repository;
     
    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }
     /////////////////////////////////////////////////
    /////
    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
    
  
    public EmployeeEntity getEmpById(Long id) {
		boolean trouve = repository.existsById(id);
		if (!trouve)
			return null;
		return repository.getOne(id);
	}
    /////
     ////////////////////////////////////////////////////////////
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
            EmployeeEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
    
	@Transactional
    public void save(EmployeeEntity entity) 
    {
       
            repository.save(entity);
            
        
    }
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
    //
    @Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
    
    
	public List<EmployeeEntity> sortBy(String fieldName) {
		 List<EmployeeEntity>  list =repository.findAll(Sort.by(fieldName));
		return list;
	}
	
	
	public List<EmployeeEntity> getAllEmployees(int pageId, int size) {
		Page<EmployeeEntity> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "firstName"));
		return result.getContent();
	}
	
	public List<EmployeeEntity> findByEmail(String email) {
		List<EmployeeEntity> list = repository.findByEmail(email);
		return list;
	}
	public List<EmployeeEntity> findByFirstName(String fonction) {
		List<EmployeeEntity> list = repository.findByFirstName(fonction);
		return list;
	}
	
	@Transactional
	public void deleteAll() {
		repository.deleteAll();
	}
}