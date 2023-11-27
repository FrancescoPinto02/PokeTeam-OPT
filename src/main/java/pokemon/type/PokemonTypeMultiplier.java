package pokemon.type;

/**
 * In this class are specified constants that represent all the possible offensive/defensive multipliers in the pokemon games
 */
public final class PokemonTypeMultiplier {
    //Offensive Properties Multipliers
    public static final Double SUPER_EFFECTIVE = 2.0; //damage dealt 2x
    public static final Double NOT_VERY_EFFECTIVE = 0.5; //damage dealt 0.5x
    public static final Double NO_EFFECT = 0.0; //damage dealt 0x

    //Defensive Properties Multipliers
    public static final Double WEAK_TO = 2.0; //damage received 2x
    public static final Double RESISTS = 0.5; //damage received 0.5x
    public static final Double IMMUNE_TO = 0.0; //damage received 0x

    //Neutral Multiplier
    public static final Double NORMAL_EFFECTIVENESS = 1.0; //damage dealt/received 1x
}
