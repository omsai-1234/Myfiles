package jbk.com.Apiassige;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class StudentGet {
	
	@Autowired
	SessionFactory sf;
	
		@GetMapping("getStudent/{sid}")
		public Student addStudent(@PathVariable int sid) {
			
			Session session = sf.openSession();
			Criteria cr=session.createCriteria(Student.class);
			     List l1=cr.list();
			Student	p1 = session.load(Student.class, sid);
		
			 System.out.println(p1);
			 return p1;
			
		}
		


}

