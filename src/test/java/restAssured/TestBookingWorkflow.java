package restAssured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restAssured.pojos.BookingResponseClass;
import restAssured.utils.BookingCustomer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestBookingWorkflow {
    //mvn test -Dtest=BookingWorkflowTestClass to run all tests from this class

    private RequestSpecification requestSpec;

    @BeforeEach
    public void createRequestSpecification() {
        // Create a basic request specification for further use
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                setContentType(ContentType.JSON).
                setRelaxedHTTPSValidation().
                build();
    }

    @Test
    public void checkFirstBookingInfo() {
        //GIVEN
        String firstBookingId = getFirstBookingId();

        //WHEN
        BookingResponseClass.BookingObject myBooking = given().
                spec(requestSpec).
                pathParam("id", firstBookingId).
                when().
                get("booking/{id}").
                getBody().
                as(BookingResponseClass.BookingObject.class);

        //THEN
        Assertions.assertNotNull(myBooking);
        Assertions.assertEquals("John", myBooking.getFirstname());
        Assertions.assertEquals("Smith", myBooking.getLastname());
    }

    @Test
    public void checkCreatedBooking() {
        //GIVEN
        // Create a new booking
        BookingResponseClass myNewBooking = given().
                spec(requestSpec).
                and().
                body(createRequestBody(BookingCustomer.FIRST_CUSTOMER.getFirstName(), BookingCustomer.FIRST_CUSTOMER.getLastName())).
                when().
                post("/booking").
                as(BookingResponseClass.class);

        // Make sure the returned bookingId is not empty/null
        Assertions.assertFalse(myNewBooking.getBookingid().isEmpty());

        //WHEN
        // Search for the newly created booking using the returned id
        BookingResponseClass.BookingObject myBooking = given().
                spec(requestSpec).
                pathParam("id", myNewBooking.getBookingid()).
                when().
                get("booking/{id}").
                getBody().
                as(BookingResponseClass.BookingObject.class);

        //THEN
        // Assert that the booking details match
        Assertions.assertEquals("Albert", myBooking.getFirstname());
        Assertions.assertEquals("Einstein", myBooking.getLastname());
        Assertions.assertEquals(1000, myBooking.getTotalprice());
        Assertions.assertEquals(true, myBooking.getDepositpaid());
        Assertions.assertEquals("Breakfast", myBooking.getAdditionalneeds());
        Assertions.assertNotNull(myBooking.getBookingdates());
    }

    private BookingResponseClass.BookingObject createRequestBody(String firstName, String lastName) {
        // Create the request body to be sent
        BookingResponseClass.BookingObject bookingObject = new BookingResponseClass.BookingObject();
        BookingResponseClass.BookingObject.BookingDateObject bookingDates = new BookingResponseClass.BookingObject.BookingDateObject();

        bookingDates.setCheckin(new Date(LocalDateTime.now().getDayOfMonth()));
        bookingDates.setCheckout(new Date(LocalDateTime.now().plusDays(7).getDayOfMonth()));

        bookingObject.setFirstname(firstName);
        bookingObject.setLastname(lastName);
        bookingObject.setTotalprice(1000);
        bookingObject.setDepositpaid(true);
        bookingObject.setBookingdates(bookingDates);
        bookingObject.setAdditionalneeds("Breakfast");

        return bookingObject;
    }

    private String getFirstBookingId() {
        // Create the request to get all bookings available
        List<BookingResponseClass> allBookings = given().
                spec(requestSpec).
                when().
                get("/booking").
                getBody().
                as(new TypeRef<List<BookingResponseClass>>() {
                });

        // Return the first booking id found for further use
        Assertions.assertFalse(allBookings.isEmpty());
        return allBookings.get(0).getBookingid();
    }

}
