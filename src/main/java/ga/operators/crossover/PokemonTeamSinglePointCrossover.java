package ga.operators.crossover;

import ga.individuals.PokemonTeam;
import ga.population.Population;
import pokemon.core.Pokemon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class PokemonTeamSinglePointCrossover extends CrossoverOperator<PokemonTeam>{

    @Override
    public Population<PokemonTeam> apply(Population<PokemonTeam> population, Random rand) throws CloneNotSupportedException {


        Population<PokemonTeam> offsprings = population.clone();
        offsprings.setId(population.getId()+1);
        offsprings.clear();

        //Effettua il crossover
        List<Pairing> pairings = makeRandomPairings(population);
        for (Pairing pairing : pairings){
            Pokemon[] firstCoding = pairing.firstParent.getCoding();
            Pokemon[] secondCoding = pairing.secondParent.getCoding();

            int minLength = Math.min(firstCoding.length, secondCoding.length);
            int cutPoint = rand.nextInt(minLength-1) + 1;

            Pokemon[] firstCodingLeft = Arrays.copyOfRange(firstCoding,0, cutPoint);
            Pokemon[] firstCodingRight = Arrays.copyOfRange(firstCoding, cutPoint, minLength);
            Pokemon[] secondCodingLeft = Arrays.copyOfRange(secondCoding,0, cutPoint);
            Pokemon[] secondCodingRight = Arrays.copyOfRange(secondCoding, cutPoint, minLength);

            Pokemon[] offspring1 = new Pokemon[firstCodingLeft.length + secondCodingRight.length];
            System.arraycopy(firstCodingLeft,0, offspring1, 0, firstCodingLeft.length);
            System.arraycopy(secondCodingRight, 0, offspring1, firstCodingLeft.length, secondCodingRight.length);

            Pokemon[] offspring2 = new Pokemon[secondCodingLeft.length + firstCodingRight.length];
            System.arraycopy(secondCodingLeft,0, offspring2, 0, secondCodingLeft.length);
            System.arraycopy(firstCodingRight, 0, offspring2, secondCodingLeft.length, firstCodingRight.length);

            offsprings.add(new PokemonTeam(offspring1));
            offsprings.add(new PokemonTeam(offspring2));
        }
        return offsprings;
    }
}
