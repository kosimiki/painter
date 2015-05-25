package hu.unideb.inf.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class enables for JAXB to marshal and unmarshal DoubleProperty objects.
 
 * @author Miklós Kosárkár
 *
 */
public class DoublePropertyAdapter extends XmlAdapter<Double, DoubleProperty> {
	/**
	 * This method returns the {@code Double} value of a {@code DoubleProperty}.
	 * @param number to marshal
	 * @throws Exception may be threw by this method
	 */
	
	@Override
	public Double marshal(DoubleProperty number) throws Exception {
		return number.doubleValue();
	}
	
	/**
	 * This method returns   a {@code DoubleProperty} from a {@code Double}.
	 * @param number which is used to create the {@code DoubleProperty}
	 * @throws Exception may be threw by this method
	 */
	@Override
	public DoubleProperty unmarshal(Double number) throws Exception {
		return new SimpleDoubleProperty(number.doubleValue());
	}

}
