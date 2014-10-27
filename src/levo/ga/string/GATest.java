//This is a modified modular version of a simple GA implementation based on a code piece that I've
//Find on net. See: github.com/calebwherry/String-Evolver

package levo.ga.string;

public class GATest
{

	public static void main(String[] args)
	{
		//This is the goal string which that StringEvolver string output will mutate into
		String testString = "Merhaba";
		
		StringEvolver evolutionEngine = new StringEvolver(testString);
        evolutionEngine.beginEvolution();
	}

}

//endTest
