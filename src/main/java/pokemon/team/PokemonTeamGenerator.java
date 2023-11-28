package pokemon.team;

import ga.individuals.PokemonTeam;
import pokemon.core.PokemonGenerator;

public class PokemonTeamGenerator {


    public static PokemonTeam generatePokemonTeam(){
        PokemonTeam team = new PokemonTeam();
        while(!team.isFull()){
            team.addMember(PokemonGenerator.generatePokemon());
        }
        return team;
    }


}
