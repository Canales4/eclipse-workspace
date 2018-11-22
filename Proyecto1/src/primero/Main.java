package primero;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
	public static void main(String args[]) {
		act36(args[0]);
	}
	public static void insercion() {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			System.out.println("Inserto una fila en la tabla DEPARTAMENTOS.");
			Departamentos dep = new Departamentos();
			dep.setDeptNo((byte) 62);
			dep.setDnombre("MARKETING");
			dep.setLoc("GUADALAJARA");
			session.save(dep);
			tx.commit();
			session.close();
			System.exit(0);
	}
	public static void act34() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		System.out.println("============================");
		System.out.println("DATOS DE LOS EMPLEADOS 7369");
		Empleados emp = new Empleados();
		emp = (Empleados) session.load(Empleados.class, (int) 2);
		System.out.println("Emp_no: "+emp.getEmpNo());
		System.out.println("Apellido: "+emp.getApellido());
		System.out.println("Oficio: "+emp.getOficio());
		System.out.println("Dir: "+emp.getDir());
		System.out.println("Fecha_alt: "+emp.getFechaAlt());
		System.out.println("Salario: "+emp.getSalario());
		System.out.println("Comision: "+emp.getComision());
		System.out.println("Dept_no: "+emp.getDepartamentos().getDeptNo());
		session.close();
		System.exit(0);
	}
	public static void act36(String arg) {//falta trasicion y guardar
		int salario = Integer.parseInt(arg);
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Departamentos dep = new Departamentos();
		dep = (Departamentos) session.load(Departamentos.class, (int) 2);
		Set<Empleados> listaempleados = dep.getEmpleadoses();
		Iterator<Empleados> it = listaempleados.iterator();
		System.out.println("Numero de empleados: "+listaempleados.size());
		while(it.hasNext()) {
			Empleados empl = it.next();
			System.out.printf("Apellido: %s, Salario: %.2f %n", empl.getApellido(), empl.getSalario());
			System.out.println("<===============================>");
			float salariomodificado = empl.getSalario()+salario;
			empl.setSalario(salariomodificado);
			System.out.printf("Apellido: %s, Salario_modif: %.2f %n", empl.getApellido(), empl.getSalario());
			System.out.println("<===========FIN_MODIF============>");
			tx.commit();
		}
		System.out.println("<===========FIN_MODIFICACIONES============>");
		session.close();
		System.exit(0);
	}
	
	public static void act37() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query q = session.createQuery("from Empleados where dept_no=2");
		List<Empleados> lista = q.list();
		Iterator<Empleados> it = lista.iterator();
		while(it.hasNext()) {
			Empleados emp = (Empleados) it.next();
			System.out.printf("IdDepartamento: %d, Nombre: %s, Localizacion: %s, Apellido: %s %n ", 
					emp.getDepartamentos().getDeptNo(), emp.getDepartamentos().getDnombre(), 
					emp.getDepartamentos().getLoc(),emp.getApellido());
		}
		session.close();
		System.exit(0);
	}
}

