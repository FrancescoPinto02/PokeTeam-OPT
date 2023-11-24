import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.Type;
import pokemon.TypePool;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args){
        Pokedex pokedex = new Pokedex();
        Pokemon pokemon = pokedex.getPokemon(345, 0);
        System.out.println(pokemon);
        System.out.println("Resistenze="+pokemon.getResistances());
        System.out.println("Debolezze="+pokemon.getWeaknesses());

    }
}
