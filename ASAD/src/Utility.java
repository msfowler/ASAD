/**
 * @author tejashree
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class Utility {

	public Utility() {
		// TODO Auto-generated constructor stub
	}
	
	public static String circularShiftLine(String sInputLine){
		
		String sReturnLine = "";
		
		//System.out.println("Input Line : "+sInputLine);
		
		if(sInputLine == null || sInputLine.equalsIgnoreCase(""))
			return ""; //return blank if i/p string is blank
		
		//Try using Iterator instead of ArrayList and StringTokenizer
		
		StringTokenizer st = new StringTokenizer(sInputLine," ");
		//int cnt = 0;
		ArrayList<String> arrOutputLine = new ArrayList<String>();
		while(st.hasMoreTokens()){
			String sWord = st.nextToken();
			arrOutputLine.add(sWord);
			//System.out.println("Word-"+(cnt++)+" is :"+sWord);
		}
		
		//Do a circular shift and display the shifted String
		
		if(arrOutputLine.size() != 0){
			
			String strFirstWord = arrOutputLine.remove(0);
			arrOutputLine.add(strFirstWord);
		
			//for (String s : arrOutputLine)
			for(int i=0; i<arrOutputLine.size()-1;i++)
			{
				String s = arrOutputLine.get(i);
				sReturnLine += s + " ";
			}
			//To append without space at end of L
			String s = arrOutputLine.get(arrOutputLine.size() - 1);
            sReturnLine+= s;
			//System.out.println("Return Line : "+sReturnLine);
		}
		 
		return sReturnLine;
	}
	
	
	/**
	  * not used
	  * @param aItems
	  * @return
	  */
	private static Map<String, Integer> sortMapByKey(Map<String, Integer> aItems){
	    TreeMap<String, Integer> result = 
	      new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
	    result.putAll(aItems);
	    return result;
	  }


	/**
	 * Not used
	 * @param map
	 * @return
	 */
	static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
            new Comparator<Map.Entry<K,V>>() {
                @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e1.getValue().compareTo(e2.getValue());
                    return res != 0 ? res : 1; // preserve items with equal values
                }
            }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		circularShiftLine("This is input String");

	}

}
