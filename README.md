# MultiThread Java Demos

This repository is a small collection of Java concurrency examples. It covers basic thread concepts, executor services, callables, producer-consumer patterns, and a simple parallel sum problem.

## Project Layout

- `CachedThreadPool/` - demo of `Executors.newCachedThreadPool()`.
- `Callables/` - callable examples, fixed and single-thread executors, and a combined thread-concepts demo.
- `FixedThreadPool/` - demo of `Executors.newFixedThreadPool()`.
- `MillionsRecord/` - parallel sum over a large array using a fixed thread pool.
- `ProducerConsumer/` - naive producer-consumer implementation without synchronization.
- `ProducerConsumerWithSemaphore/` - producer-consumer solution using semaphores.
- `SyncProducerConsumer/` - producer-consumer solution using `synchronized` blocks.
- `WebScarpper/` - simulated web scraping example comparing fixed and cached thread pools with Runnable and Callable tasks.

## What Each Demo Shows

### Thread pools and callables

- `Callables/CallableWithExecutors.java` - submits a `Callable` to a single-thread executor and reads the result with `Future`.
- `Callables/CallableWithMultiThreads.java` - submits callable tasks to a fixed thread pool.
- `Callables/ThreadConceptsDemo.java` - demonstrates `wait()` / `notify()`, `join()`, and executor-based task execution.
- `FixedThreadPool/FixedThreadPool.java` - submits many Runnable tasks to a fixed-size pool.
- `CachedThreadPool/CacheThreadPool.java` - submits batches of tasks to a cached thread pool.

### Parallel computation

- `MillionsRecord/ParallelSumProblem.java` and `MillionsRecord/SumTask.java` - split a one-million-element array across available CPU cores and combine partial sums.

### Producer-consumer patterns

- `ProducerConsumer/NaiveProducerConsumerDemo.java` - basic producer-consumer example without thread safety guarantees.
- `SyncProducerConsumer/SyncProducerConsumer.java` - uses `synchronized` blocks to guard access to shared state.
- `ProducerConsumerWithSemaphore/ProducerConsumerWithSemaphore.java` - coordinates producers and consumers using semaphores.

### Web scraping simulation

- `WebScarpper/WebScrapper.java` - compares fixed and cached thread pools using Runnable and Callable tasks.
- `WebScarpper/ScrapURL.java` - Runnable task used by the scraper demo.
- `WebScarpper/ScarpUrlCallable.java` - Callable task used by the scraper demo.

## Requirements

- Java 8 or later
- A terminal or IDE that can compile and run plain Java source files

## How To Run

Some files use the default package and should be compiled from inside their folder. Others use a package declaration and should be compiled from the repository root.

### Default-package examples

From the repository root:

```powershell
cd Callables
javac CallableWithExecutors.java
java CallableWithExecutors
```

For the other default-package demos, replace the file name with the class you want to run.

### Packaged examples

From the repository root:

```powershell
javac MillionsRecord\\ParallelSumProblem.java MillionsRecord\\SumTask.java
java MillionsRecord.ParallelSumProblem
```

```powershell
javac WebScarpper\\WebScrapper.java WebScarpper\\ScrapURL.java WebScarpper\\ScarpUrlCallable.java
java WebScarpper.WebScrapper
```

## Notes

- Several demos are intentionally simple and run in an infinite loop so you can observe coordination behavior.
- The web scraper demo is simulated with `Thread.sleep()`; it does not make real HTTP requests.
- If you want, I can also add a small `run` section for each folder with copy-paste commands.