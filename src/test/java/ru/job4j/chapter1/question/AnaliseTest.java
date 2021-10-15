package ru.job4j.chapter1.question;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnaliseTest {

    @Test
    public void whenNotChanges() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3);
        assertThat(Analise.diff(previous, current), is(new Info(0, 0, 0)));
    }

    @Test
    public void whenOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, new User(2, "BB"), u3);
        assertThat(Analise.diff(previous, current), is(new Info(0, 1, 0)));
    }

    @Test
    public void whenOneDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u3);
        assertThat(Analise.diff(previous, current), is(new Info(0, 0, 1)));
    }

    @Test
    public void whenOneAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"));
        assertThat(Analise.diff(previous, current), is(new Info(1, 0, 0)));
    }

    @Test
    public void whenOneAddedOneChangedOneDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(
                new User(1, "AA"),
                u2,
                new User(4, "D"));
        assertThat(Analise.diff(previous, current), is(new Info(1, 1, 1)));
    }

    @Test
    public void whenTwoAddedOneChangedTwoDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(
                new User(1, "AA"),
                new User(0, "S"),
                new User(4, "D"));
        assertThat(Analise.diff(previous, current), is(new Info(2, 1, 2)));
    }
}
