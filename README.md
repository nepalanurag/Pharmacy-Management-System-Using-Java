# Pharmacy Management System

A desktop app for the day to day work of a pharmacy, built in Java with JavaFX and MySQL.

## Screens

- `Main.java`, `HomePage.java`, `Login.java` - app entry and main screens
- `add_drugs.java`, `add_company.java`, `new_purchase.java`, `new_sale.java`, `add_user.java`, `Show.java` - the working screens
- `Connect.java` - the MySQL connection (database `pharmacy` on localhost, user `root`)
- `Pharmacy-Management-System_Project Report.pdf` - the project report

## What it does

From reading the code (the GUI needs a display and a MySQL server, so this walkthrough is from the source, which compiles cleanly):

- Login checks the username and password against the `users` table and records each login in the `login` table.
- The home page has sections for companies, drugs, sales, purchases, and users. Each section shows the table contents, lets you add a record, and lets you delete a record by ID.
- New sale picks a drug, checks the stock, subtracts the sold quantity, shows the amount to pay, and records the sale in `history_sales`.
- New purchase picks a drug and company, adds the quantity to stock, and records it in `purchase`.
- All database queries that take user input now use prepared statements.

## Running

You need a JDK, the JavaFX SDK, the MySQL JDBC driver, and a MySQL server. Note that `pharmacy.sql` in this repo is currently empty, so create the database and tables yourself:

```sql
CREATE DATABASE pharmacy;
```

The tables the code expects are `users`, `login`, `drugs`, `company`, `purchase`, and `history_sales` (each with an auto-increment `ID` column, since deleting a record works by ID). Connection details live in `Connect.java`.

Then compile and run (example with JavaFX 21 and MySQL Connector/J 8.0):

```bash
javac -cp "javafx.base.jar:javafx.graphics.jar:javafx.controls.jar:mysql-connector-j.jar" \
  Main.java Login.java HomePage.java Show.java add_company.java add_drugs.java \
  add_user.java new_purchase.java new_sale.java Connect.java
java -cp ".:javafx.base.jar:javafx.graphics.jar:javafx.controls.jar:mysql-connector-j.jar" \
  --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls application.Main
```

The old README said to open the project in an IDE; the commands above are the manual equivalent and were verified to compile.
