package restAssured.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseClass {

    private String bookingid;
    private BookingObject booking;

    public BookingResponseClass() {
        // This constructor is only added for deserialization purposes
        // It should not be called directly
    }

    public BookingResponseClass(String bookingid, BookingObject booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    @Getter
    @Setter
    public static class BookingObject {
        private String firstname;
        private String lastname;
        private Number totalprice;
        private Boolean depositpaid;
        private BookingDateObject bookingdates;
        private String additionalneeds;


        public BookingObject() {
            // This constructor is only added for deserialization purposes
            // It should not be called directly
        }

        public BookingObject(String firstname, String lastname, Number totalprice, Boolean depositpaid, String additionalneeds, BookingDateObject bookingDateObject) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.totalprice = totalprice;
            this.depositpaid = depositpaid;
            this.bookingdates = bookingDateObject;
            this.additionalneeds = additionalneeds;
        }

        @Getter
        @Setter
        public static class BookingDateObject {
            private Date checkin;
            private Date checkout;

            public BookingDateObject() {
                // This constructor is only added for deserialization purposes
                // It should not be called directly
            }

            public BookingDateObject(Date checkin, Date checkout) {
                this.checkin = checkin;
                this.checkout = checkout;
            }
        }
    }
}
