import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


class RecipeListRenderer extends JLabel implements ListCellRenderer<CakeRecipe> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final RecipeViewer recipe_viewer = new RecipeViewer();
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends CakeRecipe> list, CakeRecipe value, int index,
			boolean isSelected, boolean cellHasFocus) {	
		
		ImageIcon theIcon = new ImageIcon(resize_image(value));						
				
		setText(value.getName());
	    setIcon(theIcon);
	    
		if (isSelected) {			      
		    recipe_viewer.displayCakeRecipe(value);
		}
		   			   
		setBackground(isSelected ? Color.red : Color.blue);
	    setForeground(isSelected ? Color.blue : Color.black);		
		return this;
	}		
	
	
	private BufferedImage resize_image(CakeRecipe thevalue)
	{
		BufferedImage resizedImage = new BufferedImage(100, 50,BufferedImage.TYPE_INT_ARGB);		
		Graphics2D g = resizedImage.createGraphics();		
		try {
			g.drawImage(ImageIO.read(new File("files/"+ thevalue.getImgName() ))  , 0, 0, 100, 50, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resizedImage;
	}
	
}