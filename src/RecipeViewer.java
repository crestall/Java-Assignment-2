import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;




public class RecipeViewer {
	
	
	private JFrame f = null;
	
    private JTextArea title = new JTextArea();
    private JList<String> Ingredients = new JList<String>();
    private JList<String> Method = new JList<String>(); 
	
	public RecipeViewer()
	{
		f = new JFrame("RecipeViewer");					       	    
	    title.setEditable(false);
		
	}
	public void displayCakeRecipe(CakeRecipe theRecipe)
	{		
		title.setText(theRecipe.getName());		
		
		ArrayList<String> theIngredients = theRecipe.getIngredients();
		DefaultListModel<String>  ingredientsModel =  new DefaultListModel<String>();
		Ingredients.setModel(ingredientsModel);				
		ingredientsModel.clear();
		for(String ingredient: theIngredients)
			ingredientsModel.addElement(ingredient);
		
		
		ArrayList<String> theMethod = theRecipe.getMethod();
		DefaultListModel<String>  methodModel =  new DefaultListModel<String>();
		Method.setModel(methodModel);				
		methodModel.clear();
		for(String method_step: theMethod)
			methodModel.addElement(method_step);
												
	    Method.setAutoscrolls(true);
		
		ImagePanel picture = new ImagePanel("files/"+theRecipe.getImageName());	 
	    GroupLayout layout = new GroupLayout(f.getContentPane());	  
	    layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);        
	    layout.setHorizontalGroup(
	    		   layout.createParallelGroup()
	    		   		.addComponent(title)
	    		   		.addGroup(	    				   
	    				   	layout.createSequentialGroup()	    				   		
	    				   		.addComponent(Ingredients)
	    				   		.addComponent(picture)	    				   		
	    				   )
	    				 .addComponent(Method)
	    				 );	    		

	    layout.setVerticalGroup(
    		   layout.createSequentialGroup()
    		   	.addComponent(title)
    		    .addGroup(
    		    		  layout.createParallelGroup()
    		    		  	.addComponent(Ingredients)
    		    		  	.addComponent(picture)    		    		  	  		    		  	
    		    		  )	    		      
    		    .addComponent(Method)
	    		);
	    
	    f.getContentPane().setLayout(layout);		
		
		
	    f.setVisible(true);
	    f.repaint();
	    f.revalidate();	    
	}
	
	public boolean isOpen()
	{		
		return f.isVisible();		
	}
}
