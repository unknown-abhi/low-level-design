# Doctors Appointment System

A comprehensive medical appointment booking system with doctor scheduling, patient management, and appointment slot management.

## 📋 Overview

This module implements a real-world doctor appointment system with:
- Doctor profile and availability management
- Patient registration and medical history
- Appointment slot booking and cancellation
- Appointment scheduling with conflict detection
- Doctor specialization matching
- Appointment reminders and notifications
- Medical record management

## 🏗️ Architecture

### Package Structure
```
doctors_appointment/
├── dto/            # Data transfer objects
├── enums/          # Specialization, AppointmentStatus
├── exception/      # Custom exceptions
├── model/          # Doctor, Patient, Appointment
├── repository/     # Data persistence layer
├── service/        # AppointmentService, DoctorService
├── strategy/       # Slot selection strategies
├── utils/          # Utility functions
└── Main.java      # Entry point
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│      Doctors Appointment System Architecture                │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────┐
         │      Doctor      │
         ├──────────────────┤
         │- doctorId        │
         │- name            │
         │- specialization  │
         │- licenseNumber   │
         │- phoneNumber     │
         │- email           │
         │- availableSlots  │
         │- patients: List  │
         ├──────────────────┤
         │+ addSlot()       │
         │+ removeSlot()    │
         │+ getAvailable()  │
         │+ bookAppointment()
         │+ cancelAppointment()
         └──────────────────┘
                 △
         ┌───┬──┴───┬──────────┐
         │   │      │          │
      Cardiologist Dentist Surgeon

         ┌──────────────────┐
         │      Patient     │
         ├──────────────────┤
         │- patientId       │
         │- name            │
         │- age             │
         │- gender          │
         │- email           │
         │- phoneNumber     │
         │- medicalHistory  │
         │- appointments    │
         ├──────────────────┤
         │+ bookAppointment()
         │+ cancelAppointment()
         │+ getHistory()    │
         │+ addMedHistory() │
         └──────────────────┘

         ┌──────────────────┐
         │   Appointment    │
         ├──────────────────┤
         │- appointmentId   │
         │- doctor          │
         │- patient         │
         │- date            │
         │- time            │
         │- duration        │
         │- status          │
         │- notes           │
         │- symptoms        │
         │- diagnosis       │
         │- prescription    │
         ├──────────────────┤
         │+ confirm()       │
         │+ cancel()        │
         │+ complete()      │
         │+ reschedule()    │
         │+ addNotes()      │
         └──────────────────┘
                 △
         ┌───┬──┴───┬──────────┐
         │   │      │          │
      Scheduled Completed Cancelled

         ┌──────────────────┐
         │  TimeSlot        │
         ├──────────────────┤
         │- slotId          │
         │- doctor          │
         │- date            │
         │- startTime       │
         │- endTime         │
         │- isAvailable     │
         │- appointment     │
         ├──────────────────┤
         │+ isBooked()      │
         │+ book()          │
         │+ cancel()        │
         └──────────────────┘

         ┌──────────────────┐
         │   MedicalRecord  │
         ├──────────────────┤
         │- recordId        │
         │- patient         │
         │- date            │
         │- type            │
         │- diagnosis       │
         │- treatment       │
         │- prescription    │
         │- notes           │
         └──────────────────┘

    ┌──────────────────────────┐
    │   Specialization         │
    ├──────────────────────────┤
    │- CARDIOLOGY              │
    │- NEUROLOGY               │
    │- ORTHOPEDICS             │
    │- DENTISTRY               │
    │- DERMATOLOGY             │
    │- PEDIATRICS              │
    │- GENERAL_PRACTICE        │
    └──────────────────────────┘

    ┌──────────────────────────┐
    │  AppointmentStatus       │
    ├──────────────────────────┤
    │- PENDING                 │
    │- CONFIRMED               │
    │- COMPLETED               │
    │- CANCELLED               │
    │- NO_SHOW                 │
    └──────────────────────────┘
```

## 🔑 Key Features

### 1. **Doctor Management**
- Doctor profiles with specialization
- License verification
- Availability scheduling
- Patient list management
- Consultation history

### 2. **Patient Management**
- Patient registration
- Medical history tracking
- Appointment history
- Prescription storage
- Health records

### 3. **Appointment Booking**
- Real-time slot availability
- Conflict detection
- Appointment confirmation
- Automatic reminders
- Rescheduling support

