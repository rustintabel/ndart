


import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;


public class Geometry {
    
    /** Creates a new instance of Geometry */
    public Geometry() {
    }
    
    public static void main(String[] args)
    {
        int dimentions=5;
        double currentRadians=0.4;
        BSPTree tree=new BSPTree(dimentions);
        //tree.buildTreeFromRandDisjointTriangles(100);
        tree.buildTreeFromRandconectedTriangles(3);
        try{
            BufferedImage temp=null;
            int dimentionx=0;
            int dimentiony=1;
            double radiansSoFar=0;
            for(int i=0;i<100;i++){
            temp=tree.drawBSPTree();   
            radiansSoFar+=currentRadians;
            if(radiansSoFar>3.14*2)
            {
            radiansSoFar=0;
                if(dimentiony<dimentions)
                {
                    dimentiony++;
                }
                else
                {
                    if(dimentionx<dimentions)
                    {
                        dimentionx++;
                        dimentiony=dimentionx+1;
                    }else
                    {
                                dimentionx=0;
                                dimentiony=1;
                    }
                }
            }
            tree.rotateAroundOrigin(currentRadians,dimentionx,dimentiony);
            
            //Point p=new Point(dimentions);
            //p.setRandomCoordinates(1.0);
            //tree.translateByVector(p);
            tree.scale(1.01);
            ImageIO.write(temp,"jpg",new File(Integer.toString(i)+".jpg"));
            }
        }catch(Exception i)
        {
            System.out.println("dfgdg");
        }
    }
    
}
