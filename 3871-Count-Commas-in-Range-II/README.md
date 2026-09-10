# 3871. Count Commas in Range II

**Difficulty:** Medium

**LeetCode:** https://leetcode.com/problems/count-commas-in-range-ii/

## Problem Statement

You are given an integer `n`.

Return the **total** number of commas used when writing all integers from `[1, n]` (inclusive) in **standard** number formatting.

In **standard** formatting:

* A comma is inserted after **every three** digits from the right.
* Numbers with **fewer** than 4 digits contain no commas.

## Example 1

**Input:** `n = 1002`

**Output:** `3`

**Explanation:**

The numbers `"1,000"`, `"1,001"`, and `"1,002"` each contain one comma, giving a total of 3.

## Example 2

**Input:** `n = 998`

**Output:** `0`

**Explanation:**

All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.

## Constraints

* `1 <= n <= 10^15`

---

# Approach

We cannot check every number from `1` to `n` because `n` can be as large as `10^15`.

Instead, we group numbers according to how many commas they contain.

### Comma Ranges

```text
1 → 999
0 commas

1,000 → 999,999
1 comma

1,000,000 → 999,999,999
2 commas

1,000,000,000 → 999,999,999,999
3 commas
```

The solution processes one complete range at a time.

---

# Algorithm

### Step 1: Initialize

```text
comma = 1
total = 0
start = 1000
```

We start at `1000` because numbers below `1000` contain no commas.

---

### Step 2: Find the End of the Current Range

```text
end = start * 1000 - 1
```

For example:

```text
start = 1000

end = 1000 * 1000 - 1
    = 999999
```

So the first range is:

```text
1000 → 999999
```

Every number in this range has exactly **1 comma**.

---

### Step 3: Limit the Range to `n`

The calculated `end` may be larger than `n`.

For example, when:

```text
n = 1002
```

we calculate:

```text
end = 999999
```

But we only need to go up to `1002`.

Therefore:

```java
if (end > n) {
    end = n;
}
```

---

### Step 4: Count Numbers in the Range

```text
num = end - start + 1
```

The `+1` is required because both endpoints are included.

For:

```text
start = 1000
end = 1002
```

we get:

```text
num = 1002 - 1000 + 1
    = 3
```

So there are three numbers:

```text
1000
1001
1002
```

---

### Step 5: Calculate Commas

Every number in the current range contains the same number of commas.

Therefore:

```text
total += num * comma
```

For the example:

```text
num = 3
comma = 1

total = 3 * 1
      = 3
```

---

### Step 6: Move to the Next Range

After processing the current range:

```text
start *= 1000
comma++
```

So:

```text
start:
1000
↓
1,000,000
↓
1,000,000,000
↓
1,000,000,000,000
```

And:

```text
comma:
1 → 2 → 3 → 4
```

---

# Complete Dry Run

## Input

```text
n = 1002
```

### Initial State

```text
comma = 1
total = 0
start = 1000
```

### Iteration 1

Find `end`:

```text
end = start * 1000 - 1
    = 1000 * 1000 - 1
    = 999999
```

But:

```text
999999 > 1002
```

So:

```text
end = 1002
```

Count numbers:

```text
num = end - start + 1
    = 1002 - 1000 + 1
    = 3
```

Calculate total commas:

```text
total += num * comma
      = 3 * 1
      = 3
```

Move to the next range:

```text
start = 1000 * 1000
      = 1,000,000

comma = 2
```

Now:

```text
start > n
```

So the loop stops.

### Final Answer

```text
3
```

---

# Larger Example

Consider:

```text
n = 1,000,002
```

### First Range

```text
start = 1,000
end = 999,999
comma = 1
```

Number of values:

```text
999,999 - 1,000 + 1
= 999,000
```

Contribution:

```text
999,000 × 1
= 999,000
```

### Second Range

Now:

```text
start = 1,000,000
comma = 2
```

The calculated end is much larger than `n`, so:

```text
end = 1,000,002
```

Number of values:

```text
1,000,002 - 1,000,000 + 1
= 3
```

Each number has 2 commas:

```text
1,000,000 → 2
1,000,001 → 2
1,000,002 → 2
```

Contribution:

```text
3 × 2 = 6
```

Final total:

```text
999,000 + 6
= 999,006
```

---

# Important Variables

### `start`

The first number in the current comma range.

```text
1000 → 1000000 → 1000000000 → ...
```

### `end`

The last number in the current range.

```text
end = start * 1000 - 1
```

### `comma`

The number of commas in every number in the current range.

```text
1 → 2 → 3 → 4 → ...
```

### `num`

The number of integers in the current range.

```text
num = end - start + 1
```

### `total`

Stores the total number of commas found across all processed ranges.

---

# Why This Works

The key observation is that numbers with the same number of digits have the same number of commas.

For example:

```text
1,000       → 1 comma
50,000      → 1 comma
999,999     → 1 comma

1,000,000   → 2 commas
50,000,000  → 2 commas
999,999,999 → 2 commas
```

So instead of processing every number individually, we process an entire range at once:

```text
Number of numbers × commas per number
```

This makes the solution efficient even when `n` is extremely large.

---

# Complexity

### Time Complexity

**O(log₁₀ n)**

The loop jumps by a factor of `1000` each iteration, so only a small number of ranges are processed.

### Space Complexity

**O(1)**

Only a fixed number of variables are used.

---

# Key Learning

When `n` is very large, avoid processing every number individually.

Look for patterns and group the numbers:

```text
GROUP
  ↓
COUNT NUMBERS
  ↓
MULTIPLY BY COMMAS
  ↓
ADD TO TOTAL
```

The main optimization comes from processing **ranges instead of individual numbers**.
