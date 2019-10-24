/* Gerard Ortega, 171668

I have not discussed the Java language code 
in my program with anyone
other than my instructor or the teaching
assistants assigned to this course.

I have not used Java language code 
obtained from another student, or
any other unauthorized source, either 
modified or unmodified.

If any Java language
code or documentation used in my program was
obtained from another source, such as a text
book or course notes, those have been clearly
noted with a proper citation in the 
comments of my code. */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
public class Poly extends BasicShape implements DrawingObject
{
	//x and y determine the top-left of the square 
	protected ArrayList<Point2D.Double> pointList;
	
	public Poly(double x, double y, Color fillColor)
	{
		super(x,y,fillColor);
		pointList = new ArrayList<Point2D.Double>();
	}
	public Poly(Point2D.Double p, Color fillColor)
	{
		this(p.x,p.y,fillColor);
	}
	public void addPoint(Point2D.Double p)
	{
		pointList.add(p);
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Path2D.Double p = new Path2D.Double();
		p.moveTo(x,y);
		for(int i = 0; i < pointList.size(); i++)
			p.lineTo(pointList.get(i).x,pointList.get(i).y);
		drawShape(g2d,p);
	}
	public void animate(){}
}