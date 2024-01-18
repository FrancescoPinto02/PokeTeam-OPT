package pokemon.core;

import pokemon.pokedex.Pokedex;

import java.util.List;
import java.util.Random;

/**
 * Classe utilizzata per generare un Pokemon selezionandolo casualmente dal Pokedex
 */
public final class PokemonGenerator {
    private static final Pokedex pokedex = new Pokedex();

    private PokemonGenerator(){}

    /**
     * Genera un pokemon selezionandolo casualmente dal pokedex
     *
     * @return Pokemon casuale
     */
    public static Pokemon generatePokemon(){
        Random random = new Random();
        List<Pokemon> pokemonList = pokedex.getPokemon(random.nextInt(pokedex.getMaxNumber())+1);
        return pokemonList.get(random.nextInt(pokemonList.size()));
    }
}
