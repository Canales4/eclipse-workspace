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
		act6();
	}
	
	public static void act5(String arg) {
		int idjugador = Integer.parseInt(arg);
		if(idjugador!=0) {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Jugadores j1 = new Jugadores();
			j1 = (Jugadores) session.load(Jugadores.class, (int) idjugador);
			System.out.println("DATOS DEL JUGADOR: "+idjugador);
			System.out.printf("Nombre: %s %n",j1.getNombre());
			System.out.printf("Equipo: %s %n", j1.getEquipos().getNombre());
			System.out.println("Temporada  Ptos  Asis  Tap  Reb  ");
			System.out.println("=================================");
			Set<Estadisticas> listaEstadisticas = j1.getEstadisticases();
			Iterator<Estadisticas> it = listaEstadisticas.iterator();
			while(it.hasNext()) {
				Estadisticas est = it.next();
				System.out.printf("%s %.2f %.2f %.2f %.2f %n",est.getId().getTemporada(),est.getPuntosPorPartido(),
						est.getAsistenciasPorPartido(),est.getTaponesPorPartido(),est.getRebotesPorPartido());
			}
			System.out.println("=================================");
			System.out.printf("Num de registros: %d %n",listaEstadisticas.size());
			System.out.println("=================================");
		}else {
			System.out.println("No existe el id");
		}
	}
	public static void act6() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Query q = session.createQuery("from Equipos order by nombre");
		List<Equipos> listaEquipos = q.list();
		Iterator<Equipos> it = listaEquipos.iterator();
		System.out.printf("Numero de Equipos: %s %n", listaEquipos.size());
		while(it.hasNext()) {
			Equipos equipo = (Equipos) it.next();
			System.out.println("=====================");
			System.out.println("Equipo: "+equipo.getNombre());
			String consulta = "from Jugadores where equipos=:Equipo";
			Query q1 = session.createQuery(consulta);
			q1.setParameter("Equipo",equipo);
			List<Jugadores>listaJugadores = q1.list();
			Iterator<Jugadores> it2 = listaJugadores.iterator();
			while(it2.hasNext()) {
				Jugadores jugador = (Jugadores) it2.next();
				String hqlmedia = "select avg(e.puntosPorPartido) from Estadisticas as e where "
						+ "jugadores=:idJugador";
				Query qmedia = session.createQuery(hqlmedia);
				qmedia.setParameter("idJugador",jugador);
				Double media = (Double) qmedia.uniqueResult();
				System.out.printf("%d %s : %.2f %n",jugador.getCodigo(),jugador.getNombre(), media);
			}
		}
		session.close();
		System.exit(0);
	}
	
	public static void act7() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();   
		Session session = sesion.openSession();  
		Transaction tx = session.beginTransaction();
		//String hqlinsert = "insert into Estadisticas ()"
	}
}
