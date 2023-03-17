import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        /* Streams */

        /* Intermedios: Realizar transformaciones sobre los datos
           Finales: Realizar alguna acción o convertir tipos*/

        //forEach(Consumer<>action)(Final)
        //Este operador lo vamos a poder utilizar para recorrer el flujo de datos y hacer algo con los elementos,
        // pero no modifica el flujo de datos ni devuelve nada.
        //Como podemos ver este operador recibe un Consumer (expresión lambda).
        Stream<String> flujo = Stream.of("Hola", "Adios", "Hello", "Goodbye", "Thanks", "Abbey Road");
        flujo.forEach(System.out::println);
        System.out.println("----------");


        //map(Function<? super String, ? extends String> mapper) (intermedio)
        /* Con este operador sí que vamos a poder realizar modificaciones sobre nuestro flujo de datos.
          Este recibe una Function (expresión lambda) que recibe un parámetro y devuelve otro.*/
        Stream<String> flujo1 = Stream.of("Hola", "Adios", "Hello", "Goodbye", "Thanks", "Abbey Road");
        flujo1.map(e -> e.toUpperCase()).forEach(System.out::println);
        System.out.println("------------");


        //peek(Consumer<? super String> action) (Intermedio)
        //este operador no devuelve nada y lo vamos a poder utilizar para inspeccionar los elementos
        // a la hora de trabajar con otros operadores intermedios.
        Stream<String> flujo2 = Stream.of("Hola", "Adios", "Hello", "Goodbye", "Thanks", "Abbey Road");
        flujo2.peek(System.out::println).map(e -> e.toUpperCase()).forEach(System.out::println);
        System.out.println("------------");


        //collect(Collectors.toList()) (Final)
        //Ahora vamos a ver el collect() que nos va a servir para cuando queramos pasar nuestro stream
        // a, por ejemplo, una lista.
        Stream<String> flujo3 = Stream.of("Hola", "Adios", "Hello", "Goodbye", "Thanks", "Abbey Road");
        List<String> result = flujo3.peek(System.out::println).map(e -> e.toUpperCase()).collect(Collectors.toList());
        result.forEach(System.out::println);

        String clase = result.getClass().toString();
        System.out.println(clase);


        //filter(Predicate <? super Usuario> predicate) (Intermedio)
        /* Aplicar filtros a los datos del flujo.
        Este recibe una expresión Lambda de tipo Predicate que evalúa si el dato cumple la expresión booleana. */
        /* + */
        //findAny / findFirst (Final) (Tras una expresion Lambda 'Predicate'
        //        // nos devuelve el objeto encontrado - objeto Optional-)

         /*Usuario userEncontrado = Stream
                .of("Javier Martínez", "Javier Gomez", "Gonzalo Parrilla", "Alberto Manrique")
                .map(nombre -> nombre.split(" "))
                .map(nombre -> new Usuario(nombre[0], nombre[1]))
           .filter(u -> u.getNombre().equals("Javier"))
           .findAny().get();

           System.out.println(userEncontrado);*/


        //anyMatch(Predicate<? super Usuario> predicate) (Final)
        //Este operador obtiene una expresión lambda Predicate para evaluar si en el flujo de datos hay alguno
        // que coincida con la expresión y en función de eso devolverá true o false.
        //En el momento que encuentre alguno que coincida con la expresión deja de evaluar el resto.

        List<String> lista =
                List.of("Hola", "Mundo", "Java", "Programacion", "Concurrente", "Streams", "Java8", "Java11", "Java12");
        Stream<String> stream = lista.stream(); //Convertimos la lista
        boolean existe = stream.anyMatch(s -> s.equals("Hola")); //Comprobamos si existe un elemento que coincida con el patron
        System.out.println(existe); //True


        //  count() (Final)
        //Este operador, como su nombre indica, nos va a servir para contar la salida de un operador intermedio,
        // por ejemplo la salida de un filter.
        //Es muy sencillo su funcionamiento. Si controlamos SQL entonces sabremos por dónde van los tiros
        // porque funciona de la misma forma.


        //reduce() (Final)
        //Este operador lo utilizaremos para reducir un conjunto de elementos en un solo resultado.
        List<String> listaR = List.of("Javier Osma", "Angela Barragan", "Alberto González","Carlos Sheen");
        Stream<String> streamR = listaR.stream(); //Convertimos la lista

        String resultado = streamR.reduce("El resultado es = ", String::concat);
        System.out.println(resultado);

        List<Integer> listaNum = List.of(3, 5, 3, 6, 2, 32, 5, 67, 2, 23, 6, 3, 32, 5, 32);
        Stream<Integer> streamNum = listaNum.stream(); //Convertimos la lista

        int resultadoNum = streamNum.reduce(0, (a, b) -> a + b);
        //stream.reduce(0, Integer::sum);
        System.out.println(resultadoNum);




    }
}
