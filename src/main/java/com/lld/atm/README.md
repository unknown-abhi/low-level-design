# ATM System Design

A complete ATM (Automated Teller Machine) system implementation demonstrating state management, security patterns, and real-world banking operations.

## 📋 Overview

This module simulates a real ATM machine with:
- User authentication and account management
- Withdrawal and deposit operations
- Balance inquiry functionality
- Transaction history
- State-based machine behavior

## 🏗️ Architecture

### Package Structure
```
atm/
├── cor/                    # Chain of Responsibility pattern
├── enums/                  # Enum definitions (TransactionType, AtmState, etc.)
├── factory/                # Factory pattern for object creation
├── model/                  # Data models (Account, Card, Transaction)
├── repository/             # Data persistence layer
├── service/                # Business logic layer
├── state/                  # State pattern implementation
└── Main.java              # Entry point
```

## 📐 UML Class Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                        ATM System                                │
└─────────────────────────────────────────────────────────────────┘

                    ┌──────────────┐
                    │   ATMCard    │
                    ├──────────────┤
                    │ - cardNumber │
                    │ - pin        │
                    │ - expiryDate │
                    └──────────────┘
                           △
                           │
                    ┌──────────────┐
                    │   Account    │
                    ├──────────────┤
                    │ - accountId  │
                    │ - balance    │
                    │ - cardList   │
                    ├──────────────┤
                    │+ deposit()   │
                    │+ withdraw()  │
                    └──────────────┘
                           △
                           │
                    ┌──────────────┐
                    │ ATMService   │
                    ├──────────────┤
                    │ - atm: ATM   │
                    ├──────────────┤
                    │+ authenticate()
                    │+ processWithdraw()
                    │+ processDeposit()
                    └──────────────┘

        ┌──────────────────┐     ┌──────────────────┐
        │   ATMState       │     │  Transaction     │
        │ (Interface)      │     ├──────────────────┤
        ├──────────────────┤     │ - txnId          │
        │+ insertCard()    │     │ - amount         │
        │+ enterPin()      │     │ - type           │
        │+ selectOperation()
        │+ withdraw()      │     │ - timestamp      │
        │+ ejectCard()     │     └──────────────────┘
        └──────────────────┘

    ┌────────┬─────────┬──────────┐
    │        │         │          │
 IdleState WithdrawState DepositState CashOutState
```

## 🔑 Key Features

### 1. **State Pattern**
- `IdleState` - Initial ATM state
- `WithdrawState` - Withdrawal operation state
- `DepositState` - Deposit operation state
- `CashOutState` - Cash dispensing state

### 2. **Chain of Responsibility (COR)**
Used for withdrawal amount validation:
- Validate amount > 0
- Validate account has sufficient balance
- Validate ATM has cash available

### 3. **Factory Pattern**
Creates appropriate transaction objects and state instances

### 4. **Security Features**
- PIN verification
- Card validation
- Transaction logging
- Session management

## 💻 Usage Example

```java
ATM atm = new ATM();
atm.insertCard(atmCard);
atm.enterPin(1234);
atm.selectWithdrawal();
atm.withdraw(500);
atm.ejectCard();
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **State** | ATM operation states management |
| **Chain of Responsibility** | Validation chain for transactions |
| **Factory** | Transaction and state creation |
| **Singleton** | ATM instance management |
| **Repository** | Data persistence abstraction |

## 📊 Transaction Types

- `WITHDRAWAL` - Cash withdrawal
- `DEPOSIT` - Cash deposit
- `BALANCE_INQUIRY` - Check account balance
- `PIN_CHANGE` - Change PIN
- `FAST_CASH` - Quick withdrawal (predefined amounts)

## ✅ Core Methods

### ATM Service
- `authenticate()` - Verify card and PIN
- `processWithdrawal()` - Handle withdrawal request
- `processDeposit()` - Handle deposit request
- `checkBalance()` - Display account balance
- `ejectCard()` - Return card to user

### Account Service
- `withdraw(amount)` - Deduct funds
- `deposit(amount)` - Add funds
- `getBalance()` - Return available balance

## 🧪 Testing

Test cases should cover:
- Valid card insertion and PIN verification
- Withdrawal with sufficient balance
- Withdrawal with insufficient balance
- Deposit operations
- Balance inquiry
- Card ejection after timeout
- Invalid PIN handling

## 📝 Notes

- PIN is validated before any transaction
- Transaction amounts are logged for audit trail
- ATM maintains cash inventory
- Each transaction generates a receipt

---

**Back to [Parent README](../README.md)**
