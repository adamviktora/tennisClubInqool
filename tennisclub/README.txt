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

- timeInterval represents minutes (for how long will the reservation take place)
