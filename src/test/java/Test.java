import ga.fitness.PokemonTeamFitnessFunction;
import ga.individuals.Individual;
import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.operators.crossover.PokemonTeamSinglePointCrossover;
import ga.operators.mutation.PokemonTeamSinglePointMutation;
import ga.operators.selection.RouletteWheelSelection;
import ga.population.FixedSizePopulation;
import ga.population.Population;
import pokemon.pokedex.Pokedex;

import java.util.Random;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        int numbersOfIndividuals = 50;
        PokemonTeamInitializer initializer = new PokemonTeamInitializer(numbersOfIndividuals);
        FixedSizePopulation<PokemonTeam> population = new FixedSizePopulation<>(0, numbersOfIndividuals);
        PokemonTeamFitnessFunction fitnessFunction = new PokemonTeamFitnessFunction();
        RouletteWheelSelection<PokemonTeam> selection = new RouletteWheelSelection<>();
        PokemonTeamSinglePointCrossover crossover = new PokemonTeamSinglePointCrossover();
        PokemonTeamSinglePointMutation mutation = new PokemonTeamSinglePointMutation();

        population = (FixedSizePopulation<PokemonTeam>) initializer.initialize();
        fitnessFunction.evaluate(population);
        System.out.println(population);
        System.out.println(population.getBestIndividual());
        System.out.println(population.getAverageFitness());
        System.out.println(population.size());

        Population<PokemonTeam> matingPool = selection.apply(population, new Random());
        System.out.println(matingPool);
        System.out.println(matingPool.getBestIndividual());
        System.out.println(matingPool.getAverageFitness());
        System.out.println(matingPool.size());

        Population<PokemonTeam> offsprings = crossover.apply(matingPool, new Random());
        fitnessFunction.evaluate(offsprings);
        System.out.println(offsprings);
        System.out.println(offsprings.getBestIndividual());
        System.out.println(offsprings.getAverageFitness());
        System.out.println(offsprings.size());

        Population<PokemonTeam> mutatedPopulation = mutation.apply(matingPool, new Random());
        fitnessFunction.evaluate(mutatedPopulation);
        System.out.println(mutatedPopulation);
        System.out.println(mutatedPopulation.getBestIndividual());
        System.out.println(mutatedPopulation.getAverageFitness());
        System.out.println(mutatedPopulation.size());
    }
}
