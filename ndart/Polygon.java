/*
 * polygon.java
 *
 * Created on September 27, 2005, 9:28 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */


/**
 *
 * @author justin
 */
public class Polygon{

    Point[] orderedListOfPoints;
    /** Creates a new instance of polygon */
    public Polygon(int dimentions, int verticies) {

        orderedListOfPoints=new Point[verticies];
        for(int i=0;i<orderedListOfPoints.length;i++)
        {
            orderedListOfPoints[i]=new Point(dimentions);
        }
    }
    
    public Point getPoint(int index)
    {
      return orderedListOfPoints[index];  
    }
    
    public void setCoordinate(double value, int coordinate)
    {
        if(coordinate<orderedListOfPoints.length)
        {
            orderedListOfPoints[coordinate].setCoordinate(coordinate,value);
        }
    }
    
    public Point getProjectedPoint(int index)
    {
        Point pPoint=(Point)orderedListOfPoints[index].clone();
        return pPoint;
    }
    
}
