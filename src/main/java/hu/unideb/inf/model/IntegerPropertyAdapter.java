package hu.unideb.inf.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class enables for JAXB to marshal and unmarshal IntegerProperty objects.
 
 * @author Miklós Kosárkár
 *
 */
public class IntegerPropertyAdapter extends
		XmlAdapter<Integer, IntegerProperty> {
	
	
	/**
	 * This method returns the {@code Integer} value of an {@code IntProperty}.
	 * @param integer to marshal
	 * @throws Exception may be threw by this method
	 */
	@Override
	public Integer marshal(IntegerProperty integer) throws Exception {
		return integer.get();
	}
	
	/**
	 * This method returns   an {@code IntegerProperty} from an {@code Integer}.
	 * @param integer which is used to create the {@code IntegerProperty}
	 * @throws Exception may be threw by this method
	 */
	@Override
	public IntegerProperty unmarshal(Integer integer) throws Exception {
		return new SimpleIntegerProperty(integer.intValue());
	}

}
