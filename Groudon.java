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
import java.awt.event.*;
import javax.swing.*;
public class Groudon implements DrawingObject
{
	private int x;
	private int y;
	
	private boolean roaring;
	private boolean awakened;
	private GroudonEye leftEye;
	private GroudonEye rightEye;
	
	public final static Color normalCarapace = new Color(255,75,40);
	public final static Color normalCarapaceShading = new Color(255,50,50);
	public final static Color normalSkinColor = Color.BLACK;
	public final static Color normalEyeColor = Color.YELLOW;
	
	public final static Color primalCarapace = new Color(210,65,90);
	public final static Color primalCarapaceShading = new Color(210,30,60);
	public final static Color primalSkinColor = new Color(255,250,185);
	public final static Color primalEyeColor = new Color(230,120,95);
	
	private Timer roarTime;
	public Groudon(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		awakened = false;
		roaring = false;
		
		leftEye = new GroudonEye(x+55,y+160,8,Color.BLACK,5);
		rightEye = new GroudonEye(x+148,y+160,8,Color.BLACK,5);
		
		roarTime = new Timer(1000,null);
	}
	public void updateEyes(double x, double y)
	{
		leftEye.track(x,y);
		rightEye.track(x,y);
	}
	public void draw(Graphics2D g2d, AffineTransform reset)
	{
		if(roaring)
			drawOpenJaw(g2d);
		else
			drawClosedJaw(g2d);
		drawFace(g2d);
		drawEyes(g2d);
		drawCrown(g2d);
	}
	public void animate(){}
	public void drawEyes(Graphics2D g2d)
	{
		leftEye.draw(g2d,g2d.getTransform());
		rightEye.draw(g2d,g2d.getTransform());
	}
	public void drawCrown(Graphics2D g2d)
	{
		Path2D.Double p11 = new Path2D.Double();
		p11.moveTo(x+107, y+235);
		p11.lineTo(x+63, y+209);
		p11.lineTo(x+62, y+194);
		p11.lineTo(x+77, y+197);
		p11.lineTo(x+64, y+166);
		p11.lineTo(x+16, y+91);
		p11.lineTo(x+44, y+64);
		p11.lineTo(x+148, y+62);
		p11.lineTo(x+180, y+88);
		p11.lineTo(x+139, y+166);
		p11.lineTo(x+128, y+195);
		p11.lineTo(x+142, y+192);
		p11.lineTo(x+142, y+209);
		p11.lineTo(x+108, y+234);
		
		Path2D.Double p17 = new Path2D.Double();
		p17.moveTo(x+106, y+233);
		p17.lineTo(x+8, y+78);
		p17.lineTo(x+16, y+91);
		p17.lineTo(x+30, y+17);
		p17.lineTo(x+60, y+46);
		p17.lineTo(x+60, y+46);
		p17.lineTo(x+71, y+7);
		p17.lineTo(x+99, y+42);
		p17.lineTo(x+126, y+0);
		p17.lineTo(x+137, y+41);
		p17.lineTo(x+171, y+0);
		p17.lineTo(x+185, y+84);
		
		Path2D.Double p18 = new Path2D.Double();
		p18.moveTo(x+106, y+233);
		p18.lineTo(x+61, y+46);
		p18.lineTo(x+30, y+17);
		p18.lineTo(x+85, y+165);
		p18.lineTo(x+106, y+233);
		p18.lineTo(x+107, y+233);
		p18.lineTo(x+72, y+8);
		p18.lineTo(x+99, y+41);
		p18.lineTo(x+108, y+235);
		p18.lineTo(x+125, y+1);
		p18.lineTo(x+138, y+42);
		p18.lineTo(x+109, y+233);
		p18.lineTo(x+109, y+234);
		p18.lineTo(x+171, y+2);
		p18.lineTo(x+185, y+84);
		p18.lineTo(x+109, y+233);

		g2d.setColor(awakened ? primalCarapace : normalCarapace);
		g2d.draw(p11);
		g2d.fill(p11);
		g2d.fill(p17);
		
		g2d.setColor(awakened ? primalCarapaceShading : normalCarapaceShading);
		g2d.fill(p18);
	}
	public void drawFace(Graphics2D g2d)
	{
		Path2D.Double p7 = new Path2D.Double();
		p7.moveTo(x+107, y+233);
		p7.lineTo(x+15, y+180);
		p7.lineTo(x+11, y+119);
		p7.lineTo(x+23, y+91);
		p7.lineTo(x+168, y+83);
		p7.lineTo(x+186, y+117);
		p7.lineTo(x+185, y+177);
		p7.lineTo(x+107, y+234);
		
		Path2D.Double p8 = new Path2D.Double();
		p8.moveTo(x+23, y+188);
		p8.lineTo(x+30, y+263);
		p8.lineTo(x+35, y+283);
		p8.lineTo(x+17, y+269);
		p8.lineTo(x+5, y+215);
		p8.lineTo(x+1, y+159);
		p8.lineTo(x+7, y+110);
		p8.lineTo(x+28, y+141);
		p8.lineTo(x+29, y+161);
		p8.lineTo(x+46, y+192);
		p8.lineTo(x+25, y+189);
		
		Path2D.Double p10 = new Path2D.Double();
		p10.moveTo(x+159, y+188);
		p10.lineTo(x+180, y+184);
		p10.lineTo(x+178, y+257);
		p10.lineTo(x+178, y+258);
		p10.lineTo(x+173, y+279);
		p10.lineTo(x+199, y+255);
		p10.lineTo(x+203, y+204);
		p10.lineTo(x+198, y+142);
		p10.lineTo(x+190, y+102);
		p10.lineTo(x+171, y+135);
		p10.lineTo(x+173, y+156);
		p10.lineTo(x+160, y+188);
		
		Ellipse2D.Double p11 = new Ellipse2D.Double(x+45,y+135,20,40);
		
		Ellipse2D.Double p16 = new Ellipse2D.Double(x+140,y+135,20,40);
		
		Path2D.Double p20 = new Path2D.Double();
		p20.moveTo(x+34, y+282);
		p20.lineTo(x+22, y+265);
		p20.lineTo(x+11, y+185);
		p20.lineTo(x+20, y+164);
		p20.lineTo(x+45, y+189);
		p20.lineTo(x+24, y+187);
		p20.lineTo(x+29, y+263);
		p20.lineTo(x+35, y+281);
		
		Path2D.Double p21 = new Path2D.Double();
		p21.moveTo(x+174, y+279);
		p21.lineTo(x+198, y+256);
		p21.lineTo(x+204, y+205);
		p21.lineTo(x+199, y+148);
		p21.lineTo(x+190, y+102);
		p21.lineTo(x+194, y+204);
		p21.lineTo(x+193, y+231);
		p21.lineTo(x+187, y+257);
		p21.lineTo(x+175, y+277);

		g2d.setColor(awakened ? primalSkinColor : normalSkinColor);
		g2d.draw(p7);
		g2d.fill(p7);
		
		g2d.setColor(awakened ? primalCarapace : normalCarapace);
		g2d.fill(p8);
		g2d.draw(p10);
		g2d.fill(p10);
		
		g2d.setColor(awakened ? primalEyeColor : normalEyeColor);
		g2d.fill(p11);
		g2d.fill(p16);
		
		g2d.setColor(awakened ? primalCarapaceShading : normalCarapaceShading);
		g2d.draw(p20);
		g2d.fill(p20);
		g2d.draw(p21);
		g2d.fill(p21);
	}
	public void drawClosedJaw(Graphics2D g2d)
	{
		Path2D.Double p2 = new Path2D.Double();
		p2.moveTo(x+23, y+197);
		p2.lineTo(x+26, y+228);
		p2.lineTo(x+51, y+252);
		p2.lineTo(x+108, y+268);
		p2.lineTo(x+161, y+250);
		p2.lineTo(x+182, y+226);
		p2.lineTo(x+180, y+191);
		p2.lineTo(x+103, y+164);
		p2.lineTo(x+24, y+198);
		g2d.setColor(new Color(200, 200, 200));
		g2d.fill(p2);
		Path2D.Double p4 = new Path2D.Double();
		p4.moveTo(x+19, y+182);
		p4.lineTo(x+19, y+183);
		p4.lineTo(x+19, y+184);
		p4.lineTo(x+19, y+200);
		p4.lineTo(x+185, y+197);
		p4.lineTo(x+184, y+179);
		p4.lineTo(x+17, y+180);
		g2d.setColor(new Color(200, 200, 200));
		g2d.draw(p4);
		g2d.setColor(new Color(200, 200, 200));
		g2d.fill(p4);
		Path2D.Double p6 = new Path2D.Double();
		p6.moveTo(x+157, y+197);
		p6.lineTo(x+153, y+235);
		p6.lineTo(x+133, y+248);
		p6.lineTo(x+109, y+266);
		p6.lineTo(x+160, y+249);
		p6.lineTo(x+178, y+230);
		p6.lineTo(x+179, y+230);
		p6.lineTo(x+179, y+183);
		p6.lineTo(x+157, y+198);
		g2d.setColor(new Color(150, 150, 150));
		g2d.draw(p6);
		g2d.setColor(new Color(150, 150, 150));
		g2d.fill(p6);

	}
	public void drawOpenJaw(Graphics2D g2d)
	{
		Path2D.Double p2 = new Path2D.Double();
		p2.moveTo(x+23, y+217);
		p2.lineTo(x+26, y+248);
		p2.lineTo(x+51, y+272);
		p2.lineTo(x+108, y+288);
		p2.lineTo(x+161, y+270);
		p2.lineTo(x+182, y+246);
		p2.lineTo(x+180, y+211);
		p2.lineTo(x+103, y+184);
		p2.lineTo(x+24, y+218);
		g2d.setColor(new Color(200, 200, 200));
		g2d.fill(p2);
		Path2D.Double p5 = new Path2D.Double();
		p5.moveTo(x+157, y+217);
		p5.lineTo(x+153, y+255);
		p5.lineTo(x+133, y+268);
		p5.lineTo(x+109, y+286);
		p5.lineTo(x+160, y+269);
		p5.lineTo(x+178, y+250);
		p5.lineTo(x+179, y+250);
		p5.lineTo(x+179, y+203);
		p5.lineTo(x+157, y+218);
		g2d.setColor(new Color(150, 150, 150));
		g2d.draw(p5);
		g2d.setColor(new Color(150, 150, 150));
		g2d.fill(p5);
		Path2D.Double p6 = new Path2D.Double();
		p6.moveTo(x+27, y+216);
		p6.lineTo(x+27, y+219);
		p6.lineTo(x+106, y+254);
		p6.lineTo(x+180, y+213);
		p6.lineTo(x+179, y+182);
		p6.lineTo(x+107, y+229);
		p6.lineTo(x+23, y+185);
		p6.lineTo(x+26, y+217);
		g2d.setColor(new Color(200, 50, 0));
		g2d.draw(p6);
		g2d.setColor(new Color(200, 50, 0));
		g2d.fill(p6);
		Path2D.Double p8 = new Path2D.Double();
		p8.moveTo(x+168, y+221);
		p8.lineTo(x+167, y+191);
		p8.lineTo(x+178, y+184);
		p8.lineTo(x+185, y+189);
		p8.lineTo(x+185, y+213);
		p8.lineTo(x+178, y+228);
		g2d.setColor(new Color(150, 150, 150));
		g2d.setColor(new Color(150, 150, 150));
		g2d.fill(p8);
		Path2D.Double p23 = new Path2D.Double();
		p23.moveTo(x+28, y+220);
		p23.lineTo(x+27, y+220);
		p23.lineTo(x+25, y+187);
		p23.lineTo(x+39, y+192);
		p23.lineTo(x+43, y+233);
		g2d.setColor(new Color(200, 200, 200));
		g2d.draw(p23);
		g2d.setColor(new Color(200, 200, 200));
		g2d.fill(p23);
		Path2D.Double p24 = new Path2D.Double();
		p24.moveTo(x+43, y+225);
		p24.lineTo(x+43, y+227);
		p24.lineTo(x+50, y+221);
		p24.lineTo(x+54, y+231);
		p24.lineTo(x+63, y+227);
		p24.lineTo(x+65, y+235);
		p24.lineTo(x+76, y+232);
		p24.lineTo(x+78, y+242);
		p24.lineTo(x+89, y+236);
		p24.lineTo(x+90, y+247);
		p24.lineTo(x+99, y+241);
		p24.lineTo(x+103, y+253);
		p24.lineTo(x+44, y+227);
		p24.lineTo(x+40, y+194);
		p24.lineTo(x+107, y+234);
		p24.lineTo(x+95, y+238);
		p24.lineTo(x+95, y+228);
		p24.lineTo(x+86, y+233);
		p24.lineTo(x+84, y+221);
		p24.lineTo(x+72, y+227);
		p24.lineTo(x+72, y+213);
		p24.lineTo(x+61, y+220);
		p24.lineTo(x+60, y+207);
		p24.lineTo(x+50, y+211);
		p24.lineTo(x+49, y+201);
		p24.lineTo(x+43, y+205);
		p24.lineTo(x+40, y+195);
		g2d.setColor(new Color(0, 0, 0));
		g2d.setColor(new Color(255, 255, 255));
		g2d.fill(p24);
		Path2D.Double p25 = new Path2D.Double();
		p25.moveTo(x+168, y+221);
		p25.lineTo(x+107, y+254);
		p25.lineTo(x+110, y+241);
		p25.lineTo(x+118, y+249);
		p25.lineTo(x+119, y+237);
		p25.lineTo(x+129, y+241);
		p25.lineTo(x+135, y+227);
		p25.lineTo(x+142, y+233);
		p25.lineTo(x+147, y+222);
		p25.lineTo(x+155, y+228);
		p25.lineTo(x+155, y+227);
		p25.lineTo(x+155, y+226);
		p25.lineTo(x+159, y+215);
		p25.lineTo(x+167, y+220);
		p25.lineTo(x+167, y+191);
		p25.lineTo(x+106, y+233);
		p25.lineTo(x+115, y+237);
		p25.lineTo(x+118, y+226);
		p25.lineTo(x+126, y+229);
		p25.lineTo(x+130, y+216);
		p25.lineTo(x+130, y+217);
		p25.lineTo(x+138, y+222);
		p25.lineTo(x+141, y+209);
		p25.lineTo(x+149, y+215);
		p25.lineTo(x+152, y+202);
		p25.lineTo(x+160, y+208);
		p25.lineTo(x+162, y+196);
		p25.lineTo(x+165, y+201);
		p25.lineTo(x+166, y+192);
		g2d.setColor(new Color(0, 0, 0));
		g2d.setColor(new Color(255, 255, 255));
		g2d.fill(p25);

	}
	public boolean canBeAwakened()
	{
		return true;
	}
	public void awakenToggle()
	{
		awakened = !awakened;
		leftEye.awakenToggle();
		rightEye.awakenToggle();
		
		ActionListener closeMouth = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				roaring = false;
			}
		};
		
		roaring = true;
		
		roarTime.stop();
		roarTime = new Timer(2000,closeMouth);
		roarTime.setRepeats(false);
		roarTime.start();
	}
	public boolean isAwakened()
	{
		return awakened;
	}
}