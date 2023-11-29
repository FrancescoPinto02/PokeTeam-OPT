import ga.individuals.PokemonTeam;
import ga.initializer.PokemonTeamInitializer;
import ga.population.FixedSizePopulation;

public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        PokemonTeamInitializer initializer = new PokemonTeamInitializer(10);
        FixedSizePopulation<PokemonTeam> population = (FixedSizePopulation<PokemonTeam>) initializer.initialize();
        System.out.println(population);
    }
}
