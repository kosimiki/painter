package hu.unideb.inf.model;

import java.util.List;

public interface IDAO {
	public boolean updatePaints(List<Paint> list);
	public List<Paint> getAllPaint();
	public boolean addPaint(Paint p);
	boolean isFileEmpty();
	public String getPath();
}
