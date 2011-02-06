

import java.awt.image.*;
import java.awt.*;
/**
 *
 * @author justin
 */
public class Viewer {
    
    Point viewer=null;
    Point screenCenter=null;
    BufferedImage currentView=null;
    Graphics2D graphics=null;
    double viewableRadius=0;
    double distanceFromScreen=0;
    int screenHeight=0;
    int screenWidth=0;
    
    public Viewer(int dimentions, double v, double d,int sH,int sW) 
    {
        screenHeight=sH;
        screenWidth=sW;
        viewableRadius=v;
        distanceFromScreen=d;
        viewer = new Point(dimentions);
        viewer.setAsOrigin();
        viewer.setCoordinate(2, distanceFromScreen+viewableRadius);
        screenCenter= new Point(dimentions);
        screenCenter.setAsOrigin();
        screenCenter.setCoordinate(2, viewableRadius);
        currentView= new BufferedImage(screenHeight, screenWidth, BufferedImage.TYPE_INT_RGB);
        graphics=currentView.createGraphics();
        clear();
        Color c2=new Color(0,0,0);
        graphics.setBackground(c2);
    }
    
    public void clear()
    {
        graphics.clearRect(0, 0, screenHeight, screenWidth);
    }
    
    public BufferedImage getImage()
    {
        return currentView;
    }
    
    public Point getViewPoint()
    {
        return viewer;
    }
    
    public void drawTriangle(Triangle t)
    {
        int[] x=new int[3];
        int[] y=new int[3];
        for(int i=0;i<t.getNumPointsSet();i++)
        {
        Point p=t.getPoint(i);
        Point pp=orthogonalProjectPoint(p);
        //Point pp=perspectiveProjectPoint(p);
        x[i]=(int)(pp).getCoordinate(0)+(screenWidth/2);
        y[i]=(int)(pp).getCoordinate(1)+(screenHeight/2);
        }
        graphics.setColor(t.getColor());
        graphics.fillPolygon(x,y,3);
    }
    
    public Point perspectiveProjectPoint(Point p)
    {
        Point projectedPoint =new Point(2);
        double c=((viewableRadius-distanceFromScreen)/(p.getCoordinate(2)-distanceFromScreen));
        projectedPoint.setCoordinate(0, p.getCoordinate(0)*c);
        projectedPoint.setCoordinate(1, p.getCoordinate(1)*c);
        return projectedPoint;
    }
    
        public Point orthogonalProjectPoint(Point p)
    {
        Point projectedPoint =new Point(2);
        projectedPoint.setCoordinate(0, p.getCoordinate(0));
        projectedPoint.setCoordinate(1, p.getCoordinate(1));
        return projectedPoint;
    }
    
    
}
