package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Type {
    private PokemonType name;

    //For all the types not included in Offensive Properties nor Defensive Properties the damage dealt/received is x1



    //Offensive Properties
    private Set<PokemonType> superEffective; //damage dealt x2
    private Set<PokemonType> notVeryEffective; //damage dealt x1/2
    private Set<PokemonType> noEffect; //damage dealt x0
    private Set<PokemonType> normalOffensiveEffect; //damage dealt x1

    //Defensive properties
    private Set<PokemonType> weakTo; //damage received x2
    private Set<PokemonType> resist; //damage received x1/2
    private Set<PokemonType> immuneTo; //damage received x0
    private Set<PokemonType> normalDefensiveEffect; //damage received x1


    //Constructors
    public Type(PokemonType name, Set<PokemonType> superEffective, Set<PokemonType> notVeryEffective, Set<PokemonType> noEffect, Set<PokemonType> weakTo, Set<PokemonType> resist, Set<PokemonType> immuneTo) {
        this.name = name;
        this.superEffective = superEffective;
        this.notVeryEffective = notVeryEffective;
        this.noEffect = noEffect;
        this.weakTo = weakTo;
        this.resist = resist;
        this.immuneTo = immuneTo;
        this.normalOffensiveEffect = calculateNormalOffensiveEffect();
        this.normalDefensiveEffect = calculateNormalDefensiveEffect();
    }

    public Type(PokemonType name) {
        this.name = name;
        this.superEffective = new HashSet<>();
        this.notVeryEffective = new HashSet<>();
        this.noEffect = new HashSet<>();
        this.weakTo = new HashSet<>();
        this.resist = new HashSet<>();
        this.immuneTo = new HashSet<>();
    }

    //Calcola tutti i tipi con moltiplicatore offensivo pari a x1
    private Set<PokemonType> calculateNormalOffensiveEffect(){
        Set<PokemonType> normalEffect = new HashSet<>(Arrays.asList(PokemonType.values()));
        normalEffect.removeAll(this.superEffective);
        normalEffect.removeAll(this.notVeryEffective);
        normalEffect.removeAll(this.noEffect);
        return normalEffect;
    }

    //Calcola tutti i tipi con moltiplicatore difensivo pari a x1
    private Set<PokemonType> calculateNormalDefensiveEffect(){
        Set<PokemonType> normalEffect = new HashSet<>(Arrays.asList(PokemonType.values()));
        normalEffect.removeAll(this.weakTo);
        normalEffect.removeAll(this.resist);
        normalEffect.removeAll(this.immuneTo);
        return normalEffect;
    }

    //Getters and Setters
    public PokemonType getName() {
        return name;
    }

    public void setName(PokemonType name) {
        this.name = name;
    }

    public Set<PokemonType> getSuperEffective() {
        return superEffective;
    }

    public void setSuperEffective(Set<PokemonType> superEffective) {
        this.superEffective = superEffective;
    }

    public Set<PokemonType> getNotVeryEffective() {
        return notVeryEffective;
    }

    public void setNotVeryEffective(Set<PokemonType> notVeryEffective) {
        this.notVeryEffective = notVeryEffective;
    }

    public Set<PokemonType> getNoEffect() {
        return noEffect;
    }

    public void setNoEffect(Set<PokemonType> noEffect) {
        this.noEffect = noEffect;
    }

    public Set<PokemonType> getWeakTo() {
        return weakTo;
    }

    public void setWeakTo(Set<PokemonType> weakTo) {
        this.weakTo = weakTo;
    }

    public Set<PokemonType> getResist() {
        return resist;
    }

    public void setResist(Set<PokemonType> resist) {
        this.resist = resist;
    }

    public Set<PokemonType> getImmuneTo() {
        return immuneTo;
    }

    public void setImmuneTo(Set<PokemonType> immuneTo) {
        this.immuneTo = immuneTo;
    }

    public Set<PokemonType> getNormalOffensiveEffect() {
        return normalOffensiveEffect;
    }

    public void setNormalOffensiveEffect(Set<PokemonType> normalOffensiveEffect) {
        this.normalOffensiveEffect = normalOffensiveEffect;
    }

    public Set<PokemonType> getNormalDefensiveEffect() {
        return normalDefensiveEffect;
    }

    public void setNormalDefensiveEffect(Set<PokemonType> normalDefensiveEffect) {
        this.normalDefensiveEffect = normalDefensiveEffect;
    }

    //Add and Remove Types relationships
    public void addSuperEffective(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.superEffective.add(type);
        }
    }

    public void removeSuperEffective(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.superEffective.remove(type);
        }
    }

    public void addNotVeryEffective(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.notVeryEffective.add(type);
        }
    }

    public void removeNotVeryEffective(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.notVeryEffective.remove(type);
        }
    }

    public void addNoEffect(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.noEffect.add(type);
        }
    }

    public void removeNoEffect(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.noEffect.remove(type);
        }
    }

    public void addWeakTo(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.weakTo.add(type);
        }
    }

    public void removeWeakTo(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.weakTo.remove(type);
        }
    }

    public void addResist(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.resist.add(type);
        }
    }

    public void removeResist(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.resist.remove(type);
        }
    }

    public void addImmuneTo(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.immuneTo.add(type);
        }
    }

    public void removeImmuneTo(PokemonType type){
        if(type!=PokemonType.UNDEFINED){
            this.immuneTo.remove(type);
        }
    }

    //Utilities
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return name == type.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
