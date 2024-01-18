package ga.operators.crossover;

import ga.individuals.PokemonTeam;
import ga.population.Population;
import pokemon.core.Pokemon;

import java.util.List;
import java.util.Random;

public class PokemonTeamTwoPointCrossover extends CrossoverOperator<PokemonTeam> {

    @Override
    public Population<PokemonTeam> apply(Population<PokemonTeam> population, Random rand) throws CloneNotSupportedException {
        Population<PokemonTeam> offsprings = population.clone();
        offsprings.setId(population.getId() + 1);
        offsprings.clear();

        // Effettua il crossover
        List<Pairing> pairings = makeRandomPairings(population);
        for (Pairing pairing : pairings) {
            Pokemon[] firstCoding = pairing.firstParent.getCoding();
            Pokemon[] secondCoding = pairing.secondParent.getCoding();

            int minLength = Math.min(firstCoding.length, secondCoding.length);

            int cutPoint1 = rand.nextInt(minLength);
            int cutPoint2 = rand.nextInt(minLength);

            // Assicurati che i punti di taglio siano diversi
            while (cutPoint1 == cutPoint2) {
                cutPoint2 = rand.nextInt(minLength);
            }

            int start = Math.min(cutPoint1, cutPoint2);
            int end = Math.max(cutPoint1, cutPoint2);

            Pokemon[] offspring1 = new Pokemon[minLength];
            Pokemon[] offspring2 = new Pokemon[minLength];

            // Copia le parti esterne non tagliate
            System.arraycopy(firstCoding, 0, offspring1, 0, start);
            System.arraycopy(secondCoding, 0, offspring2, 0, start);

            System.arraycopy(firstCoding, end, offspring1, end, minLength - end);
            System.arraycopy(secondCoding, end, offspring2, end, minLength - end);

            // Copia la parte centrale tra i punti di taglio
            System.arraycopy(firstCoding, start, offspring2, start, end - start);
            System.arraycopy(secondCoding, start, offspring1, start, end - start);

            offsprings.add(new PokemonTeam(offspring1));
            offsprings.add(new PokemonTeam(offspring2));
        }

        return offsprings;
    }

}
