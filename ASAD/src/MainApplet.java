/**
 * @author tejashree
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.HashMap;

public class MainApplet extends Applet{
	TextArea inputArea = new TextArea("Enter your Lines here");
	TextArea outputArea = new TextArea("");
	Button submitButton = new Button("Submit");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainApplet() {
		add(inputArea);
		add(submitButton);
		submitButton.addActionListener(new MyActionListener());
		add(outputArea);
		outputArea.setEditable(false);
	}
	
	/**
	 * 
	 */
	public void paint(Graphics g){
	     // g.drawString("This is Web Search Engine",40,20);
	   }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			

	}
	
	//A local class to handle events
	class MyActionListener implements ActionListener{
		private Index indxObj = new Index(); 
		
		public void actionPerformed(ActionEvent event){
			String s = inputArea.getText();

			indxObj.setsOriginalSearchContent(s);
			
			HashMap<Integer, String> hIndx = indxObj.getAllCircularShiftLines(indxObj);
			
			outputArea.setText("");
			
			//Print all the indices and their values
			if(hIndx != null && hIndx.size() != 0){
				for(Integer id : hIndx.keySet() ){
					outputArea.append(hIndx.get(id)+"\n"); 
				}
			}

		}
	}

}
