package pokemon;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pokemon {
    private int number; //national Pokédex number
    private String name;
    private Type type1;
    private Type type2;
    private int total; //sum of all the stats
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    private Set<PokemonType> resistances;
    private Set<PokemonType> weaknesses;


    //Constructors
    public Pokemon(int number, String name, Type type1, Type type2, int total, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.number = number;
        this.name = name;
        this.type1 = (type1==null) ? new Type(PokemonType.UNDEFINED) : type1;
        this.type2 = (type2==null) ? new Type(PokemonType.UNDEFINED) : type2;
        this.total = (total==(hp + attack + defense + specialAttack + specialDefense + speed)) ? total : (hp + attack + defense + specialAttack + specialDefense + speed);
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        calculateResistances();
        calculateWeaknesses();
    }

    public Pokemon(int number, String name, Type type1, Type type2, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this(number, name, type1, type2, (hp + attack + defense + specialAttack + specialDefense + speed), hp, attack, defense, specialAttack, specialDefense, speed);
    }

    //Calcola automaticamente tutte le resistenze in base ai due Type del pokemon
    private void calculateResistances(){
        resistances = new HashSet<>();
        resistances.addAll(type1.getImmuneTo());

        //Se il pokemon ha solo un tipo allora le resistenze sono uguali alle resistenze di quel tipo
        if(type2.getName()==PokemonType.UNDEFINED){
            resistances.addAll(type1.getResist());
        }
        else{
            resistances.addAll(type2.getImmuneTo());
            for(PokemonType x : type1.getResist()){
                if(type2.getResist().contains(x) || type2.getNormalDefensiveEffect().contains(x)){
                    resistances.add(x);
                }
            }

            for(PokemonType x : type1.getNormalDefensiveEffect()){
                if(type2.getResist().contains(x)){
                    resistances.add(x);
                }
            }
        }
    }

    //Calcola automaticamente tutte le debolezze in base ai due type del pokemon
    private void calculateWeaknesses(){
        weaknesses = new HashSet<>();

        if(type2.getName()==PokemonType.UNDEFINED){
            weaknesses.addAll(type1.getWeakTo());
        }
        else{
            for(PokemonType x : type1.getWeakTo()){
                if(type2.getWeakTo().contains(x) || type2.getNormalDefensiveEffect().contains(x)){
                    weaknesses.add(x);
                }
            }

            for(PokemonType x : type1.getNormalDefensiveEffect()){
                if(type2.getWeakTo().contains(x)){
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

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        this.type2 = type2;
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

    public Set<PokemonType> getResistances() {
        return resistances;
    }

    public void setResistances(Set<PokemonType> resistances) {
        this.resistances = resistances;
    }

    public Set<PokemonType> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(Set<PokemonType> weaknesses) {
        this.weaknesses = weaknesses;
    }

    //Utility
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return number == pokemon.number && total == pokemon.total && Objects.equals(name, pokemon.name) && Objects.equals(type1, pokemon.type1) && Objects.equals(type2, pokemon.type2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    @Override
    public String toString() {
        return "#" + number + " " + name + " (" + type1 + ", " + type2 + ")";
    }
}
