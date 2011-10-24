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

/*
 * RecipeListRenderer renders a JList full of CakeRecipes. It displays its picture and title.
 */
class RecipeListRenderer extends JLabel implements ListCellRenderer<CakeRecipe> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends CakeRecipe> list, CakeRecipe value, int index,
			boolean isSelected, boolean cellHasFocus) {	
		
		ImageIcon theIcon = new ImageIcon(resizeImage(value));						
				
		setText(value.getName());
	    setIcon(theIcon);
		   			   
		setBackground(isSelected ? Color.red : Color.blue);
	    setForeground(isSelected ? Color.blue : Color.black);		
		return this;
	}		
	
	/*
	 * resizeImage resizes the cakeRecipe image to 100pixels wide by 50pixels high
	 * @param thevalue The CakeRecipe whose image you want returned and resized.
	 * @return BufferedImage this is the resized picture.
	 */
	private BufferedImage resizeImage(CakeRecipe thevalue)
	{
		BufferedImage resizedImage = new BufferedImage(100, 50,BufferedImage.TYPE_INT_ARGB);		
		Graphics2D g = resizedImage.createGraphics();		
		try {			
			g.drawImage(ImageIO.read(new File(thevalue.getImageDir() + thevalue.getImageName()) )  , 0, 0, 100, 50, null);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return resizedImage;
	}
	
}