# Car Rental System Design

A complete car rental management system demonstrating vehicle inventory management, reservation booking, pricing strategies, and rental operations.

## 📋 Overview

This module implements a real-world car rental system with:
- Vehicle catalog management (cars, SUVs, luxury vehicles)
- Reservation and booking system
- Pricing calculation based on vehicle type and rental duration
- Rental agreement generation
- Payment processing
- Vehicle availability tracking

## 🏗️ Architecture

### Package Structure
```
car_rental/
├── enums/              # VehicleType, ReservationStatus, Location
├── factory/            # Factory for vehicle and rental creation
├── model/              # Vehicle, Reservation, Rental, Payment
├── repository/         # Data persistence layer
├── service/            # RentalService, VehicleService, PaymentService
├── strategy/           # Pricing strategies (LuxuryPricingStrategy, etc.)
├── utils/              # Helper utilities
└── Main.java          # Entry point
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│              Car Rental System                              │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────┐       ┌──────────────────┐
         │    Vehicle       │       │    Customer      │
         ├──────────────────┤       ├──────────────────┤
         │- vehicleId       │       │- customerId      │
         │- type            │       │- name            │
         │- brand           │       │- licenseNumber   │
         │- pricePerDay     │       │- contact         │
         │- isAvailable     │       │- address         │
         └──────────────────┘       └──────────────────┘
                △                           △
                │                           │
         ┌──────────────────┐       ┌──────────────────┐
         │ LuxuryCar        │       │  Reservation     │
         │ SUV              │       ├──────────────────┤
         │ Sedan            │       │- reservationId   │
         └──────────────────┘       │- customer        │
                                    │- vehicle         │
                                    │- startDate       │
                                    │- endDate         │
                                    │- status          │
                                    ├──────────────────┤
                                    │+ cancel()        │
                                    │+ confirmBooking()│
                                    └──────────────────┘

         ┌──────────────────┐       ┌──────────────────┐
         │    Rental        │       │    Payment       │
         ├──────────────────┤       ├──────────────────┤
         │- rentalId        │       │- paymentId       │
         │- reservation     │       │- amount          │
         │- actualPickup    │       │- method          │
         │- actualReturn    │       │- status          │
         │- rentalCost      │       │- timestamp       │
         ├──────────────────┤       └──────────────────┘
         │+ generateBill()  │
         │+ returnVehicle() │
         └──────────────────┘

    ┌──────────────────────┐
    │  PricingStrategy     │ (Interface)
    ├──────────────────────┤
    │+ calculatePrice()    │
    └──────────────────────┘
         △         △         △
         │         │         │
    Luxury  Sedan  Economy
```

## 🔑 Key Features

### 1. **Vehicle Management**
- Vehicle catalog with different types (Sedan, SUV, Luxury)
- Availability tracking
- Real-time inventory management

### 2. **Reservation System**
- Booking vehicles for specified dates
- Reservation confirmation and cancellation
- Conflict detection (overlapping reservations)

### 3. **Pricing Strategy**
- Different pricing based on vehicle type
- Duration-based discounts
- Peak season pricing
- Strategy pattern implementation

### 4. **Rental Operations**
- Generate rental agreements
- Process payments
- Track vehicle condition on return
- Calculate late fees

## 💻 Usage Example

```java
CarRentalService rentalService = new CarRentalService();

// Create customer
Customer customer = new Customer("John", "DL123456");

// Search and reserve vehicle
Vehicle vehicle = rentalService.searchVehicle(VehicleType.SEDAN);
Reservation reservation = rentalService.makeReservation(
    customer, vehicle, startDate, endDate
);

// Confirm and rent
Rental rental = rentalService.confirmRental(reservation);

// Return vehicle
rentalService.returnVehicle(rental);
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **Strategy** | Different pricing strategies |
| **Factory** | Vehicle and rental creation |
| **Repository** | Data persistence abstraction |
| **Builder** | Complex object construction |

## 📊 Vehicle Types

- `SEDAN` - Standard 4-door sedan
- `SUV` - Sport Utility Vehicle
- `LUXURY` - Premium luxury vehicles
- `HATCHBACK` - Compact city car
- `VAN` - Multi-person vehicle

## 📋 Reservation Status

- `PENDING` - Awaiting confirmation
- `CONFIRMED` - Booking confirmed
- `ACTIVE` - Rental in progress
- `COMPLETED` - Rental finished
- `CANCELLED` - Booking cancelled

## ✅ Core Methods

### RentalService
- `searchVehicle(type)` - Find available vehicles
- `makeReservation(customer, vehicle, dates)` - Create reservation
- `confirmRental(reservation)` - Start rental
- `returnVehicle(rental)` - Complete rental
- `calculateRentalCost(rental)` - Compute total cost

### VehicleService
- `addVehicle(vehicle)` - Add to inventory
- `removeVehicle(vehicleId)` - Remove from inventory
- `getAvailableVehicles(dates)` - Find available cars
- `updateAvailability(vehicleId)` - Mark in/out of service

## 🧪 Testing

Test cases should cover:
- Vehicle availability checking
- Booking conflicts detection
- Price calculation with different strategies
- Late fee calculation
- Cancellation scenarios
- Payment processing

## 💳 Payment Methods

- Cash payment
- Credit card
- Debit card
- Online banking
- Insurance integration

---

**Back to [Parent README](../README.md)**
