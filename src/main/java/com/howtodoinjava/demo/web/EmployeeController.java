package com.howtodoinjava.demo.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;
 
@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    EmployeeService service;
 
    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();
 
        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<EmployeeEntity> createOrUpdateEmployee(@RequestBody EmployeeEntity empVo)
                                                    throws RecordNotFoundException {
        EmployeeEntity updated = service.createOrUpdateEmployee(empVo);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	EmployeeEntity empVoFound = service.getEmployeeById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("employee doen't exist", HttpStatus.OK);

        service.deleteEmployeeById(id);
		return new ResponseEntity<>("Employee is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
	public ResponseEntity<List<EmployeeEntity>> sortBy(@PathVariable String fieldName) {
    	 List<EmployeeEntity> list = service.sortBy(fieldName);
    	 System.out.println(list);
    	 
         return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}
    
    @GetMapping("/{pageid}/{size}")
	public List<EmployeeEntity> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		return service.getAllEmployees(pageid, size);
	}
 
}