package hu.unideb.inf.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBDAO implements IDAO{

	public List<Paint> paints;
	private File file;
	
	public JAXBDAO() {
		File dir = new File(System.getProperty("user.home") + "/Painter/");
		try {
			dir.mkdir();
			file = new File(System.getProperty("user.home")
					+ "/Painter/data.xml");
			file.createNewFile();
			
			if(!isFileEmpty())
				readXMLFile();
		
			
		} catch (IOException e) {
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
	
	public JAXBDAO(File file){
		this.file = file;
	}
	
	private void readXMLFile() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(PaintRoot.class);
		Unmarshaller um = context.createUnmarshaller();
		PaintRoot pr = (PaintRoot) um.unmarshal( file );
		paints = pr.getPaints();
		
	}

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

	@Override
	public List<Paint> getAllPaint() {
		if(paints== null)
			return new ArrayList<>();
		else
		return paints;
	}

	@Override
	public boolean addPaint(Paint p) {
		// TODO Auto-generated method stub
		return paints.add(p);
	}

	@Override
	public boolean isFileEmpty() {
		if(file.length() > 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public String getPath() {

		return file.getAbsolutePath();
	}

	

}
