package ru.job4j.chapter1.generic.store;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class MemStoreTest {

    private MemStore<User> userStore = new MemStore<>();
    private final String userFirst = "first user";
    private final String userSecond = "second user";
    private final String userThird = "third user";
    private User secondUser = new User(userSecond);
    private User thirdUser = new User(userThird);

    @Before
    public void setup() {
        userStore.add(new User(userFirst));
        userStore.add(secondUser);
        userStore.add(thirdUser);
    }

    @Test
    public void whenReplaceSuccessful() {
        User user = new User("");
        assertThat(userStore.replace(userFirst, user), is(true));
        assertThat(userStore.findById(userFirst), is(user));
    }

    @Test
    public void whenReplaceFailed() {
        User user = new User("new user");
        assertThat(userStore.replace("fourth user", user), is(false));
    }

    @Test
    public void whenDeleteSuccessful() {
        assertThat(userStore.delete(userSecond), is(true));
        assertThat(userStore.findById(userSecond), is(Matchers.nullValue()));
    }

    @Test
    public void whenDeleteFailed() {
        assertThat(userStore.delete("some id"), is(false));
    }

    @Test
    public void whenFindByIdSuccessful() {
        assertThat(userStore.findById(userSecond), is(secondUser));
        assertThat(userStore.findById(userThird), is(thirdUser));
    }

    @Test
    public void whenFindByIdFailed() {
        assertThat(userStore.findById("some id"), is(Matchers.nullValue()));
    }
}
