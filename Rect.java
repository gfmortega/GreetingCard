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
public class Rect extends BasicShape implements DrawingObject
{
	private double width;
	private double height;
	public Rect(double x, double y, double w, double h, Color fillColor, Color awakenedFillColor)
	{
		super(x,y,fillColor);
		
		awakenable = true;
		this.awakenedFillColor = awakenedFillColor;
		
		width = w;
		height = h;
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Rectangle2D.Double rect = new Rectangle2D.Double(x,y,width,height);
		drawShape(g2d,rect);
	}
	public void animate(){}
}