package hu.unideb.inf.model;

import java.util.List;
/**
 * Data Access Object interface.
 * @author Miklós Kosárkár 
 *
 */
public interface IDAO {
	/**
	 * This method updates the list of {@code Paint}s.
	 * @param list the new list
	 * @return true if the update was successful false otherwise
	 */
	public boolean updatePaints(List<Paint> list);
	/**
	 * This method returns the list of {@code Paint}s.
	 * @return list of {@code Paint}s.
	 */
	public List<Paint> getAllPaint();
	/**
	 * This method returns true of the source is empty.
	 * @return true of the source is empty false if not
	 */
	boolean isEmpty();
	/**
	 * This method returns the path to the source.
	 * @return path to the source.
	 */
	public String getPath();
}
