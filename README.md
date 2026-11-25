# 🍃 Food Rescue and Donation System

## 📌 About the Project

The **Food Rescue and Donation System** is a web-based application developed to reduce food wastage by connecting food donors with food receivers such as NGOs and charitable organizations.

The platform allows donors to share surplus food and enables receivers to request food based on their requirements. It provides a structured way to manage donations, requests, collections and notifications.

## 🎯 Objectives

- Reduce food wastage.
- Connect food donors with people and organizations in need.
- Make food donation and collection easier.
- Track donation requests and collection status.
- Notify users about important activities.
- Automatically identify expired food donations.

## ✨ Features

### Donor
- Donor registration and login.
- Add food donations.
- View personal donations.
- View incoming food requests.
- Approve or reject requests.
- View collection history.
- Receive notifications.

### Receiver
- Receiver registration and login.
- View available food donations.
- Request food with required quantity.
- Track request status.
- View collection history.
- Receive notifications.

### System Features
- Role-based dashboards.
- Input validation.
- Duplicate active request prevention.
- Automatic donation expiry monitoring.
- Automatic notifications.
- Collection tracking.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend programming |
| Spring Boot | Backend framework |
| Spring JDBC / JdbcTemplate | Database operations |
| MySQL | Database |
| HTML | Frontend structure |
| CSS | Frontend styling |
| JavaScript | Frontend interactions |
| Maven | Dependency management |

## 🔄 Application Workflow

1. Donors and receivers register and log in.
2. Donors add surplus food details.
3. Receivers browse available donations.
4. Receivers submit food requests.
5. Donors approve or reject requests.
6. Receivers collect approved food.
7. The system records completed collections.
8. Notifications inform users about important updates.
9. The system automatically monitors donation deadlines.

## 🗄️ Database

The application uses MySQL with the following main tables:

- `users`
- `donations`
- `requests`
- `notifications`
- `collection_history`

## ⚙️ Requirements

- Java 21
- MySQL
- Maven (or the included Maven Wrapper)
- Git
- Visual Studio Code or another Java IDE

## 🚀 Running the Project Locally

1. Clone the repository:

   ```bash
   git clone https://github.com/YOUR-USERNAME/food-rescue-and-donation-system.git
   ```

2. Open the project folder in VS Code.

3. Create a MySQL database:

   ```sql
   CREATE DATABASE food_rescue_db;
   ```

4. Configure your local `application.properties` with your MySQL database URL, username and password. Do not commit this file or share database credentials.

5. Run the application from the project root:

   ```bash
   .\mvnw.cmd spring-boot:run
   ```

6. Open the website:

   ```text
   http://localhost:8080/index.html
   ```

## 👩‍💻 Project Information

**Project Name:** Food Rescue and Donation System

**Project Type:** Web Application

**Backend:** Java and Spring Boot

**Database:** MySQL

**Frontend:** HTML, CSS and JavaScript

## 🌱 Expected Impact

The project aims to encourage food donation, reduce unnecessary food wastage and make surplus food more accessible to organizations and people who need it.
