import ga.fitness.FitnessFunction;
import ga.fitness.PokemonTeamFitnessFunction;
import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.population.FixedSizePopulation;
import ga.population.Population;
import pokemon.core.Pokemon;
import pokemon.pokedex.Pokedex;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Pokedex pokedex = new Pokedex();
        PokemonTeamFitnessFunction fitnessFunction = new PokemonTeamFitnessFunction();
        PokemonTeam team = new PokemonTeam();
        team.changeMember(0, pokedex.getPokemon(6,0));
        team.changeMember(1, pokedex.getPokemon(466,0));
        team.changeMember(2, pokedex.getPokemon(445,0));
        team.changeMember(3, pokedex.getPokemon(248,0));
        team.changeMember(4, pokedex.getPokemon(131,0));
        team.changeMember(5, pokedex.getPokemon(214,0));
        fitnessFunction.evaluate(team);
        System.out.println(team);
    }
}
