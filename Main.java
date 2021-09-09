package streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("TASK 1");
        List<String> list = Arrays.asList("df", "", "sfsfsf", "", "sfsfsf", "fffff");
        long countEmpty = list.stream()
                .filter(x -> x.length() == 0)
                .count();
        System.out.println("Пустих стрічок: " + countEmpty);
        long countMoreThanThree = list.stream()
                .filter(x -> x.length() > 3)
                .count();
        System.out.println("Стрічок де більше 3 символів: " + countMoreThanThree);
        long countStartWithS = list.stream()
                .filter(s -> s.startsWith("s"))
                .count();
        System.out.println("Стрічок які починаються з символа 's': " + countStartWithS);
        List<String> listWithoutEmpty = list.stream()
                .filter(x -> x.length() > 0)
                .collect(Collectors.toList());
        System.out.println(listWithoutEmpty);
        List<String> listMoreThanThreeSymbol = list.stream()
                .filter(x -> x.length() > 2)
                .collect(Collectors.toList());
        System.out.println(listMoreThanThreeSymbol);

        System.out.println("TASK 2");
        List<String> characters = Arrays.asList("Gandalf", "Aragorn", "Legolas", "Saruman", "Gollum", "Bilbo", "Frodo", "Gimli");
        List<String> charactersChanges = characters.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        String collect = charactersChanges.stream().collect(Collectors.joining(", "));
        System.out.println(collect);

        System.out.println("TASK 3");
        List<Integer> listOfNumbers = Arrays.asList(1, 19, 9, 10, 19, 3, 4, 7, 3, 4, 32, 11, 7);
        List<Integer> listOfSquareNumbers = listOfNumbers.stream()
                .distinct()
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println(listOfSquareNumbers);

        System.out.println("TASK 4");
        int max = listOfNumbers.stream()
                .max(Integer::compare).get();
        System.out.println("Максимальне: " + max);
        int min = listOfNumbers.stream()
                .min(Integer::compare).get();
        System.out.println("Мінімальне: " + min);
        int sum = listOfNumbers.stream()
                .mapToInt(x -> x)
                .sum();
        System.out.println("Сума: " + sum);
        OptionalDouble average = listOfNumbers.stream()
                .mapToDouble(x -> x)
                .average();
        System.out.println("Середнє арифметичне: " + average);

        LongSummaryStatistics stats = listOfNumbers.stream()
                .mapToLong(x -> x)
                .summaryStatistics();
        System.out.format("    min: %d%n", stats.getMin());
        System.out.format("    max: %d%n", stats.getMax());
        System.out.format("  count: %d%n", stats.getCount());
        System.out.format("    sum: %d%n", stats.getSum());
        System.out.format("average: %.1f%n", stats.getAverage());
    }
}

