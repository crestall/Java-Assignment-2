import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;




public class RecipeViewer {
	
	
	private JDialog f = null;
	
	
    private JTextField title = new JTextField();
    private JTextArea Ingredients = new JTextArea();
    private JTextArea Method = new JTextArea(); 
	
	public RecipeViewer(JFrame caller)
	{
		f = new JDialog(caller,"RecipeViewer",true);
		f.setModal(false);
		f.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	    title.setEditable(false);	    
	 
	}
	public void displayCakeRecipe(CakeRecipe theRecipe)
	{		
		f.getContentPane().removeAll();
		
		title.setText(theRecipe.getName());		
		title.setFont(new Font("Arial",Font.PLAIN,30));
		title.setHorizontalAlignment(JTextField.CENTER);
		title.setSize(300, 100);
		f.setTitle(theRecipe.getName());
		Method.setLineWrap(true);
		Ingredients.setLineWrap(true);
		
		Ingredients.setText("");
		for(String ingredient: theRecipe.getIngredients())
		{
			Ingredients.append(ingredient + "\n");
		}		
		
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
				
	    GroupLayout layout = new GroupLayout(f.getContentPane());	  
	    
	    layout.setAutoCreateGaps(true);	    
        layout.setAutoCreateContainerGaps(true);        
	    layout.setHorizontalGroup(
	    		   layout.createParallelGroup()
	    		   		.addComponent(title)
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
    		   	.addComponent(ingredients_label)
    		    .addGroup(
    		    		  layout.createParallelGroup()
    		    		  	.addComponent(ingredientScroll)
    		    		  	.addComponent(picture)    		    		  	  		    		  	
    		    		  )	    		      
    		    .addComponent(method_label)
    		    .addComponent(methodScroll)
	    		);
	    
	    f.getContentPane().setLayout(layout);		
		
	    f.setSize(800, 600);
	    f.repaint();
	    f.setVisible(true);
	    f.revalidate();	    
	}
	
	public boolean isOpen()
	{		
		return f.isVisible();		
	}
}
