package pokemon.team;

import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.PokemonGenerator;

import java.util.List;
import java.util.Random;

public class PokemonTeamGenerator {


    public static PokemonTeam generatePokemonTeam(){
        PokemonTeam team = new PokemonTeam();
        while(!team.isFull()){
            team.addMember(PokemonGenerator.generatePokemon());
        }
        return team;
    }


}
