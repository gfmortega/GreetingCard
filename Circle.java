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
public class Circle extends BasicShape implements DrawingObject
{
	//x and y are for the center of the circle
	protected double r;
	public Circle(double x, double y, double r)
	{
		super(x,y,null);
		this.r = r;
	}
	public Circle(double x, double y, double r, Paint fillColor)
	{
		super(x,y,fillColor);
		this.r = r;
	}
	public Circle(double x, double y, double r, Color fillColor)
	{
		super(x,y,fillColor);
		this.r = r;
	}
	public Circle(double x, double y, double r, Color fillColor, Color outlineColor)
	{
		this(x,y,r,fillColor);
		this.isOutlined = true;
		this.outlineColor = outlineColor;
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Ellipse2D.Double ell = new Ellipse2D.Double(x-r,y-r,2.0*r,2.0*r);
		drawShape(g2d,ell);
	}
	public void animate(){}
}