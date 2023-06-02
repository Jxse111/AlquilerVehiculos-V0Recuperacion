package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum Accion {

	SALIR("Salir") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 0.");
			vista.terminar();
		}
	},
	INSERTAR_CLIENTE("Insertar cliente") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 1:");
			vista.insertarCliente();
		}
	},
	INSERTAR_VEHICULO("Insertar vehículo") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 2: ");
			vista.insertarVehiculo();
		}
	},
	INSERTAR_ALQUILER("Insertar alquiler") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 3: ");
			vista.insertaAlquiler();
		}
	},
	BUSCAR_CLIENTE("Buscar cliente") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 4: ");
			vista.buscarCliente();
		}
	},
	BUSCAR_VEHICULO("Buscar vehículo") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 5: ");
			vista.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("Buscar alquiler") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 6: ");
			vista.buscarAlquiler();
		}
	},
	MODIFICAR_CLIENTE("Modificar cliente") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 7: ");
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("Devolver alquiler del cliente") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 8: ");
			vista.devolverAlquilerCliente();
		}
	},
	DEVOLVER_ALQUILER_VEHICULO("Devolver alquiler del vehículo") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 9: ");
			vista.devolverAlquilerVehiculo();
		}
	},
	BORRAR_CLIENTE("Borrar cliente") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 10: ");
			vista.borrarCliente();
		}
	},
	BORRAR_VEHICULO("Borrar vehículo") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 11: ");
			vista.borrarVehiculo();
		}
	},
	BORRAR_ALQUILER("Borrar alquiler") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 12: ");
			vista.borrarAlquiler();
		}
	},
	LISTAR_CLIENTES("Listar clientes") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 13: ");
			vista.listarClientes();
		}
	},
	LISTAR_VEHICULOS("Lista vehículos") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 14: ");
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Listar alquileres") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 15: ");
			vista.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTE("Listar alquileres cliente") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 16: ");
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar alquileres vehículos") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 17: ");
			vista.listarAlquileresVehiculo();
		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("Mostrar estadisticas mensuales") {
		@Override
		public void ejecutar() {
			System.out.printf("%nHa sido elegida la opción 18: ");
			vista.mostrarEstadisticasMensualesTipoVehiculo();
		}
	};

	private String texto;
	private static VistaTexto vista;

	private Accion(String texto) {

		this.texto = texto;
	}

	private boolean esOrdinalValido(int ordinal) {
		boolean aux = false;
		if ((ordinal >= 0) && (ordinal <= 16)) {

			aux = true;
		}
		return aux;
	}

	public Accion get(int ordinal) {
		if (!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("ERROR: El ordinal no es válido");
		}

		return Accion.values()[ordinal];
	}

	protected static void setVista(VistaTexto vista) {
		if (vista == null) {
			throw new NullPointerException("ERROR: ");
		}

		Accion.vista = vista;
	}

	public abstract void ejecutar();

	@Override
	public String toString() {
		return texto;
	}
}