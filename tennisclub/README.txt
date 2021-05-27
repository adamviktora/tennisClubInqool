REST endpoints:

@Get
courts

@Get
reservations/court_id={court_id}

@Get
reservations/phone_number={phone_number}

@Post
new_reservation

{
	"court_id": ?,
	"doubles": ?,
	"phone_number": ?,
	"user_name": ?
}
