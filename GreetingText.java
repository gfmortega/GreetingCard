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
public class GreetingText implements DrawingObject
{
	private int x;
	private int y;
	public GreetingText(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Font resetFont = g2d.getFont();
		g2d.setFont( new Font("Arial", Font.PLAIN, 36 ) );
		
		g2d.setColor(Color.RED);
		g2d.drawString("TEAM MAGMA WISHES YOU A",x,y);
		g2d.setColor(Color.YELLOW);
		g2d.drawString("SCORCHING",x+140,y+35);
		g2d.setColor(Color.RED);
		g2d.drawString("SUMMER",x+180,y+70);
		
		g2d.setFont(resetFont);
	}
	public void animate(){}
	public boolean canBeAwakened()
	{
		return false;
	}
	public void awakenToggle()
	{}
}