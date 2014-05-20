//This is a modified modular version of a simple GA implementation based on a code piece that I've
//Find on net. See: The https://github.com/calebwherry/String-Evolver

package levo.ga.string;

import levo.ga.string.StringOrganism;

import java.util.Random;

public class StringEvolver
{
	private String[] stringPallet = 
		{
			"a", "b", "c", "d", "e", "f", "g", "h", "i" ,"j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s" ,"t",
			"u", "v", "w", "x", "y", "z", " ", "A", "B", "C",
			"D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", ",", ".", "?", "!", ":", "("
		};
	
	private Random generator = 	new Random();
    
	private StringBuffer		goalString;
    
	private boolean				continueSimulation;
    
	private int					generationId = 0;
    
	private final int			numChildren = 1000;
	
	//Default Constructor
    StringEvolver(String in)
    {
    	goalString = new StringBuffer(in);
    	continueSimulation = true;
        
        System.out.println(goalString);
    }
    
    //This should be public, so that outer class can call the method
    public void beginEvolution()
    {
    	int genomeLength = goalString.length();
        StringOrganism initialOrganism = primordialWithLength(genomeLength);
        initialOrganism.fitnessScore = stringMetric(initialOrganism.genome, goalString);
        initialOrganism.hasParent = false;
        evolutionLoop(initialOrganism);
    }
    
    private void evolutionLoop(StringOrganism parentOrganism)
    {
    	while (continueSimulation)
    	{
    		displayOrganism(parentOrganism);
    		//StringBuffer[] population = make_children(parent_organism);
    		parentOrganism = rankPopulation(parentOrganism, makeChildren(parentOrganism));
    	}
    	
    	simulationEnd(parentOrganism);
    }
    
    private void displayOrganism(StringOrganism organism)
    {
    	System.out.printf
    	(
    		"%5d) [%d] %s\n",
    		++generationId,
    		organism.fitnessScore,
    		organism.genome
    	);
    }
    
    private StringOrganism rankPopulation(StringOrganism parent, StringOrganism[] population)
    {
    	int popSize = population.length;
    	StringOrganism bestChild = parent;
    	int currentHighFitness = stringMetric(parent.genome,goalString);
    	
    	for (int i = 0; i < popSize; i++)
    	{
    		int currentFitness = stringMetric((population[i]).genome,goalString);

    		if (currentFitness < currentHighFitness)
    		{
    			bestChild = population[i];
    			bestChild.fitnessScore = currentFitness;
    			currentHighFitness = currentFitness;
    		}
    	}
    	
    	if (currentHighFitness == 0)
    	{
    		continueSimulation = false;
    	}
    	
    	return bestChild;
    }
    
	private StringOrganism[] makeChildren(StringOrganism initialOrganism)
	{
		StringOrganism[] population = new StringOrganism[numChildren];
		int strlen = initialOrganism.genome.length();

		for (int child_id = 0; child_id < numChildren; child_id++)
		{
			StringOrganism newChild = new StringOrganism(initialOrganism);
			int mutation_index = generator.nextInt(strlen);
			
			newChild.genome.setCharAt(mutation_index, ((randomChar()).toCharArray())[0]);
			population[child_id] = newChild;
		}

		return population;
	}
	
	private void simulationEnd(StringOrganism finalOrganism)
	{
		displayOrganism(finalOrganism);
	}
	
	private StringOrganism primordialWithLength(int genome_length)
	{
		StringOrganism newOrganism = new StringOrganism();
		newOrganism.genome = new StringBuffer(genome_length);

		for (int i = 0; i < genome_length; i++)
		{
			newOrganism.genome.append(randomChar());
		}
		
		return newOrganism;
	}
	
	private int stringMetric(StringBuffer x, StringBuffer y)
    {
    	// This assumes x and y are of the same length;
    	// Will burn in flames if len(y) < len(x)
    	int strlen = x.length();
    	int distance = 0;

    	for (int i = 0; i < strlen; i++)
    	{
    		distance += characterDifference(x.charAt(i), y.charAt(i));
    	}

    	return distance;
    }

	private int characterDifference(char x, char y)
    {
    	return Math.abs((int)(x) - (int)(y));
    }

	private String randomChar()
    {
    	return stringPallet[generator.nextInt(stringPallet.length)];
    }
}
