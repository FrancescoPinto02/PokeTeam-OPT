package pokemon.team;

import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

public abstract class PokemonTeam {
    private List<Pokemon> members;

    public PokemonTeam(){
        this.members = new ArrayList<>();
    }

    public abstract boolean addMember(Pokemon pokemon);
    public abstract boolean removeMember(Pokemon pokemon);
    public abstract boolean changeMember(Pokemon pokemonToChange, Pokemon newPokemon);
}
