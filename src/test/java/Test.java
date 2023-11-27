import pokemon.Pokedex;
import pokemon.Pokemon;
import pokemon.team.SixPokemonTeam;
import pokemon.team.SixPokemonTeamGenerator;

public class Test {

    public static void main(String[] args){

        SixPokemonTeam team = new SixPokemonTeam();
        SixPokemonTeamGenerator generator = new SixPokemonTeamGenerator();
        team = generator.generatePokemonTeam();
        System.out.println(team);

    }
}
