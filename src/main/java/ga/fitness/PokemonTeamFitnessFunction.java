package ga.fitness;

import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.type.PokemonType;
import pokemon.type.PokemonTypeName;

import java.lang.reflect.Type;
import java.util.HashSet;

public class PokemonTeamFitnessFunction extends FitnessFunction<PokemonTeam> {
    private static final double MIN_FITNESS = 0;
    private static final double MAX_FITNESS = 100;

    public PokemonTeamFitnessFunction() {
        super(true);
    }

    @Override
    public void evaluate(PokemonTeam individual) {
        double fitness = averageTeamStats(individual) + typesDiversity(individual) + teamResistances(individual);
        individual.setFitness(fitness);
    }

    private double averageTeamStats(PokemonTeam individual){
        double total = 0;
        int n = 0;
        for(Pokemon p : individual.getCoding()){
            if(p.getTotal() > Pokemon.MAX_TOTAL_STATS_STANDARD){
                total += 560;
            }
            else{
                total += p.getTotal();
            }
            n++;
        }
        return normalizeFitness(total/n, Pokemon.MIN_TOTAL_STATS, Pokemon.MAX_TOTAL_STATS_STANDARD, MIN_FITNESS, MAX_FITNESS);
    }

    private double typesDiversity(PokemonTeam individual){
        HashSet<PokemonTypeName> teamTypes = new HashSet<>();

        for(Pokemon p : individual.getCoding()){

            teamTypes.add(p.getType1().getName());

            if(p.getType2().getName() != PokemonTypeName.UNDEFINED){
                teamTypes.add(p.getType2().getName());
            }
        }

        return normalizeFitness(teamTypes.size(), 1, 12, MIN_FITNESS, MAX_FITNESS);
    }

    private double teamResistances(PokemonTeam individual){
        HashSet<PokemonTypeName> teamResistances = new HashSet<>();
        int minTeamResistances = 1;
        int maxTeamResistances = 18;

        for(Pokemon p : individual.getCoding()){
            teamResistances.addAll(p.getResistances());
        }

        return normalizeFitness(teamResistances.size(), minTeamResistances, maxTeamResistances, MIN_FITNESS, MAX_FITNESS);
    }

    private double normalizeFitness(double x, double minX, double maxX, double minY, double maxY){
        double normalizedFitness = (x - minX) / (maxX - minX) * (maxY - minY) + minY;
        return Math.max(0, Math.min(100, normalizedFitness));
    }
}
