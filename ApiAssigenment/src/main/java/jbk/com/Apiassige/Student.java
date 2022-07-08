package jbk.com.Apiassige;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Entity
@Table
@Component

 public class Student {
		@Id
		int sid;
		String sname;
		String scity;
		public Student() {
			super();
		}
		public Student(int sid, String sname, String scity) {
			super();
			this.sid = sid;
			this.sname = sname;
			this.scity = scity;
		}
		public int getSid() {
			return sid;
		}
		public void setSid(int sid) {
			this.sid = sid;
		}
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public String getScity() {
			return scity;
		}
		public void setScity(String scity) {
			this.scity = scity;
		}
		@Override
		public String toString() {
			return "Student [sid=" + sid + ", sname=" + sname + ", scity=" + scity + "]";
		}
		

}
