package ga.individuals;

import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class PokemonTeam extends PokemonArrayIndividual {
    public static final int MAX_TEAM_MEMBERS = 6;

    public PokemonTeam(){
        super(new Pokemon[MAX_TEAM_MEMBERS]);
    }

    public PokemonTeam(Pokemon[] coding){
        this();
        int i=0;

        for(Pokemon p : coding){
            if(i >= MAX_TEAM_MEMBERS){
                break;
            }
            else{
                if(p != null){
                    this.coding[i]=p;
                }
                else{
                    this.coding[i]= PokemonGenerator.generatePokemon();
                }
                i++;
            }
        }

        while(i < MAX_TEAM_MEMBERS){
            this.coding[i]=PokemonGenerator.generatePokemon();
            i++;
        }
    }

    public PokemonTeam(List<Pokemon> pokemonList){
        this(pokemonList.toArray(new Pokemon[0]));
    }

    public Pokemon getMember(int index) {
        if(index < 0  || index >= MAX_TEAM_MEMBERS){
            return null;
        }
        else{
            return coding[index];
        }
    }

    public void changeMember(int index, Pokemon newMember) {
        if(index < 0  || index >= MAX_TEAM_MEMBERS){
            return;
        }
        else{
            coding[index] = newMember;
        }
    }

    @Override
    public String toString() {
        return "PokemonTeam=" + Arrays.toString(coding) + " Fitness=" + fitness;
    }
}
