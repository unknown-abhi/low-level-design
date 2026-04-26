# Customer Support System

A comprehensive customer support and ticketing system with priority-based ticket assignment, escalation management, and SLA tracking.

## 📋 Overview

This module implements a real-world customer support system with:
- Ticket creation and management
- Priority-based queuing
- Automatic assignment to support agents
- Ticket escalation workflow
- SLA (Service Level Agreement) tracking
- Customer feedback and ratings
- Ticket history and resolution tracking

## 🏗️ Architecture

### Package Structure
```
customer_support/
├── enums/          # TicketPriority, TicketStatus, Department
├── model/          # Ticket, Customer, Agent, Resolution
├── repository/     # Data persistence layer
├── service/        # TicketService, AssignmentService
├── strategy/       # Assignment and routing strategies
└── Main.java      # Entry point
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│       Customer Support System Architecture                  │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────┐
         │     Ticket       │
         ├──────────────────┤
         │- ticketId        │
         │- customer        │
         │- title           │
         │- description     │
         │- priority        │
         │- status          │
         │- assignedAgent   │
         │- createdAt       │
         │- updatedAt       │
         │- resolvedAt      │
         ├──────────────────┤
         │+ assignAgent()   │
         │+ updateStatus()  │
         │+ addNote()       │
         │+ escalate()      │
         │+ close()         │
         └──────────────────┘
                 △
        ┌────────┼────────┬──────────┐
        │        │        │          │
        ▼        ▼        ▼          ▼
    ┌──────┐ ┌──────┐ ┌──────┐ ┌──────────┐
    │Cust. │ │Agent │ │Note  │ │Resolution│
    └──────┘ └──────┘ └──────┘ └──────────┘

         ┌──────────────────┐
         │     Customer     │
         ├──────────────────┤
         │- customerId      │
         │- name            │
         │- email           │
         │- phone           │
         │- tier: String    │
         │- tickets: List   │
         ├──────────────────┤
         │+ createTicket()  │
         │+ addFeedback()   │
         │+ getTierLevel()  │
         └──────────────────┘

         ┌──────────────────┐
         │       Agent      │
         ├──────────────────┤
         │- agentId         │
         │- name            │
         │- email           │
         │- department      │
         │- activeTickets   │
         │- maxCapacity     │
         │- isAvailable     │
         ├──────────────────┤
         │+ assignTicket()  │
         │+ resolveTicket() │
         │+ canAccept()     │
         │+ getWorkload()   │
         └──────────────────┘

         ┌──────────────────┐
         │     TicketNote   │
         ├──────────────────┤
         │- noteId          │
         │- ticket          │
         │- author          │
         │- content         │
         │- isPublic        │
         │- createdAt       │
         └──────────────────┘

         ┌──────────────────┐
         │    Resolution    │
         ├──────────────────┤
         │- resolutionId    │
         │- ticket          │
         │- solution        │
         │- resolutionTime  │
         │- satisfactory    │
         └──────────────────┘

    ┌──────────────────────────┐
    │    TicketPriority        │
    ├──────────────────────────┤
    │- LOW                     │
    │- MEDIUM                  │
    │- HIGH                    │
    │- CRITICAL                │
    └──────────────────────────┘

    ┌──────────────────────────┐
    │     TicketStatus         │
    ├──────────────────────────┤
    │- OPEN                    │
    │- ASSIGNED                │
    │- IN_PROGRESS             │
    │- WAITING_CUSTOMER        │
    │- ESCALATED              │
    │- RESOLVED               │
    │- CLOSED                 │
    └──────────────────────────┘
```

## 🔑 Key Features

### 1. **Ticket Management**
- Create support tickets from various channels
- Track ticket lifecycle
- Assign to appropriate agents
- Add internal notes
- Customer communication

### 2. **Priority System**
```
Level 1: CRITICAL   - System down, urgent resolution needed
Level 2: HIGH       - Major functionality affected
Level 3: MEDIUM     - Minor issues, workaround available
Level 4: LOW        - Cosmetic issues, nice-to-have improvements
```

