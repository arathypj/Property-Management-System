package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradeList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Trade> tradeList;
	
	
	//constructor
	public TradeList() {
		this.tradeList = new ArrayList<Trade>();
	}
	
	//return trade object list
	public List<Trade> getTrade(){
		return tradeList;
	}
	
	//sdd trade object to the list
	public void setTrade(Trade trade) {
		tradeList.add(trade);
	}
	
	
	//to print tradelist object
	public String toString() {
		String str = "";
		for(int i= 0; i<tradeList.size() ;i++) {
			str += tradeList.get(i);
		}
		return str;
	}
}
