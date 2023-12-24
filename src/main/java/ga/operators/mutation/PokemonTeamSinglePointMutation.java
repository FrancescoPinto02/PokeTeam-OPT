package ga.operators.mutation;

import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;
import pokemon.team.PokemonTeamGenerator;

import java.util.Random;

public class PokemonTeamSinglePointMutation extends  RandomResettingMutation<PokemonTeam>{
    @Override
    protected PokemonTeam mutate(PokemonTeam individual, Random rand) throws CloneNotSupportedException {
        Pokemon[] coding = individual.getCoding();
        int position = rand.nextInt(coding.length);
        coding[position] = PokemonGenerator.generatePokemon();
        PokemonTeam mutatedIndividual = new PokemonTeam(coding);
        return mutatedIndividual;
    }
}
