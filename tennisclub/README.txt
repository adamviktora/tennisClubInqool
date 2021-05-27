This application was made using Spring Initializr, it uses lombok to reduce constructors, getters and setters code, spring web for web requests, spring data JPA for easy communication with a database. It uses a MySQL database, database "tennis_club" was created before using this app. Tables will be generated automatically. Sample data for testing to "court" and "surface" tables were added manually through MySQL Workbench.

REST endpoints:

@Get
tennis_club/courts

@Get
tennis_club/reservations/court_id={court_id}

@Get
tennis_club/reservations/phone_number={phone_number}

@Post
tennis_club/new_reservation

POST request in JSON format should look like this:
{
	"courtId": (int),
	"phoneNumber": (String),
	"userName": (String),
	"playingDoubles": (boolean),
	"date": (Date in "YYYY-MM-DD" format),
	"time": (Time in "hh:mm:ss" format),
	"timeInterval": (int)
}

- returns -1 if reservation cannot be done (court is already taken at specified time), otherwise returns the price of this reservation
- timeInterval represents minutes (for how long will the reservation take place)
- if phoneNumber exists in a table of users and new username is different than the one in the table, it is updated
