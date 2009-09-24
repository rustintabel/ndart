import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

class Hallusin8 {
   	



  public static void main(String[] args) {



   
//ffmpeg2theora -f image2 test-%03d.jpg -o output.ogg
//ffmpeg2theora -f image2 test-%03d.png -o output.ogg
//BufferedImage currentView= new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
  //    ImageIO.write(currentView,"jpg",new File("chronicalogical.jpg"));
   	

    // Create an image to save
   	
        int width = 500;
        int height = 500;
    
        // Create a buffered image in which to draw
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
        // Create a graphics contents on the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();
    
//-----Example 1
    
	int j=0;
	int i=0;
	for(;i<255;i++)
	{
		j=i;
		// Draw graphics
		g2d.setColor(new Color(i,i,(int)(Math.random()*255)));
		g2d.fillOval(i, j, i, j+9);

	
		// Write generated image to a file
		try {
			// Save as PNG
			NumberFormat formatter = new DecimalFormat("000");
			File file = new File("test-"+formatter.format(i)+".png");
			ImageIO.write(bufferedImage, "png", file);


		} catch (IOException e) {
		}
	
	}



//---Example 2

	for(i=0,j=0;i<width;i++){
		j=i*2;	// Draw graphics
		g2d.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		g2d.fillArc(i,j,width/2,height/2,0,i);
		
	}

	// Write generated image to a file
	try {
	// Save as PNG
	File file = new File("example2.png");
	ImageIO.write(bufferedImage, "png", file);


	} catch (IOException e) {
	}
//---Example 3
	
	int numberoftriangles=10;
	int[] xcoordinates=new int[3];
	int[] ycoordinates=new int[3];
	for(i=0;i<numberoftriangles;i++){
		xcoordinates[0]=(int)(Math.random()*width);
		ycoordinates[0]=(int)(Math.random()*height);

		xcoordinates[1]=(int)(Math.random()*width);
		ycoordinates[1]=(int)(Math.random()*height);

		xcoordinates[2]=(int)(Math.random()*width);
		ycoordinates[2]=(int)(Math.random()*height);

		g2d.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		g2d.fillPolygon(xcoordinates, ycoordinates, 3);
		
	}



	// Write generated image to a file
	try {
	// Save as PNG
	File file = new File("example3.png");
	ImageIO.write(bufferedImage, "png", file);


	} catch (IOException e) {
	}

        // Graphics context no longer needed so dispose it
        g2d.dispose();
    
       

 
  }
}
