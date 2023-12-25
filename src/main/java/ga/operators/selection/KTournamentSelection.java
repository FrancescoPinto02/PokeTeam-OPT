package ga.operators.selection;

import ga.individuals.Individual;
import ga.population.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KTournamentSelection <T extends Individual> extends SelectionOperator<T>{

    private final int k;
    private final int m;
    private final boolean proportional;

    public KTournamentSelection(int k, int m, boolean proportional) {
        super();
        this.k = k;
        this.m = m;
        this.proportional = proportional;
    }

    @Override
    public Population<T> apply(Population<T> population, Random rand) throws CloneNotSupportedException {
        Population<T> newPopulation = population.clone();
        newPopulation.setId(population.getId() + 1);
        newPopulation.clear();

        List<T> populationList = new ArrayList<>();
        populationList.addAll(population);

        while(newPopulation.size() < m){
            List<T> tournament = new ArrayList<>();
            for(int i=0; i<k; i++){
                int randomIndex = rand.nextInt(populationList.size());
                tournament.add(populationList.get(randomIndex));
            }

            T best = null;
            if(!proportional){
                best = Collections.max(tournament);
            }
            else{
                best = getProportionalWinner(tournament, rand);
            }

            newPopulation.add((T) best.clone());
        }

        return newPopulation;
    }


    private T getProportionalWinner(List<T> tournament, Random rand){
        double[] startPositions = new double[k];
        double[] sizes = new double[k];
        double currentPosition = 0.0;

        double totalFitness = 0;
        for(T partecipant : tournament){
            totalFitness += partecipant.getFitness();
        }

        for(int i=0; i<k; i++){
            T partecipant = tournament.get(i);
            double relativeFitness = partecipant.getFitness()/totalFitness;
            startPositions[i] = currentPosition;
            sizes[i] = relativeFitness;
            currentPosition += relativeFitness;
        }

        Double pointer = rand.nextDouble();
        for(int i=0; i<tournament.size(); i++){
            if(startPositions[i] <= pointer && pointer < startPositions[i]+sizes[i]){
                return tournament.get(i);
            }
        }
        return null;
    }
}


