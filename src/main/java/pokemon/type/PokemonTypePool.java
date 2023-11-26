package pokemon.type;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokemonTypePool {
    private Set<PokemonType> types;
    private static final String DEFAULT_TYPES_FILE = "src/main/java/pokemon/type/types.json";

    public PokemonTypePool(Set<PokemonType> types) {
        this.types = types;
    }

    public PokemonTypePool() {
        types = new HashSet<>();
        try {
            //Read JSON file
            FileReader reader = new FileReader(DEFAULT_TYPES_FILE);

            //Parsing the JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            //Iterate the array
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj; //retrieve object

                //Obtain the fields
                PokemonTypeName typeName = mapToPokemonTypeName((String) jsonObject.get("Type"));

                Map<PokemonTypeName, Double> offensiveProperties = new HashMap<>();
                Map<PokemonTypeName, Double> defensiveProperties = new HashMap<>();

                calculateTypeProperties(offensiveProperties, ((String) jsonObject.get("SuperEffective")), "SuperEffective");
                calculateTypeProperties(offensiveProperties, ((String) jsonObject.get("NotVeryEffective")), "NotVeryEffective");
                calculateTypeProperties(offensiveProperties, ((String) jsonObject.get("NoEffect")), "NoEffect");

                calculateTypeProperties(defensiveProperties, ((String) jsonObject.get("WeakTo")), "WeakTo");
                calculateTypeProperties(defensiveProperties, ((String) jsonObject.get("Resists")), "Resists");
                calculateTypeProperties(defensiveProperties, ((String) jsonObject.get("ImmuneTo")), "ImmuneTo");


                //Calculate all the normal effectiveness types
                PokemonTypeName[] allTypes = PokemonTypeName.values();
                for(PokemonTypeName x : allTypes){
                    if(x != PokemonTypeName.UNDEFINED){
                        if(!offensiveProperties.containsKey(x)){
                            offensiveProperties.put(x, PokemonTypeMultiplier.NORMAL_EFFECTIVENESS);
                        }
                        if(!defensiveProperties.containsKey(x)){
                            defensiveProperties.put(x, PokemonTypeMultiplier.NORMAL_EFFECTIVENESS);
                        }
                    }
                }


                //Add the type in the pool
                PokemonType type = new PokemonType(typeName, offensiveProperties, defensiveProperties);
                types.add(type);
            }
            reader.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public Set<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(Set<PokemonType> types) {
        this.types = types;
    }

    public PokemonType getType(PokemonTypeName type){
        for(PokemonType x : types){
            if(x.getName() == type){
                return x;
            }
        }
        return new PokemonType(PokemonTypeName.UNDEFINED);
    }

    //Get the PokemonType specified as String from the pool
    public PokemonType getType(String type){
        return getType(mapToPokemonTypeName(type.toUpperCase()));
    }

    private static PokemonTypeName mapToPokemonTypeName(String type){
        try{
            return PokemonTypeName.valueOf(type);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return PokemonTypeName.UNDEFINED;
        }
    }

    private void calculateTypeProperties(Map<PokemonTypeName, Double> properties, String values, String propertyName){
        String[] temp = values.split(" ");
        for(String s : temp){
            if(!s.isEmpty()){
                switch (propertyName){
                    case "SuperEffective":
                        properties.put(mapToPokemonTypeName(s),PokemonTypeMultiplier.SUPER_EFFECTIVE);
                        break;
                    case "NotVeryEffective":
                        properties.put(mapToPokemonTypeName(s),PokemonTypeMultiplier.NOT_VERY_EFFECTIVE);
                        break;
                    case "NoEffect":
                        properties.put(mapToPokemonTypeName(s),PokemonTypeMultiplier.NO_EFFECT);
                        break;
                    case "WeakTo":
                        properties.put(mapToPokemonTypeName(s),PokemonTypeMultiplier.WEAK_TO);
                        break;
                    case "Resists":
                        properties.put(mapToPokemonTypeName(s),PokemonTypeMultiplier.RESISTS);
                        break;
                    case "ImmuneTo":
                        properties.put(mapToPokemonTypeName(s),PokemonTypeMultiplier.IMMUNE_TO);
                        break;
                }

            }

        }
    }

}
