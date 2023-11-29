package ga.initializer;

import ga.individuals.Individual;
import ga.population.Population;

public abstract class Initializer<T extends Individual> {

    public abstract Population<T> initialize();

}