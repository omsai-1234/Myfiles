package jbk.com.Apiassige;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class StudentDelete{
	@Autowired
	SessionFactory sf;
	@PostMapping("deleteStudent/{sid}")
	public void deleteStudent(@PathVariable  int sid) {
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Student s1=session.load(Student.class, sid);
	 
	 session.delete(s1);
		
		tr.commit();
		System.out.println(s1);
	}
	

}

