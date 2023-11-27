package pokemon.team;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class PokemonTeam {
    private List<Pokemon> members;

    public PokemonTeam(){
        this.members = new ArrayList<>();
    }

    public List<Pokemon> getMembers() {
        return members;
    }

    public void setMembers(List<Pokemon> members) {
        this.members = members;
    }

    public abstract boolean addMember(Pokemon pokemon);
    public abstract boolean removeMember(Pokemon pokemon);
    public abstract boolean changeMember(Pokemon pokemonToChange, Pokemon newPokemon);
    public abstract boolean isFull();

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
