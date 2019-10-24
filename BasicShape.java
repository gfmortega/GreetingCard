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
public class BasicShape implements DrawingObject
{
	protected final static double EPS = 0.00001;
	protected double x;
	protected double y;
	
	protected boolean isOutlined;
	protected boolean isFilled;
	protected Color outlineColor;
	protected Paint fillColor;
	protected Paint awakenedFillColor;
	
	protected boolean awakenable;
	protected boolean awakened;
	
	public BasicShape(){}
	public BasicShape(double x, double y, Paint fillColor)
	{
		this.x = x;
		this.y = y;
		
		isFilled = true;
		this.fillColor = fillColor;
		
		isOutlined = false;
		outlineColor = Color.BLACK;
		
		awakenable = false;
		awakened = false;
		awakenedFillColor = this.fillColor;
	}
	public void setShowFill(boolean show)
	{
		isFilled = show;
	}
	public void setFillColor(Color c)
	{
		fillColor = c;
	}
	public void setShowOutline(boolean show)
	{
		isOutlined = show;
	}
	public void setOutlineColor(Color c)
	{
		outlineColor = c;
	}
	public void setAwakenedFillColor(Color c)
	{
		awakenedFillColor = c;
	}
	public Paint getColor(boolean awake)
	{
		return (awake ? awakenedFillColor : fillColor);
	}
	public void drawShape(Graphics2D g2d, Shape s)
	{
		
		if(isFilled)
		{
			if(awakened)
				g2d.setPaint(awakenedFillColor);
			else
				g2d.setPaint(fillColor);
			g2d.fill(s);
		}
		if(isOutlined)
		{
			g2d.setColor(outlineColor);
			g2d.draw(s);
		}
	}
	public void awakenableToggle()
	{
		awakenable = !awakenable;
	}
	public boolean canBeAwakened()
	{
		return awakenable;
	}
	public void awakenToggle()
	{
		awakened = !awakened;
	}
	public void draw(Graphics2D g2d, AffineTransform af){}
	public void animate(){}
}