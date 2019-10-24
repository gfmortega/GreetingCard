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
public class Banner extends BasicShape implements DrawingObject
{
	private double size;
	private int triangleCount;
	public Banner(double x, double y, Color c, double size, int triangleCount)
	{
		super(x,y,c);
		this.size = size;
		this.triangleCount = triangleCount;
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Square s = new Square(x,y,size,(Color)fillColor);
		s.draw(g2d,reset);
		
		double tSize = size/(double)triangleCount;
		double sqrt3 = Math.sqrt(3);
		for(int i = 0; i < triangleCount; i++)
		{
			RegularPolygon spike = new RegularPolygon(x+size-i*tSize-tSize/2.0, y+size+tSize/(2.0*sqrt3), 3, tSize, (Color)fillColor, Math.PI);
			spike.draw(g2d,reset);
		}
	}
	public void animate(){}
}