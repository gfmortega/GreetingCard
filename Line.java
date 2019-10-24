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
public class Line extends BasicShape implements DrawingObject
{
	public Point2D.Double pt1;
	public Point2D.Double pt2;
	private double x2;
	private double y2;
	private double thickness;
	private int endType;
	public Line(double x1, double y1, double x2, double y2)
	{
		this.x = x1;
		this.y = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		pt1 = new Point2D.Double(x1,y1);
		pt2 = new Point2D.Double(x2,y2);
		
		thickness = 2;
		outlineColor = Color.BLACK;
		isOutlined = true;
		isFilled = false;
		
		endType = BasicStroke.CAP_BUTT; //default has no special end
	}
	public Line(Point2D.Double pt1, Point2D.Double pt2)
	{
		this(pt1.x, pt1.y, pt2.x, pt2.y);
	}
	public Line(double x1, double y1, double x2, double y2, Color outlineColor, double thickness)
	{
		this(x1,y1,x2,y2);
		this.outlineColor = outlineColor;
		this.thickness = thickness;
	}
	public Line(Point2D.Double pt1, Point2D.Double pt2, Color outlineColor, double thickness)
	{
		this(pt1.x, pt1.y, pt2.x, pt2.y, outlineColor, thickness);
	}
	public void setThickness(double thickness)
	{
		this.thickness = thickness;
	}
	public double slope()
	{
		return (y2-y)/(x2-x);
	}
	// (y-y0) = m(x-x0)
	public double solveForX(double y_val) //find the x value which gives this y value
	{
		//(y-y0)/m + x0 = x
		if(y==y2) //horizontal line
		{
			if(y==y_val)
				return x;
			else
				throw new Error("No solutions for x.");
		}
		else if(x==x2) //vertical line
			return x;
		else
			return (y_val-y)/slope() + x;
	}
	public double solveForY(double x_val) //find the y value which gives this x value
	{
		if(y==y2) //horizontal line
			return y;
		else if(x==x2) //vertical line
		{
			if(x==x_val)
				return y;
			else
				throw new Error("No solutions for y.");
		}
		else
			return slope()*(x_val-x) + y;
	}
	public Point2D.Double getIntersection(Line line2)
	{
		//if this.slope()==line2.slope()
		if(Math.abs(this.slope()-line2.slope()) <= EPS)
		{
			// if y == line2.solveForY(x)
			if( Math.abs(y-line2.solveForY(x)) <= EPS )
				return new Point2D.Double(x,y);
			else
				return null;
		}
		if(line2.slope()==Double.POSITIVE_INFINITY || line2.slope()==Double.NEGATIVE_INFINITY) //line2 is vertical
			return new Point2D.Double(line2.x, solveForY(line2.x));
		else
		{
			double xAns = (line2.y - y - (line2.slope()*line2.x - slope()*x))/(slope()-line2.slope());
			return new Point2D.Double( xAns, solveForY(xAns));
		}
			/*
				y-y1 = m1(x-x1)
				y-y2 = m2(x-x2)
				m1(x-x1)+y1 = m2(x-x2)+y2 
				m1*x - m2*x = y2-y1 - m2*x2 + m1*x1
				x = (y2-y1-(m2x2-m1x1))/(m1-m2)
			*/
		
	}
	public Line extend()
	{
		return new Line(new Point2D.Double(solveForX(-5),-5), new Point2D.Double(solveForX(1030),1030));
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Stroke resetStroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke((float)thickness,endType,BasicStroke.JOIN_BEVEL));
		
		Line2D.Double line = new Line2D.Double(x,y,x2,y2);
		drawShape(g2d,line);
		
		g2d.setStroke(resetStroke);
	}
	public void animate(){}
}