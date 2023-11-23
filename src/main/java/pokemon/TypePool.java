package pokemon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class TypePool {
    private Set<Type> types;
    private static final String JSON_TYPE_FILE = "src/main/java/pokemon/types.json";

    //Constructor: Reads the types from a JSON File
    public TypePool(){
        types = new HashSet<>();
        try {
            //Read JSON file
            FileReader reader = new FileReader(JSON_TYPE_FILE);

            //Parsing the JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            //Iterate the array
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj; //retrieve object

                //Obtain the fields
                PokemonType name = mapToPokemonType((String) jsonObject.get("Type"));
                Set<PokemonType> superEffective = mapToPokemonTypeSet((String) jsonObject.get("SuperEffective"));
                Set<PokemonType> notVeryEffective = mapToPokemonTypeSet((String) jsonObject.get("NotVeryEffective"));
                Set<PokemonType> noEffect = mapToPokemonTypeSet((String) jsonObject.get("NoEffect"));
                Set<PokemonType> weakTo = mapToPokemonTypeSet((String) jsonObject.get("WeakTo"));
                Set<PokemonType> resists = mapToPokemonTypeSet((String) jsonObject.get("Resists"));
                Set<PokemonType> immuneTo = mapToPokemonTypeSet((String) jsonObject.get("ImmuneTo"));

                //Add the type in the pool
                Type type = new Type(name, superEffective, notVeryEffective, noEffect, weakTo, resists, immuneTo);
                types.add(type);
            }
            reader.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    //Get the specified type from the pool
    public Type getType(PokemonType type){
        for(Type t : types){
            if(t.getName() == type){
                return t;
            }
        }
        return new Type(PokemonType.UNDEFINED);
    }

    //Get the specified type specified as String from the pool
    public Type getType(String type){
        return getType(mapToPokemonType(type.toUpperCase()));
    }

    //Add a type to the pool
    public void addType(Type type){
        types.add(type);
    }

    //Remove a type to the pool
    public void removeType(Type type){
        types.remove(type);
    }

    //Map a String to the equivalent enum PokemonType
    private static PokemonType mapToPokemonType(String type){
        try{
            return PokemonType.valueOf(type);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return PokemonType.UNDEFINED;
        }
    }

    //split the parameter and use mapToPokemonType for all the pieces
    private static Set<PokemonType> mapToPokemonTypeSet(String types){
        Set<String> typeStrings = new HashSet<>(Arrays.asList(types.split(" ")));
        Set<PokemonType> typesSet = new HashSet<>();
        for(String type : typeStrings){
            if(type.isEmpty()){
                return typesSet;
            }
            else {
                typesSet.add(mapToPokemonType(type));
            }
        }
        return typesSet;
    }

    @Override
    public String toString() {
        return "TypePool{" +
                "types=" + types +
                '}';
    }
}
