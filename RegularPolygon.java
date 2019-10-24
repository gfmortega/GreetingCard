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
public class RegularPolygon extends BasicShape implements DrawingObject
{
	//x and y are for the center of the polygon	
	private int sideCount;
	private double sideLength;
	private double theta;
	
	public RegularPolygon(double x, double y, int sideCount, double sideLength, Color fillColor)
	{
		super(x,y,fillColor);
		this.sideCount = sideCount;
		this.sideLength = sideLength;
		this.theta = 0.0;
	}
	public RegularPolygon(double x, double y, int sideCount, double sideLength, Color fillColor, Color outlineColor)
	{
		this(x,y,sideCount,sideLength,fillColor);
		this.isOutlined = true;
		this.outlineColor = outlineColor;
	}
	public RegularPolygon(double x, double y, int sideCount, double sideLength, Color fillColor, double theta)
	{
		this(x,y,sideCount,sideLength,fillColor);
		this.theta = theta;
	}
	public RegularPolygon(double x, double y, int sideCount, double sideLength, Color fillColor, Color outlineColor, double theta)
	{
		this(x,y,sideCount,sideLength,fillColor,outlineColor);
		this.theta = theta;
	}
	public void setAngle(double theta)
	{
		this.theta = theta;
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		
		g2d.rotate(-theta,this.x,this.y);
		
		Path2D.Double p = new Path2D.Double();
		
		double x = this.x - sideLength/2.0;
		double y = this.y + sideLength/(2.0*Math.tan(Math.PI/sideCount)); //length of radius
		double theta = 0;
		
		p.moveTo(x,y);
		for(int i = 0; i < sideCount; i++)
		{
			//walk sideLength unit steps in the (local) theta direction
			x += sideLength*Math.cos(theta);
			y += -sideLength*Math.sin(theta); //negative to move up
			
			theta += 2.0*Math.PI/sideCount; //compound rotations of external angle
			
			p.lineTo(x,y);
		}
		drawShape(g2d,p);
		
		g2d.setTransform(reset);
		
	}
	public void animate(){}
}