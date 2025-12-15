package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReservation {
    String bookTitle;
    String readerName;
    String readerAddress;
    Long numberOfCopies;
}
