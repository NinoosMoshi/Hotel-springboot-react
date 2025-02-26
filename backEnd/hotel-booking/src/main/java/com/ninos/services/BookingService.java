package com.ninos.services;

import com.ninos.dtos.BookingDTO;
import com.ninos.dtos.Response;
import com.ninos.entities.Booking;

public interface BookingService {

    Response createBooking(BookingDTO bookingDTO);
    Response findBookingByReference(String bookingReference);
    Response updateBooking(BookingDTO bookingDTO);

}
