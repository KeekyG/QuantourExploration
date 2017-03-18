package blService;

import javax.swing.JPanel;

/**
 * compare two different stocks and draw a pic to show their difference
 * @author KeekyG
 *
 */
public interface CompareBlService {
	
	public JPanel drawLastValue();
	public double getMaxValue(String stock);
	public double getMinValue(String stock);
	public JPanel drawLogValue();
	
}
