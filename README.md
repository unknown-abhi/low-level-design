# Low-Level Design (LLD) Project

A comprehensive Java project showcasing multiple **low-level design implementations** and **system design patterns** for real-world applications.

## 📋 Overview

This project contains practical implementations of various system design problems and design patterns using **Object-Oriented Programming (OOP)** principles. Each module demonstrates how to design scalable, maintainable, and efficient systems at a detailed level.

## 🏗️ Project Structure

The project is organized into the following modules:

```
src/main/java/com/lld/
├── atm/                      # ATM System Design
├── car_rental/               # Car Rental Management System
├── customer_support/         # Customer Support/Ticketing System
├── doctors_appointment/       # Doctors Appointment Booking System
├── hashmap/                  # Custom HashMap Implementation
├── logger/                   # Logging System with Multiple Levels
├── parking_lot/              # Parking Lot Management System
├── rate_limiter/             # Rate Limiter Implementation
├── snakes_and_ladder/        # Snakes and Ladders Game
├── splitwise/                # Splitwise Bill Sharing Application
└── Main.java                 # Entry point
```

## 📚 Modules Description

### 1. **ATM System** (`atm/`)
- Simulates a real ATM machine
- Features: Account authentication, withdrawal, deposit, balance inquiry
- Design patterns: State pattern, Singleton

### 2. **Car Rental** (`car_rental/`)
- Vehicle rental management system
- Features: Car reservation, rental pricing, inventory management
- Concepts: Inheritance, Polymorphism, Composition

### 3. **Customer Support** (`customer_support/`)
- Ticketing and support system
- Features: Ticket creation, assignment, status tracking
- Design patterns: Strategy pattern, Observer pattern

### 4. **Doctors Appointment** (`doctors_appointment/`)
- Medical appointment booking system
- Features: Doctor availability, appointment scheduling, patient management
- Concepts: Factory pattern, Builder pattern

### 5. **HashMap** (`hashmap/`)
- Custom HashMap implementation from scratch
- Features: Put, get, collision handling (chaining/open addressing)
- Concepts: Hash functions, collision resolution, load factor

### 6. **Logger** (`logger/`)
- Multi-level logging system
- Features: DEBUG, INFO, WARN, ERROR levels, file/console output
- Design patterns: Singleton, Strategy pattern

### 7. **Parking Lot** (`parking_lot/`)
- Multi-level parking lot management
- Features: Spot allocation, vehicle tracking, fee calculation
- Design patterns: Factory, Strategy, State

### 8. **Rate Limiter** (`rate_limiter/`)
- Token bucket and sliding window rate limiting
- Features: Request throttling, traffic control
- Design patterns: Strategy pattern

### 9. **Snakes and Ladder** (`snakes_and_ladder/`)
- Classic board game implementation
- Features: Game mechanics, player management, dice rolls
- Concepts: OOP design, game state management

### 10. **Splitwise** (`splitwise/`)
- Bill splitting and expense sharing application
- Features: Split bills, group expenses, settlement calculation
- Concepts: Graph algorithms, debt settlement

## 🚀 Getting Started

### Prerequisites
- **Java 25+**
- **Maven 3.9.9+**
- **Git**

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YOUR_USERNAME/low-level-design.git
   cd low-level-design
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn exec:java -Dexec.mainClass="com.lld.Main"
   ```

## 🔨 Build & Compile

### Clean and Build
```bash
mvn clean install
```

### Compile Only
```bash
mvn compile
```

### Run Tests
```bash
mvn test
```

### Clean Build Artifacts
```bash
mvn clean
```

## 📦 Dependencies

- **Lombok 1.18.46** - Reduces boilerplate code with annotations
- **JUnit 5.8.1** - Unit testing framework

## 💡 Key Concepts Demonstrated

- **Object-Oriented Design (OOP)** - Encapsulation, Inheritance, Polymorphism, Abstraction
- **Design Patterns** - Singleton, Factory, Builder, Strategy, Observer, State
- **SOLID Principles** - Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- **Data Structures** - HashMaps, Queues, Stacks, Graphs
- **Algorithms** - Sorting, Searching, Graph traversal
- **Concurrency** (where applicable) - Thread safety, Synchronization

## 🧪 Testing

The project includes unit tests using JUnit 5. Run tests with:

```bash
mvn test
```

Or run specific test class:
```bash
mvn test -Dtest=YourTestClassName
```

## 📖 Best Practices

This project follows Java and OOP best practices:

- ✅ Clean Code principles
- ✅ Proper package organization
- ✅ Meaningful variable and method names
- ✅ DRY (Don't Repeat Yourself)
- ✅ SOLID principles
- ✅ Comprehensive documentation
- ✅ Unit test coverage

## 🤝 Contributing

Feel free to fork, modify, and submit pull requests for improvements!

## 📝 License

This project is open source and available under the MIT License.

## 📧 Contact

For questions or suggestions, please open an issue or contact the repository maintainer.

---

**Happy Learning! 🎓**
