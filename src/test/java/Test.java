import pokemon.Type;
import pokemon.TypePool;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args){
        TypePool typePool = new TypePool();
        Set<Type> types = typePool.getTypes();
        for(Type t : types){
            System.out.println(t);
        }

        System.out.println(typePool);
    }
}
