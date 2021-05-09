

import static java.lang.String.format;

import io.vavr.collection.LinkedHashMultimap;
import io.vavr.collection.Multimap;
import io.vavr.control.Option;
import lombok.Value;

public class VavrTest {
    static Option<String> some = Option.of("foo");
    static Option<String> none = Option.none();
   
    public static void main(String[] args) {
        multiMapTest();
//        System.out.println("fred " + getValue(Option.of(new IntHolder(15))));
//        System.out.println("barney " + getValue(Option.none()));
//        
//        
//        System.out.println(List.range(12,5));
//        some.map(s -> syso(s));
//        none.map(s -> syso(s));
//        System.out.println("----");
//        System.out.println(some);
//        System.out.println(none);
//        
//        Either<String, Integer> either = Either.left("foo");
//        System.out.println("XXXXXXXXXXXXX");
//        System.out.println(either.left());
//        System.out.println("XXXXXXXXXXXXX");
//        System.out.println(either.right());
//        System.out.println("XXXXXXXXXXXXX");
//        if (either.isLeft()) {
//            System.out.println(either.left().get());
//        } else {
//            System.out.println(either.right().get());
//        }
//        System.out.println("XXXXXXXXXXXXX");
    }
   
    public static int syso(String s) {
        System.out.println(s);
        return 1;
    }
   
    @Value
    public static class IntHolder {
        Integer integer;
    }
    public static Integer getValue(Option<IntHolder> in) {
        return in.map(in2 -> in2.getInteger()).getOrElse(0);
    }
   
    public static void multiMapTest() {
        Multimap<Integer, Character> multiMap = LinkedHashMultimap.withSet().empty();
        for (int i = 0; i < 100; i++) {
            for (Character c : Integer.toString(i).toCharArray()) {
                multiMap = multiMap.put(i, c);
            }
        }

        for (Integer i : multiMap.keySet()) {
            syso(format("%d -> {%s}", i, multiMap.get(i).get()));
        }
    }

}