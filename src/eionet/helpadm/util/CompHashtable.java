package eionet.helpadm.util;

import java.util.*;

public class CompHashtable implements Comparator{
	
	private String key = "";
	
	public CompHashtable(String key){
		this.key = key;
	}
	
	public int compare(Object o1, Object o2){
		String val1 = (String)((Hashtable)o1).get(key);
		String val2 = (String)((Hashtable)o2).get(key);
		return val1.compareTo(val2); 
	}
}
