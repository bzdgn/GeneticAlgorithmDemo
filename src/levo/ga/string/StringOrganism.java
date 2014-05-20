package levo.ga.string;

public class StringOrganism
{
	public StringOrganism parent;
	public StringBuffer genome;
	public int fitnessScore;
	public boolean hasParent = true;
	
	StringOrganism()
	{
		genome = new StringBuffer();
    }
	
	StringOrganism(StringOrganism clone)
	{
		genome = new StringBuffer(clone.genome);
    }
}
