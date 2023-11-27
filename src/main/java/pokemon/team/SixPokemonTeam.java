package pokemon.team;

import pokemon.Pokemon;

import java.util.List;

public class SixPokemonTeam extends PokemonTeam{

    private static final int MAX_TEAM_MEMBERS = 6;

    public SixPokemonTeam() {
    }

    public SixPokemonTeam(List<Pokemon> members){
        super();
        for(int i=0; i < members.size() && !isFull(); i++){
            addMember(members.get(i));
        }
    }

    @Override
    public boolean addMember(Pokemon pokemon) {
        if(isFull()){
            return false;
        }
        else{
            return this.getMembers().add(pokemon);
        }
    }

    @Override
    public boolean removeMember(Pokemon pokemon) {
        return this.getMembers().remove(pokemon);
    }

    @Override
    public boolean changeMember(Pokemon pokemonToChange, Pokemon newPokemon) {
        return removeMember(pokemonToChange) && addMember(newPokemon);
    }

    @Override
    public boolean isFull() {
        return this.getMembers().size() >= MAX_TEAM_MEMBERS;
    }
}
