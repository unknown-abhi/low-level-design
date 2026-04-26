# Logging System Design

A comprehensive multi-level logging system with multiple appenders and flexible configuration options for application-wide logging.

## 📋 Overview

This module implements a production-ready logging system with:
- Multiple log levels (DEBUG, INFO, WARN, ERROR)
- Various output appenders (Console, File, Database)
- Customizable log formatting
- Log filtering and routing
- Thread-safe operations
- Configuration management

## 🏗️ Architecture

### Package Structure
```
logger/
├── appenders/          # Log output destinations (ConsoleAppender, FileAppender, etc.)
├── enums/              # LogLevel enum
├── formatter/          # Log message formatting
├── handlers/           # LogHandler interface and implementations
├── model/              # LogMessage, LogEvent models
├── Logger.java         # Main logger interface
├── LogHandlerConfiguration.java # Configuration management
└── Main.java          # Entry point
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│               Logging System Architecture                   │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────┐
         │     Logger       │ (Singleton)
         ├──────────────────┤
         │- handlers: List  │
         │- level           │
         ├──────────────────┤
         │+ debug()         │
         │+ info()          │
         │+ warn()          │
         │+ error()         │
         │+ addHandler()    │
         └──────────────────┘
                 │
         contains many
                 │
                 ▼
         ┌──────────────────┐
         │   LogHandler     │ (Interface)
         ├──────────────────┤
         │+ handle()        │
         │+ setFormatter()  │
         │+ setLevel()      │
         └──────────────────┘
                 △
         ┌───┬──┴───┬──────────┐
         │   │      │          │
    Console File Database Socket
    Handler Handler Handler   Handler

         ┌──────────────────┐
         │   LogFormatter   │
         ├──────────────────┤
         │+ format()        │
         │+ setPattern()    │
         └──────────────────┘

         ┌──────────────────┐
         │    LogMessage    │
         ├──────────────────┤
         │- timestamp       │
         │- level           │
         │- message         │
         │- thread          │
         │- className       │
         │- methodName      │
         │- lineNumber      │
         └──────────────────┘

    ┌──────────────────┐
    │    LogLevel      │
    ├──────────────────┤
    │- DEBUG           │
    │- INFO            │
    │- WARN            │
    │- ERROR           │
    └──────────────────┘
```

## 🔑 Key Features

### 1. **Log Levels**
- **DEBUG** - Detailed diagnostic information
- **INFO** - Informational messages
- **WARN** - Warning messages (potential issues)
- **ERROR** - Error messages (failures)

### 2. **Appenders (Output Destinations)**
- **ConsoleAppender** - Output to console
- **FileAppender** - Write to file
- **DatabaseAppender** - Store in database
- **SocketAppender** - Send over network

### 3. **Formatting Options**
Customizable patterns:
```
[TIMESTAMP] [LEVEL] [THREAD] [CLASS] - MESSAGE
[yyyy-MM-dd HH:mm:ss] [INFO] [main] [com.example.App] - User logged in
```

### 4. **Configuration Management**
- Set log levels globally
- Configure appenders per logger
- Dynamic reconfiguration
- Property-based setup

### 5. **Thread Safety**
- Synchronized logging operations
- Safe for multi-threaded applications
- Lock-free appenders where possible

## 💻 Usage Example

```java
// Get logger instance (Singleton)
Logger logger = Logger.getInstance();

// Configure appenders
logger.addHandler(new ConsoleHandler(LogLevel.DEBUG));
logger.addHandler(new FileHandler("app.log", LogLevel.INFO));

// Use logging
logger.debug("Application started");
logger.info("Processing request");
logger.warn("Deprecated method called");
logger.error("Database connection failed", exception);

// Configure specific handler
FileHandler fileHandler = new FileHandler("error.log");
fileHandler.setLevel(LogLevel.ERROR);
logger.addHandler(fileHandler);
```

## 🎯 Design Patterns Used

| Pattern | Purpose |
|---------|---------|
| **Singleton** | Single logger instance |
| **Strategy** | Different appenders and formatters |
| **Observer** | Handler notification on log events |
| **Builder** | Complex configuration setup |
| **Chain of Responsibility** | Log level filtering |

## 📊 Log Message Properties

| Property | Description |
|----------|-------------|
| `timestamp` | When log occurred |
| `level` | Log severity level |
| `message` | Log message text |
| `thread` | Thread name/ID |
| `className` | Source class name |
| `methodName` | Source method name |
| `lineNumber` | Source line number |
| `throwable` | Exception (if any) |

## ✅ Core Methods

### Logger
- `debug(message)` - Log debug message
- `info(message)` - Log info message
- `warn(message)` - Log warning message
- `error(message, exception)` - Log error
- `addHandler(handler)` - Add appender
- `removeHandler(handler)` - Remove appender
- `setLevel(level)` - Set minimum log level

### LogHandler
- `handle(logMessage)` - Process log message
- `setFormatter(formatter)` - Set output format
- `setLevel(level)` - Set minimum level
- `flush()` - Flush buffer
- `close()` - Close handler

## 📝 Sample Log Output

```
[2026-04-27 10:15:32] [INFO]  [Thread-1] [com.app.UserService] - User login successful
[2026-04-27 10:15:33] [DEBUG] [Thread-1] [com.app.DataAccess] - Executing query: SELECT * FROM users
[2026-04-27 10:15:35] [WARN]  [Thread-2] [com.app.Cache] - Cache memory usage at 85%
[2026-04-27 10:15:36] [ERROR] [Thread-3] [com.app.Database] - Connection timeout after 30s
```

## 🧪 Testing

Test cases should cover:
- Logging at different levels
- Multiple appenders simultaneously
- Log formatting correctness
- Thread-safe operations
- Large log file handling
- Configuration changes at runtime
- Performance under load

## 🔧 Configuration Example

```java
LogHandlerConfiguration config = new LogHandlerConfiguration();
config.setLevel(LogLevel.INFO);
config.addAppender(new ConsoleAppender());
config.addAppender(new FileAppender("application.log"));
config.setPattern("[%d] [%level] [%thread] - %msg");

Logger logger = Logger.getInstance();
logger.configure(config);
```

## 🚀 Performance Considerations

- Lazy initialization of file handles
- Batch writing to reduce I/O
- Asynchronous logging option
- Memory-efficient message queuing
- Configurable buffer sizes

---

**Back to [Parent README](../README.md)**
