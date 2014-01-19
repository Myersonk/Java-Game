package ri.game2.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpriteSheet {

	public String path;
	public int width;
	public int height;
	
	public int[] pixels;
	
	public SpriteSheet(String pth){
		BufferedImage image = null;
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(pth));
		} catch (IOException e) {}
		
		if(image == null){
				return;
		}
		this.path = pth;
		this.width = image.getWidth();
		this.height = image.getHeight();
		pixels = image.getRGB(0,0,width,height,null,0,width);
		
	}
	
	
}//end of class
