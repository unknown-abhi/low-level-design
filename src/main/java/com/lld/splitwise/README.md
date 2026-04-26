# Splitwise - Bill Sharing Application

A sophisticated expense sharing and settlement system for tracking shared expenses and calculating debts between friends and groups.

## 📋 Overview

This module implements a real-world expense sharing platform with:
- Expense creation and categorization
- Multiple split types (equal, percentage, exact amount)
- Debt tracking between users
- Settlement suggestion system
- Group expense management
- Payment history tracking
- Expense reports and analytics

## 🏗️ Architecture

### Package Structure
```
splitwise/
├── enums/          # SplitType, TransactionType, PaymentStatus
├── factory/        # ExpenseFactory, SplitFactory
├── model/          # User, Expense, Split, Debt, Payment
├── repository/     # Data persistence layer
├── service/        # ExpenseService, SettlementService
├── strategy/       # Different split strategies
└── Main.java      # Entry point
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│          Splitwise System Architecture                      │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────┐
         │       User       │
         ├──────────────────┤
         │- userId          │
         │- name            │
         │- email           │
         │- phoneNumber     │
         │- totalBalance    │
         │- groups: List    │
         ├──────────────────┤
         │+ addExpense()    │
         │+ payBack()       │
         │+ getBalance()    │
         │+ settleUp()      │
         └──────────────────┘
                 △
        ┌────────┼────────┬──────────┐
        │        │        │          │
        ▼        ▼        ▼          ▼
    ┌──────┐ ┌──────┐ ┌──────┐ ┌──────────┐
    │Group │ │Debt  │ │Expense│ │Settlement│
    └──────┘ └──────┘ └──────┘ └──────────┘

         ┌──────────────────┐
         │     Expense      │
         ├──────────────────┤
         │- expenseId       │
         │- paidBy: User    │
         │- amount          │
         │- description     │
         │- category        │
         │- date            │
         │- splits: List    │
         │- group: Group    │
         ├──────────────────┤
         │+ addSplit()      │
         │+ calculateSplits()
         │+ getDebts()      │
         │+ isPaid()        │
         └──────────────────┘

         ┌──────────────────┐
         │       Split      │
         ├──────────────────┤
         │- splitId         │
         │- user            │
         │- amount          │
         │- type            │
         │- percentage      │
         ├──────────────────┤
         │+ calculateAmount()
         └──────────────────┘

         ┌──────────────────┐
         │       Debt       │
         ├──────────────────┤
         │- debtId          │
         │- creditor: User  │
         │- debtor: User    │
         │- amount          │
         │- amountPaid      │
         │- isSettled       │
         ├──────────────────┤
         │+ addPayment()    │
         │+ settle()        │
         │+ getRemaining()  │
         └──────────────────┘

         ┌──────────────────┐
         │      Group       │
         ├──────────────────┤
         │- groupId         │
         │- groupName       │
         │- members: List   │
         │- expenses: List  │
         │- totalSpent      │
         ├──────────────────┤
         │+ addMember()     │
         │+ addExpense()    │
         │+ getExpenses()   │
         │+ calculateDebts()│
         │+ settle()        │
         └──────────────────┘

         ┌──────────────────┐
         │      Payment     │
         ├──────────────────┤
         │- paymentId       │
         │- from: User      │
         │- to: User        │
         │- amount          │
         │- date            │
         │- method          │
         │- status          │
         └──────────────────┘

    ┌──────────────────────────┐
    │       SplitType          │
    ├──────────────────────────┤
    │- EQUAL                   │
    │- PERCENTAGE              │
    │- EXACT_AMOUNT            │
    │- BY_SHARES               │
    │- ITEM_WISE               │
    └──────────────────────────┘
```

## 🔑 Key Features

### 1. **Split Types**
```
EQUAL: Divide amount equally among participants
  $100 / 4 people = $25 each

PERCENTAGE: Split by percentage
  $100: 50% = $50, 30% = $30, 20% = $20

EXACT_AMOUNT: Each person pays exact amount
  Alice: $30, Bob: $40, Charlie: $30

BY_SHARES: Divide into weighted shares
  2 shares: $40, 3 shares: $60

ITEM_WISE: Split by individual items
  Item1: $20 (2 people)
  Item2: $30 (2 people)
```

### 2. **Group Management**
- Create expense groups
- Manage group members
- Add group expenses
- Track group debts
- Generate group reports

### 3. **Debt Tracking**
- Calculate who owes whom
- Track partial payments
- Settlement calculation
- Debt simplification
- Payment history

### 4. **Settlement Suggestions**
- Minimal payment algorithm
- Optimize settlement payments
- Suggest best payment order
- Group settlement options

