package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public abstract class VistaTexto extends Vista {

	public VistaTexto() {
		super();
		Accion.setVista(this);
	}

	@Override
	public void comenzar() {
		Accion aux;
		do {
			Consola.mostrarMenuAcciones();
			aux = Consola.elegirAccion();

			aux.ejecutar();

		} while (aux != Accion.SALIR);
	}

	public void terminar() {
		System.out.println("Hasta luego!!");

	}

	public void insertarCliente() {
		Consola.mostrarCabecera("Insertar clientes");
		try {
			getControlador().insertar(Consola.leerCliente());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha insertado el cliente");

	}

	public void insertarVehiculo() {
		Consola.mostrarCabecera("Insertar vehiculo");
		try {
			getControlador().insertar(Consola.leerVehiculo());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}

		System.out.println("Se ha insertado un vehiculo.");
	}

	public void insertaAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler.");
		try {
			getControlador().insertar(Consola.leerAlquiler());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Se ha insertado un alquiler.");
	}

	public void buscarCliente() {
		Consola.mostrarCabecera("Buscar cliente");
		try {
			System.out.println(getControlador().buscar(Consola.leerClienteDni()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
	}

	public void buscarVehiculo() {
		Consola.mostrarCabecera("Buscar vehiculo");
		try {
			System.out.println(getControlador().buscar(Consola.leerVehiculoMatricula()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
	}

	public void buscarAlquiler() {
		Consola.mostrarCabecera("Buscar alquiler");
		try {
			System.out.println(getControlador().buscar(Consola.leerAlquiler()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}

		System.out.println("Se ha buscado el cliente.");
	}

	public void modificarCliente() {
		Consola.mostrarCabecera("Modificar cliente");

		try {
			getControlador().modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha modificado el cliente.");
	}

	public void devolverAlquilerCliente() {
		Consola.mostrarCabecera("Devolver cliente");

		try {
			getControlador().devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha devuelto el alquiler.");
	}

	public void devolverAlquilerVehiculo() {
		Consola.mostrarCabecera("Devolver vehiculo");

		try {
			getControlador().devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha devuelto el alquiler.");
	}

	public void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			getControlador().borrar(Consola.leerClienteDni());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha borrado el cliente.");
	}

	public void borrarVehiculo() {
		Consola.mostrarCabecera("Borrar vehiculo");
		try {
			getControlador().borrar(Consola.leerVehiculoMatricula());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha borrado el vehiculo.");
	}

	public void borrarAlquiler() {
		Consola.mostrarCabecera("Borrar alquiler");
		try {
			getControlador().buscar(Consola.leerAlquiler());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha borrado el alquiler.");
	}

	public void listarClientes() {
		Consola.mostrarCabecera("Listar clientes");
		try {
			for (Cliente aux : getControlador().getClientes()) {
				System.out.println(aux);
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("se ha listado los clientes.");
	}

	public void listarVehiculos() {
		Consola.mostrarCabecera("Listar vehiculos");
		try {
			for (Vehiculo aux : getControlador().getVehiculos()) {
				System.out.println(aux);
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se han listado los vehiculo.");

	}

	public void listarAlquileres() {
		Consola.mostrarCabecera("Listar alquileres");
		try {
			for (Alquiler aux : getControlador().getAlquileres()) {
				System.out.println(aux);
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se han listado los alquileres");

	}

	public void listarAlquileresCliente() {
		Consola.mostrarCabecera("Listar alquileres clientes");
		try {
			for (Alquiler aux : getControlador().getAlquileres(Consola.leerClienteDni())) {
				System.out.println(aux);
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());

		}
		System.out.println("Se ha listado los alquileres de clientes.");

	}

	public void listarAlquileresVehiculo() {
		Consola.mostrarCabecera("Listar alquileres vehiculo");
		try {
			for (Alquiler aux : getControlador().getAlquileres(Consola.leerVehiculoMatricula())) {
				System.out.println(aux);
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Se ha listado los alquileres de vehiculos.");
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {
		LocalDate aux = Consola.leerMes();
		Map<TipoVehiculo, Integer> estadisticasIniciada = inicializarEstadisticas();

		for (Alquiler alquiler : getControlador().getAlquileres()) {
			if (aux.getMonthValue() == alquiler.getFechaAlquiler().getMonthValue()
					&& aux.getYear() == alquiler.getFechaAlquiler().getYear()) {
				TipoVehiculo auxVehiculo = TipoVehiculo.get(alquiler.getVehiculo());
				estadisticasIniciada.put(auxVehiculo, estadisticasIniciada.get(auxVehiculo));
			}
		}
		System.out.println(estadisticasIniciada);
	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {

		Map<TipoVehiculo, Integer> estadisticasIniciada = new EnumMap<>(TipoVehiculo.class);
		estadisticasIniciada.put(TipoVehiculo.TURIMOS, 0);
		estadisticasIniciada.put(TipoVehiculo.AUTOBUS, 0);
		estadisticasIniciada.put(TipoVehiculo.FURGONETA, 0);
		return estadisticasIniciada;
	}
}