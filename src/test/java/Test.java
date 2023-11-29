import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;
import pokemon.team.PokemonTeamGenerator;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        PokemonTeam team = PokemonTeamGenerator.generatePokemonTeam(PokemonTeam.MAX_TEAM_MEMBERS);
        System.out.println(team);

    }
}
