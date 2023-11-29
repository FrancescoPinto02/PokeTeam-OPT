package pokemon.team;

import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;

import java.util.ArrayList;
import java.util.List;

public class PokemonTeamGenerator {


    public static PokemonTeam generatePokemonTeam(int size){
        List<Pokemon> team = new ArrayList<>();
        for(int i=0; i<size; i++){
            team.add(PokemonGenerator.generatePokemon());
        }
        return new PokemonTeam(team);
    }


}
