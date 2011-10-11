import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RecipeViewer {
	
	
	private JFrame f = null;
	private JPanel top_panel = new JPanel(); // North quadrant 
    private JPanel west_panel = new JPanel(); // West quadrant
    private JPanel bottom = new JPanel(); // South quadrant
    
    private JEditorPane title = new JEditorPane();
    private JEditorPane Ingredients = new JEditorPane();
    private JEditorPane Method = new JEditorPane(); 
    
    

	public RecipeViewer()
	{
		f = new JFrame("RecipeViewer");
		f.setBackground(Color.white);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.resize(new Dimension(1100, 700));
	    
	    f.getContentPane().setLayout(new BorderLayout());
	    
	    title.setBounds(0, 0, 300, 50);
	    title.setContentType("text/html");
	    title.setEditable(false);
	    
	    Ingredients.setContentType("text/html");
	    Ingredients.setSize(50, 50);
	    Ingredients.setEditable(false);
	    
	    Method.setContentType("text/html");	    
	    Method.setEditable(false);  		
	}
	public void displayCakeRecipe(CakeRecipe theRecipe)
	{		
		title.setText("<h1>"+theRecipe.getName()+"</h1>");		
		
		ArrayList<String> theIngredients = theRecipe.getIngredients();
		
		StringBuffer ingredientsList = new StringBuffer();
		ingredientsList.append("<h2><u>Ingredients</u></h2><ol>");
		for(String Ingredient : theIngredients)
		{
			ingredientsList.append("<li><p>"+Ingredient+"</p></li>");
		}
		ingredientsList.append("</ol>");
		
		Ingredients.setText(ingredientsList.toString());
		Ingredients.setAutoscrolls(true);
		
		ArrayList<String> theMethod = theRecipe.getMethod();
		
		StringBuffer methodList = new StringBuffer();
		methodList.append("<tr><td><h2><u>Method</u></h2><br><table width=\"1000\"><tr><td><ol>");
		for(String method : theMethod)
		{
			methodList.append("<li><p>"+method+"</p></li>");
		}
		methodList.append("</ol></td></tr></table>");
		
		Method.setText(methodList.toString());						
	    Method.setAutoscrolls(true);
	    
	    bottom.removeAll();
		bottom.add(Method);		
		
		top_panel.removeAll();
		top_panel.add(title);		
		
		west_panel.removeAll();
		west_panel.add(Ingredients);
		
		f.getContentPane().removeAll();
		f.getContentPane().add(top_panel, BorderLayout.NORTH);
		f.getContentPane().add(west_panel, BorderLayout.WEST);
		f.getContentPane().add(new ImagePanel("files/"+theRecipe.getImageName()), BorderLayout.CENTER);
		f.getContentPane().add(bottom, BorderLayout.SOUTH);
	    f.setVisible(true);
	    f.repaint();
	    f.revalidate();
	    
	}
	
	public boolean isOpen()
	{		
		return f.isVisible();		
	}
}
