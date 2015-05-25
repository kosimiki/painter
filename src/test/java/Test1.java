import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
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
		mainApp.setFile(new File("data.xml"));
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

}
