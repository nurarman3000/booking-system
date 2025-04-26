# Clinic Booking System
booking system for the Boost Physio Clinic (BPC)

## Overview
This Clinic Booking System allows patients to book, cancel, and attend appointments with physiotherapists. It supports features such as:
- Adding/removing patients
- Booking appointments based on treatments and available time slots
- Viewing all booked appointments
- Canceling or attending appointments
- Searching for physiotherapists based on expertise

## Features
1. **Patient Management**:
   - Add new patients
   - Remove patients and cancel appointments associated with them

2. **Physiotherapist Management**:
   - Add new physiotherapists and their skills
   - Assign treatments to physiotherapists
   - View physiotherapists by their expertise or name

3. **Appointment Management**:
   - View and book available appointments
   - Cancel or attend appointments using appointment ID or other details like time and treatment name

## Classes

### 1. `Patient`
- Contains patient details such as ID, name, address, and phone number.

### 2. `Physiotherapist` (Inherits `Member`)
- Contains physiotherapist details such as skills, treatments, and appointments.
- Can have multiple treatments and appointments.

### 3. `Appointment`
- Represents an appointment with a physiotherapist.
- Contains the start time, end time, status (Available, Booked, Attended), and the patient associated with the appointment.

### 4. `Treatment`
- Represents a treatment offered by a physiotherapist.
- Has a name and expertise area.

### 5. `Clinic`
- Manages patients, physiotherapists, and appointments.
- Provides methods to add patients, book appointments, and search for physiotherapists by expertise or name.

### 6. `ClinicUtil` (Utility Class)
- Provides utility functions for managing patients, physiotherapists, and appointments.
- Handles operations like booking an appointment, canceling an appointment, and listing all booked appointments.

## How to Use

1. **Adding a New Patient**:
   - Choose option 1 from the main menu to add a new patient.
   - Input the patient's ID, name, address, and phone number.

2. **Booking an Appointment**:
   - Choose option 4 from the main menu to book an appointment.
   - Enter the patient's ID, the desired treatment name, and choose an available time slot from the listed options.

3. **Canceling an Appointment**:
   - Choose option 5 from the main menu to cancel an appointment.
   - Enter the patient’s ID, the treatment name, and the time slot of the appointment to cancel.

4. **Viewing All Booked Appointments**:
   - Choose option 10 from the main menu to see a list of all booked appointments.
   - Review appointment details including the appointment ID, patient, physiotherapist, and status.

---

### Main Menu Options

When interacting with the system, you will see the following menu:

```plaintext
--- Main Menu ---
1. Add a new patient
2. Search by expertise
3. Search by physiotherapist
4. Book an appointment
5. Cancel an appointment
6. Attend appointment
7. Generate report
8. See Patient List
9. Remove Patient
10. List booked appointments
Type 'exit' to quit
Choose an option: 
```

## Dependencies
- Java 8 or above for LocalDateTime and other modern Java features.

## License
This project is open-source and available under the Apache License.

