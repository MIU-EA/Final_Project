# Team 1 EA Project

### Requirements
* This system has a database of users and roles and based on roles, users will be able to perform actions.
* This system supports three roles: Admin, Counselor and Customer.
* A “Person” is a user of the system, we need to keep track of person ID, first and last name, email
address and obviously username, password and set of roles. Person can have multiple roles.
Appointments are made for “Sessions”. Each session has a date, start time, duration (in
minutes), number of seats (capacity), location and counselor (the person leading the session).
* “Appointments” connect persons to sessions. Remember that in general, each person can make
multiple appointments.
* Each session can have multiple “AppointmentRequests”, but in the end, only one of these
requests can be approved as an “Appointment”. If an appointment gets cancelled, the first
* AppointmentRequest from the list can fill its position.
* Appointments can only be changed for future sessions. Past sessions cannot be changed by
anyone
* AppointmentRequests can only be made for future sessions.
* Admins can create and manage (CRUD operations) “Sessions”.
* Each person can create new appointments for himself/herself and manage his/her existing
appointments. Appointments can be cancelled or modified up to 48 hours before the session
after that only admin can make changes.
* Send an email (to the creator of appointment, counselor and customer), each time an
appointment is created or modified.

