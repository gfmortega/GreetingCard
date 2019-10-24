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
public class BedOfWater extends Poly
{
	private double waterHeightWeight;
	private double holeDepth;
	
	private Line lowestWaterLevel;
	private Line frameBottom;
	private Point2D.Double vanishingPoint;
	
	private Color waterCenter;
	private Color waterOuter;
	private Color lavaCenter;
	private Color lavaOuter;
	
	public BedOfWater(Line lowestWaterLevel, Line frameBottom, Point2D.Double vanishingPoint, double holeDepth )
	{
		super(frameBottom.getIntersection(new Line(vanishingPoint, lowestWaterLevel.pt1)),Color.BLUE);
		
		awakenable = true;
		lavaCenter = new Color(255,0,0);
		lavaOuter = new Color(255,255,0);
		waterCenter = new Color(0,0,255);
		waterOuter = new Color(100,100,180);
		
		this.lowestWaterLevel = lowestWaterLevel;
		this.frameBottom = frameBottom;
		this.vanishingPoint = vanishingPoint;
		this.holeDepth = holeDepth;
		waterHeightWeight = 0;
		
		setPerspective();
	}
	public void animate()
	{
		waterHeightWeight += 0.01;
		
		fillColor = new Color(0,0,(int)Math.floor(255 - 60*waterHeightFrac()));
		
		waterCenter = new Color(0,0,(int)Math.floor(255 - 120*waterHeightFrac()));
		waterOuter = new Color(150,150,(int)Math.floor(130 + 120*waterHeightFrac()));
		lavaCenter = new Color((int)Math.floor(255 - 60*waterHeightFrac()), (int)Math.floor(200*waterHeightFrac()), 0);
		lavaOuter = new Color(255, (int)Math.floor(255 - 235*waterHeightFrac()), 0);
		
		setPerspective();
	}
	private double waterHeightFrac()
	{
		return (1-Math.cos(waterHeightWeight))/2.0;
	}
	private void setPerspective()
	{
		pointList.clear();
		
		Point2D.Double leftCorner = new Point2D.Double(lowestWaterLevel.pt1.x, lowestWaterLevel.pt1.y - waterHeightFrac()*holeDepth*0.67);
		Point2D.Double rightCorner = new Point2D.Double(lowestWaterLevel.pt2.x, lowestWaterLevel.pt2.y - waterHeightFrac()*holeDepth*0.67);
		Point2D.Double leftEdge = frameBottom.getIntersection(new Line(vanishingPoint,leftCorner));
		Point2D.Double rightEdge = frameBottom.getIntersection(new Line(vanishingPoint,rightCorner));
		
		x = leftCorner.x;
		y = leftCorner.y;
		
		pointList.add(leftCorner);
		pointList.add(rightCorner);
		pointList.add(rightEdge);
		pointList.add(leftEdge);
		pointList.add(new Point2D.Double(x,y));
		
		Point2D.Double center = (new Line(leftCorner,rightEdge)).getIntersection(new Line(rightCorner,leftEdge));
		
		awakenedFillColor = new RadialGradientPaint(center, 275, new float[]{0.0f, 1.0f}, new Color[]{lavaCenter,lavaOuter});
		fillColor = new RadialGradientPaint(center, 400, new float[]{0.0f, 1.0f}, new Color[]{waterCenter, waterOuter});
	}
}