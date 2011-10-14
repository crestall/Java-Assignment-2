import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class Gui {
	private JList<CakeRecipe> searchResults;
	private RecipeViewer recipeViewer = null;
	
	private ListSelectionListener recipeClick = new ListSelectionListener(){
		public void valueChanged(ListSelectionEvent e) 
		{
			if(e.getValueIsAdjusting())
				recipeViewer.displayCakeRecipe(theModel.get(searchResults.getSelectedIndex()));
		}
	};
	
	  private Action search_action = new AbstractAction("Search") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {	        		        	
	        	String[] tokens = actionName.getText().split(" ");	   
	        	if(tokens.length <= 5)
	        	{		        	
	        		theModel.clear();
	        		
	        		boolean searchName,searchMethod;
	        		
	        		if(searchComboBox.getSelectedItem().equals("Name"))
	        		{
	        			searchName = true;
	        			searchMethod = false;
	        			
	        		}else{
	        			if(searchComboBox.getSelectedItem().equals("Method"))
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
    
    private JScrollPane all_search_results;
    private JButton searchButton = new JButton(search_action);

    
    
    private JTextPane title = new JTextPane();
    
        
    private JComboBox<String[]> searchComboBox;
    private String [] searchOptions = new String[3];
    
    private JTextField actionName = new JTextField(20);
    private DefaultListModel<CakeRecipe>  theModel =  new DefaultListModel<CakeRecipe>();
    
    /** Constructor for the GUI */
    public Gui(){
    	
    	
    	
	    searchResults = new JList<CakeRecipe>();
	    searchResults.setModel(theModel);
	    
	    title.setFont(new Font("Arial",Font.PLAIN,14));
		
		title.setText("Welcome to our Cake Searcher! \r\n" +					
					  "by Adrian Cowan and Christopher Restall");		
		
		title.setEditable(false);
	    
	    RecipeListRenderer therenderer = new RecipeListRenderer();	  
	    therenderer.setTheGui(f);
	    searchResults.setCellRenderer(therenderer);
	    
	    
	    searchButton.registerKeyboardAction(search_action,KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false),JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	    searchResults.addListSelectionListener(recipeClick);
	    searchResults.setAutoscrolls(true);
	
		all_search_results = new JScrollPane(searchResults);
		all_search_results.setAutoscrolls(true);
		 
		
		searchOptions[0] = "Name";
		searchOptions[1] = "Method";
		searchOptions[2] = "Both";
		
		searchComboBox = new JComboBox(searchOptions);
	    JLabel combo_label = new JLabel("Please select which attributes you would like to search:");
	    
	    JLabel keywords_label = new JLabel("Please enter your keywords:");
	    
	    GroupLayout layout = new GroupLayout(f.getContentPane());	  
	    
	    layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
	    layout.setHorizontalGroup(
	    		   layout.createParallelGroup()
	    		   		.addComponent(title)
	    		   		.addGroup(	    				   
	    		   				layout.createSequentialGroup()	    				   		
	    				   		.addComponent(combo_label)
	    				   		.addComponent(searchComboBox)	    				   	
	    				   		)
	    				 .addGroup(
	    						 layout.createSequentialGroup()
	    				   		.addComponent(keywords_label)
	    				   		.addComponent(actionName)
	    				   		.addComponent(searchButton)
	    				   		)	    				   	
	    				 .addComponent(all_search_results)
	    		);

	    layout.setVerticalGroup(
    		   layout.createSequentialGroup()
    		   		.addComponent(title,40,45,50)
    		   		.addGroup(
    		   					layout.createParallelGroup()
    		   					.addComponent(combo_label)	
    		   					.addComponent(searchComboBox,15,15,20)    		    		  		    		  	
    		   				)
    		    	.addGroup(
    		    				layout.createParallelGroup()
    		    				.addComponent(keywords_label)
    		    				.addComponent(actionName,15,15,20)
  		    		  			.addComponent(searchButton,15,15,20)    	
    		    		  )
    		    	.addComponent(all_search_results)
	    		);
	    f.getContentPane().setLayout(layout);
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
		f.setBounds(0, 0, 500, 500);
		recipeViewer = new RecipeViewer(f);
        f.setVisible(true);
    }
    
   
}
