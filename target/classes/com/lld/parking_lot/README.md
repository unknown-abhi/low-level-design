# Parking Lot Management System

A comprehensive multi-level parking lot system demonstrating space allocation strategies, vehicle tracking, and payment processing.

## 📋 Overview

This module implements a real-world parking management system with:
- Multi-level and multi-section parking structure
- Intelligent spot allocation (nearest empty spot)
- Vehicle entry/exit tracking
- Fee calculation based on parking duration
- Reservation system
- Real-time availability display

## 🏗️ Architecture

### Package Structure
```
parking_lot/
├── enums/              # VehicleType, ParkingSpotStatus, PaymentStatus
├── factory/            # Factory for spot and ticket creation
├── model/              # ParkingLot, Level, Spot, Vehicle, Ticket
├── service/            # ParkingService, PaymentService
├── strategy/           # Pricing and spot selection strategies
├── utils/              # Helper utilities
└── Main.java          # Entry point
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│          Parking Lot Management System                      │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────┐
         │    ParkingLot    │
         ├──────────────────┤
         │- id              │
         │- levels: List    │
         │- name            │
         │- address         │
         ├──────────────────┤
         │+ parkVehicle()   │
         │+ unparkVehicle() │
         │+ getAvailableSpots()
         └──────────────────┘
                 │
                 │ contains many
                 ▼
         ┌──────────────────┐
         │      Level       │
         ├──────────────────┤
         │- levelId         │
         │- floor           │
         │- spots: List     │
         ├──────────────────┤
         │+ findEmptySpot() │
         │+ getAvailability()
         └──────────────────┘
                 │
                 │ contains many
                 ▼
         ┌──────────────────┐
         │  ParkingSpot     │
         ├──────────────────┤
         │- spotId          │
         │- spotNumber      │
         │- vehicleType     │
         │- status          │
         │- vehicle         │
         ├──────────────────┤
         │+ parkVehicle()   │
         │+ unparkVehicle() │
         │+ isEmpty()       │
         │+ isFull()        │
         └──────────────────┘
                 △
                 │
         ┌───┬──┴───┬───────┐
         │   │      │       │
      Compact Standard Large Handicap

         ┌──────────────────┐
         │     Vehicle      │
         ├──────────────────┤
         │- licensePlate    │
         │- type            │
         │- owner           │
         │- color           │
         └──────────────────┘
                 △
                 │
         ┌───┬──┴───┬───────┐
         │   │      │       │
       Car  Bus   Bike   Truck

         ┌──────────────────┐
         │   ParkingTicket  │
         ├──────────────────┤
         │- ticketId        │
         │- vehicle         │
         │- spot            │
         │- entryTime       │
         │- exitTime        │
         │- amount          │
         │- isPaid          │
         ├──────────────────┤
         │+ calculateFee()  │
         │+ markPaid()      │
         └──────────────────┘
```

## 🔑 Key Features

### 1. **Multi-Level Structure**
- Multiple parking levels
- Each level has sections
- Supports different vehicle types per spot

### 2. **Smart Spot Allocation**
- Finds nearest empty spot to entrance
- Considers vehicle type compatibility
- Prevents oversized vehicles in small spots

### 3. **Vehicle Tracking**
- Entry/exit timestamp recording
- Vehicle identification via license plate
- Owner information storage

### 4. **Dynamic Pricing**
- Hourly rates
- Duration-based pricing
- Peak hour surcharges
- Monthly pass options

### 5. **Spot Types**
- **Compact** - Small cars
- **Standard** - Regular cars
- **Large** - SUVs and trucks
- **Handicap** - Accessible spots (wheelchair)

## 💻 Usage Example

```java
ParkingLotService parkingService = new ParkingLotService();

// Create parking lot
ParkingLot lot = new ParkingLot("Central Lot", 5, 20);

// Park vehicle
Vehicle car = new Vehicle("ABC123", VehicleType.CAR);
ParkingTicket ticket = parkingService.parkVehicle(lot, car);

System.out.println("Parked at: " + ticket.getSpot().getSpotNumber());

// Unpark vehicle
parkingService.unparkVehicle(ticket);
System.out.println("Fee: " + ticket.getAmount());
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **Strategy** | Pricing and spot selection strategies |
| **Factory** | Ticket and spot creation |
| **Observer** | Lot capacity notifications |
| **Composite** | Multi-level structure |

## 📊 Parking Spot Status

- `AVAILABLE` - Empty spot
- `OCCUPIED` - Vehicle parked
- `RESERVED` - Reserved for later
- `MAINTENANCE` - Closed for maintenance
- `HANDICAP` - Disabled access only

## 💳 Pricing Strategies

- **Hourly Rate** - Fixed rate per hour
- **Daily Rate** - Fixed rate for 24 hours
- **Monthly Pass** - Unlimited parking for a month
- **Dynamic Pricing** - Higher rates during peak hours
- **Tiered Pricing** - Different rates by level proximity

## ✅ Core Methods

### ParkingLotService
- `parkVehicle(lot, vehicle)` - Allocate spot and issue ticket
- `unparkVehicle(ticket)` - Release spot and calculate fee
- `getAvailableSpots(lot)` - List empty spots
- `getOccupancy(lot)` - Current capacity usage
- `isSpotAvailable(spot)` - Check spot status

### ParkingSpot
- `parkVehicle(vehicle)` - Place vehicle
- `unparkVehicle()` - Remove vehicle
- `isEmpty()` - Check if empty
- `canFitVehicle(vehicle)` - Compatibility check

## 🧪 Testing

Test cases should cover:
- Parking on multiple levels
- Spot allocation for different vehicle types
- Fee calculation with different durations
- Handicap spot access control
- Lot capacity management
- Payment processing
- Exit without payment scenarios

## 🚗 Vehicle Types Supported

- CAR - Standard sedan/hatchback
- BIKE - Motorcycle/scooter
- TRUCK - Heavy vehicle
- BUS - Public transport
- WHEELCHAIR - Accessible vehicle

---

**Back to [Parent README](../README.md)**
