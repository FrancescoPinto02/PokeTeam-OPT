package ga.fitness;

import ga.individuals.PokemonTeam;
import pokemon.core.Pokemon;
import pokemon.core.PokemonRarity;
import pokemon.type.PokemonType;
import pokemon.type.PokemonTypeName;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PokemonTeamFitnessFunction extends FitnessFunction<PokemonTeam> {
    private static final double MIN_FITNESS = 0;
    private static final double MAX_FITNESS = 100;

    public PokemonTeamFitnessFunction() {
        super(true);
    }

    @Override
    public void evaluate(PokemonTeam individual) {
        if(megaEvolutionCount(individual) > 1){
            individual.setFitness(0);
        }
        else{
            double fitness = averageTeamStats(individual) + typesDiversity(individual) + teamResistances(individual)
                    + legendaryCount(individual) + commonWeaknesses(individual);
            individual.setFitness(fitness);
        }
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

    private double legendaryCount(PokemonTeam individual){
        double count = 0;
        for(Pokemon x : individual.getCoding()){
            PokemonRarity rarity = x.getRarity();
            if(rarity == PokemonRarity.LEGENDARY || rarity == PokemonRarity.MYTHICAL){
                count += 2;
            } else if (rarity == PokemonRarity.SUB_LEGENDARY || rarity == PokemonRarity.PARADOX) {
                count += 1;
            }
        }
        return normalizeFitness(count, 6, 0, MIN_FITNESS, MAX_FITNESS);
    }

    private int megaEvolutionCount(PokemonTeam individual){
        int count = 0;
        for(Pokemon p : individual.getCoding()){
            if(p.isMegaEvolution()){
                count++;
            }
        }
        return count;
    }

    private double commonWeaknesses(PokemonTeam individual){
        Map<PokemonTypeName, Integer> weaknessMap = new HashMap<>();
        for(PokemonTypeName type : PokemonTypeName.values()){
            weaknessMap.put(type,0);
        }

        for(Pokemon p : individual.getCoding()){
            for(PokemonTypeName weakness : p.getWeaknesses()){
                weaknessMap.put(weakness, weaknessMap.get(weakness)+1);
            }
        }

        int total = 0;
        int num = 0;
        for(PokemonTypeName key : weaknessMap.keySet()){
            int value = weaknessMap.get(key);
            if(value>0){
                total += value;
                num++;
            }
        }

        return normalizeFitness(total/num, total, 1, MIN_FITNESS, MAX_FITNESS);
    }

    private double normalizeFitness(double x, double minX, double maxX, double minY, double maxY){
        double normalizedFitness = (x - minX) / (maxX - minX) * (maxY - minY) + minY;
        return Math.max(0, Math.min(100, normalizedFitness));
    }
}
