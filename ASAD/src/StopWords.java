import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;



public class StopWords {

	private List<String> list; 
	
	public StopWords(String file) {
		loadStopFile(file);
	}
	
	public void loadStopFile(String pathname){
		//Code from StackOverflow question #13227587
	
	    String line = null;
	    list = new ArrayList<String>();
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(pathname));
	        while((line = reader.readLine()) != null){
	            list.add(line);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	//For debugging only
	public void print()
	{
		int i;
		for(i=0; i<list.size(); i++)
		{
			System.out.println(list.get(i));
		}
	}
	
	public boolean lineStartsWithStopWord(String line)
	{
		StringTokenizer st = new StringTokenizer(line, " "); 
		
		String firstWord = st.nextToken();
		firstWord = firstWord.toLowerCase(); 
		

		if(list.contains(firstWord))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
