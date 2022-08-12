package ru.msaggik.spring.dao;

import org.springframework.stereotype.Component;
import ru.msaggik.spring.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    private static int PEOPLE_COUNT; // id пользователя
    private List<Person> people; // импровизация БД
    // блок инициализации
    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@mail.abc"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 52, "bob@mail.abc"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 18, "mike@mail.abc"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 34, "katy@mail.abc"));
    }

    public List<Person> index() {
        return people; // возврат всех пользователей из списка
    }

    public Person show(int id) {
        // возврат пользователя по его id (используется лямбда выражение,
        // "(person -> person.getId() == id).findAny()" - поиск пользователя по id,
        // "orElse(null)" - если пользователь не найден, то возвращается null
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT); // назначение id новому пользователю
        people.add(person); // добавление нового пользователя
    }
    // обновление данных пользователя
    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id); // поиск нужного пользователя
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }
    // удаление данных пользователя
    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
