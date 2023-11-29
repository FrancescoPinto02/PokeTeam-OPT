package ga.initializer;

import ga.individuals.PokemonTeam;
import ga.population.FixedSizePopulation;
import ga.population.Population;
import pokemon.team.PokemonTeamGenerator;

public class PokemonTeamInitializer extends Initializer<PokemonTeam>{

    private final int numberOfIndividuals;

    public PokemonTeamInitializer(int numberOfIndividuals) {
        this.numberOfIndividuals = Math.max(numberOfIndividuals, 1);
    }

    @Override
    public Population<PokemonTeam> initialize() {
        FixedSizePopulation<PokemonTeam> population = new FixedSizePopulation<>(0, numberOfIndividuals);
        for(int i = 0; i < numberOfIndividuals; i++){
            PokemonTeam individual = PokemonTeamGenerator.generatePokemonTeam(PokemonTeam.MAX_TEAM_MEMBERS);
            population.add(individual);
        }

        return population;
    }
}
