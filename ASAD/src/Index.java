/**
 * @author tejashree
 */

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
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

	Integer iwordOffset;
	
	
	public Integer getIwordOffset() {
		return iwordOffset;
	}

	public void setIwordOffset(Integer iwordOffset) {
		this.iwordOffset = iwordOffset;
	}

	String SOriginalSearchLine;
	
	//HashMap<Integer, String> hmapListOfIndices;
	

	public String getSOriginalSearchLine() {
		return SOriginalSearchLine;
	}

	public void setSOriginalSearchLine(String sOriginalSearchLine) {
		SOriginalSearchLine = sOriginalSearchLine;
	}

	/*public HashMap<Integer, String> getHmapListOfIndices() {
		return hmapListOfIndices;
	}

	public void setHmapListOfIndices(HashMap<Integer, String> hmapListOfIndices) {
		this.hmapListOfIndices = hmapListOfIndices;
	}*/
	
	
	public ArrayList<Index> aCircularShiftedLines;
	

	public ArrayList<Index> getaCircularShiftedLines() {
		return aCircularShiftedLines;
	}

	public void setaCircularShiftedLines(ArrayList<Index> aCircularShiftedLines) {
		this.aCircularShiftedLines = aCircularShiftedLines;
	}

	public String sCircularShiftedLine;
	
	public String getsCircularShiftedLine() {
		return sCircularShiftedLine;
	}

	public void setsCircularShiftedLine(String sCircularShiftedLine) {
		this.sCircularShiftedLine = sCircularShiftedLine;
	}

	public Index() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * 
	 * @param ind
	 * @return
	 */
	public HashMap<Integer, Index>getAllCircularShiftLines(Index hIndx){
		
		HashMap<Integer, Index> hOriginalLines = separateInputLines(hIndx);
		Integer iIndx = 0;
		HashMap<Integer, Index> hCircularShiftLines = new HashMap<Integer, Index>();;
		
		if(hOriginalLines != null && hOriginalLines.size() != 0){
			
			for(Integer id : hOriginalLines.keySet() ){
				
				
				HashMap<Integer, Index> indxResultMap = circularShiftLine(hOriginalLines.get(id));
				
				if (indxResultMap != null) {
					//ArrayList<Index> arrList = indxResultObj.getaCircularShiftedLines();
					
					//for(int i= 0; i< arrList.size() ; i++) {
					for(Integer idtest : indxResultMap.keySet() ){
				
						//Index idOb = (Index)arrList.get(i);
						//System.out.println("Index-"+idOb.getiLineNumber()+" Offset-"+idOb.getIwordOffset() + "  is : "+ idOb.getsCircularShiftedLine());
						Index idOb = indxResultMap.get(idtest);
						//System.out.println("Index-"+idOb.getiLineNumber()+" Offset-"+idOb.getIwordOffset() + "  is : "+ idOb.getsCircularShiftedLine());
						
						hCircularShiftLines.put(iIndx++, idOb);
					}
					
				}
				
				//hCircularShiftLines.putAll(indxResultMap);
				
			}
			
		}
		
		
		
		/*for (Entry<Integer, String> entry  : Utility.entriesSortedByValues(hCircularShiftLines)) {
		    System.out.println(entry.getKey()+":"+entry.getValue());
		}*/

		HashMap<Integer, Index> finalMap = sortAllCircularShiftedLines(hCircularShiftLines);
		return finalMap;
	}

	/**
	 * @param hCircularShiftLines
	 * @return
	 */
	private HashMap<Integer, Index> sortAllCircularShiftedLines(
			HashMap<Integer, Index> hCircularShiftLines) {
		Utility.ValueComparator indices =  new Utility.ValueComparator(hCircularShiftLines);
        TreeMap<Integer,Index> sorted_map = new TreeMap<Integer,Index>(indices);
        sorted_map.putAll(hCircularShiftLines);
        
        HashMap<Integer, Index> finalMap = new HashMap<Integer, Index>();
        Integer iNewIndx = 0;
        if(sorted_map != null && sorted_map.size() != 0){
			for(Integer id : sorted_map.keySet() ){
				
				finalMap.put(iNewIndx, hCircularShiftLines.get(id));
				//System.out.println("Sorted Index-"+id.intValue()+" is : "+hCircularShiftLines.get(id).getsCircularShiftedLine());
				iNewIndx++;
				
			}
			
		}
		return finalMap;
	}

	/**
	 * @param ind
	 * @return
	 */
	private HashMap<Integer, Index> separateInputLines(Index ind) {
		HashMap<Integer, Index> hOriginalLines = new HashMap<Integer, Index>();
		
		Integer iIndx = 0;
		Index indxObj;
		if(ind != null){
			StringTokenizer st = new StringTokenizer(ind.getsOriginalSearchContent(),".");
			
			while(st.hasMoreTokens()){
				String sOrgLine = st.nextToken().toString();
				indxObj = new Index();
				indxObj.setSOriginalSearchLine(sOrgLine.trim());
				indxObj.setiLineNumber(iIndx + 1);
				hOriginalLines.put(iIndx, indxObj);
				iIndx++;
				
			
			}
	
		}
		return hOriginalLines;
	}

	/**
	 * @param ind
	 * @param hCircularShiftLines
	 * @param iIndx
	 * @param st
	 * @return
	 */
	private HashMap <Integer, Index> circularShiftLine(
			 		Index indLineObj
			) {
		
		HashMap<Integer, Index> indxMap = new HashMap<Integer, Index>();
		Integer indx = 0;
		if(indLineObj != null ){
				//ArrayList<Index> arrObj = new ArrayList<Index>();
				Integer iWordOffset = 1;
				String sOrgLine = indLineObj.getSOriginalSearchLine();
				Index indWordObj = new Index();
				indWordObj.setiLineNumber(indLineObj.getiLineNumber());
				indWordObj.setIwordOffset(iWordOffset++);
				indWordObj.setsCircularShiftedLine(sOrgLine);
				//arrObj.add(indWordObj);
				indxMap.put(indx, indWordObj);
				indx++;
						
				String sCircularShiftLine = Utility.circularShiftLine(sOrgLine);
		
				while (!(sOrgLine.equalsIgnoreCase(sCircularShiftLine))){ //compare original line with shifted line
					
					indWordObj = new Index();
					indWordObj.setiLineNumber(indLineObj.getiLineNumber());
					indWordObj.setIwordOffset(iWordOffset++);
					indWordObj.setSOriginalSearchLine(sOrgLine);
					indWordObj.setsCircularShiftedLine(sCircularShiftLine);
					//arrObj.add(indWordObj);
					indxMap.put(indx, indWordObj);
					
					sCircularShiftLine = Utility.circularShiftLine(sCircularShiftLine);
					indx++;
				}
				//indLineObj.setaCircularShiftedLines(arrObj);	
		}
	
		return indxMap;
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Index indxObj = new Index();
		indxObj.setsOriginalSearchContent("This is test for Web Search Engine. It would be used for keyword search.");
		//indxObj.setSOriginalSearchLine("This is test for Web Search Engine");
		HashMap<Integer, Index> hIndx = indxObj.getAllCircularShiftLines(indxObj);
		
		//Print all the indices and their values
		if(hIndx != null && hIndx.size() != 0){
			for(Integer id : hIndx.keySet() ){
				
				//System.out.println("Index-"+id.intValue()+" is : "+hIndx.get(id));
				//System.out.println(hIndx.get(id).getsCircularShiftedLine());
				System.out.println(hIndx.get(id).getsCircularShiftedLine()+"  		First : "+hIndx.get(id).getiLineNumber()+"  	Offset : "+hIndx.get(id).getIwordOffset());
			}
			
		}
	
	}



}
