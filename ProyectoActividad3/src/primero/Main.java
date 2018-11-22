package primero;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Main {
	public static void main(String args[]) {
		insertaVenta(7,"22/11/2018",2,4,2);
	}
	private static void act35(String arg) {
		int idCliente= Integer.parseInt(arg);
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Clientes cli = new Clientes();
		cli = (Clientes) session.load(Clientes.class, (int) idCliente);
		System.out.println("El cliente n�: "+cli.getId());
		Set<Ventas> listaventas = cli.getVentases();
		Iterator<Ventas> it = listaventas.iterator();
		System.out.printf("Numero de ventas: %d %n", listaventas.size());
		while(it.hasNext()) {
			Ventas vent = it.next();
			System.out.printf("IdVenta: %d, Fecha: %s, Producto: %s, Cantidad: %d %n"
					,vent.getIdventa(), vent.getFechaventa(),vent.getProductos().getDescripcion(),vent.getCantidad());
			System.out.println("===========================================================================");
		}
		session.close();
		System.exit(0);
	}
	
	private static void insertaProductos(int id, String desc, int stockactual, int stockminimo, int pvp) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("Inserto una fila en la tabla PRODUCTOS.");
		Productos prod = new Productos();
		prod.setId(id);
		prod.setDescripcion(desc);
		prod.setStockactual(stockactual);
		prod.setStockminimo(stockminimo);
		prod.setPvp(pvp);
		session.save(prod);
		tx.commit();
		session.close();
		System.exit(0);
	}

	private static void insertaClientes(int id, String nom , String dir, String pob, String telef, String nif) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("Inserto una fila en la tabla CLIENTES.");
		Clientes cli = new Clientes();
		cli.setId(id);
		cli.setNombre(nom);
		cli.setDireccion(dir);
		cli.setPoblacion(pob);
		cli.setTelef(telef);
		cli.setNif(nif);
		session.save(cli);
		tx.commit();
		session.close();
		System.exit(0);
	}
	private static void insertaVenta(int idVenta,String fecha, int idCliente, int idProducto, int cantidad) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();
		Ventas vent = (Ventas) session.get(Ventas.class, (int) idVenta);
		if(vent == null) {
			Clientes cli = (Clientes) session.get(Clientes.class, (int) idCliente);
			if(cli == null) {
				System.out.println("No existe el cliente y no puede ser insertada");
			}else {
				Productos prod = (Productos) session.get(Productos.class, (int) idProducto);
				if(prod==null) {
					System.out.println("No existe el producto y no puede ser insertada");
				}else {
					if((prod.getStockactual()-cantidad) >= prod.getStockminimo()) {
						System.out.println("Insertando Venta...");
						Ventas vents = new Ventas();
						vents.setIdventa(idVenta);
						vents.setFechaventa(fecha);
						vents.setClientes(cli);
						vents.setProductos(prod);
						vents.setCantidad(cantidad);
						session.save(vents);
					}else {
						System.out.println("No hay stock suficiente");
					}
				}
			}
		}else {
			System.out.println("Existe la venta y no puede ser insertada");
		}
		tx.commit();
		session.close();
		System.exit(0);
	}
	private static void act37() {
		int impt=0;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query q = session.createQuery("from Clientes");
		List<Clientes> lista = q.list();
		Iterator<Clientes> it = lista.iterator();
		while(it.hasNext()) {
			Clientes cli = (Clientes) it.next();
			System.out.printf("Venta del cliente: %s %n", cli.getNombre());
			Query q1 = session.createQuery("from Ventas where clientes="+cli.getId());
			List<Ventas> listaventas = q1.list();
			Iterator<Ventas> iter = listaventas.iterator();
			if(listaventas.size()>0) {
				while(iter.hasNext()) {
					Ventas vent = (Ventas) iter.next();
					System.out.printf("Venta: %d, Fecha venta: %s %n",vent.getIdventa(),vent.getFechaventa());
					System.out.printf("\t Producto: %s %n", vent.getProductos().getDescripcion());
					System.out.printf("\t Cantidad: %d, PVP: %d %n", vent.getCantidad(),
							vent.getProductos().getPvp());
					System.out.printf("\t Importe: %d� %n", vent.getCantidad()*vent.getProductos().getPvp());
					impt+=vent.getCantidad()*vent.getProductos().getPvp();
				}
				System.out.printf("Numero total de ventas: %d %n",listaventas.size());
				System.out.printf("Importe Total: %d� %n",impt);
			}
		}
		session.close();
		System.exit(0);
	}
}
