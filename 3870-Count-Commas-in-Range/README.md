# 3870. Count Commas in Range

**Difficulty:** Easy

**LeetCode:** https://leetcode.com/problems/count-commas-in-range/

---

## Problem Statement

You are given an integer `n`.

Return the **total** number of commas used when writing all integers from `[1, n]` (inclusive) in **standard** number formatting.

In **standard** formatting:

* A comma is inserted after **every three** digits from the right.
* Numbers with **fewer** than 4 digits contain no commas.

---

## Example 1

**Input:**

```text
n = 1002
```

**Output:**

```text
3
```

**Explanation:**

The numbers `"1,000"`, `"1,001"`, and `"1,002"` each contain one comma, giving a total of 3.

---

## Example 2

**Input:**

```text
n = 998
```

**Output:**

```text
0
```

**Explanation:**

All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.

---

## Constraints

* `1 <= n <= 10^5`

---

# Approach

The key observation is that **every number from 1000 onward contains exactly one comma** within the given constraint.

Let's divide the numbers into two parts.

### Numbers from 1 to 999

These numbers have fewer than 4 digits.

For example:

```text
1
25
100
999
```

None of them contains a comma.

Therefore:

```text
1 → 999
= 0 commas
```

---

### Numbers from 1000 onward

Starting from `1000`, every number contains exactly one comma.

For example:

```text
1000   → 1,000 → 1 comma
1001   → 1,001 → 1 comma
9999   → 9,999 → 1 comma
10000  → 10,000 → 1 comma
99999  → 99,999 → 1 comma
100000 → 100,000 → 1 comma
```

Because the constraint is:

```text
n <= 100000
```

every number from `1000` through `n` contributes exactly **one comma**.

So we only need to count how many numbers are present from `1000` to `n`.

---

# Formula

The number of integers from `1000` to `n` is:

```text
n - 1000 + 1
```

Simplifying:

```text
n - 999
```

Therefore:

```text
if n < 1000
    answer = 0

else
    answer = n - 999
```

---

# Step-by-Step Algorithm

### Step 1: Check whether `n` is below 1000

```java
if (n < 1000) return 0;
```

If `n` is less than `1000`, none of the numbers contain a comma.

Example:

```text
n = 998

1 → 998
```

Answer:

```text
0
```

---

### Step 2: Count numbers from 1000 to n

If `n >= 1000`, all numbers from `1000` to `n` contain exactly one comma.

The count is:

```text
n - 999
```

So:

```java
return n - 999;
```

---

# Dry Run

Let's take:

```text
n = 1002
```

Numbers containing commas:

```text
1000 → "1,000" → 1 comma
1001 → "1,001" → 1 comma
1002 → "1,002" → 1 comma
```

Number of such numbers:

```text
1002 - 999
```

```text
= 3
```

Therefore:

```text
Answer = 3
```

---

# Another Dry Run

Let's take:

```text
n = 998
```

Since:

```text
998 < 1000
```

we immediately return:

```text
0
```

No loop is required.

---

# Edge Case: n = 100000

This is the maximum value allowed by the constraint.

From:

```text
1000 → 99999
```

there are:

```text
99999 - 1000 + 1
= 99000
```

numbers.

Then:

```text
100000 → "100,000"
```

also contains exactly one comma.

So:

```text
99000 + 1 = 99001
```

Our formula gives the same result:

```text
100000 - 999 = 99001
```

Therefore, the same formula works for the entire given constraint.

---

# Why This Works

The important pattern is:

```text
1 ───────── 999 | 1000 ───────────────── 100000
                 ↑
            First number
            with a comma
```

Numbers before `1000` contribute nothing.

Every number from `1000` through `100000` contributes exactly one comma.

So instead of checking every number individually, we simply count how many numbers are in that range.

This reduces the problem to a constant-time mathematical calculation.

---

# Complexity

### Time Complexity

```text
O(1)
```

We only perform a condition check and a subtraction.

### Space Complexity

```text
O(1)
```

No additional data structure is used.

---

# Key Learning

The biggest takeaway from this problem is:

> **Before using a loop, look for a mathematical pattern.**

Instead of checking:

```text
1, 2, 3, 4, ... n
```

we identify the first number that contains a comma:

```text
1000
```

Then simply count all numbers from `1000` to `n`.

A simple observation turns the problem into an **O(1)** solution.
