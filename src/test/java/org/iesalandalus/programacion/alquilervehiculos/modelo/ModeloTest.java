package org.iesalandalus.programacion.alquilervehiculos.modelo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.negocio.Turismos;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ModeloTest {
	
	private static final String MENSAJE_ERROR_INSERTAR_ALQUILER_NULO = "ERROR: No se puede realizar un alquiler nulo.";
	private static final String MENSAJE_ERROR_INSERTAR_ALQUILER_CLIENTE_NO_EXISTE = "ERROR: No existe el cliente del alquiler.";
	private static final String MENSAJE_ERROR_INSERTAR_ALQUILER_TURISMO_NO_EXISTE = "ERROR: No existe el turismo del alquiler.";
	private static final String MENSAJE_ERROR_DEVOLVER_ALQUILER_NO_EXISTE = "ERROR: No existe el alquiler a devolver.";

	@InjectMocks
	private Modelo modelo;

	@Mock
	private static Clientes clientes;
	@Mock
	private static Turismos turismos;
	@Mock
	private static Alquileres alquileres;

	private static Cliente cliente;
	private static Turismo turismo;
	private static Alquiler alquiler;

	@BeforeAll
	public static void setup() {
		cliente = mock();
		mockConstruction(Clientes.class);
		mockConstruction(Cliente.class);
		when(cliente.getNombre()).thenReturn("Bob Esponja");
		when(cliente.getDni()).thenReturn("11223344B");
		when(cliente.getTelefono()).thenReturn("950112233");
		turismo = mock();
		mockConstruction(Turismos.class);
		mockConstruction(Turismo.class);
		when(turismo.getMarca()).thenReturn("Seat");
		when(turismo.getModelo()).thenReturn("León");
		when(turismo.getMatricula()).thenReturn("1234BCD");
		when(turismo.getCilindrada()).thenReturn(90);
		LocalDate hoy = LocalDate.now();
		LocalDate ayer = hoy.minusDays(1);
		alquiler = mock();
		mockConstruction(Alquileres.class);
		mockConstruction(Alquiler.class);
		when(alquiler.getCliente()).thenReturn(cliente);
		when(alquiler.getTurismo()).thenReturn(turismo);
		when(alquiler.getFechaAlquiler()).thenReturn(ayer);
	}

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void terminarNoHaceNada() {
		assertDoesNotThrow(() -> modelo.terminar());
	}

	@Test
	void insertarClienteLlamaClientesInsertar() {
		assertDoesNotThrow(() -> modelo.insertar(cliente));
		assertDoesNotThrow(() -> verify(clientes).insertar(any(Cliente.class)));
		assertNotSame(cliente, modelo.buscar(cliente));
	}

	@Test
	void insertarTurismoLlamaTurismosInsertar() {
		assertDoesNotThrow(() -> modelo.insertar(turismo));
		assertDoesNotThrow(() -> verify(turismos).insertar(any(Turismo.class)));
		assertNotSame(turismo, modelo.buscar(turismo));
	}

	@Test
	void insertarAlquilerLlamaClientesBuscarTurismosBuscarAlquileresInsertar() {
		InOrder orden = inOrder(clientes, turismos, alquileres);
		when(clientes.buscar(cliente)).thenReturn(cliente);
		when(turismos.buscar(turismo)).thenReturn(turismo);
		assertDoesNotThrow(() -> modelo.insertar(alquiler));
		orden.verify(clientes).buscar(cliente);
		orden.verify(turismos).buscar(turismo);
		assertDoesNotThrow(() -> orden.verify(alquileres).insertar(any(Alquiler.class)));
		assertNotSame(alquiler, modelo.buscar(alquiler));
	}
	
	@Test
	void insertarAlquilerAlquilerNoValidoLanzaExcepcion() {
		Alquiler alquilerNulo = null;
		NullPointerException npe = assertThrows(NullPointerException.class, () -> modelo.insertar(alquilerNulo));
		assertEquals(npe.getMessage(), MENSAJE_ERROR_INSERTAR_ALQUILER_NULO);
		OperationNotSupportedException onse = assertThrows(OperationNotSupportedException.class, () -> modelo.insertar(alquiler));
		assertEquals(onse.getMessage(), MENSAJE_ERROR_INSERTAR_ALQUILER_CLIENTE_NO_EXISTE);
		when(clientes.buscar(cliente)).thenReturn(cliente);
		onse = assertThrows(OperationNotSupportedException.class, () -> modelo.insertar(alquiler));
		assertEquals(onse.getMessage(), MENSAJE_ERROR_INSERTAR_ALQUILER_TURISMO_NO_EXISTE);
	}

	@Test
	void buscarClienteLlamaClientesBuscar() {
		assertDoesNotThrow(() -> modelo.insertar(cliente));
		Cliente clienteBuscado = modelo.buscar(cliente);
		verify(clientes).buscar(cliente);
		assertNotSame(cliente, clienteBuscado);
	}

	@Test
	void buscarTurismoLlamaTurismosBuscar() {
		assertDoesNotThrow(() -> modelo.insertar(turismo));
		Turismo turismoBuscado = modelo.buscar(turismo);
		verify(turismos).buscar(turismo);
		assertNotSame(turismo, turismoBuscado);
	}

	@Test
	void buscarAlquilerLlamaAlquileresBuscar() {
		assertDoesNotThrow(() -> modelo.insertar(cliente));
		when(clientes.buscar(cliente)).thenReturn(cliente);
		when(turismos.buscar(turismo)).thenReturn(turismo);
		Alquiler alquilerBuscado = modelo.buscar(alquiler);
		verify(alquileres).buscar(alquiler);
		assertNotSame(alquiler, alquilerBuscado);
	}

	@Test
	void modificarClienteLlamaClientesBuscarModificar() {
		assertDoesNotThrow(() -> modelo.modificar(cliente, "Patricio Estrella", "950123456"));
		assertDoesNotThrow(() -> verify(clientes).modificar(cliente, "Patricio Estrella", "950123456"));
	}
	
	@Test
	void devolverAlquilerLlamaAlquileresBuscarAlquilerDevolver() {
		when(alquileres.buscar(alquiler)).thenReturn(alquiler);
		LocalDate hoy = LocalDate.now();
		assertDoesNotThrow(() -> modelo.devolver(alquiler, hoy));
		InOrder orden = inOrder(alquileres, alquiler);
		orden.verify(alquileres).buscar(alquiler);
		assertDoesNotThrow(() -> orden.verify(alquiler).devolver(hoy));
	}
	
	@Test
	void devolverAqluilerNoValidoLanzaExcepcion() {
		OperationNotSupportedException onse = assertThrows(OperationNotSupportedException.class, () -> modelo.devolver(alquiler, LocalDate.now()));
		assertEquals(onse.getMessage(), MENSAJE_ERROR_DEVOLVER_ALQUILER_NO_EXISTE);
	}

	@Test
	void borrarClienteLlamaAlquileresGetPrestamosBorrarClientesBorrar() {
		simularClienteConAlquileres();
		InOrder orden = inOrder(clientes, alquileres);
		assertDoesNotThrow(() -> modelo.borrar(cliente));
		orden.verify(alquileres).get(cliente);
		for (Alquiler alquiler : alquileres.get(cliente)) {
			assertDoesNotThrow(() -> orden.verify(alquileres).borrar(alquiler));
		}
		assertDoesNotThrow(() -> orden.verify(clientes).borrar(cliente));
	}

	private void simularClienteConAlquileres() {
		List<Alquiler> alquileresCliente = new ArrayList<>();
		Alquiler alquiler1 = mock();
		Alquiler alquiler2 = mock();
		alquileresCliente.add(alquiler1);
		alquileresCliente.add(alquiler2);
		when(alquileres.get(cliente)).thenReturn(alquileresCliente);
	}

	@Test
	void borrarTurismoLlamaAlquileresGetPrestamosBorrarTurismosBorrar() {
		simularTurismoConAlquileres();
		InOrder orden = inOrder(turismos, alquileres);
		assertDoesNotThrow(() -> modelo.borrar(turismo));
		orden.verify(alquileres).get(turismo);
		for (Alquiler alquiler : alquileres.get(turismo)) {
			assertDoesNotThrow(() -> orden.verify(alquileres).borrar(alquiler));
		}
		assertDoesNotThrow(() -> orden.verify(turismos).borrar(turismo));
	}

	private void simularTurismoConAlquileres() {
		List<Alquiler> alquileresTurismo = new ArrayList<>();
		Alquiler alquiler1 = mock();
		Alquiler alquiler2 = mock();
		alquileresTurismo.add(alquiler1);
		alquileresTurismo.add(alquiler2);
		when(alquileres.get(turismo)).thenReturn(alquileresTurismo);
	}
	
	@Test
	void borrarAlquilerLllamaAlquileresBorrar() {
		when(alquileres.buscar(alquiler)).thenReturn(alquiler);
		assertDoesNotThrow(() -> modelo.borrar(alquiler));
		assertDoesNotThrow(() -> verify(alquileres).borrar(alquiler));
	}
	
	@Test
	void getClientesLlamaClientesGet() {
		List<Cliente> clientesDevueltos = new ArrayList<>();
		clientesDevueltos.add(cliente);
		when(clientes.get()).thenReturn(clientesDevueltos);
		List<Cliente> clientesExistentes = modelo.getClientes();
		verify(clientes).get();
		assertNotSame(cliente, clientesExistentes.get(0));
	}
	
	@Test
	void getTurismosLlamaTurismosGet() {
		List<Turismo> turismosDevueltos = new ArrayList<>();
		turismosDevueltos.add(turismo);
		when(turismos.get()).thenReturn(turismosDevueltos);
		List<Turismo> turismosExistentes = modelo.getTurismos();
		verify(turismos).get();
		assertNotSame(turismo, turismosExistentes.get(0));
	}
	
	@Test
	void getAlquileresLlamaAlquileresGet() {
		List<Alquiler> alquileresDevueltos = new ArrayList<>();
		alquileresDevueltos.add(alquiler);
		when(alquileres.get()).thenReturn(alquileresDevueltos);
		List<Alquiler> alquileresExistentes = modelo.getAlquileres();
		verify(alquileres).get();
		assertNotSame(alquiler, alquileresExistentes.get(0));
	}
	
	@Test
	void getAlquileresClienteLlamaAlquileresGetCliente() {
		List<Alquiler> alquileresDevueltos = new ArrayList<>();
		alquileresDevueltos.add(alquiler);
		when(alquileres.get(cliente)).thenReturn(alquileresDevueltos);
		List<Alquiler> alquileresCliente = modelo.getAlquileres(cliente);
		verify(alquileres).get(cliente);
		assertNotSame(alquiler, alquileresCliente.get(0));
	}
	
	@Test
	void getAlquileresTurismoLlamaAlquileresGetTurismo() {
		List<Alquiler> alquileresDevueltos = new ArrayList<>();
		alquileresDevueltos.add(alquiler);
		when(alquileres.get(turismo)).thenReturn(alquileresDevueltos);
		List<Alquiler> alquileresTurismo = modelo.getAlquileres(turismo);
		verify(alquileres).get(turismo);
		assertNotSame(alquiler, alquileresTurismo.get(0));
	}

}
