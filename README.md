# Movie Ticket Booking Management System

---

A movie ticket booking & management application written in Java.

## Table of Contents

---
* [General Info](#general-info)
* [Setup](#setup)
* [Run the Program](#run-the-program)
* [Functionalities and Usages](#functionalities-and-usages)
* [Tests](#tests)
* [Project Contributors](#project-contributors)
* [Reference](#reference)


---

## General Info

---
This project is a simple ticket booking & management system. 

You are viewing the sprint 3 ver. 3.2. 

This is the final version of the Movie Ticket Booking Management System.



## Setup

---
1. ### **Installing Gradle**
- **Windows**

  Firstly, generate the necessary gradle wrapper files:

      gradle wrapper

  Then run this command:

      ./gradlew wrapper --gradle-version 4.2

  Now, you must use the “gradlew” program in the current folder to build your tasks under this new specified version.
  The version may be changed.

  For more information on gradle wrapper, please refer to this link:
  https://docs.gradle.org/current/userguide/gradle_wrapper.html



- **Mac OS / Linux**

  Firstly, please check your Java version in your machine (you will need Java JDK version 7 or higher):

      java -version

  You should see something like this:

      java 9.0.4
      Java(TM) SE Runtime Environment (build 9.0.4+11)
      Java HotSpot(TM) 64-Bit Server VM (build 9.0.4+11, mixed mode)

  To install Gradle in your own machine, you can find the detailed instruction here:
  https://gradle.org/install/

2. ### Connect to pgadmin4

   The link to the pgadmin4 instance connected to our shared database is:

    ```
    http://13.211.7.238/pgadmin4/browser/
   ```

   Username: alie0302@uni.sydney.edu.au
   
   Password: triceratops

    #### Personal database:

   If you don't want to use the shared database we provided, you can use your local database.
  We have provide you the database schema in `moviebooking_schema.sql`
   
    To connect to your local database Please change all the database connection into the following
format:

    ```
    dbInstance =  new Database("jdbc:postgresql://localhost:<port>/<database_name>", "<username>", "<password>");
    ```
  

## Run the Program

---
Firstly, please run the command:

`gradle build`

If build successful, you will see something like this:

```
BUILD SUCCESSFUL in 13s
7 actionable tasks: 7 executed
```

To run the program, please use the command:

`gradle run --console=plain`

If run successful, you will see the greeting:

```
> Task :app:run
                                                                     
       Welcome to Fancy Cinemas Official Website!!

    If you have an account, please sign in (｡･ω･｡)ﾉ 
    If you haven't joined us, you can sign up today! o(｀ω´ )o

    Enter 1 for "Log in"            Enter 2 for "Sign up"                                                                  

```
Now, you can use the application :)

## Functionalities and Usages

---

- ### **User's Interface**

**The User's interface we made for the application is CLI.** 

Guest User Interface (Default Page)
  
The program will start with guest user interface as the default page where
user can view the list of the upcoming movies. They can also choose to view the list of movies which are now showing.
  They can filter upcoming movies or movies which are now showing through cinema name or screen size. They are able to view
  the movie details by typing in the movie name. They can also choose to log in or sign up at the default page. 
  They won't be able to book movie ticket at the default page.
  
```
    Welcome to Fancy Cinemas Official Website!!

    If you have an account, please sign in (｡･ω･｡)ﾉ 
    If you haven't joined us, you can sign up today! o(｀ω´ )o

    Enter 1 for "Log in"            Enter 2 for "Sign up"                                    

=====================================================================
    Enter 3 for "Now Showing"       Enter 4 for "Filter"
    Enter movie name for more details
=====================================================================

<<Upcoming Movies!>>
Sooryavanshi [PG] 2021-11-05
Black Box [M] 2021-11-05

=====================================================================
You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ 
Enter 1 for "Log in"
Enter 2 for "Sign up"
=====================================================================
```
Customer Interface:

If the user logged in as a customer, the system will bring them to the customer interface. In customer interface
has the same functionalities as the default page, additionally, it will allow user to book movie tickets.
Despite customers are able to choose the "booking" option, there are some issue related to booking and payment remain unsolved in this version,
user could meet booking or payment failure. This will be fixed in the next sprint.

```
Logging in as customer...
Welcome Andy!

======================================================
Enter 1 for "Now Showing"   Enter 2 for "Filter"
Enter 3 for "Booking"
Enter movie name for more details
Enter 5 for "Log out"
======================================================

<<Upcoming Movies!>>
Sooryavanshi [PG] 2021-11-05
Black Box [M] 2021-11-05
```

Staff Interface:

If the user logged in as a staff/ manager, the system will bring them to the staff interface.

(Not fully implemented yet)


- ### **Log in**

Users who have made their account can log in at the default page. They have to enter a valid username
and password otherwise an error message will be printed out. They will be able to enter the username and
the password for unlimited times, but they can choose to return to the default page by type in `back`.
The password entered by the user will be hidden for security.

```
Please enter your username:
Andy
Please enter your password:
                                                                                                                                                                              
Incorrect username or password (｡´︿`｡)
Please try again
If you wish to return to the default page, please enter "back"

Please enter your username:                                                                                                                                  
```


If log in successfully, the system will identify the user's identity and bring them to the 
right interface:

* If logged in as a customer:

```                                                                          
Logging in as customer...
Welcome Andy!

======================================================
Enter 1 for "Now Showing"   Enter 2 for "Filter"
Enter 3 for "Booking"
Enter movie name for more details
Enter 5 for "Log out"
======================================================

<<Upcoming Movies!>>
Sooryavanshi [PG] 2021-11-05
Black Box [M] 2021-11-05
```
* If logged in as a staff: (Not implemented correctly at this stage.)
```                                                                          
Logging in as staff...
Welcome Pluto!
Note: To quit, Enter "-Exit-"
1.Modify Movie 2.Add/Remove Gift Card 3.Generate Upcoming Movie Report
Enter the number to operate: 
Staff operations exits.
```

* If logged in as a manager: (Not implemented correctly at this stage.)
```                                                                          
Logging in as manager...
Welcome Nat!
Note: To quit, Enter "-Exit-"
1.Modify Movie 2.Add/Remove Gift Card 3.Generate Upcoming Movie Report
Enter the number to operate: 
Staff operations exits.
```

Note: The user's interface will be fully implemented in next sprint.

- ### **Sign up**

The user can choose to sign up. The username they created has to be unique in the
database. Therefore, if the username they provided has already existed, an error msg 
will be printed out. They will be able to enter multiple times until the username
they created is unique.

```
Please create your username:
Pluto
==========================================================
User name already existed. Please enter again (｡´︿`｡)
==========================================================
```
Once they created their unique username, they will be asked to create their password. A valid password
has to be over 4 character. If the user enter the invalid password, an error message will be shown:

```
Please create your username:
Judy
Please create your password:
                                                                                                                                                                            
Password has to be longer than 4 characters! (｡´︿`｡) Please try again

```
Only three attempts allowed, if user exceed the limit, they will be taken back to the default page:

```
============================================
Sign up failed (｡´︿`｡)
============================================
Returning...
```

After they have created the valid password,they will be asked to enter the password they just created again.
If not matching, an error message will be shown, they will be able to create their password again:

```
Please enter your password again:
                                                                                                                                                                            
Please create your password:

Password not matching! (｡´︿`｡)

Please create your password:

```

Only three attempts allowed, if user exceed the limit, they will be taken back to the default page:

```
============================================
Sign up failed (｡´︿`｡)
============================================
Returning...
```

After they have successfully set their username and password, they will be asked to choose their identity type:

```
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
Enter 3 - for "Manager"
```
If invalid input received, an error message will be print out:

```
============================================
Wrong Input! (｡´︿`｡)
============================================
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
Enter 3 - for "Manager"
```

Only three attempts allowed for invalid input. If the user exceed the limit, they will be 
brought back to the default page:

```
============================================
Wrong Input! (｡´︿`｡)
============================================

============================================
Sign up failed (｡´︿`｡)
============================================

Returning...
```
If the user chose to sign up as a customer, they will be automatically logged into customer interface:

```
Congratulations! You have made your account (｡･ω･｡)ﾉ
Logging in as customer...
Welcome billie!
```
If the user choose to sign up as a staff/ manager, they will be asked to provide the staff code `shawshank` for staff and `zootopia` for manager. If they
provided the wrong passcode, an error message will be printed out. They have two attempts in total, and then
they will be asked to choose their identity again (in case the user is actually a customer/manager but 
accidentally choose the wrong identity.). 

```
2
Please enter the staff code:
                                                                                                                                                                            Wrong Passcode.
Please enter the staff code:
                                                                                                                                                                            Wrong Passcode.
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
Enter 3 - for "Manager"
```

The whole process can only iterate three times, after three attempts, the sign up request will be
cancelled. The user will be taken back to the default page:

```
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
Enter 3 - for "Manager"
2
Please enter the staff code:

Wrong Passcode.                                                                                                                                                                         
Please enter the staff code:

Wrong Passcode.                                                                                                                                                                          
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
Enter 3 - for "Manager"
2
Please enter the staff code:

Wrong Passcode.                                                                                                                                                                            
Please enter the staff code:

Wrong Passcode.                                                                                                                                                                          
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
Enter 3 - for "Manager"
3
Please enter the staff code:

Wrong Passcode.                                                                                                                                                                            
Please enter the staff code:

Wrong Passcode.                                                                                                                                                                        

============================================
Sign up failed (｡´︿`｡)
============================================

Returning...
```

If nothing went wrong, the user will be signed up successfully, and the user's detail
will be recorded in users table in our database. Then, the user will be logged in automatically.


```
Please create your username:
Nick 
Please create your password:

Please enter your password again:
                                                                                                                                                                           
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
Enter 3 - for "Manager"
1
Congratulations! You have made your account (｡･ω･｡)ﾉ
Logging in as customer...
Welcome Nick!

```

- ### **List of Upcoming Movies**

List of upcoming movies will be displayed at the default page as well as once the customer has logged
into his account:

```
<<Upcoming Movies!>>
Sooryavanshi [PG] 2021-11-05
Black Box [M] 2021-11-05
```
All upcoming movies are recorded in our database - upcoming movie table. This table will be refreshed
every week on Monday at 6 am. We achieve this by setting a timer task.

- ### **List of Now Showing**

This functionality is provided in both guest and customer interface:

In guest interface:
```
3
=====================================================================
   Enter 5 for "Filter"      Enter movie name for more details
=====================================================================

<<Now Showing!>>
Ip Man [PG] 2021-10-25
Ip Man 2 [PG] 2021-10-25
Ip Man 3 [PG] 2021-10-25
SHANG-CHI AND THE LEGEND OF THE TEN RINGS [M] 2021-09-01
FREE GUY [M] 2021-08-12
JUNGLE CRUISE [M] 2021-07-28
THE LAST DUEL [MA15+] 2021-10-21
Test [G] 2021-10-15
THE HARDER THEY FALL [MA15+] 2021-10-21
THE SUICIDE SQUAD [MA15+] 2021-08-05
PAW PATROL: THE MOVIE [G] 2021-09-16
SPACE JAM: A NEW LEGACY [PG] 2021-07-15
Japanese Action Movie [R18+] 2021-10-21

===================================================================
You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ 
Enter 1 for "Log in"
Enter 2 for "Sign up"
Enter "return" to return to home page
=====================================================================
```
In customer interface:

```
1
======================================================
Enter 3 for "Booking"   Enter 4 for "Filter"
Enter movie name for more details
Enter "return" to return to home page
Enter 5 for "Log out"
======================================================

<<Now Showing!>>
Ip Man [PG] 2021-10-25
Ip Man 2 [PG] 2021-10-25
Ip Man 3 [PG] 2021-10-25
SHANG-CHI AND THE LEGEND OF THE TEN RINGS [M] 2021-09-01
FREE GUY [M] 2021-08-12
JUNGLE CRUISE [M] 2021-07-28
THE LAST DUEL [MA15+] 2021-10-21
Test [G] 2021-10-15
THE HARDER THEY FALL [MA15+] 2021-10-21
THE SUICIDE SQUAD [MA15+] 2021-08-05
PAW PATROL: THE MOVIE [G] 2021-09-16
SPACE JAM: A NEW LEGACY [PG] 2021-07-15
Japanese Action Movie [R18+] 2021-10-21
```

- ### **Movie Filter**

Both upcoming movies and now showing movies are able to be filtered through cinema name or screen size:

```
Enter 5 for "Filter through cinema"     Enter 6 for "Filter through screen size"
```
If invalid input received, error message will be shown and the user will be 
taken back to the home page/ default page:

```
============================================
Wrong input (｡´︿`｡)
============================================
```
**Filter Through Cinema**

User will be ask to choose the cinema. They can choose by entering the number:

```

=======================
Please select a cinema:
=======================
1. Warringah Mall
2. Town Hall
3. Blacktown
4. Eastgarden
5. abc cinema
6. Ali's cinema
7. ali's cinema

```

If wrong cinema number received:

```
=============================================================
Wrong input! Please enter the right cinema number (｡´︿`｡)
=============================================================

=====================================================================
You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ 
Enter 1 for "Log in"
Enter 2 for "Sign up"
Enter "return" to return to home page
=====================================================================

```


If no upcoming movie is scheduled to be played in this cinema:

```
======================================================================================
Sorry, no upcoming movie is scheduled to be played in this cinema at the moment (｡´︿`｡)
======================================================================================
```
User has to return to home page and select filter again.

If no movie is played in this cinema:

```
=============================================================
Sorry, no movie is played currently in this cinema (｡´︿`｡)
=============================================================
```
User has to return to home page and select filter again.

If succeed, movies that are being/ scheduled to be plated in this cinema will be
listed out with all the showing times:

```
Warringah Mall

===============================================
Black Box [M] 2021-11-05
===============================================

Showing Time
2021-11-05 18:00:00 [Gold]
2021-11-06 18:00:00 [Sliver]

===============================================
Sooryavanshi [PG] 2021-11-05
===============================================

Showing Time
2021-11-05 10:00:00 [Bronze]
```

**Filter Through Screen Size**

User will be ask to enter the screen size. They can choose the screen size by entering
the number:

```
============================
Please select a screen size:
============================
1. Gold
2. Sliver
3. Bronze
```
If wrong input received:

```

=============================================================
Wrong input, please enter the right screen type num (｡´︿`｡)
=============================================================


=====================================================================
You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ 
Enter 1 for "Log in"
Enter 2 for "Sign up"
Enter "return" to return to home page
=====================================================================
```

User has to return to home page and select filter again.

If no upcoming movie is scheduled to be played in this screen size

```
==============================================================================
Sorry no upcoming movie is scheduled to be played in this screen type (｡´︿`｡)
==============================================================================
```
User has to return to home page and select filter again.

If no movie is played in this screen size:

```
======================================================================
Sorry no movie is scheduled to be played in this screen type (｡´︿`｡)
======================================================================
```
User has to return to home page and select filter again.

If succeed, movies that are being/ scheduled to be plated in this screen size will be displayed:

```
Gold

FREE GUY [M] 2021-08-12
Ip Man [PG] 2021-10-25
SHANG-CHI AND THE LEGEND OF THE TEN RINGS [M] 2021-09-01
THE LAST DUEL [MA15+] 2021-10-21
```

- ### **Movie Details**

User can view the movie details via enter the movie name (case-insensitive)

```
THE LAST DUEL

King Charles VI declares that Knight Jean de Carrouges settle his dispute with his squire by challenging him to a duel

Classification: [MA15+]
Release String: 2021-10-21
Director: Ridley Scott
Cast: Adam Driver, Ben Affleck, Matt Damon, Jodie Comer
Screen Size: [Gold, Sliver]
```

- ### **Booking**

Customers are able to book movie ticket:

If they choose the "Booking" option at the home page, they will be ask to choose the cinema:
```
3

=======================
Please select a cinema:
=======================
1. Warringah Mall
2. Town Hall
3. Blacktown
4. Eastgarden
5. abc cinema
6. Ali's cinema
7. ali's cinema
```

After they have chose the cinema, a list of movie sessions in the cinema they chose will be printed out:

```
Movies on show at Warringah Mall

===============================================
1. Sooryavanshi [PG] 2021-11-05
===============================================

Showing Time
2021-11-07 09:00:00 [Sliver]

===============================================
2. Ip Man [PG] 2021-10-25
===============================================

Showing Time
2021-11-04 17:00:00 [Gold]

===============================================
3. Double 11 [G] 2021-11-11
===============================================

```
Then they will be asked to choose the movie via number:

```
======================================================
Please select a movie:
======================================================

1. Sooryavanshi
2. Ip Man
3. Double 11
4. Made Up
5. Harry Potter 8
6. JUNGLE CRUISE
7. THE LAST DUEL
8. FREE GUY
9. Black Box
```

Then they will be asked to choose the screen type via number:

```
======================================================
Please select a screenType:
======================================================

1. Gold
```

Then they will be asked to choose the time via number:

```
======================================================
Please select a start time:
======================================================

1. 2021-11-04 17:00:00
```

Then they will be asked to choose seat location:

```
======================================================
Please select your seatLocation:
======================================================

1.Front: 19 seats left
2.Mid: 19 seats left
3.Back: 20 seats left
```
Then they will be asked for the number of tickets wish to book and the ticket type:

```
======================================================
How many seats do you wish to book for adults?
======================================================

1

======================================================
How many seats do you wish to book for kids?
======================================================

0

======================================================
How many seats do you wish to book for students?
======================================================

0

======================================================
How many seats do you wish to book for seniors?
======================================================

0
```

Then they will be processed to payment:

```
======================================================
Which payment do you want to make?
1.Credit Card       2.Gift Card
======================================================
```

#### **Choose 1 （Credit Card)**

Their card number input will not be shown.
```
======================================================
Please enter your card number
Enter "cancel" to cancel transaction
======================================================
                                                                                                                                                                            
======================================================
Please enter your cardholder name
Enter "cancel" to cancel transaction
======================================================
Blake
```

Their card detail will be checked. If the card detail provided is not valid, error message
will be printed out:

```
Wrong card number or name
```

If their card has insufficent balance error message
will be printed out:

```
Insufficient balance
```

If it wasn't their first payment and they chose to save their credit card detail,
their card info will be auto filled.

#### **Choose 2 （Gift Card)**

```
======================================================
Please enter your gift card number
Enter "cancel" to cancel transaction
======================================================
```
If invalid gift card number received:

```
Wrong gift card number, it should be 16-digit with suffix GC
```

If book succeed:
```
You have successfully booked a movie!
Transaction id: 2c4d316e-2845-4dce-ae49-9750fcf928f0
```

- ### **Cancellation During Payment**

If user enter cancel, the transaction will be ended.

If no user's input in 2 minutes, time out, and the transaction will be ended.

- ### **Personal Setting**

```
======================================================
Enter 1 for "Update Password"
Enter 2 for "Cinema Preference"
Enter "return" to return to home page
======================================================
```
Customer can choose to update their account's password or set their cinema preference.

After they have set their cinema preference, a list of movie sessions in the cinema they chose
will be list out in their home page.

- ### **Staff Interface**

```
============================================
Enter the number to operate: 
============================================

1.Add Movie
2.Remove Movie
3.Modify Movie
4.Enter Gift Card
5.Redeem Gift Card
6.Add Movie Session
7.Remove Movie Session
8.Generate Upcoming Movie Report
9.Generate Movie Session Report
10.Log out
```

- ### **Manager Interface**
```
============================================
Enter the number to operate: 
============================================

1.Add Movie
2.Remove Movie
3.Modify Movie
4.Enter Gift Card
5.Redeem Gift Card
6.Add Movie Session
7.Remove Movie Session
8.Add staff
9.Remove Staff
10.Generate Upcoming Movie Report
11.Generate Movie Session Report
12.Generate Cancellation Report
13.Log Out
```

- ### **Add Movie**

```
1
Enter the name of the movie: 
Demo
Enter the classification: 
G
Enter the release date in YYYY-MM-DD format:
2021-11-11
Enter the synopsis:
It's for demo
Enter the directors:
Nat
Enter the genre:
Horror
Enter the casts:
Nat
Enter the showing date in YYYY-MM-DD format:
2021-11-11
Success: Movie added. (｡･ω･｡)ﾉ
```

- ### **Remove Movie**

```
2
Enter the name of the movie: 
Demo
Success: Movie removed. (｡･ω･｡)ﾉ
```

If the movie does not exist:

```
a

============================================
Error: Movie does not exists. (｡´︿`｡)
============================================

Enter the name of the movie: 
```

- ### **Modify Movie**

```
3
Enter the name of the movie: 
Test
1.Enter the number of the section to modify: 
1.Classification 2.Release Date 3.Synopsis 4.Directors 5.Genre 6.Casts 7.Showing Date
1
Enter the classification: 
G
Success: Movie Modified. (｡･ω･｡)ﾉ
```
If the movie does not exist:

```
============================================
Error: Movie does not exists. (｡´︿`｡)
============================================

Enter the name of the movie: 
```

- ### **Enter Gift Card**

```
4
Enter the gift card number:
1111111111111113GC
Success: Gift Card added. (｡･ω･｡)ﾉ
```

If wrong gift card entered:

```
============================================
Error: Gift card format is invalid. (｡´︿`｡)
============================================

Enter the gift card number:
```
- ### **Redeem Gift Card**
```
5
Enter the gift card number:
1111111111111117GC
Success: Gift Card redeemed. (｡･ω･｡)ﾉ
```

If wrong gift card entered:

```
============================================
Error: Gift card format is invalid. (｡´︿`｡)
============================================

Enter the gift card number:
```

- ### **Add Movie Session**

```
Enter the name of the cinema: 
Warringah Mall
Enter the name of the movie: 
Test
Enter the screen type: 
Gold
Enter the start time in YYYY-MM-DD HH:MI:SS format:
2021-11-11 12:00:00
Enter the ticket price for kid: 
10
Enter the ticket price for adult: 
20
Enter the ticket price for senior: 
10
Enter the ticket price for student: 
10
Success: Movie Session added. (｡･ω･｡)ﾉ
```
If cinema not exist:

```
Enter the name of the cinema: 
Warringah

============================================
Error: Cinema does not exist. (｡´︿`｡)
============================================
```

If movie not exist:

```
6
Enter the name of the cinema: 
Warringah Mall
Enter the name of the movie: 
a

============================================
Error: Movie does not exists. (｡´︿`｡)
============================================
```

If invalid screen type entered (it is case-sensitive). Screen types: 
Gold, Bronze, Sliver

```
Enter the screen type: 
G

============================================
Error: Screen type is invalid. (｡´︿`｡)
============================================
```

- ### **Remove Movie Session**

```
Enter the name of the cinema: 
Warringah Mall
Enter the name of the movie: 
Test
Enter the screen type: 
Gold
Enter the start time in YYYY-MM-DD HH:MI:SS format:
2021-11-11 11:00:00
Success: Movie Session removed. (｡･ω･｡)ﾉ

```

If the session not exist:

```
Enter the name of the cinema: 
Warringah Mall
Enter the name of the movie: 
Test
Enter the screen type: 
Gold
Enter the start time in YYYY-MM-DD HH:MI:SS format:
2021-11-11 12:00:00

============================================
Error: Movie Session does not exists. (｡´︿`｡)
============================================


============================================
Error: Movie failed to be removed. (｡´︿`｡)
============================================
```

- ### **Generate Report**

Staff can:

1.Generate Upcoming Movie Report

2.Generate Movie Session Report

Manager can:

1.Generate Upcoming Movie Report

2.Generate Movie Session Report

3.Generate Cancellation Report

The report will be made in a txt file


- ### **Handle Invalid Input at Default Page**

At the default page, user is asked to choose the service. If invalid input is received, error
message will be printed out:

```
  Welcome to Fancy Cinemas Official Website!!

    If you have an account, please sign in (｡･ω･｡)ﾉ 
    If you haven't joined us, you can sign up today! o(｀ω´ )o

    Enter 1 for "Log in"            Enter 2 for "Sign up"
           
=====================================================================
    Enter 3 for "Now Showing"       Enter 4 for "Filter"
    Enter movie name for more details
=====================================================================

<<Upcoming Movies!>>
Sooryavanshi [PG] 2021-11-05
Black Box [M] 2021-11-05

=====================================================================
You have to log in / sign up to book movie tickets! (｡･ω･｡)ﾉ 
Enter 1 for "Log in"
Enter 2 for "Sign up"
=====================================================================                                                                           

haha
============================================
Wrong Input! (｡´︿`｡)
Please enter:
1 - for log in
2 - for sign up
3 - for now showing
4 - for filter upcoming movies
5 - for filter now showing movies
Enter correct movie name for movie detail
============================================
```

- ### **Handle Invalid Input at Customer Interface - Home Page**
```
======================================================
Enter 1 for "Now Showing"   Enter 2 for "Filter"
Enter 3 for "Booking"
Enter movie name for more details
Enter 5 for "Log out"
======================================================

<<Upcoming Movies!>>
Sooryavanshi [PG] 2021-11-05
Black Box [M] 2021-11-05                                                                              

u
============================================
Wrong Input! (｡´︿`｡)
Please enter:
1 - for now showing
2 - for filter upcoming movies
3 - for booking tickets
4 - for filter now showing movies
5 - for log out
Enter correct movie name for movie detail
============================================
```
- ### **Log out**

If user choose to log out, they will be logged out from their account and brought back to 
default page.
```
=================
Logging out...
=================

    Welcome to Fancy Cinemas Official Website!!

    If you have an account, please sign in (｡･ω･｡)ﾉ 
    If you haven't joined us, you can sign up today! o(｀ω´ )o

    Enter 1 for "Log in"            Enter 2 for "Sign up"
```

## **Tests**

---
Tests cases are available in :

`/app/src/test/java/MTBMS`

You can run the tests and get a code coverage report by running the following commands:

```
gradle clean build
gradle test jacocoTestReport
```

You can find the code coverage report here:
`/app/build/reports/jacoco/test/html/index.html`

Or

You can conduct black box testing with the information provided in 'Functionalities & Usages'
section.

## **Project Contributors**

---

Ali Lieberman -- https://github.sydney.edu.au/alie0302

Chao (Charles) Luo -- https://github.sydney.edu.au/cluo3734

Jiayin (Natalie) Lu -- https://github.sydney.edu.au/jilu8927

Linxuan (John) Jiang -- https://github.sydney.edu.au/ljia0550

Xinyuan (Albert) Wang -- https://github.sydney.edu.au/xwan2252


## Reference

---
SOFT 2412 - Tutorial 3 - Build Automation with Gradle
https://edstem.org/au/courses/6848/lessons/14709/slides/106167
