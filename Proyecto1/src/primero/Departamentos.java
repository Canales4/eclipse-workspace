package primero;
// Generated 13-nov-2018 9:07:58 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos implements java.io.Serializable {

	private int deptNo;
	private String dnombre;
	private String loc;
	private Set<Empleados> empleadoses = new HashSet<Empleados>(0);

	public Departamentos() {
	}

	public Departamentos(int deptNo, String dnombre, String loc) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
	}

	public Departamentos(int deptNo, String dnombre, String loc, Set<Empleados> empleadoses) {
		this.deptNo = deptNo;
		this.dnombre = dnombre;
		this.loc = loc;
		this.empleadoses = empleadoses;
	}

	public int getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDnombre() {
		return this.dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set<Empleados> getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set<Empleados> empleadoses) {
		this.empleadoses = empleadoses;
	}

}
