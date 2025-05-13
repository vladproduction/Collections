# Java Collections Framework (JCF) Playground

Welcome to the **Java Collections Framework (JCF) Playground** — a curated repository of learning projects, code examples, and reference implementations for mastering the Java Collections Framework. Whether you're preparing for interviews, brushing up on core Java, or building efficient applications, this repo provides a solid foundation.

---

## 📚 What is the Java Collections Framework?

The **Java Collections Framework** is a unified architecture for storing and manipulating groups of objects. It provides **interfaces**, **implementations**, and **algorithms** to handle data structures efficiently.

JCF is a **core part of the Java Standard Library** (`java.util`) and was introduced in Java 2 (JDK 1.2).

---

## 🧠 Core Concepts

### 🔹 Interfaces

JCF defines a set of core interfaces, which are the **foundation of collections**:

- `Collection<E>` – The root interface for all collections.
- `List<E>` – An ordered collection (e.g., `ArrayList`, `LinkedList`).
- `Set<E>` – A collection that does not allow duplicate elements (e.g., `HashSet`, `TreeSet`).
- `SortedSet<E>` / `NavigableSet<E>` – Sorted set variants (e.g., `TreeSet`).
- `Queue<E>` / `Deque<E>` – Used for holding elements prior to processing (e.g., `LinkedList`, `ArrayDeque`).
- `Map<K, V>` – A mapping between keys and values (e.g., `HashMap`, `TreeMap`, `LinkedHashMap`).
- `SortedMap<K, V>` / `NavigableMap<K, V>` – Sorted key-value pairs (e.g., `TreeMap`).

### 🔹 Implementations

Each interface has multiple **concrete classes** optimized for specific needs:

| Interface | Implementation Examples     |
|-----------|-----------------------------|
| List      | `ArrayList`, `LinkedList`   |
| Set       | `HashSet`, `LinkedHashSet`, `TreeSet` |
| Queue     | `PriorityQueue`, `ArrayDeque` |
| Map       | `HashMap`, `TreeMap`, `LinkedHashMap`, `Hashtable` |

### 🔹 Utility Classes

- `Collections` – A utility class with static methods for sorting, searching, synchronization, etc.
- `Arrays` – Similar utilities for arrays.

---

## ⚙️ Why Use the Java Collections Framework?

- ✅ **Standardization** – One consistent API across implementations.
- ✅ **Efficiency** – Built-in algorithms like sorting, searching, shuffling.
- ✅ **Flexibility** – Replace one implementation with another easily.
- ✅ **Thread Safety** – Utilities for synchronized collections.
- ✅ **Rich API** – Offers powerful iteration patterns and functional programming support (e.g., with `Stream` API in Java 8+).

---





