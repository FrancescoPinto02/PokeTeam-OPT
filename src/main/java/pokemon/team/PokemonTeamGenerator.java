package pokemon.team;

import pokemon.Pokedex;

public abstract class PokemonTeamGenerator<T extends PokemonTeam> {
    private static Pokedex pokedex = new Pokedex();

    protected static Pokedex getPokedex() {
        return pokedex;
    }

    public abstract T generatePokemonTeam();


}
