import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

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
				recipeViewer.displayCakeRecipe(results_list_model.get(searchResults.getSelectedIndex()));
		}
	};
	
	     
	private ArrayList<CakeRecipe> recipes = CakeRecipeUtil.parseDirectory("files");
		
	// Initialise all swing objects. 
	private JCheckBox nameBox = new JCheckBox();
	private JCheckBox methodBox = new JCheckBox();
	private JCheckBox ingredientsBox = new JCheckBox();       
    private JComboBox<String[]> sortByComboBox;
    private JComboBox<String[]> ascendingDescending;    
    private JTextField actionName = new JTextField(20);
    private DefaultListModel<CakeRecipe>  results_list_model =  new DefaultListModel<CakeRecipe>();
  
        
    /** Constructor for the GUI */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Gui(){	    	    
	    setTitle("Receipe Viewer");
	    
	    searchResults = new JList<CakeRecipe>();
	    searchResults.setModel(results_list_model);
	    
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
	    
	    
	    String[] ascendingDescdingOptions = new String[2];
	    ascendingDescdingOptions[0] = "Ascending";
	    ascendingDescdingOptions[1] = "Descending";
	    
	    ascendingDescending = new JComboBox(ascendingDescdingOptions);
	    JLabel view_results_order = new JLabel("View results in what order:");
	    
	    searchResults.addListSelectionListener(recipeClick);
	    searchResults.setAutoscrolls(true);
	    
	    JScrollPane all_search_results;
		all_search_results = new JScrollPane(searchResults);
		all_search_results.setAutoscrolls(true);				
		 
		JLabel search_in_label = new JLabel("Please select which attribute you would like to search in:");	
				
		nameBox.setText("Name");		
		nameBox.setSelected(true);
		
		methodBox.setText("Method");
		methodBox.setSelected(true);
		
		ingredientsBox.setText("Ingredients");
		ingredientsBox.setSelected(true);
						
	    JLabel combo_label = new JLabel("Please select which attribute you would like to sort by:");	    
	    sortByComboBox = new JComboBox(SearchResultOrder.values());
	    
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
	    				   		.addComponent(view_results_order)
	    				   		.addComponent(ascendingDescending)	    				   	
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
	    				   		.addComponent(view_results_order)
	    				   		.addComponent(ascendingDescending,15,15,20)	    				   	
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
    
	private Action search_action = new AbstractAction("Search") {
			private static final long serialVersionUID = 1L;
	        public void actionPerformed(ActionEvent evt) {	        		        	
	        	String[] tokens = actionName.getText().split(" ");	   
	        	if(tokens.length <= 5)
	        	{		        	
	        		results_list_model.clear();	        		
	        		boolean searchName,searchMethod,searchIngredients;	        			        			        		
     			searchName = nameBox.getModel().isSelected();
     			searchMethod = methodBox.getModel().isSelected();
     			searchIngredients =ingredientsBox.getModel().isSelected(); 
	        		
		        	ArrayList<SearchResult<CakeRecipe>> matchingRecipes = CakeRecipeUtil.search(recipes, tokens, searchName, searchMethod,searchIngredients);

		        	int ascDesc = 1; 
		        	
		        	if(ascendingDescending.getSelectedItem() == "Descending")
		        		ascDesc = -1;
		        	
		        	CakeRecipeUtil.orderResultsBy(matchingRecipes, (SearchResultOrder) sortByComboBox.getSelectedItem(), ascDesc);
		        	
		        	for (SearchResult<CakeRecipe> result:matchingRecipes) {
		    				 try
		 			        {		    					     					 		 			        			 			        			 			        	
		 			        	results_list_model.addElement(result.item);			 		
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
   
}
