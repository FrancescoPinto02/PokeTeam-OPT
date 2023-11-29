package ga.operators;

import ga.individuals.Individual;
import ga.population.Population;

import java.util.Random;

public abstract class GeneticOperator<T extends Individual> {

    public abstract Population<T> apply(Population<T> population, Random rand) throws CloneNotSupportedException;

}
