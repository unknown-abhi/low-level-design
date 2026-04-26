# Custom HashMap Implementation

A complete HashMap implementation from scratch demonstrating hash functions, collision resolution, and dynamic resizing.

## 📋 Overview

This module implements a fully functional HashMap data structure with:
- Custom hash function
- Collision handling using separate chaining
- Dynamic resizing and rehashing
- Generic type support
- O(1) average time complexity for operations
- Load factor management

## 🏗️ Architecture

### Implementation Details
```
hashmap/
├── CustomHashMap.java     # Main HashMap implementation
└── Main.java             # Usage examples and tests
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│              CustomHashMap<K, V> Implementation            │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────────────────┐
         │    CustomHashMap<K, V>       │
         ├──────────────────────────────┤
         │ - buckets: Entry<K,V>[]      │
         │ - size: int                  │
         │ - capacity: int              │
         │ - loadFactor: float          │
         ├──────────────────────────────┤
         │ + put(key, value)            │
         │ + get(key)                   │
         │ + remove(key)                │
         │ + containsKey(key)           │
         │ + size()                     │
         │ + isEmpty()                  │
         │ + clear()                    │
         │ - hash(key)                  │
         │ - resize()                   │
         │ - rehash()                   │
         └──────────────────────────────┘
                      │
          contains many
                      │
                      ▼
         ┌──────────────────────────────┐
         │    Entry<K, V>               │
         │   (Linked List Node)         │
         ├──────────────────────────────┤
         │ - key: K                     │
         │ - value: V                   │
         │ - hash: int                  │
         │ - next: Entry<K, V>          │
         └──────────────────────────────┘
```

## 🔑 Key Features

### 1. **Hash Function**
```
hash(key) = Math.abs(key.hashCode()) % capacity
```
- Distributes keys evenly
- Reduces collisions
- Handles negative hashes

### 2. **Collision Handling (Separate Chaining)**
- Uses linked lists for collision resolution
- Each bucket contains chain of entries
- O(1 + load_factor) average access time

### 3. **Dynamic Resizing**
- Automatically increases capacity when load factor exceeded
- Triggers rehashing of all entries
- Maintains performance characteristics
- Default load factor: 0.75

### 4. **Operations**

| Operation | Time Complexity | Space |
|-----------|-----------------|-------|
| put() | O(1) average, O(n) worst | O(n) |
| get() | O(1) average, O(n) worst | O(1) |
| remove() | O(1) average, O(n) worst | O(1) |
| containsKey() | O(1) average, O(n) worst | O(1) |

### 5. **Load Factor**
```
Load Factor = size / capacity
Resize when: Load Factor > 0.75
New Capacity = old_capacity * 2
```

## 💻 Usage Example

```java
// Create HashMap with initial capacity
CustomHashMap<String, Integer> map = new CustomHashMap<>(16);

// Put operations
map.put("apple", 1);
map.put("banana", 2);
map.put("cherry", 3);

// Get operations
int value = map.get("apple");  // Returns 1
System.out.println(value);

// Check containment
if (map.containsKey("banana")) {
    System.out.println("Found banana");
}

// Remove operations
map.remove("banana");

// Get size
System.out.println("Size: " + map.size());

// Iterate through entries
for (String key : map.keySet()) {
    System.out.println(key + " -> " + map.get(key));
}

// Clear all
map.clear();
```

## 🎯 Design Principles

### 1. **Hash Function Properties**
- Deterministic (same input → same hash)
- Uniform distribution
- Efficient computation
- Collision minimization

### 2. **Collision Resolution**
- Separate chaining (linked lists)
- Handles multiple keys with same hash
- Simple implementation
- Effective for uniform hashing

### 3. **Capacity Management**
- Powers of 2 for efficiency
- Load factor threshold
- Geometric growth (doubling)
- Rehashing on resize

### 4. **Generic Type Support**
```java
<K, V> where K = key type, V = value type
```

## 📊 Internal Structure

```
Capacity = 16
┌─────┬─────┬─────┬─────┬─────┬─────┐
│ 0   │ 1   │ 2   │ 3   │ ... │ 15  │  (buckets array)
├─────┴─────┴─────┴─────┴─────┴─────┤
│                                   │
│ [key1→val1] → [key17→val17] → [...] (collision chain)
│ [key2→val2] → null
│ null
│ [key3→val3] → null
```

## ✅ Core Methods

### Main Operations
```java
public V put(K key, V value)        // Add/update
public V get(K key)                 // Retrieve
public V remove(K key)              // Delete
public boolean containsKey(K key)   // Check existence
public int size()                   // Get element count
public boolean isEmpty()            // Check if empty
public void clear()                 // Remove all
```

### Helper Methods
```java
private int hash(K key)             // Hash function
private void resize()               // Increase capacity
private void rehash()               // Redistribute entries
```

## 🧪 Testing Scenarios

Test cases should cover:
- Basic put/get/remove operations
- Hash collisions handling
- Capacity resizing and rehashing
- Null key/value handling
- Load factor threshold
- Performance under load
- Iterator functionality
- Edge cases (empty map, duplicate keys)

## 📈 Performance Characteristics

| Operation | Best Case | Average | Worst Case |
|-----------|-----------|---------|------------|
| Put | O(1) | O(1) | O(n) |
| Get | O(1) | O(1) | O(n) |
| Remove | O(1) | O(1) | O(n) |
| Space | - | O(n) | O(n) |

*Worst case occurs with poor hash function or many collisions*

## 🔍 Example Execution

```
Initial: capacity=16, size=0

put("alice", 100):
  hash = 5 (simplified)
  buckets[5] = alice→100
  
put("bob", 200):
  hash = 5 (collision!)
  buckets[5] = bob→200 → alice→100
  
get("bob"):
  hash = 5
  Search chain: bob→200 → alice→100
  Found! Return 200

When size > capacity * 0.75 (12 entries):
  Resize: capacity = 32
  Rehash all entries with new hash function
```

---

**Back to [Parent README](../README.md)**
