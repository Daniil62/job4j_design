package ru.job4j.chapter1.list.map;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import java.util.Calendar;

public class UserTest {

    @Test
    public void whenUsersDifferent() {
        User a = new User("Ivan", 2, new Calendar
                .Builder().setDate(1989, 1, 11).build());
        User b = new User("Philip", 2, new Calendar
                .Builder().setDate(1986, 15, 4).build());
        Assert.assertThat(a.equals(b), is(false));
        Assert.assertThat(a.hashCode() == b.hashCode(), is(false));
    }

    @Test
    public void whenOneOfObjectsNotUser() {
        User a = new User("Ivan", 2, new Calendar
                .Builder().setDate(1989, 1, 11).build());
        User b = null;
        Assert.assertThat(a.equals(new Thread()), is(false));
    }

    @Test
    public void whenOneOfUsersIsNull() {
        User a = new User("Ivan", 2, new Calendar
                .Builder().setDate(1989, 1, 11).build());
        User b = null;
        Assert.assertThat(a.equals(b), is(false));
    }

    @Test
    public void whenUsersSame() {
        User a = new User("Ivan", 2, new Calendar
                .Builder().setDate(1989, 1, 11).build());
        User b = new User("Ivan", 2, new Calendar
                .Builder().setDate(1989, 1, 11).build());
        Assert.assertThat(a.equals(b), is(true));
        Assert.assertThat(a.hashCode() == b.hashCode(), is(true));
    }
}
