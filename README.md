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

2. ### Connect to pgadmin4

   The link to the pgadmin4 instance connected to our shared database is:

    ```
    http://13.211.7.238/pgadmin4/browser/
   ```

   Username: alie0302@uni.sydney.edu.au
   Password: triceratops
   

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
(Not implemented yet)

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
Once they created their unique username, they will be asked to create their password and
choose their identity type. If invalid input received, an error message will be print out.
Only three attempts allowed for invalid input. If the user exceed the limit, they will be 
brought back to the default page:

```
Please create your password:
Natalie
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
4
============================================
Wrong Input! (｡´︿`｡)
============================================
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
4
============================================
Wrong Input! (｡´︿`｡)
============================================
Please choose your identity:
Enter 1 - for "Customer"
Enter 2 - for "Staff"
4
============================================
Wrong Input! (｡´︿`｡)
============================================

============================================
Sign up failed (｡´︿`｡)
============================================

Returning...

```
If the user chose '2' which indicates that the user is the staff, the system will
ask the role. If invalid input received, an error message will be print out.
Only three attempts allowed for invalid input. If the user exceed the limit, they will be
brought back to the default page:

Note: We are aware of the security hole here: We didn't check the authenticity of 
user's 'Staff' identity. We will fix it in sprint 2.

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
