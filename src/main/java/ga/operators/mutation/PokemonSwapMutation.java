package ga.operators.mutation;

import ga.individuals.PokemonTeam;
import ga.population.Population;
import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonSwapMutation extends MutationOperator<PokemonTeam>{

    private static final double DEFAULT_MUTATION_PROBABILITY = 0.2;

    private final double mutationProbability;


    public PokemonSwapMutation(double mutationProbability) {
        super();
        if(0 <= mutationProbability && mutationProbability <= 1){
            this.mutationProbability = mutationProbability;
        }
        else{
            this.mutationProbability = DEFAULT_MUTATION_PROBABILITY;
        }
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    @Override
    public Population<PokemonTeam> apply(Population<PokemonTeam> population, Random rand) throws CloneNotSupportedException {
        Population<PokemonTeam> newPopulation = population.clone();
        newPopulation.setId(population.getId()+1);
        newPopulation.clear();

        for(PokemonTeam individual : population){
            if(rand.nextDouble() <= mutationProbability){
                PokemonTeam mutatedIndividual = mutate(individual, rand);
                newPopulation.add(mutatedIndividual);
            }
            else{
                newPopulation.add(individual);
            }
        }

        return newPopulation;
    }

    private PokemonTeam mutate(PokemonTeam individual, Random rand){
        Pokemon[] coding = individual.getCoding();
        int position = rand.nextInt(coding.length);
        coding[position] = PokemonGenerator.generatePokemon();
        PokemonTeam mutatedIndividual = new PokemonTeam(coding);
        return mutatedIndividual;
    }


}
