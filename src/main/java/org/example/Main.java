package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = getPeople();

        // Imperative approach (zorunlu yaklaşım)
        List<Person> females = new ArrayList<>();
        for(Person person : people){
            if(person.getGender().equals(Gender.FEMALE)){
                females.add(person);
            }
        }
        //---print this to console
//         females.forEach(System.out::println);


        // Declarative approach (Bildirimsel yaklaşım)

        // Filter -> listeyi filtrelemek için
        List<Person> filterFemales = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
//        filterFemales.forEach(System.out::println);

        // Sort -> listeyi sıralamak için
        List<Person> sortedList = people.stream()
                .sorted(Comparator.comparing(Person::getAge)) // Yaşa göre sıraladık tersden yapmak için .reversed() ekle
                .collect(Collectors.toList());
//        sortedList.forEach(System.out::println);

        // All match -> boolean döndürür bütün listeyle şartı karşılaştırır
        boolean allM = people.stream()
                .allMatch(person -> person.getAge() > 2);
//        System.out.println(allM);

        // Any match -> all match'den farklı olarak en az bir liste üyesinin şartı sağlaması yeter
        boolean anyM = people.stream()
                .anyMatch(person -> person.getAge() > 4);
//        System.out.println(anyM);

        // None match -> listede hiç eşleşmeyen bir şart olduğunda (true) döndürür
        boolean none = people.stream()
                .noneMatch(person -> person.getName().equals("Goetia"));
//        System.out.println(none);

        // Max -> Örneğin listedeki en büyük yaşı çekmek için
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
//                .ifPresent(person -> {
//                    System.out.println(person);
//                });

        // Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        System.out.println();

        // Group -> örneğin Listeyi female ve male olarak gruplandırmak istersek
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });


        // Stream'in bir diğer güzel özelliği zincir yapısını kullanabiliriz. Örneğin;
        // En büyük yaşa sahip erkek birey'in ismini konsola bastırmak için
        Optional<String> oldestMale= people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestMale.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {

        return List.of(
                new Person("Abdullah",27,Gender.MALE),
                new Person("Asya",4,Gender.FEMALE),
                new Person("Alcor",27,Gender.MALE),
                new Person("Eray",3,Gender.MALE)
        );
    }
}