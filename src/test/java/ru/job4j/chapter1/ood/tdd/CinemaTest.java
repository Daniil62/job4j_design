package ru.job4j.chapter1.ood.tdd;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CinemaTest {

    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceInvalid() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.FEBRUARY, 11, 21, 0);
        cinema.buy(account, 1000, 1000, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenDateInvalid() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.FEBRUARY, 31, 21, 0);
        cinema.buy(account, 5, 5, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTryToByeSoldPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.FEBRUARY, 11, 21, 0);
        cinema.buy(account, 5, 5, date);
        cinema.buy(account, 5, 5, date);
    }

    @Test
    public void whenNothingFound() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> false);
        assertNull(sessions);
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Collections.singletonList(new Session3D())));
    }

    @Ignore
    @Test
    public void whenFindAfterAdding() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> false);
        assertNull(sessions);
        cinema.add(new Session3D());
        sessions = cinema.find(session -> true);
        assertThat(sessions, is(Collections.singletonList(new Session3D())));
    }
}