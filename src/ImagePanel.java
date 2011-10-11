import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

    public ImagePanel(String fileName) {
       try {                
          image = ImageIO.read(new File(fileName));
       } catch (IOException ex) {
    	   System.out.println("Couldn't read picture file");
    	   System.exit(0);
       }
       
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null); 

    }

}