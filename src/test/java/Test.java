import ga.fitness.PokemonTeamFitnessFunction;
import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.population.FixedSizePopulation;
import pokemon.core.Pokemon;
import pokemon.pokedex.Pokedex;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Pokedex pokedex = new Pokedex();
        PokemonTeamInitializer initializer = new PokemonTeamInitializer(3);
        FixedSizePopulation<PokemonTeam> population = (FixedSizePopulation<PokemonTeam>) initializer.initialize();
        PokemonTeamFitnessFunction fitnessFunction = new PokemonTeamFitnessFunction();
        System.out.println(population);
        System.out.println("Evaluating");
        fitnessFunction.evaluate(population);
        System.out.println(population);

        List<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pokedex.getPokemon(6,0));
        pokemonList.add(pokedex.getPokemon(34,0));
        pokemonList.add(pokedex.getPokemon(65,0));
        pokemonList.add(pokedex.getPokemon(149,0));
        pokemonList.add(pokedex.getPokemon(94,0));
        pokemonList.add(pokedex.getPokemon(131,0));
        PokemonTeam team = new PokemonTeam(pokemonList);
        fitnessFunction.evaluate(team);
        System.out.println(team);
    }
}
