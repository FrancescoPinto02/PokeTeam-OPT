package pokemon.team;

import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce la generazione automatica di squadre Pokemon.
 */
public final class PokemonTeamGenerator {

    private PokemonTeamGenerator(){}

    /**
     * Genera una squadra di Pokémon con la dimensione specificata.
     *
     * @param size Dimensione della squadra che si desidera generare.
     * @return Team Pokemon generato
     */
    public static PokemonTeam generatePokemonTeam(int size){
        List<Pokemon> team = new ArrayList<>();
        for(int i=0; i<size; i++){
            team.add(PokemonGenerator.generatePokemon());
        }
        return new PokemonTeam(team);
    }


}
