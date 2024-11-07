package org.iesvdm.kata1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Exercise5Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    public void partitionPetAndNonPetPeople()
    {
        //TODO
        // Obtain a partition over people with or without pets
        List<Person> partitionListPetPeople = this.people.stream()
                .filter(person -> person.isPetPerson())
                .toList();

        List<Person> partitionListNotPetPeople = this.people.stream()
                .filter(person -> !person.isPetPerson())
                .toList();

        Assertions.assertEquals(7, partitionListPetPeople.size());
        Assertions.assertEquals(1, partitionListNotPetPeople.size());
    }

    @Test
    @Tag("KATA")
    @DisplayName("getOldestPet - 🐶")
    public void getOldestPet()
    {
        //TODO
        // obtain the oldest pet
        Pet oldestPet = this.people.stream()
                .map(person -> person.getPets())
                .flatMap(pets -> pets.stream())
                .min((o1, o2) -> o2.getAge() - o1.getAge())
                .orElse(null);

        Assertions.assertEquals(PetType.DOG, oldestPet.getType());
        Assertions.assertEquals(4, oldestPet.getAge());
    }

    @Test
    @Tag("KATA")
    public void getAveragePetAge()
    {
        //TODO
        // obtain the average age of the pets
        double averagePetAge = this.people.stream()
                .map(person -> person.getPets())
                .flatMap(pets -> pets.stream())
                .mapToDouble(pet -> pet.getAge())
                .average()
                .orElse(0);

        Assertions.assertEquals(1.88888, averagePetAge, 0.00001);
    }

    @Test
    @Tag("KATA")
    public void addPetAgesToExistingSet()
    {
        //TODO
        // obtain the set of pet ages
        Set<Integer> petAges = this.people.stream()
                .map(person -> person.getPets())
                .flatMap(pets -> pets.stream())
                .map(pet -> pet.getAge())
                .collect(Collectors.toSet());


        var expectedSet = Set.of(1, 2, 3, 4); //no hay de edad 5
        Assertions.assertEquals(expectedSet, petAges);
    }

    @Test
    @Tag("KATA")
    @DisplayName("findOwnerWithMoreThanOnePetOfTheSameType - 🐹 🐹")
    public void findOwnerWithMoreThanOnePetOfTheSameType()
    {
        //TODO
        // obtain owner with more than one pet of the same type
        Person petOwner =  this.people.stream()
                .filter(p -> p.getPets().stream()
                .toList().size() != p.getPets().stream()
                .map(pet -> pet.getType())
                .distinct()
                .toList()
                .size())
                .findFirst()
                .orElse(null);;

        Assertions.assertEquals("Harry Hamster", petOwner.getFullName());
        //TODO
        // obtain the concatenation of the pet emojis of the owner
        Assertions.assertEquals("🐹 🐹", petOwner.getPets().stream().map(Pet::toString).collect(joining(" ")));
    }
}
