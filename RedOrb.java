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
import java.awt.event.*;
public class RedOrb extends Circle
{
	private float[] dist;
	
	private Point2D.Float orbCenter;
	private float orbRadius;
	private boolean isGlowing;
	
	private Color innerColor;
	private Color outerColor;
	private Color outerColorAwakened;
	
	private Color innerColorGlowing;
	private Color outerColorGlowing;
	
	private double diffuseOutWeight;
	private double tParticle;
	public RedOrb(Point2D.Float pt, float r)
	{
		super(pt.x,pt.y,r);
		
		awakenable = true;
		
		orbCenter = pt;
		orbRadius = r;
		dist = new float[]{0.0f, 1f};
		isGlowing = false;
		
		diffuseOutWeight = 0;
		tParticle = 0;
	}
	public double diffuseFrac()
	{
		return (1.0-Math.cos(diffuseOutWeight))/2.0;
	}
	public void animate()
	{
		diffuseOutWeight += 0.01;
		tParticle += 0.015;
		
		innerColor = new Color((int)Math.floor(255-255*diffuseFrac()),0,0);
		outerColor = new Color((int)Math.floor(220*diffuseFrac()),0,0);
		outerColorAwakened = new Color(255,(int)Math.floor(255-255*diffuseFrac()), (int)Math.floor(255-255*diffuseFrac()));
		innerColorGlowing = new Color((int)Math.floor(255-120*diffuseFrac()), 60, 60);
		outerColorGlowing = new Color((int)Math.floor(180*diffuseFrac()), 0, 0);
		
		if(isGlowing)
			fillColor = awakenedFillColor = new RadialGradientPaint(orbCenter,orbRadius,dist, new Color[]{innerColorGlowing, outerColorGlowing});
		else
		{
			fillColor = new RadialGradientPaint(orbCenter,orbRadius+30,dist, new Color[]{innerColor, outerColor});
			awakenedFillColor = new RadialGradientPaint(orbCenter,orbRadius+30,dist, new Color[]{innerColor, outerColorAwakened});
		}
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		super.draw(g2d,reset);
		
		/*draw the omega*/
		Font resetFont = g2d.getFont();
		
		g2d.setColor( awakened ? Groudon.normalCarapace : Groudon.primalSkinColor );
		g2d.setFont( new Font("Arial", Font.PLAIN, 72 ) );
		g2d.drawString( "\u03A9", (int)(x-r/2), (int)(y+r/2) );
		if(!isGlowing) //draw particles
		{
			drawParticles(g2d,reset,2*orbRadius/3,3*orbRadius/2,2,3,2);
			drawParticles(g2d,reset,3*orbRadius/2,2*orbRadius/3.0,3,5,3);
			drawParticles(g2d,reset,2*orbRadius/3,2*orbRadius/3,5,7,4);
		}
		g2d.setFont(resetFont);
	}
	public void drawParticles(Graphics2D g2d, AffineTransform reset, double a, double b,  double r, double s, double tOff)
	{
		double x = this.x + a*Math.cos(r*tParticle+tOff);
		double y = this.y + b*Math.sin(s*tParticle+tOff);
		
		int effCount = 3;
		double effRadius = 8;
		double effSize = 5;
		for(int i = 0; i < effCount; i++)
			(new RegularPolygon(x+effRadius*Math.sin(i*2.0*Math.PI/effCount+tParticle*i), y+effRadius*Math.cos(i*2.0*Math.PI/effCount+tParticle*i), 7, effSize, new Color(255,255-80*i,0,130), 2.0*Math.PI*i/effCount)).draw(g2d,reset);
			
	}
	public boolean getIsGlowing()
	{
		return isGlowing;
	}
	public void flipGlowing()
	{
		isGlowing = !isGlowing;
	}
	public boolean isInside(double x, double y)
	{
		return (x-orbCenter.x)*(x-orbCenter.x) + (y-orbCenter.y)*(y-orbCenter.y) <= orbRadius*orbRadius;
	}
}