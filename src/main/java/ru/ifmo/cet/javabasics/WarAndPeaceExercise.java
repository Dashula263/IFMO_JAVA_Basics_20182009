package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;

public class WarAndPeaceExercise {

    public static String warAndPeace() throws IOException {

        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");        
        HashMap<String, Integer> map = new HashMap<>();
        Charset set = Charset.forName("windows-1251");
        List<String> lines = readAllLines(tome12Path, set);
        lines.addAll(readAllLines(tome34Path, set));
        String line = lines.toString();
        line = line.replaceAll("[^a-zA-Zа-яА-Я]", " ").toLowerCase();
        Stream.of(line.split(" "))
                .map(String::toString)
                .filter(word -> word.length() >= 4)
                .forEach(word -> map.put(word, map.getOrDefault(word, 0) + 1));
        ArrayList<Map.Entry<String, Integer>> nodes = new ArrayList<>(map.entrySet());
        nodes.sort(Comparator.comparing(Map.Entry::getKey));
        nodes.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        String res = "";
        res = nodes.stream()
                .filter(entry -> entry.getValue() > 9)
                .map(entry -> entry.getKey() + " - " + entry.getValue())
                .collect(Collectors.joining("\n"));
        return res.trim();
    }

}