package com.jbk.mapping.controller;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.mapping.entity.Employee;

@RestController
public class Test {
	@Autowired
	private SessionFactory sf;
	
	
	@PostMapping(value="/save")
	public ResponseEntity<String>saveEmployee(@RequestBody Employee employee){
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
	//	String msg="Record Alresdy Exists";
		boolean isAdded=false;
		
		try {
		Employee emp = session.get(Employee.class, employee.getEid());
		if(emp==null) {
			session.save(employee);
			tx.commit();
			isAdded=true;
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
		if(isAdded) {
			return new ResponseEntity<String>("Saved !!!",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Already Exists !!!"+employee.getEid(),HttpStatus.OK);
		}
		
	}

}
