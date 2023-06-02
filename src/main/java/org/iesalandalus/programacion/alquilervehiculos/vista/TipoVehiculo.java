package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import java.org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public enum TipoVehiculo {

	TURIMOS("Turismo"), AUTOBUS("Autobus"), FURGONETA("Furgoneta");

	private String nombre;

	private TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}

	private static boolean esOrdinalValido(int ordinal) {
		boolean aux = false;
		if ((ordinal >= 0) && (ordinal <= Accion.values().length)) {

			aux = true;
		}
		return aux;
	}

	public static TipoVehiculo get(int ordinal) {
		if (!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("ERROR: El ordinal no es válido");
		}

		return TipoVehiculo.values()[ordinal];
	}

	public static TipoVehiculo get(Vehiculo vehiculo) {
		TipoVehiculo tipoVehiculo = null;
		if (vehiculo instanceof Turismo) {
			tipoVehiculo = TURIMOS;
		}
		if (vehiculo instanceof Autobus) {
			tipoVehiculo = AUTOBUS;
		}
		if (vehiculo instanceof Furgoneta) {
			tipoVehiculo = FURGONETA;
		}

		return tipoVehiculo;
	}

	@Override
	public String toString() {
		return nombre;
	}
}