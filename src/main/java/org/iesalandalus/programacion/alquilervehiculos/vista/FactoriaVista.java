package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum FactoriaVista {

	TEXTO {
		@Override
		public Vista crear() {
			return new VistaTexto();
		}
	};

	public abstract Vista crear();
}