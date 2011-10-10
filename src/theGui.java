import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;




public class theGui {
	private JList<CakeRecipe> searchResults;
	
	  private Action search_action = new AbstractAction("Search") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {	        	
	        	
	        	StringBuffer htmlCode = new StringBuffer();
	        	
	        	htmlCode.append("<ol>");
	        	Iterator<CakeRecipe> itr = recipes.iterator();
	            while (itr.hasNext()) {
	            	CakeRecipe element = itr.next();
	            	htmlCode.append("<li>" + element.cookTime + "</li>");
	            }
	        	htmlCode.append("</ol>");
	        	
	        	ReceipeTextBox.setText("");
	        	
	        	
	        	String[] tokens = actionName.getText().split(" ");	   
	        	if(tokens.length <= 5)
	        	{
		        	
	        		theModel.clear();
		        	ArrayList<SearchResult<CakeRecipe>> matchingRecipes = CakeRecipeUtil.search(recipes, tokens, true, true);
		        	for (SearchResult<CakeRecipe> result:matchingRecipes) {
		    				 try
		 			        {		    					     					 
		 			        	ReceipeTextBox.setText(ReceipeTextBox.getText() + result.item.getImageName() + "\n");		 			        			 			        	
		 			        	//RecipeLabel label = new RecipeLabel("files/"+result.item.getImgName(),result.item.getName());		 			        			 			        	
		 			        	theModel.addElement(result.item);	
		 			        	
		 			        	//System.out.println("number: " + theModel.getSize());		 			        			 			        			 			        			 			        	
		 			        	//searchResults.revalidate();
		 			        //	all_search_results.revalidate();
		 			        	//f.revalidate();
		 			        	
		 			        	//label.setLocation(29, 37);
		 			        	//f.getContentPane().add(searchResults);
		 			        //   f.setVisible(true);
		 			        	//JFrame frame = new JFrame(result.item.getImageName());
		 			        //	Panel panel = new ShowReceipePreview(result.item);		 			        	
		 			        	//f.getContentPane().add(panel,BorderLayout.CENTER);
		 			        	/*
		 			        	(frame.getContentPane().add(panel);
		 			        	frame.setSize(500, 500);
		 			        	frame.setVisible(true);
		 			        	*/
		 			        }
		    				 catch(Exception e){	
		    					 System.out.println("Exception Occured:" + e.toString());
		 			        	//System.exit(1);
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
    //private JPanel all_search_results = new JPanel(); // South quadrant
    private JScrollPane all_search_results;
    //Search Button
    private JButton searchButton = new JButton(search_action);

    
    private ButtonGroup group = new ButtonGroup();
    
    //Radio Buttons
    private JRadioButton nameButton = new JRadioButton("Name");
    private JRadioButton methodButton = new JRadioButton("Method");
    private JRadioButton everythingButton = new JRadioButton("Both");
    
    private JEditorPane  ReceipeTextBox = new JEditorPane(); 
    
    private JTextField actionName = new JTextField(20);
    private DefaultListModel<CakeRecipe>  theModel =  new DefaultListModel<CakeRecipe>();
    
    /** Constructor for the GUI */
    public theGui(){
    	
    searchResults = new JList<CakeRecipe>();
  //  searchResults.setVisibleRowCount(2);
    searchResults.setModel(theModel);
    RecipeListRenderer therenderer = new RecipeListRenderer();
    
 //   therenderer.setPreferredSize(new Dimension(100,50));
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
       
   //     ReceipeTextBox.setContentType("text/html");
       // ReceipeTextBox.setSize(50, 50);
        bottom.add(ReceipeTextBox);
        // Setup Main Frame
        f.getContentPane().setLayout(new BorderLayout());	
        f.getContentPane().add(top_panel, BorderLayout.NORTH);
		f.getContentPane().add(below, BorderLayout.CENTER);
		f.getContentPane().add(bottom, BorderLayout.SOUTH);
		
		
		
		
        f.getContentPane().add(all_search_results,BorderLayout.CENTER);

        
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
	
    @SuppressWarnings("deprecation")
	public void launchFrame(){
        // Display Frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  f.pack(); //Adjusts panel to components for display
        //f.resize(1000, 800);
        f.resize(new Dimension(1000, 500));
		//should be full screen - Works
		//f.setUndecorated(true);  
        //f.setExtendedState(Frame.MAXIMIZED_BOTH);
        
        
        f.setVisible(true);
    }
    
   
}
