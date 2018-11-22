package primero;
// Generated 20-nov-2018 12:45:40 by Hibernate Tools 3.5.0.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Emp generated by hbm2java
 */
public class Emp implements java.io.Serializable {

	private short empno;
	private Dept dept;
	private Emp emp;
	private String ename;
	private String job;
	private Date hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
	private Set<Emp> emps = new HashSet<Emp>(0);

	public Emp() {
	}

	public Emp(short empno) {
		this.empno = empno;
	}

	public Emp(short empno, Dept dept, Emp emp, String ename, String job, Date hiredate, BigDecimal sal,
			BigDecimal comm, Set<Emp> emps) {
		this.empno = empno;
		this.dept = dept;
		this.emp = emp;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.emps = emps;
	}

	public short getEmpno() {
		return this.empno;
	}

	public void setEmpno(short empno) {
		this.empno = empno;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Emp getEmp() {
		return this.emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public BigDecimal getSal() {
		return this.sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public BigDecimal getComm() {
		return this.comm;
	}

	public void setComm(BigDecimal comm) {
		this.comm = comm;
	}

	public Set<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}

}
