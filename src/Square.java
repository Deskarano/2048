import java.util.Random;

public class Square
{
	private int exponent;
	private static Random randGen = new Random();
	
	public Square()
	{
		randGen.setSeed(System.nanoTime());
		
		if(randGen.nextInt(10) == 0)
		{
			exponent = 2;
		}
		else
		{
			exponent = 1;
		}
	}
	
	public int getExponent()
	{
		return exponent;
	}
	
	public void merge()
	{
		exponent++;
	}
}
