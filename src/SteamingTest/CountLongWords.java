package SteamingTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        String contents = Files.readString(Paths.get("C:\\Users\\53137\\IdeaProjects\\learning\\alice.txt"));
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        for (String word : words) {
            if(word.length()>12) count++;
        }
        System.out.println(count);

        count = words.stream().skip(1).filter(word->word.length()>12).count();
        System.out.println(count);

        count = words.parallelStream().filter(word->word.length()>12).count();
        System.out.println(count);

    }
}
