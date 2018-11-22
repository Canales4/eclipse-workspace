package ejemplosJaxb;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import clasesdedatos.DatosArtic;
import clasesdedatos.ObjectFactory;
import clasesdedatos.Ventas;
import clasesdedatos.VentasType;
import clasesdedatos.Ventas.Venta;

public class visualizarxml {
	public static void main(String[] args) {
		borrarVenta(10);
		//visualizarxml();
	}
	public static void visualizarxml() {
		System.out.println("------------------------------ ");
		System.out.println("-------VISUALIZAR XML--------- ");
		System.out.println("------------------------------ ");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller u = jaxbContext.createUnmarshaller();
			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(jaxbElement, System.out);
			VentasType miventa = (VentasType) jaxbElement.getValue();
			Ventas vent = miventa.getVentas();
			List listaVentas = new ArrayList();
			listaVentas = vent.getVenta();

			System.out.println("---------------------------- ");
			System.out.println("---VISUALIZAR LOS OBJETOS--- ");
			System.out.println("---------------------------- ");

			DatosArtic miartic = (DatosArtic) miventa.getArticulo();

			System.out.println("Nombre art: " + miartic.getDenominacion());
			System.out.println("Codigo art: " + miartic.getCodigo());
			System.out.println("Stock art: " + miartic.getCodigo());
			System.out.println("Ventas  del articulo , hay: " + listaVentas.size());

			for (int i = 0; i < listaVentas.size(); i++) {
				Ventas.Venta ve = (Venta) listaVentas.get(i);
				System.out.println("Numero de venta: " + ve.getNumventa() + ". Nombre cliente: " + ve.getNombrecliente()
						+ ", unidades: " + ve.getUnidades() + ", fecha: " + ve.getFecha());
			}

		} catch (JAXBException je) {
			System.out.println(je.getCause());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}
	private static void insertarventa(int numeventa, String nomcli, int uni, String fecha) {

		System.out.println("---------------------------- ");
		System.out.println("-------A�ADIR VENTA--------- ");
		System.out.println("---------------------------- ");
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller u = jaxbContext.createUnmarshaller();
			JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));
			VentasType miventa = (VentasType) jaxbElement.getValue();
			Ventas vent = miventa.getVentas();
			List listaVentas = new ArrayList();
			listaVentas = vent.getVenta();
			int existe = 0; 
			for (int i = 0; i < listaVentas.size(); i++) {
				Ventas.Venta ve = (Venta) listaVentas.get(i);
				if (ve.getNumventa() == numeventa) {
					existe = 1;
					break;
				}
			}

			if (existe == 0) {
				// Crear el objeto Ventas.Venta, y si no existe se a�ade a la
				// lista

				Ventas.Venta ve = new Ventas.Venta();
				ve.setNombrecliente(nomcli);
				ve.setFecha(fecha);
				ve.setUnidades(uni);
				int nume = numeventa;
				ve.setNumventa(nume);

				// a�adimos la venta a la lista

				listaVentas.add(ve);

				// crear el Marshaller, volcar la lista al fichero XML
				Marshaller m = jaxbContext.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));

				System.out.println("Venta a�adida: " + numeventa);

			} else {
				System.out.println("En n�mero de venta ya existe: " + numeventa);		
			}
		} catch (JAXBException je) {
			System.out.println(je.getCause());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}
	private static void borrarVenta(int numeventa) {
		boolean borrado = false;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));
		VentasType miventa = (VentasType) jaxbElement.getValue();
		Ventas vent = miventa.getVentas();
		List listaVentas = new ArrayList();
		listaVentas = vent.getVenta();
		for (int i = 0; i < listaVentas.size(); i++) {
			Ventas.Venta ve = (Venta) listaVentas.get(i);
			if (ve.getNumventa() == numeventa) {
				listaVentas.remove(ve);
				borrado = true;
				break;
			}
		}				
		System.out.print(borrado);
		Marshaller m = jaxbContext.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
	} catch (JAXBException je) {
		System.out.println(je.getCause());
	} catch (IOException ioe) {
		System.out.println(ioe.getMessage());
	}
	}
	private static void modificarStock(String cod, int stock) {
		boolean modificado = false;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));
		VentasType miventa = (VentasType) jaxbElement.getValue();
		DatosArtic datos = miventa.getArticulo();
		int sum = datos.getStock()+stock;
		datos.setStock(sum);
		if(datos.getStock()==sum) {
			modificado=true;
			System.out.println(modificado);
		}
		Marshaller m = jaxbContext.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
	} catch (JAXBException je) {
		System.out.println(je.getCause());
	} catch (IOException ioe) {
		System.out.println(ioe.getMessage());
	}
	}
	private static void modificarVenta(int numVenta, int unidad, String fecha) {
		boolean modificadoVenta = false;
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller u = jaxbContext.createUnmarshaller();
		JAXBElement jaxbElement = (JAXBElement) u.unmarshal(new FileInputStream("./ventasarticulos.xml"));
		VentasType miventa = (VentasType) jaxbElement.getValue();
		Ventas ve = miventa.getVentas();
		List listaVentas = new ArrayList<>();
		listaVentas=ve.getVenta();
		int existe=0;
		for(int i=0;i<listaVentas.size();i++) {
			Ventas.Venta ven = (Venta) listaVentas.get(i);
			if(ven.getNumventa()==numVenta) {
				ven.setUnidades(unidad);
				ven.setFecha(fecha);
				modificadoVenta= true;
				System.out.println(modificadoVenta);
			}
		}
		Marshaller m = jaxbContext.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(jaxbElement, new FileOutputStream("./ventasarticulos.xml"));
	} catch (JAXBException je) {
		System.out.println(je.getCause());
	} catch (IOException ioe) {
		System.out.println(ioe.getMessage());
	}
	}
}
