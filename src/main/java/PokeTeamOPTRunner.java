import ga.fitness.PokemonTeamFitnessFunction;
import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.metaheuristics.SimpleGeneticAlgorithm;
import ga.operators.crossover.PokemonTeamSinglePointCrossover;
import ga.operators.mutation.PokemonSwapMutation;
import ga.operators.selection.KTournamentSelection;
import ga.results.Results;

public class PokeTeamOPTRunner {

    public static void main(String[] args) throws CloneNotSupportedException {
        final int numberOfIndividuals = 50;
        final double mutationProbability = 0.2;
        final int maxIterations = 100;
        final int maxIterationNoImprovements = 25000;
        final int k = 5;

        PokemonTeamFitnessFunction fitnessFunction = new PokemonTeamFitnessFunction();
        PokemonTeamInitializer initializer = new PokemonTeamInitializer(numberOfIndividuals);
        KTournamentSelection<PokemonTeam> selectionOperator = new KTournamentSelection<>(k, numberOfIndividuals, true);
        PokemonTeamSinglePointCrossover crossoverOperator = new PokemonTeamSinglePointCrossover();
        PokemonSwapMutation mutationOperator = new PokemonSwapMutation(mutationProbability);


        //RouletteWheelSelection<PokemonTeam> selectionOperator = new RouletteWheelSelection<>();
        //PokemonTeamSinglePointMutation mutationOperator = new PokemonTeamSinglePointMutation();

        SimpleGeneticAlgorithm<PokemonTeam> geneticAlgorithm = new SimpleGeneticAlgorithm<>(fitnessFunction, initializer, selectionOperator, crossoverOperator,
                mutationOperator, maxIterations, maxIterationNoImprovements);

        Results<PokemonTeam> results = geneticAlgorithm.run();
        PokemonTeam bestPokemonTeam = results.getBestIndividual();
        results.getLog().forEach(System.out::println);
        System.out.printf("Ricerca terminata in %d/%d iterazioni.%n", results.getNumberOfIterations(), geneticAlgorithm.getMaxIterations());
        System.out.println("Migliore individuo: " + bestPokemonTeam);
    }
}
