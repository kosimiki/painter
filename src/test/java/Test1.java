import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Paint;
import hu.unideb.inf.model.Surface;

import org.junit.Before;
import org.junit.Test;


public class Test1 {
	
	MainApp mainApp;
	@Before
	public void setUp() throws Exception {
		mainApp = new MainApp();
		System.out.println(getClass().getResource("/data.xml").toURI());
		mainApp.setFile(new File( getClass().getResource("/data.xml").toURI()));
	}

	@Test
	public void testStreamToString() {
	   assertNotNull("Test file missing", 
	               getClass().getResource("/data.xml"));
	   
	}
	@Test
	public void testCalcSurface(){
		
		Surface surface = new Surface("Wall", 4.0, 1.0, 2);
		
		mainApp.getNotToPaintSurfaceData().clear();
		mainApp.getNotToPaintSurfaceData().add(surface);
		mainApp.getAllSurfaceData().clear();
		mainApp.getAllSurfaceData().add(surface);
		assertTrue(mainApp.calculateSurface() == 0.0);
		
		Surface surface2 = new Surface("Wall", 2.0, 1.0, 2);
		

		mainApp.getNotToPaintSurfaceData().clear();
		mainApp.getNotToPaintSurfaceData().add(surface2);
		assertTrue(mainApp.calculateSurface() == 4);
		
		
		mainApp.getNotToPaintSurfaceData().clear();
		mainApp.getNotToPaintSurfaceData().add(surface);
		mainApp.getAllSurfaceData().clear();
		mainApp.getAllSurfaceData().add(surface2);
		assertTrue(mainApp.calculateSurface() == -4);
	
	}
	@Test
	public void testReadFile() throws URISyntaxException{
		assertTrue(new File( getClass().getResource("/data.xml").toURI())!= null);
		assertTrue(mainApp.getPaintData().size()>0);
	}
	@Test
	public void testEqual() {
		Paint paint = new Paint();
		paint.setBrand("Snezka");
		paint.setName("White");
		paint.setColor("FFFFFF");
		paint.setSize(5.0);
		paint.setPrice(5.3);	
		
		Paint paint2 = new Paint();
		paint2.setBrand("Snezka");
		paint2.setName("White");
		paint2.setColor("FFFFFF");
		paint2.setSize(5.0);
		paint2.setPrice(5.3);
		
		assertEquals(paint, paint2);
		
		paint.setBrand("Snezka");
		paint.setName("White");
		paint.setColor("FFFFFF");
		paint.setSize(5.0);
		paint.setPrice(5.3);
		
	
		paint2.setBrand("Snezka");
		paint2.setName("White");
		paint2.setColor("FFFFFA");
		paint2.setSize(5.0);
		paint2.setPrice(5.3);
		
		assertNotEquals(paint, paint2);
		
	}
	
	@Test
	public void testHashCode() {
		Paint	paint = new Paint();
		paint.setBrand("Snezka");
		paint.setName("White");
		paint.setColor("FFFFFF");
		paint.setSize(5.0);
		paint.setPrice(5.3);
		assertTrue(paint.hashCode() == paint.hashCode());
		
		Paint	paint2 = new Paint();
		paint2.setBrand("Snezka");
		paint2.setName("White");
		paint2.setColor("FFFFFA");
		paint2.setSize(5.0);
		paint2.setPrice(5.3);
		assertFalse(paint.hashCode() == paint2.hashCode());
	}
	
	@Test
	public void testUniqueBrands(){
		List<Paint> list = mainApp.getUniqueBrands();
		for(int i =0; i<list.size(); i++){
			for(int j = 0; j<list.size(); j++)
				if(i!=j)
					assertFalse(list.get(i).getBrand().equals(list.get(j).getBrand()));
		}
	}
	
	@Test
	public void testCost(){
		//without surface
		assertTrue(0 == mainApp.calculateCost(mainApp.getPaintData().get(0)));
		Surface surface = new Surface("Wall", 4.1, 1.0, 2);
		
		mainApp.getAllSurfaceData().add(surface);
		Paint p = new Paint();
		p.setBrand("testBrand");
		p.setColor("FFAA00");
		p.setPrice(10.0);
		p.setSize(10.0);
		//1 litre = 10 > 4.1*2
		assertTrue(10 == mainApp.calculateCost(p));
		
		
	}
	
	@Test
	public void testCheapestOfThisColor(){
		mainApp.getPaintData().clear();
		Paint paint = new Paint();
		paint.setBrand("Sniezka");
		paint.setName("White");
		paint.setColor("FFFFFF");
		paint.setSize(5.0);
		paint.setPrice(5.3);	
		
		Paint paint2 = new Paint();
		paint2.setBrand("TestBrand");
		paint2.setName("White");
		paint2.setColor("FFFFFF");
		paint2.setSize(10.0);
		paint2.setPrice(5.3);	
		
		
		mainApp.getPaintData().add(paint2);
		mainApp.getPaintData().add(paint);
		assertEquals(paint2,mainApp.cheapestOfThisColor(paint));
		
		
		Paint paint3 = new Paint();
		paint3.setBrand("Cheapest");
		paint3.setName("White");
		paint3.setColor("FFFFFF");
		paint3.setSize(10.0);
		paint3.setPrice(5.2);	
		
		
		mainApp.getPaintData().add(paint3);
		assertEquals(paint3,mainApp.cheapestOfThisColor(paint));
		
	}
	@Test
	public void calcRequredLitres(){
		Surface surface = new Surface("TestWall", 12.3, 1.0, 2);
		mainApp.getAllSurfaceData().clear();
		mainApp.getNotToPaintSurfaceData().clear();
		mainApp.getAllSurfaceData().add(surface);
		assertEquals(5, mainApp.calculateRequredLitres(5), 0.000001);
		
		surface = new Surface("TestWall", 1.1, 2.0, 1);
		mainApp.getAllSurfaceData().clear();
		mainApp.getNotToPaintSurfaceData().clear();
		mainApp.getAllSurfaceData().add(surface);
		assertEquals(1, mainApp.calculateRequredLitres(5), 0.000001);
		
	}
	
	@Test
	public void testReqiredNumber(){
		
		assertEquals("1 of 1l ", mainApp.calcRequiredNumberOfPaints(10, 10));
		assertEquals("10 of 10l ", mainApp.calcRequiredNumberOfPaints(100, 1));
		assertEquals("1 of 10l 1 of 5l 1 of 2l ",
				mainApp.calcRequiredNumberOfPaints(17, 1));
		
	}


}
