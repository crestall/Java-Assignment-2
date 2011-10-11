import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;




public class Gui {
	private JList<CakeRecipe> searchResults;
	
	  private Action search_action = new AbstractAction("Search") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {	        	
	        		      
	        	String[] tokens = actionName.getText().split(" ");	   
	        	if(tokens.length <= 5)
	        	{		        	
	        		theModel.clear();
	        		
	        		boolean searchName,searchMethod;
	        		
	        		if(group.getSelection().getActionCommand().equals("Name"))
	        		{
	        			searchName = true;
	        			searchMethod = false;
	        			
	        		}else{
	        			if(group.getSelection().getActionCommand().equals("Method"))
		        		{
		        			searchName = false;
		        			searchMethod = true;
		        		}else{
		        			searchName = true;
		        			searchMethod = true;
		        		}
	        		}
	        		
		        	ArrayList<SearchResult<CakeRecipe>> matchingRecipes = CakeRecipeUtil.search(recipes, tokens, searchName, searchMethod);
		        	//Collections.sort(matchingRecipes);
		        	
		        	for (SearchResult<CakeRecipe> result:matchingRecipes) {
		    				 try
		 			        {		    					     					 		 			        			 			        			 			        	
		 			        	theModel.addElement(result.item);			 		
		 			        }
		    				 catch(Exception e){	
		    					System.out.println("Exception Occured:" + e.toString());
		 			        	System.exit(1);
		 			        }		    						    					    				
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

    private JScrollPane all_search_results;
    private JButton searchButton = new JButton(search_action);

    private ButtonGroup group = new ButtonGroup();
    
    //Radio Buttons
    private JRadioButton nameButton = new JRadioButton("Name");
    private JRadioButton methodButton = new JRadioButton("Method");
    private JRadioButton everythingButton = new JRadioButton("Both");
    
    
    
    private JTextField actionName = new JTextField(20);
    private DefaultListModel<CakeRecipe>  theModel =  new DefaultListModel<CakeRecipe>();
    
    /** Constructor for the GUI */
    public Gui(){
    	
	    searchResults = new JList<CakeRecipe>();
	    searchResults.setModel(theModel);
	    
	    RecipeListRenderer therenderer = new RecipeListRenderer();	    
	    searchResults.setCellRenderer(therenderer);
	
		all_search_results = new JScrollPane(searchResults);
		all_search_results.setAutoscrolls(true);
		 
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
	    bottom.add(actionName);
	    bottom.add(searchButton);
	   
	    // Setup Main Frame
	    f.getContentPane().setLayout(new BorderLayout());	
	    f.getContentPane().add(top_panel, BorderLayout.NORTH);
		f.getContentPane().add(below, BorderLayout.CENTER);
		f.getContentPane().add(bottom, BorderLayout.SOUTH);
		
		
		
		
	    f.getContentPane().add(all_search_results,BorderLayout.CENTER);
	    f.addWindowListener(new ListenCloseWdw());
			
        
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
		f.setBounds(0, 0, 500, 300);
        f.setVisible(true);
    }
    
   
}
