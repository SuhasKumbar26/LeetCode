# 3898. Find the Degree of Each Vertex

**Difficulty:** Easy

**LeetCode:** https://leetcode.com/problems/find-the-degree-of-each-vertex/

---

## Problem Statement

You are given a 2D integer array `matrix` of size `n x n` representing the adjacency matrix of an undirected graph with `n` vertices labeled from 0 to `n - 1`.

* `matrix[i][j] = 1` indicates that there is an edge between vertices `i` and `j`.
* `matrix[i][j] = 0` indicates that there is no edge between vertices `i` and `j`.

The **degree** of a vertex is the number of edges connected to it.

Return an integer array `ans` of size `n` where `ans[i]` represents the degree of vertex `i`.

---

## Example 1

**Input:**

```text
matrix = [[0,1,1],
          [1,0,1],
          [1,1,0]]
```

**Output:**

```text
[2,2,2]
```

**Explanation:**

* Vertex 0 is connected to vertices 1 and 2, so its degree is 2.
* Vertex 1 is connected to vertices 0 and 2, so its degree is 2.
* Vertex 2 is connected to vertices 0 and 1, so its degree is 2.

Thus, the answer is `[2, 2, 2]`.

---

## Example 2

**Input:**

```text
matrix = [[0,1,0],
          [1,0,0],
          [0,0,0]]
```

**Output:**

```text
[1,1,0]
```

**Explanation:**

* Vertex 0 is connected to vertex 1, so its degree is 1.
* Vertex 1 is connected to vertex 0, so its degree is 1.
* Vertex 2 is not connected to any vertex, so its degree is 0.

Thus, the answer is `[1, 1, 0]`.

---

## Example 3

**Input:**

```text
matrix = [[0]]
```

**Output:**

```text
[0]
```

**Explanation:**

There is only one vertex and it has no edges connected to it. Thus, the answer is `[0]`.

---

## Constraints

* `1 <= n == matrix.length == matrix[i].length <= 100`
* `matrix[i][i] == 0`
* `matrix[i][j]` is either 0 or 1
* `matrix[i][j] == matrix[j][i]`

---

# Approach

The key idea is to find the **degree of each vertex** by counting the number of `1`s connected to that vertex.

Because the graph is represented using an adjacency matrix, we can look at each **column**.

For vertex `i`, the values:

```text
matrix[0][i]
matrix[1][i]
matrix[2][i]
...
matrix[n-1][i]
```

tell us whether every other vertex is connected to vertex `i`.

So the degree of vertex `i` is:

```text
degree[i] = matrix[0][i]
          + matrix[1][i]
          + ...
          + matrix[n-1][i]
```

In other words:

> **Sum the entire column `i` to get the degree of vertex `i`.**

---

# Step-by-Step Algorithm

### Step 1: Get the number of vertices

```java
int n = matrix.length;
```

If the matrix is:

```text
3 × 3
```

then there are `3` vertices.

---

### Step 2: Create the answer array

```java
int[] degree = new int[n];
```

This stores the degree of every vertex.

Initially:

```text
degree = [0, 0, 0]
```

---

### Step 3: Select each vertex

Use the outer loop:

```java
for(int i = 0; i < n; i++)
```

Here, `i` represents the vertex whose degree we are calculating.

---

### Step 4: Calculate the sum of column `i`

For every vertex `i`, create:

```java
int sum = 0;
```

Then traverse the column:

```java
for(int j = 0; j < n; j++){
    sum += matrix[j][i];
}
```

Notice that we use:

```text
matrix[j][i]
```

not:

```text
matrix[i][j]
```

This means we are moving **down the column**.

---

### Step 5: Store the degree

After summing the entire column:

```java
degree[i] = sum;
```

Now `degree[i]` contains the number of edges connected to vertex `i`.

---

# Dry Run

Consider:

```text
matrix = [
    [0,1,1],
    [1,0,1],
    [1,1,0]
]
```

We start with:

```text
degree = [0,0,0]
```

---

### Calculate degree of vertex 0

Set:

```text
i = 0
sum = 0
```

Read column `0`:

```text
matrix[0][0] = 0
matrix[1][0] = 1
matrix[2][0] = 1
```

Calculate:

```text
sum = 0 + 1 + 1
    = 2
```

Store:

```text
degree[0] = 2
```

Now:

```text
degree = [2,0,0]
```

---

### Calculate degree of vertex 1

Set:

```text
i = 1
sum = 0
```

Read column `1`:

```text
matrix[0][1] = 1
matrix[1][1] = 0
matrix[2][1] = 1
```

Calculate:

```text
sum = 1 + 0 + 1
    = 2
```

Store:

```text
degree[1] = 2
```

Now:

```text
degree = [2,2,0]
```

---

### Calculate degree of vertex 2

Set:

```text
i = 2
sum = 0
```

Read column `2`:

```text
matrix[0][2] = 1
matrix[1][2] = 1
matrix[2][2] = 0
```

Calculate:

```text
sum = 1 + 1 + 0
    = 2
```

Store:

```text
degree[2] = 2
```

Final result:

```text
degree = [2,2,2]
```

---

# Visualizing the Column Approach

For:

```text
        Vertex
          0  1  2

        ┌────────
      0 │ 0  1  1
      1 │ 1  0  1
      2 │ 1  1  0
```

To find the degree of vertex `1`, take **column 1**:

```text
        ↓
      1
      0
      1
```

Sum:

```text
1 + 0 + 1 = 2
```

Therefore:

```text
degree[1] = 2
```

---

# Why This Works

For an undirected graph:

```text
matrix[i][j] == matrix[j][i]
```

So the adjacency matrix is symmetric.

For any vertex `i`, every `1` in column `i` represents another vertex connected to `i`.

Therefore, adding all values in column `i` gives exactly the number of edges connected to vertex `i`.

Since we calculate this for every column, we get the degree of every vertex.

---

# Important Detail

Your solution uses:

```java
sum += matrix[j][i];
```

This means:

```text
j → moves down
i → stays fixed
```

So for every `i`, we calculate:

```text
column i
```

The result is then stored in:

```java
degree[i]
```

Because the matrix is symmetric, summing rows would also produce the same result, but your implementation specifically uses the **column approach**.

---

# Complexity

There are `n` vertices.

For every vertex, we traverse `n` elements.

Therefore:

### Time Complexity

```text
O(n²)
```

### Space Complexity

```text
O(n)
```

The `degree` array contains `n` elements.

---

# Key Learning

The main graph concept from this problem is understanding how an adjacency matrix represents connections.

The core idea is:

```text
Choose vertex i
      ↓
Traverse column i
      ↓
Add all values
      ↓
Store sum in degree[i]
```

Or simply:

> **Degree of vertex `i` = sum of column `i` in the adjacency matrix.**

This is a simple graph problem, but understanding this representation is an important foundation for more advanced graph algorithms.
