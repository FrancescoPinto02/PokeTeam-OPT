package pokemon.pokedex;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pokemon.core.Pokemon;
import pokemon.core.PokemonRarity;
import pokemon.type.PokemonType;
import pokemon.type.PokemonTypeName;
import pokemon.type.PokemonTypePool;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Pokedex {
    private static final String DEFAULT_POKEDEX_FILE = "src/main/java/pokemon/pokedex/pokedex_data.json";
    private PokemonTypePool typePool;
    private HashMap<Integer, List<Pokemon>> pokemons;
    private int maxNumber;

    public Pokedex() {
        typePool = new PokemonTypePool();
        pokemons = new HashMap<>();

        try{
            //Read JSON file
            FileReader reader = new FileReader(DEFAULT_POKEDEX_FILE);

            //Parsing the JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            int number = 0;

            //Iterate the array
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj; //retrieve object

                //Obtain the fields
                number = Integer.parseInt((String) jsonObject.get("#"));
                String name = (String) jsonObject.get("Name");

                String[] types = ((String) jsonObject.get("Type")).split(" ");
                PokemonType type1 = typePool.getType(types[0]);
                PokemonType type2;
                if(types.length > 1){
                    type2 = typePool.getType(types[1]);
                }
                else{
                    type2 = new PokemonType(PokemonTypeName.UNDEFINED);
                }

                int total = Integer.parseInt((String) jsonObject.get("Total"));
                int hp = Integer.parseInt((String) jsonObject.get("HP"));
                int att = Integer.parseInt((String) jsonObject.get("Attack"));
                int def = Integer.parseInt((String) jsonObject.get("Defense"));
                int spAtt = Integer.parseInt((String) jsonObject.get("Sp. Atk"));
                int spDef = Integer.parseInt((String) jsonObject.get("Sp. Def"));
                int speed = Integer.parseInt((String) jsonObject.get("Speed"));

                PokemonRarity rarity = mapToPokemonRarity((String) jsonObject.get("Rarity"));

                Pokemon pokemon = new Pokemon(number, name, type1, type2, total, hp, att, def, spAtt, spDef, speed, rarity);
                addPokemon(pokemon);
            }
            reader.close();
            this.maxNumber = number;
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }

    }

    public int getMaxNumber() {
        return maxNumber;
    }

    //Retrieve a list of Pokémon with number = number from HashMap pokemon
    //ATTENTION: The list can contain variant version of a Pokémon that have the same number but can differ for name, types and stats
    public List<Pokemon> getPokemon(int number){
        return pokemons.getOrDefault(number, new ArrayList<>());
    }

    //Return the variant of the pokemon with the specified number at the specified index position
    //ATTENTION: Can return null
    public Pokemon getPokemon(int number, int index){
        List<Pokemon> pokemonList = getPokemon(number);
        if(pokemonList.isEmpty()){
            return null; //return null if there is not pokemon with the specified number
        }

        if(index >= 0 && index < pokemonList.size()){
            return pokemonList.get(index); //return the Pokémon at the specified index if it is in range
        }
        else{
            return pokemonList.get(0); //return the first pokemon of the list if the index is not in range
        }
    }

    //Add a pokemon to the HashMap pokemons
    public void addPokemon(Pokemon pokemon){
        int number = pokemon.getNumber();

        //Check if there is already a list of pokemon with this key
        if(pokemons.containsKey(number)){
            pokemons.get(number).add(pokemon); //add the pokemon at the existent list
        }
        else{
            List<Pokemon> pokemonList = new ArrayList<>(); //create a new list
            pokemonList.add(pokemon); //add pokemon at the new list
            pokemons.put(number,pokemonList); //add the new list in the map
        }
    }

    private PokemonRarity mapToPokemonRarity(String string){
        if(string != null){
            return switch (string) {
                case "Pseudo-Legendary" -> PokemonRarity.PSEUDO_LEGENDARY;
                case "Sub-Legendary" -> PokemonRarity.SUB_LEGENDARY;
                case "Legendary" -> PokemonRarity.LEGENDARY;
                case "Mythical" -> PokemonRarity.MYTHICAL;
                case "Paradox" -> PokemonRarity.PARADOX;
                default -> PokemonRarity.COMMON;
            };
        }
        else{
            return PokemonRarity.COMMON;
        }
    }

    @Override
    public String toString() {
        return pokemons.toString();
    }
}
