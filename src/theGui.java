import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class theGui {

	  private Action search_action = new AbstractAction("Search") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {	        	
	        	
	        	StringBuffer htmlCode = new StringBuffer();
	        	
	        	htmlCode.append("<ol>");
	        	Iterator<CakeRecipe> itr = recipes.iterator();
	            while (itr.hasNext()) {
	            	CakeRecipe element = itr.next();
	            	htmlCode.append("<li>" + element.name + "</li>");
	            	System.out.println(element.name);
	            }
	        	htmlCode.append("</ol>");
	        	
	        	
	        	
	        	
	        	String[] tokens = actionName.getText().split(" ");	        	
	        	if(tokens.length <= 5)
	        	{
		        	for (String word : tokens)
		        	{
		        		System.out.println(word);
		        	}		        	
		        	System.out.println("Made it here: "+ group.getSelection().getActionCommand() + " " + actionName.getText());
		        try
		        {
		        	ReceipeTextBox.setText(htmlCode.toString());
		        }catch(Exception e){
		        	
		        	System.exit(1);
		        }
	        	}else
	        	{
	        		System.out.println("Too many keywords entered!");	        		
	        	}
	        }
	    };
	    
	private ArrayList<CakeRecipe> recipes = CakeRecipeUtil.parseDirectory("files");
	
	
	
	// Initialize all swing objects.
    private JFrame f = new JFrame("Recipe Searcher"); //create Frame
    
    //Panels
    private JPanel top_panel = new JPanel(); // North quadrant 
    private JPanel below = new JPanel(); // South quadrant
    private JPanel bottom = new JPanel(); // South quadrant
    
    
    //Search Button
    private JButton searchButton = new JButton(search_action);

    
    private ButtonGroup group = new ButtonGroup();
    
    //Radio Buttons
    private JRadioButton nameButton = new JRadioButton("Name");
    private JRadioButton methodButton = new JRadioButton("Method");
    private JRadioButton everythingButton = new JRadioButton("Both");
    
    private JEditorPane  ReceipeTextBox = new JEditorPane(); 
    
    private JTextField actionName = new JTextField(20);
    
    /** Constructor for the GUI */
    public theGui(){
	    
    	everythingButton.setMnemonic(KeyEvent.VK_B);
    	everythingButton.setActionCommand("Both");
    	everythingButton.setSelected(true);
    	top_panel.add(everythingButton);
    	
    	nameButton.setMnemonic(KeyEvent.VK_B);  
    	nameButton.setActionCommand("Name");
        top_panel.add(nameButton);
        
        methodButton.setMnemonic(KeyEvent.VK_N);  
        methodButton.setActionCommand("Method");
        top_panel.add(methodButton);
        
    	       
        group.add(nameButton);
        group.add(methodButton);
        group.add(everythingButton);
      
        
        
        // Add Buttons                
        below.add(actionName);
        below.add(searchButton);
       
        ReceipeTextBox.setContentType("text/html");
        bottom.add(ReceipeTextBox);
        // Setup Main Frame
        f.getContentPane().setLayout(new BorderLayout());	
        f.getContentPane().add(top_panel, BorderLayout.NORTH);
		f.getContentPane().add(below, BorderLayout.CENTER);
		f.getContentPane().add(bottom, BorderLayout.SOUTH);
        

        
		// Allows the Swing App to be closed
        f.addWindowListener(new ListenCloseWdw());
		
		//Add Menu listener
        
    }
	
    public class ListenMenuQuit implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);         
        }
    }
	
    public class ListenCloseWdw extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);         
        }
    }
	
    public void launchFrame(){
        // Display Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack(); //Adjusts panel to components for display
        
		//should be full screen - Works
		//f.setUndecorated(true);  
        //f.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        
        f.setVisible(true);
    }
    
   
}
