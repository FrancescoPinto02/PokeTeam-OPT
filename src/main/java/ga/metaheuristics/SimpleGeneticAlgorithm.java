package ga.metaheuristics;

import ga.fitness.FitnessFunction;
import ga.individuals.Individual;
import ga.initializer.Initializer;
import ga.operators.crossover.CrossoverOperator;
import ga.operators.mutation.MutationOperator;
import ga.operators.selection.SelectionOperator;
import ga.population.Population;
import ga.results.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class SimpleGeneticAlgorithm <T extends Individual> extends GeneticAlgorithm<T>{

    private final double mutationProbability;
    private final int maxIterations;
    private final int maxIterationsNoImprovements;

    public SimpleGeneticAlgorithm(FitnessFunction<T> fitnessFunction, Initializer<T> initializer,
                                  SelectionOperator<T> selectionOperator, CrossoverOperator<T> crossoverOperator,
                                  MutationOperator<T> mutationOperator, double mutationProbability,
                                  int maxIterations, int maxIterationsNoImprovements) {
        super(fitnessFunction, initializer, selectionOperator, crossoverOperator, mutationOperator);
        if (0.0 <= mutationProbability && mutationProbability <= 1.0) {
            this.mutationProbability = mutationProbability;
        } else {
            this.mutationProbability = 0.1;
        }
        this.maxIterations = Math.max(maxIterations, 1);
        this.maxIterationsNoImprovements = Math.max(maxIterationsNoImprovements, 0);
    }

    @Override
    public Results<T> run() throws CloneNotSupportedException {
        Random rand = new Random();
        List<String> log = new ArrayList<>();
        Stack<Population<T>> generations = new Stack<>();

        // Initialization of the first generation
        generations.push(getInitializer().initialize());
        Population<T> firstGeneration = generations.peek();
        getFitnessFunction().evaluate(firstGeneration);
        log.add("Gen 1) " + firstGeneration.getAverageFitness() + " (CurrentAvg)");
        Population<T> bestGeneration = firstGeneration;

        int iterations = 1;
        int iterationsNoImprovements = 0;
        boolean maxNoImprovementsExceeded = false;
        do {
            StringBuilder logEntry = new StringBuilder();
            Population<T> currentGeneration = generations.peek();

            // Selection
            Population<T> matingPool = getSelectionOperator().apply(currentGeneration, rand);
            // Crossover
            Population<T> offsprings = getCrossoverOperator().apply(matingPool, rand);
            // Mutation
            Population<T> newGeneration = offsprings;
            if (rand.nextDouble() <= mutationProbability) {
                newGeneration = getMutationOperator().apply(offsprings, rand);
            }
            getFitnessFunction().evaluate(newGeneration);
            generations.push(newGeneration);
            iterations++;
            logEntry.append("Gen ").append(iterations).append(") ");

            // Check if there is an average improvement
            double bestAverageFitness = bestGeneration.getAverageFitness();
            double newAverageFitness = newGeneration.getAverageFitness();
            logEntry.append(newAverageFitness).append(" vs ").append(bestAverageFitness).append(" (NewAvg vs BestAvg)");

            if (getFitnessFunction().isMaximum() && newGeneration.compareTo(bestGeneration) > 0 ||
                    !getFitnessFunction().isMaximum() && newGeneration.compareTo(bestGeneration) < 0) {
                bestGeneration = newGeneration;
                iterationsNoImprovements = 0;
                logEntry.append(" ==> Improvement");
            } else {
                iterationsNoImprovements++;
                // Check if there is a limit of no improvements and this limit is exceeded
                maxNoImprovementsExceeded = 0 < maxIterationsNoImprovements && maxIterationsNoImprovements < iterationsNoImprovements;
                if (maxNoImprovementsExceeded) {
                    logEntry.append(" ==> Early Stop");
                }
            }
            log.add(logEntry.toString());
        } while (iterations < maxIterations && !maxNoImprovementsExceeded);
        return new Results<>(this, generations, bestGeneration, log);
    }
}
