package pokemon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Pokedex {

    private static final String JSON_POKEDEX_FILE = "src/main/java/pokemon/pokedex_data.json";
    private TypePool typePool;
    private HashMap<Integer, List<Pokemon>> pokemons;

    public Pokedex() {
        typePool = new TypePool();
        pokemons = new HashMap<Integer, List<Pokemon>>();

        try{
            //Read JSON file
            FileReader reader = new FileReader(JSON_POKEDEX_FILE);

            //Parsing the JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            //Iterate the array
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj; //retrieve object

                //Obtain the fields
                int number = Integer.parseInt((String) jsonObject.get("#"));
                String name = (String) jsonObject.get("Name");

                String[] types = ((String) jsonObject.get("Type")).split(" ");
                Type type1 = typePool.getType(types[0]);
                Type type2;
                if(types.length > 1){
                    type2 = typePool.getType(types[1]);
                }
                else{
                    type2 = new Type(PokemonType.UNDEFINED);
                }

                int total = Integer.parseInt((String) jsonObject.get("Total"));
                int hp = Integer.parseInt((String) jsonObject.get("HP"));
                int att = Integer.parseInt((String) jsonObject.get("Attack"));
                int def = Integer.parseInt((String) jsonObject.get("Defense"));
                int spAtt = Integer.parseInt((String) jsonObject.get("Sp. Atk"));
                int spDef = Integer.parseInt((String) jsonObject.get("Sp. Def"));
                int speed = Integer.parseInt((String) jsonObject.get("Speed"));

                Pokemon pokemon = new Pokemon(number, name, type1, type2, total, hp, att, def, spAtt, spDef, speed);
                addPokemon(pokemon);
            }
            reader.close();
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }


    }

    //Retrieve a list of pokemons with number = number from HashMap pokemons
    //ATTENTION: The list can contains variant version of a pokemon that have the same number but can differ for name, types and stats
    public List<Pokemon> getPokemon(int number){
        return pokemons.getOrDefault(number, new ArrayList<>());
    }

    //Add a pokemon to the HashMap pokemons
    public void addPokemon(Pokemon pokemon){
        int number = pokemon.getNumber();

        if(pokemons.containsKey(number)){
            pokemons.get(number).add(pokemon);
        }
        else{
            List<Pokemon> pokemonList = new ArrayList<>();
            pokemonList.add(pokemon);
            pokemons.put(number,pokemonList);
        }
    }

    @Override
    public String toString() {
        return "Pokedex{" +
                "pokemons=" + pokemons +
                '}';
    }
}
