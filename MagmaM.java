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
public class MagmaM implements DrawingObject
{
	private double x;
	private double y;
	
	private boolean awakened;
	public MagmaM(double x, double y)
	{
		awakened = false;
		this.x = x;
		this.y = y;
	}
	public boolean canBeAwakened()
	{
		return true;
	}
	public void awakenToggle()
	{
		awakened = !awakened;
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		Path2D.Double p1 = new Path2D.Double();
		p1.moveTo(x+1, y+131);
		p1.lineTo(x+54, y+3);
		p1.lineTo(x+83, y+47);
		p1.lineTo(x+106, y+20);
		p1.lineTo(x+129, y+47);
		p1.lineTo(x+157, y+0);
		p1.lineTo(x+215, y+131);
		p1.lineTo(x+143, y+131);
		p1.lineTo(x+160, y+115);
		p1.lineTo(x+160, y+96);
		p1.lineTo(x+140, y+78);
		p1.lineTo(x+108, y+69);
		p1.lineTo(x+74, y+80);
		p1.lineTo(x+55, y+97);
		p1.lineTo(x+58, y+118);
		p1.lineTo(x+74, y+131);
		p1.lineTo(x+1, y+131);
		
		Path2D.Double p2 = new Path2D.Double();
		p2.moveTo(x+55, y+27);
		p2.lineTo(x+39, y+67);
		p2.lineTo(x+42, y+80);
		p2.lineTo(x+49, y+88);
		p2.lineTo(x+65, y+75);
		p2.lineTo(x+109, y+59);
		p2.lineTo(x+145, y+71);
		p2.lineTo(x+168, y+90);
		p2.lineTo(x+176, y+84);
		p2.lineTo(x+177, y+67);
		p2.lineTo(x+155, y+21);
		p2.lineTo(x+130, y+57);
		p2.lineTo(x+107, y+31);
		p2.lineTo(x+82, y+60);
		p2.lineTo(x+55, y+28);
		
		Path2D.Double p3 = new Path2D.Double();
		p3.moveTo(x+48, y+99);
		p3.lineTo(x+50, y+121);
		p3.lineTo(x+58, y+126);
		p3.lineTo(x+14, y+126);
		p3.lineTo(x+25, y+100);
		p3.lineTo(x+40, y+88);
		p3.lineTo(x+48, y+99);
		
		Path2D.Double p4 = new Path2D.Double();
		p4.moveTo(x+36, y+74);
		p4.lineTo(x+28, y+91);
		p4.lineTo(x+37, y+83);
		p4.lineTo(x+36, y+74);
		
		Path2D.Double p5 = new Path2D.Double();
		p5.moveTo(x+164, y+120);
		p5.lineTo(x+156, y+126);
		p5.lineTo(x+201, y+125);
		p5.lineTo(x+189, y+96);
		p5.lineTo(x+177, y+89);
		p5.lineTo(x+168, y+96);
		p5.lineTo(x+169, y+113);
		p5.lineTo(x+165, y+119);
		
		Path2D.Double p6 = new Path2D.Double();
		p6.moveTo(x+180, y+72);
		p6.lineTo(x+179, y+84);
		p6.lineTo(x+187, y+89);
		p6.lineTo(x+181, y+72);
	
		g2d.setColor(awakened ? Groudon.primalCarapace : Color.RED );
		g2d.fill(p1);
		g2d.setColor(awakened ? Groudon.primalSkinColor : new Color(25, 25, 25));
		g2d.fill(p2);
		g2d.fill(p3);
		g2d.fill(p4);
		g2d.fill(p5);
		g2d.fill(p6);
	}
	public void animate(){}
}