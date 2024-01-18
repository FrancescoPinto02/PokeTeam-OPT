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

/**
 * Classe utilizzate per gestire il Pokedex
 */
public class Pokedex {
    private static final String DEFAULT_POKEDEX_FILE = "src/main/java/pokemon/pokedex/pokedex_data.json"; //Directory dataset di default
    private PokemonTypePool typePool; //Pool di tipi Pokemon
    private HashMap<Integer, List<Pokemon>> pokemons; //Hashmap di Pokemon
    private int maxNumber; //Numero massimo del pokedex

    /**
     * Costruttore del pokedex. Tutti i dati necessari a riempire il pokedex
     * verranno letti da un apposito file JSON
     */
    public Pokedex() {
        typePool = new PokemonTypePool();
        pokemons = new HashMap<>();

        try{
            //Lettura file JSON
            FileReader reader = new FileReader(DEFAULT_POKEDEX_FILE);

            //Parsing del file JSON
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            int number = 0;

            //Itera l`array
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj; //retrieve object

                //Ottieni i vari campi
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

                //crea un nuovo pokemon con i campi appena letti
                Pokemon pokemon = new Pokemon(number, name, type1, type2, total, hp, att, def, spAtt, spDef, speed, rarity);

                //aggiungi il pokemon al pokedex
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


    /**
     * Recupera una lista di Pokemon dal pokedex che corrispondono al numero specificato.
     * Ad un numero possono corrispondere diverse varianti di uno stesso pokemon che si differenziano
     * per tipo e statistiche.
     *
     * @param number Numero dei pokemon da recuperare
     * @return Lista di pokemon
     */
    public List<Pokemon> getPokemon(int number){
        return pokemons.getOrDefault(number, new ArrayList<>());
    }

    /**
     * Recupera un Pokemon dal pokedex che corrispondono al numero e alla variante specificati.
     *
     * @param number Numero del pokemon da recuperare
     * @param index Indice della variante da recuperare
     * @return Pokemon cercato o la prima variante se index non è valido
     */
    public Pokemon getPokemon(int number, int index){
        List<Pokemon> pokemonList = getPokemon(number);
        if(pokemonList.isEmpty()){
            return null; //restituisce null se non ci sono pokemon con il numero specificato
        }

        if(index >= 0 && index < pokemonList.size()){
            return pokemonList.get(index); //restituisce la variante specificata se l`indice è valido
        }
        else{
            return pokemonList.get(0); //restituisce la prima variante se l`indice non è valido
        }
    }

    /**
     * Permette di aggiungere un nuovo pokemon al Pokedex
     *
     * @param pokemon Pokemon da aggiungere
     */
    public void addPokemon(Pokemon pokemon){
        int number = pokemon.getNumber();

       //Verifica se già esiste un pokemon con quel numero
        if(pokemons.containsKey(number)){
            pokemons.get(number).add(pokemon); //aggiunge il pokemon alla lista esistente
        }
        else{
            List<Pokemon> pokemonList = new ArrayList<>(); //crea una nuova lista
            pokemonList.add(pokemon); //aggiunge il pokemon alla nuova lista
            pokemons.put(number,pokemonList); //inserisce la lista nella hashmap
        }
    }

    //Metodo per mappare una stringa indicante la rarità di un Pokemon nell`enum corrispondente
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
