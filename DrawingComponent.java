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
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
public class DrawingComponent extends JComponent
{
	private int width;
	private int height;
	
	private Point2D.Double vanishingPoint;
	private Line frameBottom;
	private int horizonY;
	
	ArrayList<DrawingObject> allDrawingObjects;
	
	Timer animationTimer;
	
	private int leftGroudon_i;
	private int rightGroudon_i;
	
	private RedOrb orb;
	
	public DrawingComponent(int w, int h)
	{
		width = w;
		height = h;
		allDrawingObjects = new ArrayList<DrawingObject>();
		
		vanishingPoint = new Point2D.Double(512,350);
		horizonY = 400;
		frameBottom = new Line(0,height,width,height);
		
		setUpBackground();
		setUpWater();
		decorateWall();
		giveGreetings();
		setUpListeners();
		setUpBGM();
	}
	public void setUpBGM()
	{
		try
		{
			 URL url = this.getClass().getClassLoader().getResource("BGM.wav");
			 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			 Clip clip = AudioSystem.getClip();
			 clip.open(audioIn);
			 clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e)
		{
			System.out.println("An error happened in reading the BGM audio file.");
		}	
	}
	public void setUpListeners()
	{
		//Make Groudon's Eyes Follow You
		MouseMotionListener mouseTracker = new MouseMotionListener()
		{
			@Override
			public void mouseMoved(MouseEvent me)
			{
				double x = me.getX();
				double y = me.getY();
				((Groudon)allDrawingObjects.get(leftGroudon_i)).updateEyes(x,y);
				((Groudon)allDrawingObjects.get(rightGroudon_i)).updateEyes(x,y);
				
				if(orb.isInside(x,y))
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					if(!orb.getIsGlowing())
						orb.flipGlowing();
				}
				else
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					if(orb.getIsGlowing())
						orb.flipGlowing();
				}
			}
			public void mouseDragged(MouseEvent me){}
		};
		this.addMouseMotionListener(mouseTracker);
		
		MouseListener clickTracker = new MouseListener()
		{
			@Override
			public void mousePressed(MouseEvent me)
			{
				if(orb.isInside(me.getX(),me.getY()))
				{
					for(int i = 0; i < allDrawingObjects.size(); i++)
						if(allDrawingObjects.get(i).canBeAwakened())
							allDrawingObjects.get(i).awakenToggle();
					
					try
					{
						 URL url = this.getClass().getClassLoader().getResource( !((Groudon)allDrawingObjects.get(leftGroudon_i)).isAwakened() ? "383 - Groudon.wav" : "383P - Groudon (Primal).wav");
						 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
						 Clip clip = AudioSystem.getClip();
						 clip.open(audioIn);
						 clip.start();
					}
					catch(Exception e)
					{
						System.out.println("An error happened in reading the audio file.");
					}					
				}
			}
			@Override 
			public void mouseClicked(MouseEvent me){}
			@Override 
			public void mouseExited(MouseEvent me){}
			@Override 
			public void mouseEntered(MouseEvent me){}
			@Override 
			public void mouseReleased(MouseEvent me){}
		};
		this.addMouseListener(clickTracker);
		
		ActionListener animator = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				animateAll();
			}
		};
		animationTimer = new Timer(10,animator);
		animationTimer.setRepeats(true);
		animationTimer.start();
	}
	public void setUpBackground()
	{
		Rect theWall = new Rect(0,0,width,horizonY,new Color(128,0,0), new Color(168,40,40));
		Rect theFloor = new Rect(0,horizonY,width,height-(vanishingPoint.y+50), new Color(237,28,36), new Color(200,0,0));
		
		Line horizon = new Line(0,horizonY,1024,horizonY);
		Line shadingLine = new Line(vanishingPoint, new Point2D.Double(400,height));
		
		Poly floorShading = new Poly(horizon.getIntersection(shadingLine), ((Color)theFloor.getColor(false)).darker());
		floorShading.awakenableToggle();
		floorShading.setAwakenedFillColor(((Color)theFloor.getColor(true)).darker());
		floorShading.addPoint(frameBottom.getIntersection(shadingLine));
		floorShading.addPoint(new Point2D.Double(0,height));
		floorShading.addPoint(new Point2D.Double(0,horizonY));
		
		allDrawingObjects.add(theWall);
		allDrawingObjects.add(theFloor);
		allDrawingObjects.add(floorShading);
	}
	private void setUpWater() //drawn in perspective
	{
		double holeBottomWidth = 800;
		Line topPartOfHoleGuide = new Line(0,vanishingPoint.y+150.0,1,vanishingPoint.y+150.0);
		Line leftTopSideOfHoleGuide = new Line(vanishingPoint, new Point2D.Double(vanishingPoint.x - holeBottomWidth/2.0, height));
		Line rightTopSideOfHoleGuide = new Line(vanishingPoint, new Point2D.Double(vanishingPoint.x + holeBottomWidth/2.0, height));
		
		Point2D.Double topLeftCornerOfHole = topPartOfHoleGuide.getIntersection(leftTopSideOfHoleGuide);
		Point2D.Double topRightCornerOfHole = topPartOfHoleGuide.getIntersection(rightTopSideOfHoleGuide);
		
		Line leftDepthLine = new Line(topLeftCornerOfHole,new Point2D.Double(topLeftCornerOfHole.x,topLeftCornerOfHole.y+80));
		Line rightDepthLine = new Line(topRightCornerOfHole,new Point2D.Double(topRightCornerOfHole.x,topRightCornerOfHole.y+80));
		Line topPartOfWater = new Line(leftDepthLine.pt2,rightDepthLine.pt2);
		
		Line leftSideOfWaterGuide = (new Line(vanishingPoint,topPartOfWater.pt1));
		Line rightSideOfWaterGuide = (new Line(vanishingPoint,topPartOfWater.pt2));
		
		Poly dropDown = new Poly(leftDepthLine.pt2,new Color(180,180,180));
		dropDown.addPoint(rightDepthLine.pt2);
		dropDown.addPoint(rightSideOfWaterGuide.getIntersection(frameBottom));
		dropDown.addPoint(rightTopSideOfHoleGuide.pt2);
		dropDown.addPoint(rightDepthLine.pt1);
		dropDown.addPoint(leftDepthLine.pt1);
		dropDown.addPoint(leftTopSideOfHoleGuide.pt2);
		dropDown.addPoint(leftSideOfWaterGuide.getIntersection(frameBottom));
		allDrawingObjects.add(dropDown);
		
		Poly dropDownDarkShade = new Poly(leftDepthLine.pt2, new Color(160,160,195));
		dropDownDarkShade.addPoint(leftSideOfWaterGuide.getIntersection(frameBottom));
		dropDownDarkShade.addPoint(leftTopSideOfHoleGuide.pt2);
		dropDownDarkShade.addPoint(leftDepthLine.pt1);
		dropDownDarkShade.addPoint(rightDepthLine.pt1);
		dropDownDarkShade.addPoint(new Point2D.Double(rightDepthLine.pt1.x,rightDepthLine.pt1.y+30));
		dropDownDarkShade.addPoint(new Point2D.Double(leftDepthLine.pt1.x,leftDepthLine.pt1.y+40));
		allDrawingObjects.add(dropDownDarkShade);
				
		BedOfWater theWater = new BedOfWater(topPartOfWater, frameBottom, vanishingPoint, 80);
		allDrawingObjects.add(theWater);
		/*
		allDrawingObjects.add(new Line(0,vanishingPoint.y + 50,width,vanishingPoint.y + 50, Color.BLACK, 3)); //the floor
		
		allDrawingObjects.add(new Line(topLeftCornerOfHole,topRightCornerOfHole));
		allDrawingObjects.add(new Line(topLeftCornerOfHole,leftTopSideOfHoleGuide.pt2));
		allDrawingObjects.add(new Line(topRightCornerOfHole,rightTopSideOfHoleGuide.pt2));
		
		allDrawingObjects.add(leftDepthLine);
		allDrawingObjects.add(rightDepthLine);
		allDrawingObjects.add(topPartOfWater);
		allDrawingObjects.add(new Line(leftDepthLine.pt2,leftSideOfWaterGuide.getIntersection(new Line(0,768,1024,768))));
		allDrawingObjects.add(new Line(rightDepthLine.pt2,rightSideOfWaterGuide.getIntersection(new Line(0,768,1024,768))));
		*/
	}
	private void decorateWall()
	{
		Banner leftBanner = new Banner(35,10,new Color(50,50,50),300,5);
		allDrawingObjects.add(leftBanner);
		Banner midBanner = new Banner(380,40,new Color(50,50,50),280,10);
		allDrawingObjects.add(midBanner);
		Banner rightBanner = new Banner(705,10,new Color(50,50,50),300,5);
		allDrawingObjects.add(rightBanner);
		
		Groudon leftGroudon = new Groudon(80,15);
		Groudon rightGroudon = new Groudon(750,15);
		
		leftGroudon_i = allDrawingObjects.size();
		allDrawingObjects.add(leftGroudon);
		rightGroudon_i = allDrawingObjects.size();
		allDrawingObjects.add(rightGroudon);
		
		MagmaM theBigM = new MagmaM(415,105);
		allDrawingObjects.add(theBigM);
		
		Point2D.Float orbCenter = new Point2D.Float(515f,425f);
		float orbRadius = 50f;
		orb = new RedOrb(orbCenter,orbRadius);
		allDrawingObjects.add(orb);
	}
	private void giveGreetings()
	{
		GreetingText gText = new GreetingText(263,30);
		allDrawingObjects.add(gText);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		
		AffineTransform af = g2d.getTransform();
		
		for(int i = 0; i < allDrawingObjects.size(); i++)
			allDrawingObjects.get(i).draw(g2d,af);
			
		//g2d.setColor(Color.BLACK);
		//g2d.fillOval((int)vanishingPoint.x-5,(int)vanishingPoint.y-5,10,10);
	}
	private void animateAll()
	{
		for(int i = 0; i < allDrawingObjects.size(); i++)
			allDrawingObjects.get(i).animate();
		repaint();
	}
}