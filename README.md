# 🧬 Genetic Algorithm Knapsack Solver

This project implements a **Genetic Algorithm** in Java to solve the **0/1 Knapsack Problem**, where the objective is to find the best combination of items to load onto a plane without exceeding a weight limit, while maximizing the total value.

---

## 🚀 Why It’s Cool

- 🧠 Uses bio-inspired computation (evolution, crossover, mutation).
- 📦 Models real-world resource allocation problems.
- 🛠️ Highly modular and easy to extend.
- 📊 Provides visual insight into how solutions evolve over generations.

---

## 📦 Project Structure & Component Roles

| Component | Purpose |
|----------|---------|
| `Main.java` | Application entry point. Takes user inputs and starts the optimization process. |
| `GeneticAlgorithm.java` | Core evolutionary algorithm: selection, crossover, mutation, generation loop. |
| `Population.java` | Manages chromosomes, calculates fitness (value/weight), and builds new generations. |
| `ItemChromosome.java` | Represents a single solution (binary string). Supports mutation and cloning. |
| `Shop.java` | Randomly generates items with weights and values. Acts as the item source. |
| `Track.java` | Associates chromosomes with performance metrics for ranking. |
| `TrackComparator.java` | Comparator used to sort `Track` instances based on fitness. |
| `Bound.java` | Utility class for generating random integers within a given range. |
| `Plane.java` | (Optional/Extendable) Represents the carrying capacity of a plane. |

---

## 📐 UML Class Diagram

> 🖼️ 
> ![image](https://github.com/user-attachments/assets/e56c87ff-acb8-49ba-a87e-439ff8a7f6d3)


---

## ⚙️ Requirements

Ensure the following is installed on your system:

- [Java JDK 17 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/) or your favorite build tool (optional but helpful)
- A terminal or IDE (e.g., IntelliJ, Eclipse)

---

## ▶️ How to Run

### 1. **Clone the Repository**
```bash
git clone https://github.com/your-username/genetic-knapsack.git
cd genetic-knapsack
```

### 2. **Compile the Project**
```bash
javac -d out src/com/company/*.java
```

### 3. **Run the Program**
```bash
java -cp out com.company.Main
```

### 4. **Follow the Prompts**
You will be asked to provide:

- Crossover Rate (e.g., `0.8`)
- Mutation Rate (e.g., `0.05`)
- Plane Threshold (e.g., `300`)

Then, the algorithm will run for 20 generations and print the best found solution.

---

## 🧠 How It Works

- **Initialization**: A random population of chromosomes is created.
- **Selection**: The top performers are selected based on their value-to-weight performance.
- **Crossover**: Selected parents are combined to produce new offspring.
- **Mutation**: Some genes in the offspring are randomly flipped.
- **Evaluation**: This process repeats for 20 generations. At the end, the best chromosome is printed.



