import pokemon.Pokedex;
import pokemon.Type;
import pokemon.TypePool;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args){
        Pokedex pokedex = new Pokedex();
        System.out.println(pokedex.getPokemon(6));
        System.out.println(pokedex.getPokemon(55));
        System.out.println(pokedex.getPokemon(341));
        System.out.println(pokedex.getPokemon(734));
    }
}
