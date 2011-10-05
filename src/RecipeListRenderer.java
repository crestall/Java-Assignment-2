import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.sun.image.codec.jpeg.JPEGImageEncoder;


class RecipeListRenderer extends JLabel implements ListCellRenderer<CakeRecipe> {

	@Override
	public Component getListCellRendererComponent(
			JList<? extends CakeRecipe> list, CakeRecipe value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		//RecipeLabel aLabel = new RecipeLabel(value.getImageName(), value.getName());
		//System.out.println("made it in here");
		//ImageIcon theIcon = new ImageIcon("files/"+ value.getImgName());
		//theIcon.setImage(new Image())
		
		
		BufferedImage resizedImage = new BufferedImage(100, 50,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		try {
			g.drawImage(ImageIO.read(new File("files/"+ value.getImgName() ))  , 0, 0, 100, 50, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.dispose();
				
		ImageIcon theIcon = new ImageIcon(resizedImage);				
		setIcon(theIcon);
//setText("files/"+ value.getImageSrc());
		setText(value.getName());
		
		   if (isSelected) {
			      System.out.println("Selected: "+ value.getName());
			      
			    }
		
		
		return this;
	}

	
	
}