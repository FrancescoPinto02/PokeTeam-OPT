package ga.operators.crossover;

import ga.individuals.PokemonTeam;
import ga.population.Population;
import pokemon.core.Pokemon;

import java.util.List;
import java.util.Random;

public class PokemonTeamUniformCrossover extends CrossoverOperator<PokemonTeam>{

    @Override
    public Population<PokemonTeam> apply(Population<PokemonTeam> population, Random rand) throws CloneNotSupportedException {
        Population<PokemonTeam> offsprings = population.clone();
        offsprings.setId(population.getId()+1);
        offsprings.clear();

        List<Pairing> pairings = makeRandomPairings(population);
        for(Pairing pairing : pairings){
            PokemonTeam offspring1 = crossover(pairing.firstParent, pairing.secondParent, rand);
            PokemonTeam offspring2 = crossover(pairing.secondParent, pairing.firstParent, rand);
            offsprings.add(offspring1);
            offsprings.add(offspring2);
        }

        return offsprings;
    }

    private PokemonTeam crossover(PokemonTeam parent1, PokemonTeam parent2, Random rand){
        Pokemon[] genes1 = parent1.getCoding();
        Pokemon[] genes2 = parent2.getCoding();

        int lenght = Math.min(genes1.length, genes2.length);
        Pokemon[] offspringGene = new Pokemon[lenght];

        //Seleziona l`i-esimo gene casualmente dai genitori
        for(int i=0; i<lenght; i++){
            if(rand.nextBoolean()){
                offspringGene[i] = genes1[i];
            }
            else{
                offspringGene[i] = genes2[i];
            }
        }

        return new PokemonTeam(offspringGene);
    }
}
