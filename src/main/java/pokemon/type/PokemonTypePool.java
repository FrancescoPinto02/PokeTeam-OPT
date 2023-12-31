package pokemon.type;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Classe che rappresenta un pool di tipi Pokemon.
 * I tipi vengono presi in input da un file JSON.
 */
public class PokemonTypePool {
    private Set<PokemonType> types; //Pool
    private static final String DEFAULT_TYPES_FILE = "src/main/java/pokemon/type/types.json"; //input file di default

    /**
     * Costruttore che inizializza il pool di tipi Pokemon
     * utilizzando il file JSON di default
     */
    public PokemonTypePool(){
        this(DEFAULT_TYPES_FILE);
    }

    /**
     * Costruttore che inizializza il pool di tipi pokemon
     * da un file JSON
     * @param filePath percorso del file JSON da cui caricare i tipi Pokemon
     */
    public PokemonTypePool(String filePath) {
        types = new HashSet<>();
        try {
            //Lettura file JSON
            FileReader reader = new FileReader(filePath);

            //Parsing del file JSON
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            //Itera l`array
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj; //oggetto recuperato

                //Accesso ai campi dell`oggetto
                PokemonTypeName typeName = mapToPokemonTypeName((String) jsonObject.get("Type"));

                Map<PokemonTypeName, Double> offensiveProperties = new HashMap<>();
                Map<PokemonTypeName, Double> defensiveProperties = new HashMap<>();

                //Calcola proprietà offensive
                calculateTypeProperties(offensiveProperties, ((String) jsonObject.get("SuperEffective")), "SuperEffective");
                calculateTypeProperties(offensiveProperties, ((String) jsonObject.get("NotVeryEffective")), "NotVeryEffective");
                calculateTypeProperties(offensiveProperties, ((String) jsonObject.get("NoEffect")), "NoEffect");

                //Calcola proprietà difensive
                calculateTypeProperties(defensiveProperties, ((String) jsonObject.get("WeakTo")), "WeakTo");
                calculateTypeProperties(defensiveProperties, ((String) jsonObject.get("Resists")), "Resists");
                calculateTypeProperties(defensiveProperties, ((String) jsonObject.get("ImmuneTo")), "ImmuneTo");

                //Calcola le proprietà con efficacia normale
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

                //Creazione e aggiunta del tipo al pool
                PokemonType type = new PokemonType(typeName, offensiveProperties, defensiveProperties);
                types.add(type);
            }
            reader.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    //Getters e Setters
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

    //Cerca un tipo nel pool corrispondendte alla stringa passata in input
    public PokemonType getType(String type){
        return getType(mapToPokemonTypeName(type.toUpperCase()));
    }

    //Mappa una Stringa che indica un tipo Pokemon nella enum PokemonTypeName corrispondente
    private static PokemonTypeName mapToPokemonTypeName(String type){
        try{
            return PokemonTypeName.valueOf(type);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return PokemonTypeName.UNDEFINED;
        }
    }

    //Inserisce i tipi Pokemon letti dal file JSON nella Map properties corretta
    private void calculateTypeProperties(Map<PokemonTypeName, Double> properties, String values, String propertyName){
        //Split della stringa con i nomi dei tipi
        String[] temp = values.split(" ");

        //Inserimento di ogni tipo nella Map properties
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

    @Override
    public String toString() {
        return types.toString();
    }
}
