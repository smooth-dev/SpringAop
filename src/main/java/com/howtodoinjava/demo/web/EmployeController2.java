package com.howtodoinjava.demo.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.howtodoinjava.demo.exception.RecordNotFoundException;
import com.howtodoinjava.demo.model.EmployeeEntity;
import com.howtodoinjava.demo.service.EmployeeService;

@Controller
public class EmployeController2 {

	@Autowired
	 EmployeeService service;

	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile() {
		return "index";
	}
	
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("empVo", new EmployeeEntity());
		return "empform";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("empVo") EmployeeEntity emp) {
		
				service.save(emp);
		return "redirect:/viewemp";// will redirect to viewemp request mapping
	}
	
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<EmployeeEntity> list = service.getAllEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}
	
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable Long id, Model m) {
		EmployeeEntity emp = null;
		emp = service.getEmpById(id);
		m.addAttribute("empVo", emp);
		return "empeditform";
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") EmployeeEntity emp) {
		service.save(emp);
		return "redirect:/viewemp";
	}
	
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/viewemp";
	}
	
	@RequestMapping("/email/{email}")
	public String getByEmail(@PathVariable String email, Model m) {
		List<EmployeeEntity> list = service.findByEmail(email);
		m.addAttribute("list", list);
		return "viewemp";
	}
	
	@RequestMapping("/firstname/{name}")
	public String getByFirstName(@PathVariable String name, Model m) {
		List<EmployeeEntity> list = service.findByFirstName(name);
		m.addAttribute("list", list);
		return "viewemp";
	}
	
//	@RequestMapping("/salary_and_fonction/{salary}/{fonction}")
//	public String getBySalaryAndFonction(@PathVariable Double salary, @PathVariable String fonction, Model m) {
//		List<EmployeeEntity> list = service.findByFirstnameAndEmail(salary, fonction);
//		m.addAttribute("list", list);
//		return "viewemp";
//	}

	
//	@RequestMapping("/max_salary")
//	public String getMaxSalary(Model m) {
//		EmployeeEntity empVo = service.getEmpHavaingMaxSalary();
//		List<EmployeeEntity> list = new ArrayList<>();
//		list.add(empVo);
//		m.addAttribute("list", list);
//		return "viewemp";
//	}
	
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<EmployeeEntity> list = service.getAllEmployees(pageid, size);
		m.addAttribute("list", list);
		return "viewemp";
	}
	
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<EmployeeEntity> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "viewemp";
	}
}