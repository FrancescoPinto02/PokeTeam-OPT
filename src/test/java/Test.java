import pokemon.core.Pokemon;
import pokemon.pokedex.Pokedex;

import java.util.List;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Pokedex pokedex = new Pokedex();
        for(int i=0; i< pokedex.getMaxNumber(); i++){
            List<Pokemon> pokemonList = pokedex.getPokemon(i);
            for(Pokemon x : pokemonList){
                if(x.isMegaEvolution()){
                    System.out.println(x);
                }
            }
        }
    }
}
