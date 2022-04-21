package Stream;

import java.util.*;
import java.util.stream.Collectors;

public class Sample {
    List<Person> personList = Arrays.asList(
            new Person(1, "Michael", "Robert", 37, "TR"),
            new Person(2, "Mary", "Patricia", 11, "EN"),
            new Person(3, "John", "Michael", 7, "FR"),
            new Person(4, "Jennifer", "Linda", 77, "TR"),
            new Person(5, "William", "Elizabeth", 23, "US"),
            new Person(6, "Sue", "Jackson", 11, "IT"),
            new Person(7, "Michael", "Tommy", 37, "EN")
    );

    public static void main(String[] args) {
        Sample sample = new Sample();
        sample.test10();
    }

    //Перебор всех элементов userList с помощью forEach() и вывод их в консоль.
    private void test1() {
        System.out.println("Test 1");
        personList.forEach(System.out::println);
    }

    //Перебор всех элементов с выполнением некоторой операции над каждым элементом списка и вывод их на консоль.
    private void test2() {
        System.out.println("Test 2");
        personList.stream()
                .map(p -> {
                    return new Person(
                            p.getId(),
                            "X " + p.getFirstName(),
                            "Y " + p.getLastName(),
                            p.getAge() + 10,
                            p.getNationally());
                })
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    //Сортировка списка по свойству age.
    private void test3() {
        System.out.println("Test 3");
        personList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(System.out::println);

    }

    //Сортировка списка по нескольким свойствам: age, firstName, lastName.
    private void test4() {
        System.out.println("Test 4");
        personList.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getFirstName)
                        .thenComparing(Person::getLastName))
                .forEach(System.out::println);
    }

    //Вычисление среднего возраста (age) и максимальной длины firstName.
    private void test5() {
        System.out.println("Test 5");
        double averageAge = personList.stream()
                .mapToInt(Person::getAge)
                .summaryStatistics()
                .getAverage();
        System.out.println("averageAge: " + averageAge);
        int maxFirstNameLength = personList.stream()
                .mapToInt((value) -> {
                    return value.getFirstName().length();
                })
                .summaryStatistics()
                .getMax();
        System.out.println("maxFirstNameLength " + maxFirstNameLength);
    }

    //Проверка, что у всех User возраст (age) больше 6.
    private void test6() {
        System.out.println("Test 6");
        boolean isAllAgesGreaterThan6 = personList.stream()
                .allMatch(Person -> Person.getAge() > 5);
        System.out.println("isAllAgesGreaterThan6 " + isAllAgesGreaterThan6);
    }

    //Проверка, есть ли кто-то с firstName, начинающийся с символа S
    private void test7() {
        System.out.println("Test 7");
        boolean isFirstCharS = personList.stream()
                .anyMatch(Person -> Person.getFirstName().charAt(0) == 'S');
        System.out.println("isFirstCharS: " + isFirstCharS);
    }

    //Преобразование одной коллекцию в другую.
    private void test8() {
        System.out.println("Test 8");
        List<Person> list = personList.stream()
                .collect(Collectors.toList());
        Set<Person> set = personList.stream()
                .collect(Collectors.toSet());
        List<Person> linkedList = personList.stream()
                .collect(Collectors.toCollection(LinkedList::new));
        Map<Long, Person> map = personList.stream()
                .collect(Collectors.toMap(Person -> Person.getId(), Person -> Person));
    }

    //Количество разных национальностей (nationality).
    private void test9() {
        System.out.println("Test 9");
        long countDifferentNationals = personList.stream()
                .map(Person::getNationally)
                .distinct()
                .count();
        System.out.println("countDifferentNationals: " + countDifferentNationals);
    }

    //User старше 10 лет, у которых первый символ firstName не равен M.
    private void test10() {
        System.out.println("Test 10");
        personList.stream()
                .filter(p -> (p.getFirstName().charAt(0) != 'M'))
                .filter(p -> (p.getAge() > 10))
                .forEach(System.out::println);
    }
}
