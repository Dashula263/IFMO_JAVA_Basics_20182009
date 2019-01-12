package ru.ifmo.cet.javabasics;

import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import static java.nio.file.Files.readAllLines;

public class WarAndPeaceExercise {

    private static class Node implements Comparable<Node>{

        private String key;
        private Integer value;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            if(node.value.equals(this.value)) return this.key.compareTo(node.key);
            return node.value - this.value;
        }
    }

    public static String warAndPeace() throws IOException {

        final Path tome12Path = Paths.get("src", "main", "resources", "WAP12.txt");
        final Path tome34Path = Paths.get("src", "main", "resources", "WAP34.txt");
        HashMap<String, Integer> map = new HashMap<>();
        Charset set = Charset.forName("windows-1251");
        List<String> lines = readAllLines(tome12Path, set);
        lines.addAll(readAllLines(tome34Path, set));
        for (String line : lines) {
            line = line.replaceAll("[^a-zA-Zа-яА-Я]", " ");
            for (String word : line.split(" ")) {
                if (word.length() >= 4) {
                    word = word.toLowerCase();
                    if (map.containsKey(word)) {
                        map.put(word, map.get(word) + 1);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
        }
        ArrayList<Node> nodes = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        StringBuilder res = new StringBuilder();
        Collections.sort(nodes);
        for (Node node : nodes){
            if(node.value >= 10) {
                res.append(node.key).append(" - ").append(node.value).append("\n");
            }
        }
        return res.toString().substring(0, res.length()-1);
    }

}