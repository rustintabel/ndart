/*
 * BSPTreeNode.java
 *
 * Created on September 27, 2005, 7:47 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */


import java.util.Random;

/**
 *
 * @author justin
 */
public class BSPTreeNode {
   
    private BSPTreeNode parent;
    private BSPTreeNode left=null;
    private BSPTreeNode right=null;
    private Point normal;
    private Point center;
    private int dimentions=0;
    private Triangle triangle=null;
    
    public BSPTreeNode(int d) 
    {
        dimentions=d;   
        triangle= new Triangle(dimentions);
    }
    
    public void setRandTriangle()
    {
        
        triangle.setRandTriangleAndPossibleHyperPlanes();
        center=triangle.getHyperplaneCenter();
        normal=triangle.getHyperplaneNormal(); 
    }
    
     public void addPointToTriangle(Point p)
    {
        triangle.addPoint(p);
    }   
    
    public Triangle getTriangle()
    {
        return triangle;
    }
    
    public boolean isRight(Point p)
    {
        boolean isRight=false;
        Point cp=center.minus(p);
        Point cn=center.minus(normal);
        double cpdotcn=cp.dot(cn);
        if(cpdotcn>0)
        {
            isRight=true;
        }else
        {
            isRight=false;
        }
        return isRight;
    }
    
    public boolean isLeft(Point p)
    {
        boolean isLeft=false;
        Point cp=center.minus(p);
        Point cn=center.minus(normal);
        double cpdotcn=cp.dot(cn);
        if(cpdotcn<0)
        {
            isLeft=true;
        }else
        {
            isLeft=false;
        }
        return isLeft;
    }    
    
    public boolean isRight(Triangle t)
    {
        boolean isRight=true;
        for(int i=0;i<t.getNumPointsSet();i++)
        {
            Point p=t.getPoint(i);
            if(!isRight(p))
            {
                isRight=false;
            }
        }
        return isRight;
    }
    
    public boolean isLeft(Triangle t)
    {
        boolean isLeft=true;
        for(int i=0;i<t.getNumPointsSet();i++)
        {
            Point p=t.getPoint(i);
            if(!isLeft(p))
            {
                isLeft=false;
            }
        }
        return isLeft;
    }
    
   public boolean addNode(BSPTreeNode newNode)
   {
	    boolean success=false;
	    if(isRight(newNode.getTriangle()))
	    {
	        if(right==null)
	        {
	            setRight(newNode);
	            success=true;
	        }
	        else
	        {
	            success=right.addNode(newNode);
	        }    
	    }
	    else if(isLeft(newNode.getTriangle()))
	    {
	         if(left==null)
	        {
	            setLeft(newNode);
	            success=true;
	        }
	        else
	        {
	            success=left.addNode(newNode);
	        }    
	    }
	
	    return success;
   } 
    
   public void setParent(BSPTreeNode p)
   {
        parent=p;
   }   
   
   public void setLeft(BSPTreeNode l)
   {
        left=l;
        l.setParent(this);
   }   
   
   public void setRight(BSPTreeNode r)
   {
        right=r;
        r.setParent(this);
   }  
   
   public BSPTreeNode getParent()
   {
        return parent;
   }   
   
   public BSPTreeNode setLeft()
   {
        return left;
   }   
   
   public BSPTreeNode setRight()
   {
        return left;
   }    
   
   public void draw(Viewer v)
   {
       if(isRight(v.getViewPoint()))
       {
            if(left!=null)
            {
                left.draw(v);
            }
            v.drawTriangle(triangle);
            if(right!=null)
            {
                right.draw(v);
            }
       }   
       if(isLeft(v.getViewPoint()))
       {
            if(right!=null)
            {
                right.draw(v);
            }
            v.drawTriangle(triangle);
            if(left!=null)
            {
                left.draw(v);
            }
       } 
       
   }
   
    public void rotateAroundOrigin(double radians, int coordinate1,int coordinate2)
    {
        triangle.rotateAroundOrigin(radians, coordinate1, coordinate2);
        
        if(left!=null)
        {
        left.rotateAroundOrigin(radians, coordinate1, coordinate2);
        }
        
        if(right!=null)
        {
        right.rotateAroundOrigin(radians, coordinate1, coordinate2);
        }
    }  
    
        public void translateByVector(Point p)
    {
        if(triangle!=null)
        {
            triangle.translateByVector(p);
        }
        
        if(left!=null)
        {
            left.translateByVector(p);
        }
        
        if(right!=null)
        {
            right.translateByVector(p);
        }        
    } 
        
    public void scale(double ratio)
    {
        if(triangle!=null)
        {
            triangle.scale(ratio);
        }
        
        if(left!=null)
        {
            left.scale(ratio);
        }
        
        if(right!=null)
        {
            right.scale(ratio);
        }
    }    
    
    public void findNormals()
    {
        triangle.findNormals();
    }
    
    public void chooseHyperplane(int whichOne)
    {
        triangle.chooseHyperplane(whichOne);
    }
   
    
}
