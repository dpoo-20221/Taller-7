package uniandes.cupi2.almacen.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;

public class AlmacenTest 
{
	
	private Almacen almacen;
	
	@Before
	public void escenario()
	{
		try {
			almacen = new Almacen(new File( "./data/datosTest.txt" ));
		} catch (AlmacenException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAlmacen()
	{
		assertEquals("Deberia cargar bien los nodos", "identificadorPrueba", almacen.darCategoriaRaiz().darIdentificador());
	}
	
	@Test
	public void testAgregarNodo()
	{
		try 
		{
			almacen.agregarNodo("identificadorPrueba2", "Categoria", "identificadorPrueba8", "nombrePrueba8");
			assertEquals("Deberia agregar correctamente el nodo", "identificadorPrueba8", almacen.darCategoriaRaiz().darPreorden().get(3).darIdentificador());
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testEliminarnodo()
	{
		try 
		{
			almacen.agregarNodo("identificadorPrueba2", "Categoria", "identificadorPrueba8", "nombrePrueba8");
			almacen.eliminarNodo("identificadorPrueba7");
			assertNotEquals("Deberia eliminar el nodo", "identificadorPrueba8", almacen.darCategoriaRaiz().darPreorden().get(3).darIdentificador());
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testVenderProducto()
	{
		almacen.venderProducto("identificadorPrueba7", 10);
		assertEquals(5000, almacen.darCategoriaRaiz().darValorVentas(), 0.01);
	}
	
	@Test
	public void testBuscarNodo()
	{
		assertEquals("Debio encontrar el nodo", "identificadorPrueba5", almacen.buscarNodo("identificadorPrueba5").darIdentificador());
		assertEquals("Debio encontrar el nodo", "identificadorPrueba2", almacen.buscarNodo("identificadorPrueba2").darIdentificador());
		assertEquals("Debio encontrar el nodo", "identificadorPrueba4", almacen.buscarNodo("identificadorPrueba4").darIdentificador());
	}
	
	@Test
	public void testAgregarProducto()
	{
		try 
		{
			almacen.agregarProducto("identificadorPrueba6", "identificadorPrueba8", "nombrePrueba8", "descripcionPrueba8", 1000);
			assertEquals("Debio agregar el nodo", "identificadorPrueba8", almacen.darCategoriaRaiz().darProductos().get(1).darCodigo());
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testEliminarProducto()
	{
		try 
		{
			almacen.agregarProducto("identificadorPrueba6", "identificadorPrueba8", "nombrePrueba8", "descripcionPrueba8", 1000);
			almacen.eliminarProducto("identificadorPrueba8");
			assertEquals("Debio eliminar el nodo", 1, almacen.darCategoriaRaiz().darProductos().size());
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
}