### 4. **Specializations**
```
- Cardiology (Heart specialist)
- Neurology (Nervous system)
- Orthopedics (Bones & joints)
- Dentistry (Dental care)
- Dermatology (Skin)
- Pediatrics (Children)
- General Practice (General health)
```

### 5. **Appointment Status**
- `PENDING` - Awaiting confirmation
- `CONFIRMED` - Appointment confirmed
- `COMPLETED` - Consultation finished
- `CANCELLED` - Appointment cancelled
- `NO_SHOW` - Patient didn't show up

## 💻 Usage Example

```java
// Create appointment service
AppointmentService appointmentService = new AppointmentService();

// Register doctor
Doctor doctor = new Doctor(
    "Dr. Smith",
    Specialization.CARDIOLOGY,
    "LIC12345"
);
appointmentService.registerDoctor(doctor);

// Add available slots
LocalDateTime slotTime = LocalDateTime.now().plusDays(2).withHour(10);
doctor.addSlot(new TimeSlot(slotTime, 30));

// Register patient
Patient patient = new Patient("John Doe", 35, "M", "john@email.com");
appointmentService.registerPatient(patient);

// Book appointment
Appointment appointment = appointmentService.bookAppointment(
    doctor,
    patient,
    slotTime,
    "Chest pain and shortness of breath"
);

System.out.println("Appointment booked: " + appointment.getAppointmentId());

// Complete appointment
appointmentService.completeAppointment(
    appointment,
    "Hypertension",
    "Rest and medication"
);
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **Strategy** | Slot selection and matching |
| **Factory** | Appointment creation |
| **Observer** | Appointment reminders |
| **Builder** | Appointment configuration |
| **Template Method** | Booking workflow |

## 📅 Appointment Workflow

```
┌──────────┐  search  ┌────────┐  book   ┌─────────┐
│ Search   │─────────→│ Select │────────→│ Confirm │
│ Doctors  │          │ Slot   │         │ Details │
└──────────┘          └────────┘         └─────────┘
                                              │
                                         confirm
                                              ▼
                                         ┌──────────┐
                                         │Confirmed │
                                         └──────────┘
                                              │
                                      appointment time
                                              ▼
                                         ┌──────────┐
                                         │Completed │
                                         └──────────┘
```

## ✅ Core Methods

### AppointmentService
- `bookAppointment(doctor, patient, datetime, symptoms)` - Create appointment
- `completeAppointment(appointment, diagnosis, treatment)` - Finish consultation
- `cancelAppointment(appointment)` - Cancel booking
- `rescheduleAppointment(appointment, newDateTime)` - Change time
- `getAvailableSlots(doctor, date)` - Find free slots
- `getPatientAppointments(patient)` - Get patient history

### DoctorService
- `registerDoctor(doctor)` - Add new doctor
- `addSlot(doctor, slot)` - Add available time
- `removeSlot(doctor, slot)` - Remove availability
- `getAvailableSlots(doctor)` - List free slots
- `getPatientHistory(doctor)` - Get consultation history

### PatientService
- `registerPatient(patient)` - New patient registration
- `getAppointmentHistory(patient)` - Get appointments
- `addMedicalHistory(patient, record)` - Add health record
- `getPrescriptions(patient)` - Get medications
- `updateContactInfo(patient, info)` - Update details

## 📊 Appointment Durations

```
Standard Consultation: 30 minutes
Follow-up Appointment: 20 minutes
Complex Case: 60 minutes
Emergency: Immediate (if available)
```

## 🧪 Testing Scenarios

Test cases should cover:
- Doctor registration
- Patient registration
- Slot creation and availability
- Appointment booking success
- Booking conflict detection
- Double booking prevention
- Cancellation handling
- Rescheduling workflow
- Medical history tracking
- Notification sending
- No-show handling

## 🔔 Notification System

Automated notifications:
- Appointment confirmation → Patient
- Appointment reminder (24 hours) → Patient
- Appointment reminder (1 hour) → Patient
- Doctor availability updated → Relevant patients
- Cancellation confirmation → Both parties
- Prescription uploaded → Patient

## 💊 Medical Records

Tracked information:
- Symptoms reported
- Diagnosis made
- Treatment prescribed
- Medications
- Follow-up recommendations
- Lab test results
- Previous medical conditions

---

**Back to [Parent README](../README.md)**
