import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;
import pokemon.team.PokemonTeamGenerator;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {

        PokemonTeam team = new PokemonTeam();
        team = PokemonTeamGenerator.generatePokemonTeam();
        System.out.println("team 1:" + team);

        PokemonTeam team2 = (PokemonTeam) team.clone();
        System.out.println("team 2:" + team2);
        team2.changeMember(team2.getMembers().get(0), PokemonGenerator.generatePokemon());
        System.out.println("Clonazione");
        System.out.println("team 1:" + team);
        System.out.println("team 2:" + team2);


    }
}