### 5. **Categories**
- Groceries
- Utilities
- Entertainment
- Travel
- Meals
- Other

## 💻 Usage Example

```java
// Create splitwise service
SplitwiseService splitwise = new SplitwiseService();

// Create users
User alice = new User("alice@email.com", "Alice");
User bob = new User("bob@email.com", "Bob");
User charlie = new User("charlie@email.com", "Charlie");

// Create group
Group tripGroup = new Group("Vegas Trip");
tripGroup.addMember(alice);
tripGroup.addMember(bob);
tripGroup.addMember(charlie);

// Add expense - Alice paid $300 for hotel
Expense expense = new Expense(
    alice,
    300,
    "Hotel",
    "Vegas trip hotel"
);

// Equal split among 3 people
EqualSplit split1 = new EqualSplit(alice, 100);
EqualSplit split2 = new EqualSplit(bob, 100);
EqualSplit split3 = new EqualSplit(charlie, 100);

expense.addSplit(split1);
expense.addSplit(split2);
expense.addSplit(split3);

tripGroup.addExpense(expense);

// Get debts
List<Debt> debts = tripGroup.calculateDebts();
// Bob owes Alice $100
// Charlie owes Alice $100

// Get settlement plan
List<Payment> settlements = splitwise.getSettlementPlan(debts);
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **Strategy** | Different split types |
| **Factory** | Split and expense creation |
| **Observer** | Debt notifications |
| **Graph Algorithm** | Settlement optimization |
| **Builder** | Expense configuration |

## 📊 Expense Status Flow

```
┌─────────────┐  create  ┌────────────┐  settle  ┌──────────┐
│   Created   │─────────→│   Active   │─────────→│ Settled  │
│   Expense   │          │  (Debt)    │          │ (Paid)   │
└─────────────┘          └────────────┘          └──────────┘
```

## 📋 Settlement Algorithm

The system uses a greedy algorithm for optimal settlement:

```
1. Calculate net balance for each person
   Alice: +$200 (others owe her)
   Bob: -$100 (he owes)
   Charlie: -$100 (he owes)

2. Match creditors with debtors
   Bob pays Alice $100
   Charlie pays Alice $100

3. Minimum payments:
   2 payments instead of 3
```

## ✅ Core Methods

### SplitwiseService
- `createExpense(paidBy, amount, splits)` - Add expense
- `addToGroup(group, expense)` - Add to group
- `settleUp(user1, user2)` - Record payment
- `getBalance(user)` - Total balance
- `getSettlementPlan(debts)` - Optimize settlements

### ExpenseService
- `createExpense(paidBy, amount, description)` - Create expense
- `addSplit(expense, split)` - Add participant split
- `calculateSplits(expense)` - Compute amounts
- `getExpense(id)` - Retrieve expense
- `deleteExpense(id)` - Remove expense

### DebtService
- `calculateDebt(expense)` - Compute debts from expense
- `addPayment(debt, amount)` - Record payment
- `getDebtsBetween(user1, user2)` - Direct debts
- `simplifyDebts(debts)` - Minimize transactions
- `settleDebt(debt)` - Mark as settled

### GroupService
- `createGroup(name)` - New group
- `addMember(group, user)` - Add participant
- `getGroupDebts(group)` - Get all debts
- `getGroupExpenses(group)` - Get expenses
- `generateReport(group)` - Group summary

## 📈 Key Calculations

### Net Balance Calculation
```
Net Balance = Total Money Owed To User - Total Money User Owes
Positive = User is owed money
Negative = User owes money
```

### Settlement Amount
```
Settlement Amount = min(creditor's balance, debtor's balance)
```

## 🧪 Testing Scenarios

Test cases should cover:
- Equal split calculations
- Percentage split calculations
- Exact amount splits
- Group expense management
- Debt calculation accuracy
- Partial payment handling
- Settlement optimization
- Multiple currency (if applicable)
- Expense deletion and recalculation
- Group settlement reporting

## 💳 Payment Methods

- Direct transfer
- Cash payment
- Digital payment (Venmo, PayPal)
- Bank transfer
- Payment tracking

## 📊 Sample Expense Report

```
Group: "Vacation 2026"
Total Expenses: $1,250

Alice paid: $500
  - Owes: $0
  - Owed: $250

Bob paid: $400
  - Owes: $150
  - Owed: $50

Charlie paid: $350
  - Owes: $100
  - Owed: $0

Settlement Plan:
1. Bob pays Alice $100
2. Charlie pays Alice $100
3. Bob pays Alice $50 (alternative: Charlie pays Bob $100)
```

---

**Back to [Parent README](../README.md)**
