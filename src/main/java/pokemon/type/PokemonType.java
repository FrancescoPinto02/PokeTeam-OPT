package pokemon.type;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PokemonType {
    private PokemonTypeName name;
    private Map<PokemonTypeName, Double> offensiveProperties;
    private Map<PokemonTypeName, Double> DefensiveProperties;

    public PokemonType(PokemonTypeName name, Map<PokemonTypeName, Double> offensiveProperties, Map<PokemonTypeName, Double> defensiveProperties) {
        this.name = name;
        this.offensiveProperties = offensiveProperties;
        DefensiveProperties = defensiveProperties;
    }

    public PokemonType(PokemonTypeName name) {
        this(name, new HashMap<>(), new HashMap<>());
    }


    public PokemonTypeName getName() {
        return name;
    }

    public void setName(PokemonTypeName name) {
        this.name = name;
    }

    public Map<PokemonTypeName, Double> getOffensiveProperties() {
        return offensiveProperties;
    }

    public void setOffensiveProperties(Map<PokemonTypeName, Double> offensiveProperties) {
        this.offensiveProperties = offensiveProperties;
    }

    public Map<PokemonTypeName, Double> getDefensiveProperties() {
        return DefensiveProperties;
    }

    public void setDefensiveProperties(Map<PokemonTypeName, Double> defensiveProperties) {
        DefensiveProperties = defensiveProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonType that = (PokemonType) o;
        return name == that.name;
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
