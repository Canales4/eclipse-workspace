package primero;
// Generated 20-nov-2018 12:45:40 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Dept generated by hbm2java
 */
public class Dept implements java.io.Serializable {

	private byte deptno;
	private String dname;
	private String loc;
	private Set<Emp> emps = new HashSet<Emp>(0);

	public Dept() {
	}

	public Dept(byte deptno) {
		this.deptno = deptno;
	}

	public Dept(byte deptno, String dname, String loc, Set<Emp> emps) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
		this.emps = emps;
	}

	public byte getDeptno() {
		return this.deptno;
	}

	public void setDeptno(byte deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

}
