package uniandes.cupi2.almacen.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.Marca;
import uniandes.cupi2.almacen.mundo.Producto;

public class CategoriaTest 
{
	
	private Categoria categoria;
	
	@Before
	public void escenario()
	{
		categoria = new Categoria("identificadorPrueba", "nombrePrueba");
	}
	
	@Test
	public void testCategoria()
	{
		assertNotNull("La lista de nodos deberia estar creada", categoria.darNodos());
		assertEquals("Deberia guardar el identificador dado","identificadorPrueba",categoria.darIdentificador());
		assertEquals("Deberia guardar el nombre dado","nombrePrueba",categoria.darNombre());
		assertEquals("Deberia ser de tipo categoria","Categoria",categoria.darTipo());
	}
	
	@Test
	public void testAgregarNodo()
	{
		try 
		{
			categoria.agregarNodo("identificadorPrueba", "Categoria", "identificadorPrueba2", "nombrePrueba2");
			assertEquals("No se agrego el nodo a la lista de nodos", 1, categoria.darNodos().size());
			assertEquals("No se agrego el nodo correctamente", "identificadorPrueba2", categoria.darNodos().get(0).darIdentificador());
		}
		catch (Exception e)
		{
			
		}
	}
	
	@Test
	public void testBuscarNodo()
	{
		try 
		{
			Categoria nodo = new Categoria( "identificadorPrueba2", "nombrePrueba2" );
			categoria.agregarNodo("identificadorPrueba", nodo);
			assertEquals("Deberia encontrar el nodo buscado", nodo, categoria.buscarNodo("identificadorPrueba2"));			
		}
		catch (Exception e)
		{
			
		}
	}
	
