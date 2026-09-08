# 704. Binary Search

**Difficulty:** Easy

**LeetCode:** https://leetcode.com/problems/binary-search/

## Problem Statement

Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`. If `target` exists, then return its index. Otherwise, return `-1`.

You must write an algorithm with `O(log n)` runtime complexity.

## Example 1

```text
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
```

## Example 2

```text
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
```

## Constraints

* `1 <= nums.length <= 104`
* `-104 < nums[i], target < 104`
* All the integers in `nums` are **unique**.
* `nums` is sorted in ascending order.

---

# Approach

The array is already sorted, so we can use **Binary Search**.

Instead of checking every element one by one, binary search repeatedly checks the middle element and eliminates half of the remaining search space.

We maintain two pointers:

* `left` → beginning of the search range
* `right` → end of the search range

Then calculate:

```text
mid = left + (right - left) / 2
```

## Algorithm

1. Set `left = 0`.
2. Set `right = nums.length - 1`.
3. While `left <= right`:

    * Calculate the middle index.
    * If `nums[mid] == target`, return `mid`.
    * If `nums[mid] < target`, the target must be on the right side, so:

      ```text
      left = mid + 1
      ```
    * If `nums[mid] > target`, the target must be on the left side, so:

      ```text
      right = mid - 1
      ```
4. If the loop finishes, the target does not exist, so return `-1`.

---

# Dry Run

### Input

```text
nums = [-1, 0, 3, 5, 9, 12]
target = 9
```

### Step 1

```text
left = 0
right = 5

mid = 0 + (5 - 0) / 2
    = 2

nums[mid] = nums[2] = 3
```

Compare:

```text
3 < 9
```

So the target must be on the right.

```text
left = mid + 1
left = 3
```

Search range:

```text
[5, 9, 12]
 ↑        ↑
left    right
```

### Step 2

```text
left = 3
right = 5

mid = 3 + (5 - 3) / 2
    = 4

nums[4] = 9
```

Now:

```text
nums[mid] == target
9 == 9
```

Therefore:

```text
return 4
```

### Answer

```text
4
```

---

# Why Binary Search Works

The important property is that the array is **sorted in ascending order**.

If:

```text
nums[mid] < target
```

everything before or at `mid` is too small, so we can discard that entire half.

If:

```text
nums[mid] > target
```

everything after or at `mid` is too large, so we discard that half.

Therefore, every iteration removes approximately half of the remaining elements.

---

# Example 2

```text
nums = [-1,0,3,5,9,12]
target = 2
```

Start:

```text
left = 0
right = 5
mid = 2
nums[mid] = 3
```

Since:

```text
3 > 2
```

search left:

```text
right = mid - 1
right = 1
```

Now:

```text
left = 0
right = 1
mid = 0
nums[0] = -1
```

Since:

```text
-1 < 2
```

search right:

```text
left = 1
```

Now:

```text
left = 1
right = 1
mid = 1
nums[1] = 0
```

Since:

```text
0 < 2
```

move right:

```text
left = 2
```

Now:

```text
left > right
```

The search is finished.

```text
return -1
```

---

# Important Formula

Instead of:

```java
int mid = (left + right) / 2;
```

we use:

```java
int mid = left + (right - left) / 2;
```

This avoids potential integer overflow when `left` and `right` are very large.

---

# Complexity

### Time Complexity

**O(log n)**

Each iteration eliminates approximately half of the search space.

### Space Complexity

**O(1)**

Only a few variables such as `left`, `right`, and `mid` are used.

---

# Key Learning

Binary Search is useful when:

* The data is sorted.
* We need to repeatedly eliminate half of the search space.
* The required time complexity is `O(log n)`.

The core pattern to remember is:

```text
left → mid → right

target == nums[mid] → found
target > nums[mid]  → move left
target < nums[mid]  → move right
```
