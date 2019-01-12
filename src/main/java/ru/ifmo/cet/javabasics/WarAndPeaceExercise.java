package ru.ifmo.cet.javabasics;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;

public class WarAndPeaceExercise {

    private static class Node{

        String key;
        Integer value;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

    }

    public static String warAndPeace() throws IOException {

        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        final Charset charset = Charset.forName("windows-1251");
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
        ArrayList<Node> nodes = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        StringBuilder res = new StringBuilder();
        nodes.sort((o1, o2) -> {
            if (o1.value.equals(o2.value)) return o1.key.compareTo(o2.key);
            return o2.value.compareTo(o1.value);
        });
        for (Node node : nodes){
            if(node.value >= 10) {
                res.append(node.key).append(" - ").append(node.value).append("\n");
            }
        }
        return res.toString().substring(0, res.length()-1);
    }

}