	@Test
	public void testDarValorVentas()
	{
		try 
		{
			Marca nodo = new Marca( "identificadorPrueba2", "nombrePrueba2");
			nodo.agregarProducto("identificadorPrueba3", "nombrePrueba3", "descripcionPrueba3", 500);
			nodo.agregarProducto("identificadorPrueba4", "nombrePrueba4", "descripcionPrueba4", 1000);
			nodo.darProductos().get(0).vender(10);
			nodo.darProductos().get(1).vender(5);
			categoria.agregarNodo("identificadorPrueba", nodo);
			assertEquals(10000, categoria.darValorVentas(), 0.001);
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testEliminarNodo()
	{
		try 
		{
			Categoria nodo = new Categoria( "identificadorPrueba2", "nombrePrueba2" );
			categoria.agregarNodo("identificadorPrueba", nodo);
			categoria.eliminarNodo("identificadorPrueba2");
			assertEquals("Deberia eliminar el nodo de la lista de nodos", 0, categoria.darNodos().size());			
		}
		catch (Exception e)
		{
			
		}
	}
	
	@Test
	public void testBuscarProducto()
	{
		try 
		{
			Marca nodo = new Marca( "identificadorPrueba2", "nombrePrueba2");
			nodo.agregarProducto("identificadorPrueba3", "nombrePrueba3", "descripcionPrueba3", 500);
			Producto producto = new Producto("identificadorPrueba3", "nombrePrueba3", "descripcionPrueba3", 500);
			categoria.agregarNodo("identificadorPrueba", nodo);
			assertEquals("Deberia encontrar el producto buscado", producto.darCodigo(), categoria.buscarProducto("identificadorPrueba3").darCodigo());
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	
	@Test
	public void testBuscarPadre()
	{
		try 
		{
			Marca nodo = new Marca( "identificadorPrueba2", "nombrePrueba2");
			categoria.agregarNodo("identificadorPrueba", nodo);
			assertEquals("Deberia encontrar la categoria padre del nodo dado", categoria, categoria.buscarPadre("identificadorPrueba2"));
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testDarProductos()
	{
		try 
		{
			Marca nodo = new Marca( "identificadorPrueba2", "nombrePrueba2");
			nodo.agregarProducto("identificadorPrueba3", "nombrePrueba3", "descripcionPrueba3", 500);
			nodo.agregarProducto("identificadorPrueba4", "nombrePrueba4", "descripcionPrueba4", 1000);
			Producto producto1 = new Producto("identificadorPrueba3", "nombrePrueba3", "descripcionPrueba3", 500);
			Producto producto2 = new Producto("identificadorPrueba4", "nombrePrueba4", "descripcionPrueba4", 1000);
			categoria.agregarNodo("identificadorPrueba", nodo);
			assertEquals("Deberia dar el producto dado", producto1.darCodigo(), categoria.darProductos().get(0).darCodigo());
			assertEquals("Deberia dar el producto dado", producto2.darCodigo(), categoria.darProductos().get(1).darCodigo());
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testDarMarcas()
	{
		try 
		{
			Marca nodo1 = new Marca( "identificadorPrueba2", "nombrePrueba2");
			Marca nodo2 = new Marca( "identificadorPrueba3", "nombrePrueba3");
			categoria.agregarNodo("identificadorPrueba", nodo1);
			categoria.agregarNodo("identificadorPrueba", nodo2);
			assertEquals("Deberia dar la marca dada",  nodo1, categoria.darMarcas().get(0));
			assertEquals("Deberia dar la marca dada",  nodo2, categoria.darMarcas().get(1));
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testDarPreorden()
	{
		try 
		{
			Categoria nodo2 = new Categoria("identificadorPrueba2", "nombrePrueba2");
			Marca nodo3 = new Marca( "identificadorPrueba3", "nombrePrueba3");
			Categoria nodo4 = new Categoria("identificadorPrueba4", "nombrePrueba4");
			Marca nodo5 = new Marca( "identificadorPrueba5", "nombrePrueba5");
			Marca nodo6 = new Marca( "identificadorPrueba6", "nombrePrueba6");
			categoria.agregarNodo("identificadorPrueba", nodo2);
			categoria.agregarNodo("identificadorPrueba2", nodo3);
			categoria.agregarNodo("identificadorPrueba", nodo4);
			categoria.agregarNodo("identificadorPrueba4", nodo5);
			categoria.agregarNodo("identificadorPrueba4", nodo6);
			assertEquals("Deberia dar los nodos en preorden", categoria,categoria.darPreorden().get(0));
			assertEquals("Deberia dar los nodos en preorden", nodo2,categoria.darPreorden().get(1));
			assertEquals("Deberia dar los nodos en preorden", nodo3,categoria.darPreorden().get(2));
			assertEquals("Deberia dar los nodos en preorden", nodo4,categoria.darPreorden().get(3));
			assertEquals("Deberia dar los nodos en preorden", nodo5,categoria.darPreorden().get(4));
			assertEquals("Deberia dar los nodos en preorden", nodo6,categoria.darPreorden().get(5));
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
	@Test
	public void testDarPosorden()
	{
		try 
		{
			Categoria nodo2 = new Categoria("identificadorPrueba2", "nombrePrueba2");
			Marca nodo3 = new Marca( "identificadorPrueba3", "nombrePrueba3");
			Categoria nodo4 = new Categoria("identificadorPrueba4", "nombrePrueba4");
			Marca nodo5 = new Marca( "identificadorPrueba5", "nombrePrueba5");
			Marca nodo6 = new Marca( "identificadorPrueba6", "nombrePrueba6");
			categoria.agregarNodo("identificadorPrueba", nodo2);
			categoria.agregarNodo("identificadorPrueba2", nodo3);
			categoria.agregarNodo("identificadorPrueba", nodo4);
			categoria.agregarNodo("identificadorPrueba4", nodo5);
			categoria.agregarNodo("identificadorPrueba4", nodo6);
			assertEquals("Deberia dar los nodos en postorden", nodo3,categoria.darPosorden().get(0));
			assertEquals("Deberia dar los nodos en postorden", nodo2,categoria.darPosorden().get(1));
			assertEquals("Deberia dar los nodos en postorden", nodo5,categoria.darPosorden().get(2));
			assertEquals("Deberia dar los nodos en postorden", nodo6,categoria.darPosorden().get(3));
			assertEquals("Deberia dar los nodos en postorden", nodo4,categoria.darPosorden().get(4));
			assertEquals("Deberia dar los nodos en postorden", categoria,categoria.darPosorden().get(5));
		} 
		catch (AlmacenException e) 
		{
			
		}
	}
	
}
