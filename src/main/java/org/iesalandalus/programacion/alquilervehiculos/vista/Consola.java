package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.awt.Desktop.Action;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);
	private static final String PATRON_MES = "MM/yyyy";

	private Consola() {

	}

	public static void mostrarCabecera(String mensaje) {
		StringBuilder cabecera = new StringBuilder();
		cabecera.append("-");
		System.out.printf("%n%s%n", mensaje);
		for (int i = 0; i < mensaje.length(); i++) {
			System.out.print(cabecera);
		}
		System.out.println("");
	}

	public static void mostrarMenuAcciones() {
		mostrarCabecera("menú");
		for (int i = 0; i < Action.values().length; i++) {
			System.out.printf("%d.-%s %n", i, Action.values()[i]);
		}
	}

	public static Action elegirAccion() {
		int auxElegirAccion = 0;

		do {
			auxElegirAccion = leerEntero("Introduce una acción: ");
		} while (auxElegirAccion < 0 && auxElegirAccion > Action.values().length);

		return Action.values()[auxElegirAccion];
	}

	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		return Entrada.cadena();
	}

	private static Integer leerEntero(String mensaje) {
		System.out.print(mensaje);
		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje, String patron) {
		LocalDate aux = null;

		try {
			System.out.print(mensaje);
			if (patron.equals(PATRON_MES)) {
				aux = LocalDate.parse("01/" + Entrada.cadena(), FORMATO_FECHA);
			} else {
				aux = LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
			}
		} catch (DateTimeParseException e) {
			System.out.println(e.getMessage());

		}
		return aux;
	}

	public static Cliente leerCliente() {
		Cliente nuevoCliente = null;
		nuevoCliente = new Cliente(leerNombre(), leerCadena("Introduce un dni: "), leerTelefono());
		return nuevoCliente;

	}

	public static Cliente leerClienteDni() {
		return Cliente.getClienteConDni(leerCadena("Introduce un dni: "));

	}

	public static String leerNombre() {

		return leerCadena("Introduce un nombre: ");

	}

	public static String leerTelefono() {
		return leerCadena("Introduce un número de teléfono: ");

	}

	public static Vehiculo leerVehiculo() {
		mostrarMenuTiposVehiculos();
		return leerVehiculo(elegirTipoVehiculo());

	}

	private static void mostrarMenuTiposVehiculos() {
		mostrarCabecera("menú tipo de vehículos; ");
		for (int i = 0; i < TipoVehiculo.values().length; i++) {
			System.out.printf("%d.-%s %n", i, TipoVehiculo.values()[i]);
		}
	}

	private static TipoVehiculo elegirTipoVehiculo() {
		TipoVehiculo auxElegirAccion = null;
		do {
			try {
				auxElegirAccion = TipoVehiculo.get(leerEntero("Introduce una acción: "));

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (auxElegirAccion == null);

		return auxElegirAccion;
	}

	private static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo) {
		Vehiculo vehiculo = null;
		String marca = leerCadena("Introduce una marca: ");
		String modelo = leerCadena("Introduce un modelo: ");
		String matricula = leerCadena("Introduce una matrícula: ");

		if (tipoVehiculo == TipoVehiculo.TURIMOS) {
			vehiculo = new Turismo(marca, modelo, leerEntero("Introduce una cilindrada: "), matricula);
		}
		if (tipoVehiculo == TipoVehiculo.AUTOBUS) {
			vehiculo = new Autobus(marca, modelo, leerEntero("Introduce un número de plazas: "), matricula);
		}
		if (tipoVehiculo == TipoVehiculo.FURGONETA) {

			vehiculo = new Furgoneta(marca, modelo, leerEntero("Introduce el pma: "),
					leerEntero("Introduce un número de plazas: "), matricula);
		}

		return vehiculo;
	}

	public static Vehiculo leerVehiculoMatricula() {
		return Vehiculo.getVehiculoConMatricula(leerCadena("Introduce una matricula: "));

	}

	public static Alquiler leerAlquiler() {
		Alquiler nuevoAlquiler = null;
		nuevoAlquiler = new Alquiler(leerClienteDni(), leerVehiculoMatricula(),
				leerFecha("Introduce una fecha de alquiler: ", PATRON_FECHA));
		return nuevoAlquiler;

	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("Introduce una fecha de devolución: ", PATRON_FECHA);

	}

	public static LocalDate leerMes() {
		return leerFecha("Introduceme un mes y un año: ", PATRON_MES);
	}

}