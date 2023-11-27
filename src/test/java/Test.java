import pokemon.Pokedex;
import pokemon.Pokemon;

public class Test {

    public static void main(String[] args){
        Pokedex pokedex = new Pokedex();
        Pokemon pokemon = pokedex.getPokemon(543, 0);
        System.out.println(pokedex);

    }
}
