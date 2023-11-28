package ga.individuals;

import pokemon.core.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO: implementare la codifica degli individui come una matrice 6x2 con numeri e nomi di ogni pokemon

public class PokemonTeam extends IntListIndividual {
    private static final int MAX_TEAM_MEMBERS = 6;

    private List<Pokemon> members;

    public PokemonTeam(){
        super(new int[MAX_TEAM_MEMBERS]);
        this.members = new ArrayList<>();
    }

    public PokemonTeam(List<Pokemon> members){
        this();
        for(int i=0; i < members.size() && !isFull(); i++){
            Pokemon pokemon = members.get(i);
            addMember(pokemon);
        }
    }

    public List<Pokemon> getMembers() {
        return members;
    }

    public void setMembers(List<Pokemon> members) {
        this.members = members;
    }

    public boolean addMember(Pokemon pokemon){
        if(isFull()){
            return false;

        }
        else{
            members.add(pokemon);
            coding[members.size()-1] = pokemon.getNumber();
            return true;
        }
    }

    public boolean changeMember(Pokemon pokemonToChange, Pokemon newPokemon){
        if(members.remove(pokemonToChange)){
            members.add(newPokemon);
            for(int i=0; i<MAX_TEAM_MEMBERS; i++){
                if(coding[i] == pokemonToChange.getNumber()){
                    coding[i] = newPokemon.getNumber();
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isFull(){
        return this.getMembers().size() >= MAX_TEAM_MEMBERS;
    };

    public boolean isEmpty(){
        return members.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonTeam that = (PokemonTeam) o;
        return Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(members);
    }

    @Override
    public String toString() {
        return "PokemonTeam{" +
                "members=" + members +
                '}';
    }

    @Override
    public Individual clone() throws CloneNotSupportedException {
        PokemonTeam clone = (PokemonTeam) super.clone();

        //Note: it doesn`t clone the pokemon
        if (this.members != null) {
            clone.members = new ArrayList<>(this.members);
        }

        return clone;
    }
}
