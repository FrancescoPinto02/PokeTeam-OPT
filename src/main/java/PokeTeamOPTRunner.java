import ga.fitness.PokemonTeamFitnessFunction;
import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.metaheuristics.SimpleGeneticAlgorithm;
import ga.operators.crossover.PokemonTeamSinglePointCrossover;
import ga.operators.crossover.PokemonTeamTwoPointCrossover;
import ga.operators.crossover.PokemonTeamUniformCrossover;
import ga.operators.mutation.PokemonSwapMutation;
import ga.operators.selection.KTournamentSelection;
import ga.operators.selection.RouletteWheelSelection;
import ga.results.Results;

public class PokeTeamOPTRunner {

    public static void main(String[] args) throws CloneNotSupportedException {
        final int numberOfIndividuals = 100;
        final double mutationProbability = 0.3;
        final int maxIterations = 35;
        final int maxIterationNoImprovements = 600;
        final int k = 5; //tournament selection size

        PokemonTeamFitnessFunction fitnessFunction = new PokemonTeamFitnessFunction();
        PokemonTeamInitializer initializer = new PokemonTeamInitializer(numberOfIndividuals);

        KTournamentSelection<PokemonTeam> selectionOperator = new KTournamentSelection<>(k, numberOfIndividuals, false);
        //RouletteWheelSelection<PokemonTeam> selectionOperator = new RouletteWheelSelection<>();


        PokemonSwapMutation mutationOperator = new PokemonSwapMutation(mutationProbability);

        PokemonTeamUniformCrossover crossoverOperator = new PokemonTeamUniformCrossover();
        //PokemonTeamSinglePointCrossover crossoverOperator = new PokemonTeamSinglePointCrossover();
        //PokemonTeamTwoPointCrossover crossoverOperator = new PokemonTeamTwoPointCrossover();

        SimpleGeneticAlgorithm<PokemonTeam> geneticAlgorithm = new SimpleGeneticAlgorithm<>(fitnessFunction, initializer, selectionOperator, crossoverOperator,
                mutationOperator, maxIterations, maxIterationNoImprovements);

        Results<PokemonTeam> results = geneticAlgorithm.run();
        PokemonTeam bestPokemonTeam = results.getBestIndividual();
        results.getLog().forEach(System.out::println);
        System.out.printf("Ricerca terminata in %d/%d iterazioni.%n", results.getNumberOfIterations(), geneticAlgorithm.getMaxIterations());
        System.out.println("Migliore individuo: " + bestPokemonTeam);
    }
}
