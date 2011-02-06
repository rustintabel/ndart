import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

class NDArt {
   	



  public static void main(String[] args) {

	  int numberOfDimentions=10;
	  BSPTree tree = new BSPTree(numberOfDimentions);
	  tree.buildTreeFromRandconectedTriangles(30);
	  //tree.buildTreeFromRandDisjointTriangles(50);
	  
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
        Runtime runTime = Runtime.getRuntime();
        
        try
        {
        	Process pr = runTime.exec("rm /home/me/workspace/ndart/frames/*");
        	pr.waitFor();
        }
        catch(Exception ie)
        {
        	System.exit(-1);
        }
        

        
        int i=0;
        int j=1;
        int count=0;
        
        for(;i<(numberOfDimentions-1);i++){

        	j=i+1;
        	for(;j<(numberOfDimentions);j++){
		        for(int r=0;r<300;r++)
		        {
		               
		                // Draw graphics
		                //g2d.setColor(new Color(i,i,(int)(Math.random()*255)));
		                //g2d.fillOval(i, j, i, j+9);
		                bufferedImage = tree.drawBSPTree();
		                tree.rotateAroundOrigin(0.012566,i,j);
		                // Write generated image to a file
		                try {
		                        // Save as PNG
		                        NumberFormat formatter = new DecimalFormat("0000");
		                        File file = new File("/home/me/workspace/ndart/frames/test-"+formatter.format(count)+".png");
		                        count++;
		                        ImageIO.write(bufferedImage, "png", file);
		
		
		                } catch (IOException e) {
		                }
		        
		        }
        	}
        }
        
        try
        {
        	
        	UUID uuid = UUID.randomUUID();
        	String randomUUIDString = uuid.toString();
        	Process pr = runTime.exec("ffmpeg2theora -f image2 /home/me/workspace/ndart/frames/test-%04d.png -o /home/me/workspace/ndart/videos/"+randomUUIDString+".ogg");
        	pr.waitFor();
        }
        catch(Exception ie)
        {
        	System.exit(-1);
        }


        // Graphics context no longer needed so dispose it
        g2d.dispose();
    
       

 
  }
}
