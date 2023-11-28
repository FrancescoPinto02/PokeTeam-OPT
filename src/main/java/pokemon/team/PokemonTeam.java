package pokemon.team;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokemonTeam {
    private static final int MAX_TEAM_MEMBERS = 6;

    private List<Pokemon> members;

    public PokemonTeam(){
        this.members = new ArrayList<>();
    }

    public PokemonTeam(List<Pokemon> members){
        this();
        for(int i=0; i < members.size() && !isFull(); i++){
            addMember(members.get(i));
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
            return this.getMembers().add(pokemon);
        }
    }

    public boolean removeMember(Pokemon pokemon){
        return this.getMembers().remove(pokemon);
    }

    public boolean changeMember(Pokemon pokemonToChange, Pokemon newPokemon){
        return removeMember(pokemonToChange) && addMember(newPokemon);
    }

    public boolean isFull(){
        return this.getMembers().size() >= MAX_TEAM_MEMBERS;
    };

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
}
