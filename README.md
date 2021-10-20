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

You are viewing the sprint 1 ver. 1.1. 

By far, the system can perform user's login, signup. The valid new user's detail
will be recorded in our database.

Note: Some existed functionalities in this version will be updated in furture releases.


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

2. ### Connect to Database

   You are connected to our shared database. The link to the shared database is:

    ```
    http://13.211.7.238/pgadmin4/browser/
   ```

   Username: `dbmasteruser`
   Password: `A>XV>D*7r-V{y_wL}}I{+U=8zEtj1*T<`
   

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
    If you don't want to join us today, you can continue as a guest (´･ω･`)

    1. Log in       2. Sign up      3. I wish to continue                                                                    

```
Now, you can use the application :)

## Functionalities and Usages

---

- ### **User's Interface**

  The User's interface we made for the application is CLI. The program will start with the default page where
  the user will be asked to log in, sign up or continue as a guest:
  
  (Note: The default page will be updated later.)

```
Welcome to Fancy Cinemas Official Website!!

    If you have an account, please sign in (｡･ω･｡)ﾉ 
    If you haven't joined us, you can sign up today! o(｀ω´ )o
    If you don't want to join us today, you can continue as a guest (´･ω･`)

    1. Log in       2. Sign up      3. I wish to continue     
```

If the user logged in as a staff/ manager, the system will bring them to the staff interface.

If the user logged in as a customer, the system will bring them to the customer interface.

- ### **Log in**

Users who have made their account can log in at the default page. They have to enter valid usernane
and password otherwise an error message will be printed out and they will be brought back to default page:

```
Please enter your username:
ali
Please enter your password:
password1
                                                                         
Incorrect username or password (｡´︿`｡)                                                                                                                                      
```

If log in successfully, the system will identify the user's identity and bring them to the 
right interface:

* If logged in as a customer:

```                                                                          
Logging in as customer...
Welcome Lappland!

======================================================
Enter 1 for "Filter Movies"   2 for "Book Tickets"
======================================================

<<Upcoming Movies!>>

```
* If logged in as a staff:
```                                                                          
Logging in as staff...
```

* If logged in as a manager:
```                                                                          
Logging in as manager...
```

Note: The user's interface for staff is not implemented at this stage.

