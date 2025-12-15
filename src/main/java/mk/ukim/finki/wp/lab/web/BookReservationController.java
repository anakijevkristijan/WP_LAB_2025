package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping
    public String placeReservation(@RequestParam String bookTitle,
                                   @RequestParam String readerName,
                                   @RequestParam String readerAddress,
                                   @RequestParam long numCopies,
                                   HttpServletRequest req,
                                   Model model) {
        String ipAddress = req.getRemoteAddr();

        BookReservation reservation = bookReservationService.placeReservation(
                bookTitle, readerName, readerAddress, numCopies
        );

        model.addAttribute("readerName", readerName);
        model.addAttribute("ipAddress", ipAddress);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("numCopies", numCopies);

        return "reservationConfirmation";
    }
}
