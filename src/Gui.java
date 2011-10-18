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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Gui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	        		
	        		boolean searchName,searchMethod,searchIngredients;	        			        			        		
        			searchName = nameBox.getModel().isSelected();
        			searchMethod = methodBox.getModel().isSelected();
        			searchIngredients =ingredientsBox.getModel().isSelected(); 
	        		
		        	ArrayList<SearchResult<CakeRecipe>> matchingRecipes = CakeRecipeUtil.search(recipes, tokens, searchName, searchMethod,searchIngredients);

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
  //  private JFrame f = new JFrame("Recipe Searcher"); //create Frame
    

	private JCheckBox nameBox = new JCheckBox();
	private JCheckBox methodBox = new JCheckBox();
	private JCheckBox ingredientsBox = new JCheckBox();
       
    private JComboBox<String[]> sortByComboBox;
    
    private JTextField actionName = new JTextField(20);
    private DefaultListModel<CakeRecipe>  theModel =  new DefaultListModel<CakeRecipe>();
    
    /** Constructor for the GUI */
    public Gui(){
	    searchResults = new JList<CakeRecipe>();
	    searchResults.setModel(theModel);
	    
	    
	    JTextPane title = new JTextPane();
	    title.setFont(new Font("Arial",Font.BOLD,14));		
		title.setText("Welcome to our Cake Searcher! \r\n" +					
					  "ID1: #6515258 and ID2: #6515029");				
		title.setEditable(false);
	    
	    RecipeListRenderer therenderer = new RecipeListRenderer();	  
	    therenderer.setTheGui(this);
	    searchResults.setCellRenderer(therenderer);
	    
	    JButton searchButton = new JButton(search_action);
	    searchButton.registerKeyboardAction(search_action,KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false),JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
	    searchResults.addListSelectionListener(recipeClick);
	    searchResults.setAutoscrolls(true);
	    
	    JScrollPane all_search_results;
		all_search_results = new JScrollPane(searchResults);
		all_search_results.setAutoscrolls(true);
		
		nameBox.setSelected(true);
		methodBox.setSelected(true);
		ingredientsBox.setSelected(true);
		 
		String [] sortByOptions = new String[4];
		sortByOptions[0] = "Relevance";
		sortByOptions[1] = "Preparation Time";
		sortByOptions[2] = "Cooking Time";
		sortByOptions[3] = "Required Time";
		
		
		JLabel search_in_label = new JLabel("Please select which attribute you would like to search in:");	
		
		
		nameBox.setText("Name");
		
		
		methodBox.setText("Method");
		
		
		ingredientsBox.setText("Ingredients");
		
		
		sortByComboBox = new JComboBox(sortByOptions);
		
	    JLabel combo_label = new JLabel("Please select which attribute you would like to sort by:");	    
	    
	    JLabel keywords_label = new JLabel("Please enter your keywords:");	    
	    
	    
	    GroupLayout layout = new GroupLayout(this.getContentPane());	  
	    
	    layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
	    layout.setHorizontalGroup(
	    		   layout.createParallelGroup()
	    		   		.addComponent(title)	    		   		    				   		
	    				.addComponent(search_in_label)	    				   					   		    				
	    				.addGroup(
		    		    		layout.createSequentialGroup()	    		    		
		    		    		.addComponent(nameBox,70,80,90)	    		    		
		    		    		.addComponent(methodBox,70,80,90)	    		    		
		    		    		.addComponent(ingredientsBox,70,80,90)
		    		    		)
	    		   		.addGroup(	    				   
	    		   				layout.createSequentialGroup()	    				   		
	    				   		.addComponent(combo_label)
	    				   		.addComponent(sortByComboBox)	    				   	
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
	    		   				layout.createSequentialGroup()	    				   		
	    				   		.addComponent(search_in_label)	    				   			    				   	
	    				   	)
	    		    .addGroup(
	    		    		layout.createParallelGroup()
	    		    		.addComponent(nameBox)
	    		    		.addComponent(methodBox)
	    		    		.addComponent(ingredientsBox)
	    		    		)	    		    		
    		   		.addGroup(
    		   					layout.createParallelGroup()
    		   					.addComponent(combo_label)	
    		   					.addComponent(sortByComboBox,15,15,20)    		    		  		    		  	
    		   				)
    		    	.addGroup(
    		    				layout.createParallelGroup()
    		    				.addComponent(keywords_label)
    		    				.addComponent(actionName,15,15,20)
  		    		  			.addComponent(searchButton,15,15,20)    	
    		    		  )
    		    	.addComponent(all_search_results)
	    		);
	    getContentPane().setLayout(layout);
	    addWindowListener(new ListenCloseWdw());
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 500, 500);		
        setVisible(true);
        recipeViewer = new RecipeViewer(this);
    }
    
   
}
