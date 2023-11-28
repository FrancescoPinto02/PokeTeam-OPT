import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.core.PokemonGenerator;
import pokemon.team.PokemonTeamGenerator;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        PokemonTeam t1 = PokemonTeamGenerator.generatePokemonTeam();
        System.out.println(t1.getCoding());
        System.out.println(t1);
        PokemonTeam t2 = (PokemonTeam) t1.clone();
        t2.changeMember(t2.getMembers().get(0), PokemonGenerator.generatePokemon());
        System.out.println(t2.getCoding());

    }
}
