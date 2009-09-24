
package geometry;

/**
 *
 * @author justin
 */
public class Point implements Cloneable {
    
    double[] coordinates;
    int dimentions=0;
    /** Creates a new instance of Point */
    public Point(int d) {
        dimentions=d;
        coordinates=new double[dimentions];
    }
    
    public void setCoordinate(int index, double value)
    {
        if(index<coordinates.length)
        {
            coordinates[index]=value;
        }
    
    }
    
    public void setAsOrigin()
    {
        for(int i=0;i<dimentions;i++)
        {
            coordinates[i]=0.0;
        }
    
    }
    
    public void setRandomCoordinates(double max)
    {
        for(int i=0;i<dimentions;i++)
        {
            if(Math.random()>0.5)
            {
                coordinates[i]=Math.random()*max*-1.0;
            }else
            {
                coordinates[i]=Math.random()*max;
            }

        }
    }
    
    public void negative()
    {
        for(int i=0;i<dimentions;i++)
        {
            coordinates[i]*=-1.0;
        }
    }
    
    public Point add(Point p1)
    {
        Point sum=new Point(dimentions);
        for(int i=0;i<dimentions;i++)
        {
            sum.setCoordinate(i, coordinates[i]+p1.getCoordinate(i));
        }
        return sum;
    }
    
    public Point minus(Point p1)
    {
        Point sum=new Point(dimentions);
        for(int i=0;i<dimentions;i++)
        {
            sum.setCoordinate(i, coordinates[i]-p1.getCoordinate(i));
        }
        return sum;
    }
        
    public double dot(Point p1)
    {
        double dotProduct=0.0;
        for(int i=0;i<dimentions;i++)
        {
            dotProduct +=coordinates[i]*p1.getCoordinate(i);
        }
        return dotProduct;
    }  
    
    public double getAngleRadians(Point p)
    {
        Point x=new Point(dimentions);
        Point y= new Point(dimentions);
        double radians=0.0;
        for(int i=0;i<dimentions;i++)
        {
            x.setCoordinate( i , p.getCoordinate(i) );
            y.setCoordinate(i, coordinates[i]);
        }
        radians =x.dot(y);
        return radians;
    }
    
    public double getCoordinate(int i)
    {
        return coordinates[i];
    }  
    
    public int getDimentions()
    {
        return dimentions;
    }
    
    public double getLength()
    {
        double length = 0.0;
        for(int i=0;i<dimentions;i++)
        {
            length+=(coordinates[i])*(coordinates[i]);
        }
        length=Math.sqrt(length);    
        return length;
    }
    
    public void normalize()
    {
        double length=getLength();
        for(int i=0;i<dimentions;i++)
        {
            coordinates[i]=coordinates[i]/length;
        }      
        
    }
    
    public void rotateAroundOrigin(double radians, int coordinate1, int coordinate2)
    {
        double newCoordinate1=(Math.cos(radians)*coordinates[coordinate1])+(Math.sin(radians)*coordinates[coordinate2]); 
        double newCoordinate2=(-1*Math.sin(radians)*coordinates[coordinate1])+(Math.cos(radians)*coordinates[coordinate2]);
        coordinates[coordinate1]=newCoordinate1;
        coordinates[coordinate2]=newCoordinate2;     
    }
    
    public void translateAlongAxis(int axis,double amount)
    {
        coordinates[axis]+=coordinates[axis]+amount;
    }
    
    public void translateByVector(Point p)
    {
        if(p.getDimentions()==dimentions)
        {
            for(int i=0;i<dimentions;i++)
            {
                coordinates[i]+=p.getCoordinate(i);
            }
        }
    }
    
    public void scale(double ratio)
    {
        for(int i=0;i<coordinates.length;i++)
        {
            coordinates[i]=coordinates[i]*ratio;
        }      
    }  
        
      protected Object clone()
    {
        Point temp = new Point(dimentions);
        
        for(int i=0;i<coordinates.length;i++)
        {
            temp.setCoordinate(i,coordinates[i]);
        }
        return temp;
    }  
      
    public double distant(Point p)
    {
        double distance=0;
        if(p.dimentions==this.dimentions){
        Point differance=this.minus(p);
        distance=differance.length();
        }
        else
        {
            System.out.println("must add  exception handling");
        }
        return distance;
    }  
    
    public double length()
    {
        double length=0;
        for(int i=0;i<dimentions;i++)
        {
            length+=coordinates[i]*coordinates[i];
        }
        length=Math.sqrt(length);
        return length;
    }
    
    public double[] getCosArrayRotateToAxis(int whichAxis)
    {
        double[] cos=new double[dimentions];
        if(whichAxis<dimentions){
        
        Point axis= new Point(dimentions);  
        axis.setCoordinate(whichAxis, 1.0);
        Point projectedPoint=null;
        for(int i=0;i<dimentions;i++)
        {
            projectedPoint=new Point(dimentions);
            projectedPoint.setAsOrigin();
            projectedPoint.setCoordinate(whichAxis, coordinates[whichAxis]);
            projectedPoint.setCoordinate(i, coordinates[i]);
            projectedPoint.normalize();
            cos[i]=axis.dot(projectedPoint);
        }
        
        }
        return cos;
    }
    
    public void rotateEachAxisGivenCos(double[] cos,int axis)
    {
        for(int i=0;i<dimentions;i++)
        {
            if(axis == i)
            {
            continue;
            }
        double sin=Math.sqrt(1.0-(cos[i]*cos[i]));
        double newCoordinate1=(cos[i]*coordinates[i])+(sin*coordinates[axis]); 
        double newCoordinate2=(-1*sin*coordinates[i])+(cos[i]*coordinates[axis]);
        coordinates[i]=newCoordinate1;
        coordinates[i]=newCoordinate2;
        }
        
    }
    
    public Point findPerpendicularLineInPlaneOfSecondVector(Point second)
    {
    Point newPoint=new Point(dimentions);
    double scale=(this.dot(this))/(this.dot(second));
    second.scale(scale);
    newPoint=this.minus(second);
    return newPoint;
    }
     
    
}