### 3. **Assignment Strategy**
- Round-robin assignment
- Load-based assignment (least busy agent)
- Skill-based assignment (matching tags)
- Automatic escalation if unresolved

### 4. **SLA Tracking**
```
CRITICAL:  4 hours response, 8 hours resolution
HIGH:      8 hours response, 24 hours resolution
MEDIUM:    24 hours response, 48 hours resolution
LOW:       48 hours response, 1 week resolution
```

### 5. **Escalation Workflow**
- Automatic escalation on SLA breach
- Manual escalation by agent
- Escalation to supervisor
- Priority boost on escalation

## 💻 Usage Example

```java
// Create support service
CustomerSupportService supportService = new CustomerSupportService();

// Create customer and ticket
Customer customer = new Customer("John", "john@email.com");
Ticket ticket = new Ticket(
    "Database connection failed",
    "Unable to connect to database",
    TicketPriority.CRITICAL
);

// Create ticket
ticket = supportService.createTicket(customer, ticket);
System.out.println("Ticket created: " + ticket.getTicketId());

// Assign to agent
Agent agent = supportService.findAvailableAgent();
supportService.assignTicket(ticket, agent);

// Add notes
supportService.addNote(ticket, agent, "Investigating database connection");

// Resolve ticket
supportService.resolveTicket(ticket, agent, "Restarted database service");

// Get feedback
supportService.addFeedback(ticket, customer, 5, "Very helpful!");
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **Strategy** | Different assignment strategies |
| **Observer** | Notify customers of updates |
| **Factory** | Create tickets and resolutions |
| **Queue** | Priority-based ticket queue |
| **Command** | Ticket actions and history |

## 📊 Ticket Status Flow

```
┌─────────┐   create   ┌────────┐   assign   ┌──────────┐
│  OPEN   │──────────→ │ASSIGNED│──────────→ │IN_PROGRESS│
└─────────┘            └────────┘            └──────────┘
                            ▲                     │
                            │                     │ resolve
                            │                     ▼
                    escalate │              ┌──────────┐
                            │              │ RESOLVED │
                            └──────────────│──────────┘
                                           │
                                      close│
                                           ▼
                                       ┌────────┐
                                       │ CLOSED │
                                       └────────┘
```

## ✅ Core Methods

### CustomerSupportService
- `createTicket(customer, ticket)` - Create new support ticket
- `assignTicket(ticket, agent)` - Assign to agent
- `reassignTicket(ticket, newAgent)` - Change assignment
- `resolveTicket(ticket, agent, solution)` - Mark resolved
- `escalateTicket(ticket)` - Escalate priority
- `closeTicket(ticket)` - Close completed ticket
- `findAvailableAgent()` - Find next agent

### TicketService
- `addNote(ticket, author, content)` - Add internal note
- `updateStatus(ticket, status)` - Change ticket status
- `getTicket(ticketId)` - Retrieve ticket
- `getCustomerTickets(customer)` - Get customer's tickets
- `searchTickets(criteria)` - Search with filters

### AgentService
- `assignTicket(agent, ticket)` - Assign to agent
- `completeTicket(agent, ticket)` - Mark complete
- `getWorkload(agent)` - Get current load
- `canAccept(agent)` - Check availability
- `updateAvailability(agent, available)` - Set availability

## 📈 Metrics & Reporting

```
Key Metrics:
- Average Response Time
- Average Resolution Time
- SLA Compliance Rate
- Customer Satisfaction Score
- Agent Performance Rating
- Ticket Volume per Agent
- Escalation Rate
```

## 🧪 Testing Scenarios

Test cases should cover:
- Ticket creation and validation
- Automatic agent assignment
- Load-based routing
- SLA tracking and violations
- Escalation workflow
- Status transitions
- Ticket search and filtering
- Customer feedback
- Performance metrics

## 🔔 Notification System

Tickets trigger notifications:
- Ticket created → Customer confirmation
- Assigned → Agent notification
- Updated → Customer & Agent notifications
- Resolved → Customer feedback request
- SLA breach → Manager alert
- Escalated → Supervisor notification

---

**Back to [Parent README](../README.md)**
