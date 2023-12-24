import ga.fitness.PokemonTeamFitnessFunction;
import ga.individuals.Individual;
import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.population.FixedSizePopulation;
import pokemon.pokedex.Pokedex;

public class Test {

    public static void main(String[] args){
        int numbersOfIndividuals = 50;
        PokemonTeamInitializer initializer = new PokemonTeamInitializer(numbersOfIndividuals);
        FixedSizePopulation<PokemonTeam> population = new FixedSizePopulation<>(0, numbersOfIndividuals);
        PokemonTeamFitnessFunction fitnessFunction = new PokemonTeamFitnessFunction();

        population = (FixedSizePopulation<PokemonTeam>) initializer.initialize();
        fitnessFunction.evaluate(population);
        System.out.println(population);
        System.out.println(population.getBestIndividual());
        System.out.println(population.getAverageFitness());

    }
}
