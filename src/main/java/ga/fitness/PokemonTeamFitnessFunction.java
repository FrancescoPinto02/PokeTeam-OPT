package ga.fitness;

import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;

public class PokemonTeamFitnessFunction extends FitnessFunction<PokemonTeam> {
    private static final double MIN_FITNESS = 0;
    private static final double MAX_FITNESS = 100;

    public PokemonTeamFitnessFunction() {
        super(true);
    }

    @Override
    public void evaluate(PokemonTeam individual) {
        individual.setFitness(averageTeamStats(individual));
    }

    private double averageTeamStats(PokemonTeam individual){
        double total = 0;
        int n = 0;
        for(Pokemon p : individual.getCoding()){
            if(p.getTotal() > Pokemon.MAX_TOTAL_STATS_STANDARD){
                total += Pokemon.MAX_TOTAL_STATS_STANDARD;
            }
            else{
                total += p.getTotal();
            }
            n++;
        }

        return normalizeFitness(total/n, Pokemon.MIN_TOTAL_STATS, Pokemon.MAX_TOTAL_STATS_STANDARD, MIN_FITNESS, MAX_FITNESS);
    }


    private double normalizeFitness(double x, double minX, double maxX, double minY, double maxY){
        double normalizedFitness = (x - minX) / (maxX - minX) * (maxY - minY) + minY;
        return Math.max(0, Math.min(100, normalizedFitness));
    }
}
