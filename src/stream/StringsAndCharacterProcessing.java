package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringsAndCharacterProcessing {
    public static void main(String[] args) {
        // 16. Count the frequency of each character.
        String str = "aabcczddbsseeqweqwe";
        Map<Character, Long> collect = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(a -> a, Collectors.counting()));
        System.out.println(collect);

        // 17. Find the first non-repeating character in a string.
        str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(m -> m.getValue() == 1)
                .findFirst()
                .map(m -> m.getKey())
                .ifPresent(System.out::println);

        // 18. Find duplicate characters in a string.
        List<Character> duplicateList =
                str.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        ))
                        .entrySet()
                        .stream()
                        .filter(m -> m.getValue() > 1)
                        .map(m -> m.getKey())
                        .collect(Collectors.toList());
        System.out.println(duplicateList);

        // 19. Reverse each word in a sentence.
        String words = "I am a boy from";
        String reversedWords = Arrays.stream(words.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));
        System.out.println(reversedWords);

        // 20. Find the longest string in a list.
        Arrays.stream(words.split(" "))
                .max(Comparator.comparing(String::length))
                .ifPresent(System.out::println);

        // 21. Group words that are anagrams.
        Map<String, List<String>> collect1 = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat")
                .stream()
                .collect(Collectors.groupingBy(
                        a -> Arrays.stream(a.split(""))
                                .sorted()
                                .collect(Collectors.joining())
                ));
        System.out.println(collect1);
        // 22. Check whether a string is a palindrome.
        String word = "madams";
        boolean isPalindrome = IntStream.range(0, word.length() / 2)
                .allMatch(i -> word.charAt(i) == word.charAt(word.length() - 1 - i));
        System.out.println("IS PALINDROME " + isPalindrome);
        // 23. Count the frequency of each word in a sentence.
        LinkedHashMap<String, Long> collect2 = Arrays.stream(words.split(" "))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        System.out.println(collect2);
        // 24. Find the first character that appears only once.
        String input = "simplest";
        input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(m -> m.getValue() == 1)
                .findFirst()
                .ifPresent(System.out::println);
        // 25. Find the most frequent character.
        input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);
    }
}
