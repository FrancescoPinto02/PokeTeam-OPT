package pokemon;

import java.util.List;
import java.util.Random;

/**
 * A class used to generate random pokemon through the static method generatePokemon(), that takes a random
 * Pokemon from the Pokedex and return it.
 */
public class PokemonGenerator {
    private static final Pokedex pokedex = new Pokedex();

    public static Pokemon generatePokemon(){
        Random random = new Random();
        List<Pokemon> pokemonList = pokedex.getPokemon(random.nextInt(pokedex.getMaxNumber())+1);
        return pokemonList.get(random.nextInt(pokemonList.size()));
    }
}
