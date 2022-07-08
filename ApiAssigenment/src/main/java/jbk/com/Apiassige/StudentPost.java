package jbk.com.Apiassige;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class StudentPost {
	
	@Autowired
	SessionFactory sf;
	
		@PostMapping("addStudent")
		public void addStudent(@RequestBody Student s1) {
			Session session=sf.openSession();
			Transaction t1=session.beginTransaction();
			session.save(s1);
			t1.commit();

			System.out.println(s1);
			
		}
		


}
