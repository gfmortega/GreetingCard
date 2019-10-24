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
import javax.swing.*;
import java.awt.geom.*;
public class GroudonEye extends Circle
{
	private double leash;
	private double anchorX;
	private double anchorY;
	public GroudonEye(double x, double y, double r, Color fillColor, double leash)
	{
		super(x,y,r,fillColor);
		
		awakenable = true;
		awakenedFillColor =  new Color(255,250,185);
		
		anchorX = x;
		anchorY = y;
		this.leash = leash;
	}
	public void track(double mouseX, double mouseY)
	{
		if(mouseX==anchorX && mouseY==anchorY)
			return;
		
		double A = (mouseX-anchorX);
		double O = (mouseY-anchorY);
		double H = Math.sqrt(A*A+O*O);
		
		double frac = Math.min(1, H/500.0);
		
		x = anchorX + frac*leash*(A/H);
		y = anchorY + frac*leash*(O/H);
	}
}