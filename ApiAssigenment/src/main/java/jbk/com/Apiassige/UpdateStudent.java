package jbk.com.Apiassige;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



	@RestController
	@RequestMapping("api")
	public class UpdateStudent{
		@Autowired
		SessionFactory sf;
		@PutMapping("updateStudent")
		public void updateCountyName(@RequestBody Student e1) {
			Session session=sf.openSession();
			Transaction tr=session.beginTransaction();
			Student s1=e1;
			session.saveOrUpdate(s1);
			tr.commit();
			System.out.println(s1);
		}
		

	}

