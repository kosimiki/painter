package hu.unideb.inf.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/**
 * This is a DAO class for Paint objects.
 * It uses xml to store data.
 * @author Miklós Kosárkár
 *
 */
public class JAXBDAO implements IDAO{
	
	/**
	 * The list of {@code Paint}s.
	 */
	public List<Paint> paints;
	/**
	 * The file object to the XMl.
	 */
	private File file;
	
	/**
	 * This constructor creates an XML file in the users home directory.
	 * If a file already exist then it will read it.
	 */
	public JAXBDAO() {
		File dir = new File(System.getProperty("user.home") + "/Painter/");
		try {
			dir.mkdir();
			file = new File(System.getProperty("user.home")
					+ "/Painter/data.xml");
			file.createNewFile();
			
			if(!isEmpty())
				readXMLFile();
		
			
		} catch (IOException e) {
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This constructor uses a parameter to set the file.
	 * @param file XML file
	 */
	public JAXBDAO(File file){
		this.file = file;
		try {
			readXMLFile();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method read the data from the {@code file} field.
	 * @throws JAXBException when something goes wrong whit unmarshaling or accessing the file
	 */
	private void readXMLFile() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(PaintRoot.class);
		Unmarshaller um = context.createUnmarshaller();
		PaintRoot pr = (PaintRoot) um.unmarshal( file );
		paints = pr.getPaints();
		
	}
	/**
	 * This method updates the list of {@code Paint}s.
	 * @param list the new list
	 * @return true if the update was successful false otherwise
	 */
	@Override
	public boolean updatePaints(List<Paint> list) {
		JAXBContext context;
		paints = list;
		try {
			context = JAXBContext.newInstance(PaintRoot.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(new PaintRoot(paints), file );
			return true; 
		} catch (JAXBException e) {
			return false;
		}
		
	}
	/**
	 * This method returns the list of {@code Paint}s.
	 * @return list of {@code Paint}s.
	 */
	@Override
	public List<Paint> getAllPaint() {
		if(paints== null)
			return new ArrayList<>();
		else
		return paints;
	}

	/**
	 * This method returns true of the source is empty.
	 * @return true of the source is empty false if not
	 */
	@Override
	public boolean isEmpty() {
		if(file.length() > 0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * This method returns the path to the source.
	 * @return path to the source.
	 */
	@Override
	public String getPath() {
		
		return file.getAbsolutePath();
	}

	

}
