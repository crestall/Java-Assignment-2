import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ShowReceipePreview extends Panel {
		  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		BufferedImage  image;		  
		  public ShowReceipePreview(CakeRecipe theRecipe) {
			  
		  try {
			  	System.out.println("Enter image name\n");		  		
		  		File input = new File("files/" + theRecipe.getImageName());
		  		image = ImageIO.read(input);
		  } catch (IOException ie) 
		  {
			  System.out.println("Error:"+ie.getMessage());
		  }
	  }

	  public void paint(Graphics g) {
		  g.drawImage( image, 0, 0, null);
	  }			  
	}