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
public class Greeting
{
	public static void main(String[] args)
	{
		DrawingComponent dc = new DrawingComponent(1024,768);
		CardFrame c = new CardFrame(1024,768,dc);
	}
}