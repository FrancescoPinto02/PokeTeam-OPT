import pokemon.team.PokemonTeam;
import pokemon.team.PokemonTeamGenerator;

public class Test {

    public static void main(String[] args){

        PokemonTeam team = new PokemonTeam();
        team = PokemonTeamGenerator.generatePokemonTeam();
        System.out.println(team);

    }
}
