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

    private final double mutationProbability; //Probabilità di applicare l`operatore di mutazione
    private final int maxIterations; //Numero massimo di generazioni
    private final int maxIterationsNoImprovements; //Numero massimo di generazioni senza miglioramenti



    public SimpleGeneticAlgorithm(FitnessFunction<T> fitnessFunction, Initializer<T> initializer,
                                  SelectionOperator<T> selectionOperator, CrossoverOperator<T> crossoverOperator,
                                  MutationOperator<T> mutationOperator, double mutationProbability,
                                  int maxIterations, int maxIterationsNoImprovements) {
        super(fitnessFunction, initializer, selectionOperator, crossoverOperator, mutationOperator);

        //Se il valore fornito per la probabilità di mutazione non è valido
        //la probabilità viene impostata automaticamente al 100%
        if (0.0 <= mutationProbability && mutationProbability <= 1.0) {
            this.mutationProbability = mutationProbability;
        } else {
            this.mutationProbability = 1;
        }

        this.maxIterations = Math.max(maxIterations, 1);
        this.maxIterationsNoImprovements = Math.max(maxIterationsNoImprovements, 0);
    }

    //Se non viene fornito il parametro mutationProbability, viene impostato ad 1
    public SimpleGeneticAlgorithm(FitnessFunction<T> fitnessFunction, Initializer<T> initializer,
                                  SelectionOperator<T> selectionOperator, CrossoverOperator<T> crossoverOperator,
                                  MutationOperator<T> mutationOperator, int maxIterations, int maxIterationsNoImprovements) {
        this(fitnessFunction, initializer, selectionOperator, crossoverOperator, mutationOperator, 1, maxIterations, maxIterationsNoImprovements);
    }


    @Override
    public Results<T> run() throws CloneNotSupportedException {
        Random rand = new Random();
        List<String> log = new ArrayList<>();
        Stack<Population<T>> generations = new Stack<>();

        // Inizializzazione della prima generazione
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

            // Selezione
            Population<T> matingPool = getSelectionOperator().apply(currentGeneration, rand);
            // Crossover
            Population<T> offsprings = getCrossoverOperator().apply(matingPool, rand);
            // Mutazione
            Population<T> newGeneration = offsprings;
            if (rand.nextDouble() <= mutationProbability) {
                newGeneration = getMutationOperator().apply(offsprings, rand);
            }
            getFitnessFunction().evaluate(newGeneration);
            generations.push(newGeneration);
            iterations++;
            logEntry.append("Gen ").append(iterations).append(") ");

            // Controlla se ci sono miglioramenti
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
                // Controlla se il limite di generazioni senza miglioramenti è stato superato
                maxNoImprovementsExceeded = 0 < maxIterationsNoImprovements && maxIterationsNoImprovements < iterationsNoImprovements;
                if (maxNoImprovementsExceeded) {
                    logEntry.append(" ==> Early Stop");
                }
            }
            log.add(logEntry.toString());
        } while (iterations < maxIterations && !maxNoImprovementsExceeded);
        return new Results<>(this, generations, bestGeneration, log);
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public int getMaxIterationsNoImprovements() {
        return maxIterationsNoImprovements;
    }
}
