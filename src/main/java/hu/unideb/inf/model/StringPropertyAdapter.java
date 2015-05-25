package hu.unideb.inf.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * This class enables for JAXB to marshal and unmarshal StringProperty objects.
 
 * @author Miklós Kosárkár
 *
 */
public class StringPropertyAdapter extends XmlAdapter<String, StringProperty> {
	/**
	 * This method returns the {@code String} value of a {@code StringProperty}.
	 * @param stringProperty to marshal
	 * @throws Exception may be threw by this method
	 */
	@Override
	public String marshal(StringProperty stringProperty) throws Exception {
		
		return stringProperty.get();
	}
	/**
	 * This method returns   a {@code StringProperty} from a {@code String}.
	 * @param string which is used to create the {@code StringProperty}
	 * @throws Exception may be threw by this method
	 */
	@Override
	public StringProperty unmarshal(String string) throws Exception {
		return new SimpleStringProperty(string);
	}

}
