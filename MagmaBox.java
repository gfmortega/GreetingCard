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
public class MagmaBox extends BasicShape implements DrawingObject
{
	public final int width = 250;
	public final int height = 150;
	public MagmaBox(double x, double y, Color c )
	{
		super(x,y,c);
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Rect r = new Rect(x,y,width,height,(Color)fillColor,(Color)awakenedFillColor);
		r.draw(g2d,reset);
		MagmaM M = new MagmaM(x+15,y+10);
		M.draw(g2d,reset);
	}
	public void animate(){}
}