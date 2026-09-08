# 3512. Minimum Operations to Make Array Sum Divisible by K

**Difficulty:** Easy

**LeetCode:** https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/

---

## Problem Statement

You are given an integer array `nums` and an integer `k`. You can perform the following operation any number of times:

* Select an index `i` and replace `nums[i]` with `nums[i] - 1`.

Return the **minimum** number of operations required to make the sum of the array divisible by `k`.

---

## Example 1

**Input:**

```text
nums = [3,9,7], k = 5
```

**Output:**

```text
4
```

**Explanation:**

* Perform 4 operations on `nums[1] = 9`. Now, `nums = [3, 5, 7]`.
* The sum is 15, which is divisible by 5.

---

## Example 2

**Input:**

```text
nums = [4,1,3], k = 4
```

**Output:**

```text
0
```

**Explanation:**

* The sum is 8, which is already divisible by 4. Hence, no operations are needed.

---

## Example 3

**Input:**

```text
nums = [3,2], k = 6
```

**Output:**

```text
5
```

**Explanation:**

* Perform 3 operations on `nums[0] = 3` and 2 operations on `nums[1] = 2`. Now, `nums = [0, 0]`.
* The sum is 0, which is divisible by 6.

---

## Constraints

* `1 <= nums.length <= 1000`
* `1 <= nums[i] <= 1000`
* `1 <= k <= 100`

---

# Approach

The key observation is that **every operation decreases the total sum by exactly 1**.

Suppose the sum of the array is:

```text
sum
```

We need to make:

```text
sum % k == 0
```

The remainder:

```text
sum % k
```

tells us exactly how many times we need to decrease the sum to reach the nearest smaller multiple of `k`.

Therefore:

```text
minimum operations = sum % k
```

---

# Step-by-Step Algorithm

### Step 1: Calculate the total sum

Use Java's `Arrays.stream(nums).sum()` to calculate the sum of all elements.

For example:

```text
nums = [3, 9, 7]

sum = 3 + 9 + 7
    = 19
```

---

### Step 2: Find the remainder

Calculate:

```text
sum % k
```

For:

```text
sum = 19
k = 5
```

we get:

```text
19 % 5 = 4
```

This means the sum is 4 greater than the previous multiple of 5.

```text
19
↓ decrease by 4
15
```

So we need **4 operations**.

---

### Step 3: Return the remainder

Each operation decreases one array element by `1`.

Therefore, each operation also decreases the total sum by `1`.

So if the remainder is `4`, exactly 4 operations are required.

```text
return sum % k;
```

---

# Dry Run — Example 1

```text
nums = [3, 9, 7]
k = 5
```

### Calculate sum

```text
3 + 9 + 7 = 19
```

### Calculate remainder

```text
19 % 5 = 4
```

Therefore:

```text
Minimum operations = 4
```

We can reduce `9` by 4:

```text
[3, 9, 7]
     ↓
[3, 5, 7]
```

New sum:

```text
3 + 5 + 7 = 15
```

And:

```text
15 % 5 = 0
```

Answer:

```text
4
```

---

# Dry Run — Example 2

```text
nums = [4, 1, 3]
k = 4
```

Sum:

```text
4 + 1 + 3 = 8
```

Remainder:

```text
8 % 4 = 0
```

The sum is already divisible by `4`.

Therefore:

```text
Minimum operations = 0
```

---

# Dry Run — Example 3

```text
nums = [3, 2]
k = 6
```

Sum:

```text
3 + 2 = 5
```

Remainder:

```text
5 % 6 = 5
```

We need to decrease the sum by 5:

```text
5
↓
4
↓
3
↓
2
↓
1
↓
0
```

That requires:

```text
5 operations
```

The final array can become:

```text
[0, 0]
```

Sum:

```text
0
```

And:

```text
0 % 6 = 0
```

Answer:

```text
5
```

---

# Why This Works

The operation is:

```text
nums[i] = nums[i] - 1
```

So the total array sum changes like this:

```text
sum → sum - 1
```

If:

```text
sum % k = r
```

then subtracting `r` gives:

```text
sum - r
```

which is divisible by `k`.

Therefore:

```text
minimum operations = sum % k
```

The actual element we decrease does not matter, because every operation has the same effect on the total sum.

---

# Key Learning

The important pattern in this problem is:

> **When an operation changes a global value by exactly 1, check whether the remainder directly gives the answer.**

Instead of simulating every decrement, we can directly calculate:

```text
sum % k
```

This turns the problem into a simple mathematical observation.

---

# Complexity

### Time Complexity

```text
O(n)
```

We need to calculate the sum of all `n` elements.

### Space Complexity

```text
O(1)
```

No additional data structure is required.

---

# Solution

```java
class MinimumOperationsToMakeArraySumDivisibleByK {

    public int minOperations(int[] nums, int k) {
        return Arrays.stream(nums).sum() % k;
    }
}
```
