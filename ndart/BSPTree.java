package geometry;

import java.awt.image.*;
import java.awt.*;

/**
 *
 * @author justin
 */
public class BSPTree {
    int dimentions=0;
    BSPTreeNode root=null;
    int numberOfNodes=0;
    Viewer viewer =null;
    int screenHeight=300;
    int screenWidth=300;
    double[] currentUnitOfRotation=null;

    public BSPTree(int d) {
        
        dimentions=d;
        currentUnitOfRotation=new double[dimentions];
                for(int i=0;i<dimentions;i++)
        {
            currentUnitOfRotation[i]=0.1;
        }
    }
    
    public void setCurrentUnitOfRotation(double radians)
    {
        for(int i=0;i<dimentions;i++)
        {
            currentUnitOfRotation[i]=radians;
        }
    }
    
    public void buildTreeFromRandDisjointTriangles(int numberOfTriangles)
    {
        int nodesSoFar=0;
        numberOfNodes=numberOfTriangles;
        viewer =new Viewer(dimentions, 1, 1,screenHeight,screenWidth);
        BSPTreeNode temp=null;
        root=new BSPTreeNode(dimentions);
        root.setRandTriangle();
        root.setParent(root);
        while(nodesSoFar<numberOfNodes)
        {
           temp=new BSPTreeNode(dimentions);
           temp.setRandTriangle();
           if(root.addNode(temp))
           {
                nodesSoFar++;
           }
        } 
    }
    
    public void buildTreeFromRandconectedTriangles(int numberOfTriangles)
    {
        int nodesSoFar=0;
        numberOfNodes=numberOfTriangles;
        viewer =new Viewer(dimentions, 1, 1,screenHeight,screenWidth);
        BSPTreeNode temp=null;
        root=new BSPTreeNode(dimentions);
        root.setRandTriangle();
        root.setParent(root);
        BSPTreeNode lastNode=root;
        Point newPoint=null;
        while(nodesSoFar<numberOfNodes)
        {
           temp=new BSPTreeNode(dimentions);
           temp.addPointToTriangle(lastNode.getTriangle().getPoint(1));
           temp.addPointToTriangle(lastNode.getTriangle().getPoint(2));
           newPoint=new Point(dimentions);
           newPoint.setRandomCoordinates(200.0);
           temp.addPointToTriangle(newPoint);
           temp.getTriangle().setCenter();
           temp.findNormals();
           temp.chooseHyperplane(1);
           if(root.addNode(temp))
           {
               lastNode=temp;
                nodesSoFar++;
           }
        }     
    }
    
    
    public BufferedImage drawBSPTree()
    {
        viewer.clear();
        root.draw(viewer);
        return viewer.getImage();
    }
    
    public BufferedImage drawBSPTreeToSameImage()
    {
        root.draw(viewer);
        return viewer.getImage();
    }    
    
    public void scale(double ratio)
    {
       root.scale(ratio); 
    }
    
    public void rotateRandomly()
    {
        double radians=0.0;
        for(int i=0;i<dimentions;i++)
        {
            for(int j=i+1;j<dimentions;j++)
            {
               rotateAroundOrigin(((Math.PI*2.0)*Math.random()), i,j);
            }
        }
        
    }
    
    public void rotateEachAxis(double radians)
    {
        for(int i=0;i<dimentions;i++)
        {
            for(int j=i+1;j<dimentions;j++)
            {
               rotateAroundOrigin(radians, i,j);
            }
        }
        
    }    
    
    public void rotateAroundOrigin(double radians, int coordinate1,int coordinate2)
    {
        root.rotateAroundOrigin(radians, coordinate1, coordinate2);
    }
    
    public void rotateAroundOrigin(int coordinate1,int coordinate2)
    {
        root.rotateAroundOrigin(currentUnitOfRotation[0], coordinate1, coordinate2);
    }
    
    
     public void translateByVector(Point p)
    {
        root.translateByVector(p);
    }   
    
}
