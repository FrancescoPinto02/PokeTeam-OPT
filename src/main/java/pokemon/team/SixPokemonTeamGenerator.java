package pokemon.team;

import pokemon.Pokemon;

import java.util.List;
import java.util.Random;

public class SixPokemonTeamGenerator extends PokemonTeamGenerator<SixPokemonTeam>{
    @Override
    public SixPokemonTeam generatePokemonTeam() {
        SixPokemonTeam team = new SixPokemonTeam();
        Random random = new Random();
        while(!team.isFull()){
            List<Pokemon> pokemonList = getPokedex().getPokemon(random.nextInt(getPokedex().getMaxNumber())+1);
            Pokemon pokemon = pokemonList.get(random.nextInt(pokemonList.size()));
            team.addMember(pokemon);
        }
        return team;
    }
}
