import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * RecipeViewer displays a CakeRecipe in a JDialog
 */
public class RecipeViewer extends JDialog{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField title = new JTextField();
    private JTextArea Ingredients = new JTextArea();
    private JTextArea Method = new JTextArea();
    private JTextArea cookingTime = new JTextArea();
    private JTextArea preparationTime = new JTextArea();
    
	
	public RecipeViewer(JFrame caller)
	{
		super(caller,"RecipeViewer",true);	
		setModal(false);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	    title.setEditable(false);	     
	    Method.setEditable(false);
	    Ingredients.setEditable(false);
	    preparationTime.setEditable(false);
	    cookingTime.setEditable(false);
	}
	
	public void displayCakeRecipe(CakeRecipe theRecipe)
	{		
		getContentPane().removeAll();
		
		setTitle(theRecipe.getName());
		
		cookingTime.setText(String.valueOf(theRecipe.getCookTime())+ " minutes");
		cookingTime.setBackground(this.getBackground());
		
		preparationTime.setText(String.valueOf(theRecipe.getPrepTime()) + " minutes");
		preparationTime.setBackground(this.getBackground());
		
		JLabel cookTimeLabel = new JLabel("Cooking Time:");
		JLabel prepTimeLabel = new JLabel("Preparation Time:");		
		
		title.setText(theRecipe.getName());		
		title.setFont(new Font("Arial",Font.PLAIN,30));
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setSize(300, 100);
		
		
		
		Ingredients.setLineWrap(true);
		
		Ingredients.setText("");
		for(String ingredient: theRecipe.getIngredients())
		{
			Ingredients.append(ingredient + "\n");
		}		
		
		Method.setLineWrap(true);
		Method.setText("");		
		int step = 1;
		for(String method_step: theRecipe.getMethod())
		{
			Method.append("Step "+ step + ": " + method_step + "\n\n");
			step++;
		}												
		
		Ingredients.setAutoscrolls(true);
		
		JScrollPane ingredientScroll = new JScrollPane(Ingredients);
		ingredientScroll.setWheelScrollingEnabled(true);
		
		
		JScrollPane methodScroll = new JScrollPane(Method);
		methodScroll.setSize(500, 200);
		methodScroll.setWheelScrollingEnabled(true);
		
		JLabel ingredients_label = new JLabel("Ingredients:");
		JLabel method_label = new JLabel("Method:");
		
		ImagePanel picture = new ImagePanel(theRecipe.getImageDir() + theRecipe.getImageName());
				
	    GroupLayout layout = new GroupLayout(getContentPane());	  
	    
	    layout.setAutoCreateGaps(true);	    
        layout.setAutoCreateContainerGaps(true);        
	    layout.setHorizontalGroup(
	    		   layout.createParallelGroup()
	    		   		.addComponent(title)
	    		   		.addGroup(	    				   
	    				   	layout.createSequentialGroup()	    				   		
	    				   		.addComponent(prepTimeLabel)
	    				   		.addComponent(preparationTime)	
	    				   		.addComponent(cookTimeLabel)
	    				   		.addComponent(cookingTime)	    				   		 				   		
	    				   )
	    		   		.addComponent(ingredients_label)	    		   		
	    		   		.addGroup(	    				   
	    				   	layout.createSequentialGroup()	    				   		
	    				   		.addComponent(ingredientScroll,150,250,350)
	    				   		.addComponent(picture)	    				   		
	    				   )
	    				 .addComponent(method_label)
	    				 .addComponent(methodScroll)
	    				 );	    		

	    layout.setVerticalGroup(
    		   layout.createSequentialGroup()
    		   	.addComponent(title)
    		   	.addGroup(	    				   
	    				   	layout.createParallelGroup()	
	    				   		.addComponent(prepTimeLabel)
	    				   		.addComponent(preparationTime) 		
	    				   		.addComponent(cookTimeLabel)
	    				   		.addComponent(cookingTime)	    				   				   		
	    				   )
    		   	.addComponent(ingredients_label)
    		    .addGroup(
    		    		  layout.createParallelGroup()
    		    		  	.addComponent(ingredientScroll)
    		    		  	.addComponent(picture,200,250,300)    		    		  	  		    		  	
    		    		  )	    		      
    		    .addComponent(method_label)
    		    .addComponent(methodScroll)
	    		);
	    
	    getContentPane().setLayout(layout);		
		
	    setSize(800, 600);
	    repaint();	    
	    setVisible(true);
	    
	}
	
	public boolean isOpen()
	{		
		return isVisible();		
	}
}
