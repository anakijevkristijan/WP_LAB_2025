package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.BookReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {

    private final List<BookReservation> reservations = new ArrayList<>();

    @Override
    public BookReservation save(BookReservation reservation) {
        reservations.add(reservation);
        return reservation;
    }
}
