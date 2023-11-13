package SteamingTest;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingStreams {
    public static <T> void show(String title, Stream<T> stream){
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE + 1).toList();
        System.out.print(title+": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if(i>0) System.out.print(", ");
            if(i<SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path of = Path.of("C:\\Users\\53137\\IdeaProjects\\learning\\alice.txt");
        String contents = Files.readString(of);
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words",words);

        Stream<String> song = Stream.of("gently","down","the","stream");
        show("song",song);

        Stream<String> silence = Stream.empty();
        show("silence",silence);

        Stream<String> echo = Stream.generate(()->"Echo");
        show("echo",echo);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms",randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE,n->n.add(BigInteger.ONE));
        show("integers",integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay",wordsAnotherWay);

        try (Stream<String> lines = Files.lines(of, StandardCharsets.UTF_8)){
            show("lines",lines);
        }
    }
}
