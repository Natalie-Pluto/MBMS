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

You are viewing the sprint 2 ver. 2.1. 

By far, the program is able to perform all the functionalities related to guest user interface. 
(Some features such as case-insensitive search will be updated in sprint 3)

The customer interface is able to perform all features in guest user's interface. Booking option is make available for the customers.
Despite customers are able to choose the "booking" option, there are some issue related to booking and payment remain unsolved in this version,
user could meet booking or payment failure. This will be fixed in the next sprint.

The staff interface is made available for staff/manager in this version. However, the CLI format is not finalised. User will be 
directed to staff interface if they logged in as staff/manager, but the functionalities related to
staff interface is not fully implemented hence users could meet unexpected error. These issues will all 
be fixed in the next sprint.


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
If the user chose '2' which 


```
Are you a Manager?
Enter: Y/N
P
============================================
Wrong Input! (｡´︿`｡)
============================================
Are you a Manager?
Enter: Y/N
P
============================================
Wrong Input! (｡´︿`｡)
============================================
Are you a Manager?
Enter: Y/N
P
============================================
Wrong Input! (｡´︿`｡)
============================================

============================================
Sign up failed (｡´︿`｡)
============================================

Returning...
```

If nothing went wrong, the user will be signed up successfully, and the user's detail
will be recorded in users table in our database. Then, the user will be logged in automatically.

```
Please create your username:
nat
Please create your password:
disney
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
1
Congratulations! You have made your account (｡･ω･｡)ﾉ
Logging in as customer...
Welcome nat!

                                                                                
======================================================
Enter 1 for "Filter Movies"   2 for "Book Tickets"
======================================================

<<Upcoming Movies!>>
```

- ### **Handle Invalid Input at Default Page**

At the default page, user is asked to choose the service. If invalid input is received, error
message will be printed out:

```
 Welcome to Fancy Cinemas Official Website!!

    If you have an account, please sign in (｡･ω･｡)ﾉ 
    If you haven't joined us, you can sign up today! o(｀ω´ )o
    If you don't want to join us today, you can continue as a guest (´･ω･`)

    1. Log in       2. Sign up      3. I wish to continue
                                                                          

haha
============================================
Wrong Input! (｡´︿`｡)
Please enter:
1 - for log in
2 - for sign up
3 - for continue
============================================

Returning...
                                                                               

    Welcome to Fancy Cinemas Official Website!!

    If you have an account, please sign in (｡･ω･｡)ﾉ 
    If you haven't joined us, you can sign up today! o(｀ω´ )o
    If you don't want to join us today, you can continue as a guest (´･ω･`)

    1. Log in       2. Sign up      3. I wish to continue
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
