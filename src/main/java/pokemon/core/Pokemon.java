package pokemon.core;

import pokemon.type.PokemonType;
import pokemon.type.PokemonTypeMultiplier;
import pokemon.type.PokemonTypeName;

import java.util.*;

public class Pokemon {
    private int number; //national Pokédex number
    private String name;
    private PokemonType type1;
    private PokemonType type2;
    private int total; //sum of all the stats
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    private Set<PokemonTypeName> resistances;
    private Set<PokemonTypeName> weaknesses;


    //Constructors
    public Pokemon(int number, String name, PokemonType type1, PokemonType type2, int total, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.number = number;
        this.name = name;
        this.type1 = (type1==null) ? new PokemonType(PokemonTypeName.UNDEFINED) : type1;
        this.type2 = (type2==null) ? new PokemonType(PokemonTypeName.UNDEFINED) : type2;
        this.total = (total==(hp + attack + defense + specialAttack + specialDefense + speed)) ? total : (hp + attack + defense + specialAttack + specialDefense + speed);
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        resistances = new HashSet<>();
        weaknesses = new HashSet<>();
        calculateResistances();
        calculateWeaknesses();
    }

    public Pokemon(int number, String name, PokemonType type1, PokemonType type2, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this(number, name, type1, type2, (hp + attack + defense + specialAttack + specialDefense + speed), hp, attack, defense, specialAttack, specialDefense, speed);
    }

    //Calcola automaticamente tutte le resistenze in base ai due Type del pokemon
    private void calculateResistances(){
        Set<PokemonTypeName> allTypes = EnumSet.allOf(PokemonTypeName.class);
        allTypes.remove(PokemonTypeName.UNDEFINED);
        Map<PokemonTypeName, Double> defProperties1 = type1.getDefensiveProperties();
        boolean monotype = type2.getName() == PokemonTypeName.UNDEFINED;

        if(monotype){
            for(PokemonTypeName x : allTypes){
                Double mul1 = defProperties1.get(x);
                if(mul1.equals(PokemonTypeMultiplier.IMMUNE_TO) || mul1.equals(PokemonTypeMultiplier.RESISTS)){
                    resistances.add(x);
                }
            }
        }
        else{
            Map<PokemonTypeName, Double> defProperties2 = type2.getDefensiveProperties();

            for(PokemonTypeName x : allTypes){
                Double mul1 = defProperties1.get(x);
                Double mul2 = defProperties2.get(x);

                if(mul1.equals(PokemonTypeMultiplier.IMMUNE_TO) || mul2.equals(PokemonTypeMultiplier.IMMUNE_TO)){
                    resistances.add(x);
                }
                else if(mul1.equals(PokemonTypeMultiplier.NORMAL_EFFECTIVENESS) && mul2.equals(PokemonTypeMultiplier.RESISTS)){
                    resistances.add(x);
                }
                else if(mul1.equals(PokemonTypeMultiplier.RESISTS)){
                    if(mul2.equals(PokemonTypeMultiplier.RESISTS) || mul2.equals(PokemonTypeMultiplier.NORMAL_EFFECTIVENESS)){
                        resistances.add(x);
                    }
                }
            }
        }
    }

    //Calcola automaticamente tutte le debolezze in base ai due type del pokemon
    private void calculateWeaknesses(){
        Set<PokemonTypeName> allTypes = EnumSet.allOf(PokemonTypeName.class);
        allTypes.remove(PokemonTypeName.UNDEFINED);
        Map<PokemonTypeName, Double> defProperties1 = type1.getDefensiveProperties();
        boolean monotype = type2.getName() == PokemonTypeName.UNDEFINED;

        if(monotype){
            for(PokemonTypeName x : allTypes){
                Double mul1 = defProperties1.get(x);
                if(mul1.equals(PokemonTypeMultiplier.WEAK_TO)){
                    weaknesses.add(x);
                }
            }
        }
        else{
            Map<PokemonTypeName, Double> defProperties2 = type2.getDefensiveProperties();

            for(PokemonTypeName x : allTypes){
                Double mul1 = defProperties1.get(x);
                Double mul2 = defProperties2.get(x);

                if(mul1.equals(PokemonTypeMultiplier.WEAK_TO)){
                    if(mul2.equals(PokemonTypeMultiplier.WEAK_TO) || mul2.equals(PokemonTypeMultiplier.NORMAL_EFFECTIVENESS)){
                        weaknesses.add(x);
                    }
                }
                else if(mul1.equals(PokemonTypeMultiplier.NORMAL_EFFECTIVENESS) && mul2.equals(PokemonTypeMultiplier.WEAK_TO)){
                    weaknesses.add(x);
                }
            }
        }
    }

    //Getter and Setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonType getType1() {
        return type1;
    }

    public void setType1(PokemonType type1) {
        this.type1 = type1;
    }

    public PokemonType getType2() {
        return type2;
    }

    public void setType2(PokemonType type2) {
        this.type2 = type2;
    }

    public void setResistances(Set<PokemonTypeName> resistances) {
        this.resistances = resistances;
    }

    public void setWeaknesses(Set<PokemonTypeName> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Set<PokemonTypeName> getResistances() {
        return resistances;
    }

    public Set<PokemonTypeName> getWeaknesses() {
        return weaknesses;
    }

    //Utility
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return number == pokemon.number && Objects.equals(name, pokemon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    @Override
    public String toString() {
        return "#" + number + " " + name;
    }
}
