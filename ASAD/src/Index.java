/**
 * @author tejashree
 */

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class Index {
	
	String sOriginalSearchContent;
	public String getsOriginalSearchContent() {
		return sOriginalSearchContent;
	}

	public void setsOriginalSearchContent(String sOriginalSearchContent) {
		this.sOriginalSearchContent = sOriginalSearchContent;
	}

    Integer iLineNumber;
 
	public Integer getiLineNumber() {
		return iLineNumber;
	}

	public void setiLineNumber(Integer iLineNumber) {
		this.iLineNumber = iLineNumber;
	}

	Integer iOffset;
	
	public Integer getiOffset() {
		return iOffset;
	}

	public void setiOffset(Integer iOffset) {
		this.iOffset = iOffset;
	}

	String SOriginalSearchLine;
	HashMap<Integer, String> hmapListOfIndices;
	

	public String getSOriginalSearchLine() {
		return SOriginalSearchLine;
	}

	public void setSOriginalSearchLine(String sOriginalSearchLine) {
		SOriginalSearchLine = sOriginalSearchLine;
	}

	public HashMap<Integer, String> getHmapListOfIndices() {
		return hmapListOfIndices;
	}

	public void setHmapListOfIndices(HashMap<Integer, String> hmapListOfIndices) {
		this.hmapListOfIndices = hmapListOfIndices;
	}

	public Index() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @author tejashree
	 *
	 */
	 class ValueComparator implements Comparator<Integer> {
       
	    Map<Integer, String> iMap;
	    public ValueComparator(Map<Integer, String> imap) {
	        this.iMap = imap;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(Integer a, Integer b) {
	        if ((iMap.get(b).compareToIgnoreCase(iMap.get(a))) >= 0) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	}
	
	/**
	 * 
	 * @param ind
	 * @return
	 */
	public HashMap<Integer, String>getAllCircularShiftLines(Index ind){
		
		HashMap<Integer, String> hCircularShiftLines = new HashMap<Integer, String>();
		

		Integer iIndx = 0;
		if(ind != null){
			StringTokenizer st = new StringTokenizer(ind.getsOriginalSearchContent(),".");
			
			while(st.hasMoreTokens()){
				String sOrgLine = st.nextToken().toString();
				//System.out.println("Original Line : " +sOrgLine);
				ind.setSOriginalSearchLine(sOrgLine.trim());
				hCircularShiftLines.put(iIndx, ind.getSOriginalSearchLine()); //Add original line 
				
				String sCircularShiftLine = Utility.circularShiftLine(ind.getSOriginalSearchLine());
				iIndx++;	
				hCircularShiftLines.put(iIndx, sCircularShiftLine); //Add first circular shift Line


				while(!(ind.getSOriginalSearchLine().equalsIgnoreCase(sCircularShiftLine))){ //compare original line with shifted line
					hCircularShiftLines.put(iIndx, sCircularShiftLine); //add to map before shifting
					sCircularShiftLine = Utility.circularShiftLine(sCircularShiftLine);
					iIndx++;
				}
			}
		
		}
		/*for (Entry<Integer, String> entry  : Utility.entriesSortedByValues(hCircularShiftLines)) {
		    System.out.println(entry.getKey()+":"+entry.getValue());
		}*/

		ValueComparator indices =  new ValueComparator(hCircularShiftLines);
        TreeMap<Integer,String> sorted_map = new TreeMap<Integer,String>(indices);
        sorted_map.putAll(hCircularShiftLines);
        
        HashMap<Integer, String> finalMap = new HashMap<Integer, String>();
        Integer iNewIndx = 0;
        if(sorted_map != null && sorted_map.size() != 0){
			for(Integer id : sorted_map.keySet() ){
				
				finalMap.put(iNewIndx, hCircularShiftLines.get(id));
				//System.out.println("Sorted Index-"+id.intValue()+" is : "+hCircularShiftLines.get(id));
				iNewIndx++;
				
			}
			
		}
		return finalMap;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Index indxObj = new Index();
		indxObj.setsOriginalSearchContent("This is test for Web Search Engine. It would be used for keyword search.");
		//indxObj.setSOriginalSearchLine("This is test for Web Search Engine");
		HashMap<Integer, String> hIndx = indxObj.getAllCircularShiftLines(indxObj);
		
		//Print all the indices and their values
		if(hIndx != null && hIndx.size() != 0){
			for(Integer id : hIndx.keySet() ){
				
				//System.out.println("Index-"+id.intValue()+" is : "+hIndx.get(id));
				System.out.println(hIndx.get(id));
				
			}
			
		}
	
	}



}
