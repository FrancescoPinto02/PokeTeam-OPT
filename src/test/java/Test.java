import pokemon.pokedex.Pokedex;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Pokedex pokedex = new Pokedex();
        System.out.println(pokedex.getPokemon(151,0).getRarity());
        System.out.println(pokedex.getPokemon(150,0).getRarity());
        System.out.println(pokedex.getPokemon(149,0).getRarity());
        System.out.println(pokedex.getPokemon(146,0).getRarity());
    }
}
