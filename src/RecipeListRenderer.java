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

	private JFrame theGui;
	
	
	public void setTheGui(JFrame theGui) {
		this.theGui = theGui;
	}


	
	
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