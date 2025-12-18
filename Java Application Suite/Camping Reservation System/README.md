 ## Camping Reservation System

 This program lets users interact with a fictional National Park booking
 system using simple text commands:
 
 * **list** – displays all campgrounds and all campsites loaded from text files
 * **reserve** – guides the user through creating a new campsite reservation, including campsite selection, date range, and party size
 * **my** – shows all reservations that have been saved in the reservations file
 * **quit** – exits the application
   
 The program validates user input, enforces campground rules (capacity limits, maximum stay, and date rules), checks for overbooking       against existing reservations, and writes confirmed reservations to a file for later viewing.
 It also uses custom exceptions to report clear error messages when input or business rules are violated.

