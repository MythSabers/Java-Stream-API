package stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumbersAndLists {
    public static void main(String[] args) {
        // question 1: Find even and odd numbers from a list.
        List<Integer> q1 = IntStream.range(0, 100)
                .filter(s -> s % 2 == 0)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        // question 1: Find even and odd numbers from a list.
        List<Integer> q2 = IntStream.range(0, 100)
                .mapToObj(String::valueOf)
                .filter(s -> s.startsWith("1"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // question 3: Find duplicate elements in a list.
        List<Integer> q3 = Stream.concat(IntStream.range(0, 100).boxed(), IntStream.range(0, 20).boxed())
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(s -> s.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // question 4: Find unique elements in their original order.
        final List<Integer> numbers = Arrays.asList(5, 1, 2, 5, 3, 1, 4, 2);
        List<Integer> strictlyUnique = numbers
                .stream()
                .filter(i -> Collections.frequency(numbers, i) == 1)
                .collect(Collectors.toList());

        // 5. Find the first non-repeating element.
        List<Integer> elements = Arrays.asList(5, 1, 2, 5, 3, 1, 4, 2);
        elements.stream()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(s -> s.getValue() == 1)
                .findFirst()
                .map(s -> s.getKey())
                .ifPresent(System.out::println);

        // 6. Find the second-highest and second-lowest numbers.
        // 1. Find the second-lowest number
        Integer secondLowest = numbers.stream()
                .distinct()                    // Remove duplicates
                .sorted()                      // Sort in natural ascending order (0, 1, 2, 3...)
                .skip(1)                       // Skip the absolute lowest (0)
                .findFirst()                   // Grab the next element (1)
                .orElse(null);

        // 2. Find the second-highest number
        Integer secondHighest = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);

        // 7. Find the maximum and minimum numbers.
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

//        System.out.println(stats.getMax());
//        System.out.println(stats.getMin());

        // 8. Sort numbers in ascending and descending order.
        // ascending
        numbers.stream().sorted().forEach(s -> System.out.print(s + " "));
        // descending
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(s -> System.out.print(s + " "));

        // 9. Find the sum and average of a list.
        stats =
                numbers.stream().mapToInt(Integer::intValue).summaryStatistics();
        double average = stats.getAverage();
        long sum = stats.getSum();
        System.out.println(average + " " + sum);

        // 10. Partition numbers into even and odd groups.
        Map<Boolean, List<Integer>> collect = numbers.stream().collect(Collectors.partitioningBy(s -> s % 2 == 0));
        System.out.println(collect.get(true));
        System.out.println(collect.get(false));

        // 11. Find numbers whose squares exceed a given threshold.
        final int threshold = 4;
        List<Integer> result = numbers.stream()
                .filter(n -> (n * n) > threshold)
                .collect(Collectors.toList());
        System.out.println(result);

        // 12. Find the top three distinct numbers.
        Set<Integer> collect1 = numbers
                .stream()
                .distinct()
                .limit(3)
                .collect(Collectors.toSet());
        System.out.println(collect1);

        // 13. Merge two lists and remove duplicates.
        List<Integer> uniqueElements =
                Stream.concat(IntStream.range(0, 100).boxed(), IntStream.range(0, 20).boxed())
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(uniqueElements);

        // 14. Convert a list of integers into a comma-separated string.
        String str =
                numbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));
        System.out.println(str);

        // 15. Find common elements between two lists.
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);
        List<Integer> commonElements = list1.stream()
                .filter(list2::contains) // Directly checks presence in list2
                .distinct()              // Removes duplicates
                .collect(Collectors.toList());
        System.out.println(commonElements); // Output: [4, 5]
    }
}
