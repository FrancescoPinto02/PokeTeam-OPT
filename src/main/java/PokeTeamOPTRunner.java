import ga.fitness.PokemonTeamFitnessFunction;
import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.metaheuristics.SimpleGeneticAlgorithm;
import ga.operators.crossover.PokemonTeamSinglePointCrossover;
import ga.operators.mutation.PokemonTeamSinglePointMutation;
import ga.operators.selection.KTournamentSelection;
import ga.results.Results;

public class PokeTeamOPTRunner {

    public static void main(String[] args) throws CloneNotSupportedException {
        final int numberOfIndividuals = 50;
        final double mutationProbability = 1;
        final int maxIterations = 200;
        final int maxIterationNoImprovements = 200;
        final int k = 5;

        PokemonTeamFitnessFunction fitnessFunction = new PokemonTeamFitnessFunction();
        PokemonTeamInitializer initializer = new PokemonTeamInitializer(numberOfIndividuals);
        KTournamentSelection<PokemonTeam> selectionOperator = new KTournamentSelection<>(k, numberOfIndividuals, false);
        PokemonTeamSinglePointCrossover crossoverOperator = new PokemonTeamSinglePointCrossover();
        PokemonTeamSinglePointMutation mutationOperator = new PokemonTeamSinglePointMutation();

        SimpleGeneticAlgorithm<PokemonTeam> geneticAlgorithm = new SimpleGeneticAlgorithm<>(fitnessFunction, initializer, selectionOperator, crossoverOperator,
                mutationOperator, mutationProbability, maxIterations, maxIterationNoImprovements);

        Results<PokemonTeam> results = geneticAlgorithm.run();
        PokemonTeam bestPokemonTeam = results.getBestIndividual();
        results.getLog().forEach(System.out::println);
        System.out.printf("Ricerca terminata in %d/%d iterazioni.%n", results.getNumberOfIterations(), geneticAlgorithm.getMaxIterations());
        System.out.println("Migliore individuo: " + bestPokemonTeam);
    }
}
