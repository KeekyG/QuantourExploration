package po;

import java.util.HashMap;

/**
 * @author ssw's daddy
 * 
 * 利息PO，用来存放银行每年的一年利息
 * 
 * interests 银行利息，左边为年份，右边为利率
 *
 */
public class InterestPO {
	
	private HashMap<String, Double> interests;
	
	public InterestPO(HashMap<String, Double> interests) {
		this.interests = interests;
	}

	public HashMap<String, Double> getInterests() {
		return interests;
	}
}
