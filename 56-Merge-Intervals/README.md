# 56. Merge Intervals

[LeetCode Problem](https://leetcode.com/problems/merge-intervals/)

**Difficulty:** Medium

## Problem Statement

Given an array of `intervals` where `intervals[i] = [starti, endi]`, merge all overlapping intervals, and return *an array of the non-overlapping intervals that cover all the intervals in the input*.

## Examples

### Example 1

```text
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
```

### Example 2

```text
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
```

### Example 3

```text
Input: intervals = [[4,7],[1,4]]
Output: [[1,7]]
Explanation: Intervals [1,4] and [4,7] are considered overlapping.
```

## Constraints

* `1 <= intervals.length <= 10^4`
* `intervals[i].length == 2`
* `0 <= starti <= endi <= 10^4`

## Approach

The key idea is:

> **Sort the intervals by their starting value, then compare each interval with the last interval in the result.**

### Step 1: Sort by Start

First, sort all intervals according to their starting value.

For example:

```text
[[4,7], [1,4]]
```

becomes:

```text
[[1,4], [4,7]]
```

This makes it easier to determine whether the next interval overlaps with the previous one.

---

### Step 2: Add the First Interval

The first interval becomes the first interval in our result.

Example:

```text
intervals = [[1,3],[2,6],[8,10],[15,18]]
```

Initially:

```text
res = [[1,3]]
```

We call `[1,3]` the **previous/current merged interval**.

---

### Step 3: Compare the Current Interval

For every interval, get the last interval from `res`:

```java
int[] pre = res.get(res.size() - 1);
```

This is the interval we need to compare against.

Suppose:

```text
pre = [1,3]
cur = [2,6]
```

Now check:

```text
cur[0] <= pre[1]
```

which means:

```text
2 <= 3
```

So the intervals overlap.

---

### Step 4: Merge Overlapping Intervals

When intervals overlap, we keep the smaller start and choose the larger end.

For:

```text
pre = [1,3]
cur = [2,6]
```

the merged interval becomes:

```text
[1,6]
```

The code does:

```java
pre[1] = Math.max(pre[1], cur[1]);
```

So:

```text
Math.max(3, 6) = 6
```

and:

```text
pre = [1,6]
```

---

### Step 5: What if There is No Overlap?

Suppose:

```text
pre = [1,6]
cur = [8,10]
```

Check:

```text
cur[0] <= pre[1]

8 <= 6 → false
```

There is no overlap.

Therefore, `[8,10]` starts a new interval:

```text
res = [[1,6], [8,10]]
```

---

## Important Part: Why `cur[0] <= pre[1]`?

This is the main condition of the problem.

We only need to check whether the **current interval's start** is before or equal to the **previous interval's end**.

### Overlap

```text
[1--------6]
      [4--------8]
```

Current start = `4`

Previous end = `6`

```text
4 <= 6 ✓
```

So merge them:

```text
[1-------------8]
```

### No Overlap

```text
[1--------6]

             [8--------10]
```

Current start = `8`

Previous end = `6`

```text
8 <= 6 ✗
```

So keep them separate.

---

## Important: Why `Math.max()`?

Consider:

```text
pre = [1,10]
cur = [2,6]
```

They overlap, but the current interval ends earlier.

If we simply used `cur[1]`, we would incorrectly get:

```text
[1,6]
```

Instead:

```java
Math.max(pre[1], cur[1])
```

gives:

```text
Math.max(10,6) = 10
```

So we correctly keep:

```text
[1,10]
```

This ensures we never lose the farthest ending point of the merged interval.

---

## Complete Walkthrough

Input:

```text
[[1,3],[2,6],[8,10],[15,18]]
```

Already sorted.

### Start

```text
res = [[1,3]]
```

### Current = `[1,3]`

It overlaps with itself, so the result remains:

```text
[[1,3]]
```

### Current = `[2,6]`

Compare:

```text
2 <= 3 ✓
```

Merge:

```text
[1,3] + [2,6]
      ↓
   [1,6]
```

Result:

```text
[[1,6]]
```

### Current = `[8,10]`

Compare:

```text
8 <= 6 ✗
```

No overlap.

Add it:

```text
[[1,6],[8,10]]
```

### Current = `[15,18]`

Compare:

```text
15 <= 10 ✗
```

No overlap.

Add it:

```text
[[1,6],[8,10],[15,18]]
```

### Final Answer

```text
[[1,6],[8,10],[15,18]]
```

---

## Special Case: Touching Intervals

For:

```text
[[1,4],[4,5]]
```

Check:

```text
4 <= 4 ✓
```

They are considered overlapping according to the problem.

So:

```text
[1,4] + [4,5]
      ↓
   [1,5]
```

---

## Why the Algorithm Works

Sorting guarantees that intervals are processed from the smallest start to the largest start.

For every current interval, we only need to compare it with the **last interval in the result**:

```text
Sort
 ↓
Take current interval
 ↓
Compare with last result interval
 ↓
Overlap?
 ↙       ↘
YES       NO
 ↓         ↓
Merge    Add new interval
```

If they overlap, extend the existing interval.

If they don't overlap, the current interval cannot overlap with any earlier result interval because the intervals are sorted by start.

---

## Complexity

### Time Complexity

**O(n log n)**

Sorting takes `O(n log n)`.

The single loop takes `O(n)`.

Therefore:

```text
O(n log n) + O(n)
= O(n log n)
```

### Space Complexity

**O(n)**

The result list can contain up to `n` non-overlapping intervals.

The sorting operation may also use additional space depending on the implementation.

---

## Key Takeaway

```text
SORT BY START
      ↓
COMPARE CURRENT START
WITH PREVIOUS END
      ↓
OVERLAP?
 ↙          ↘
YES          NO
 ↓            ↓
MERGE       ADD NEW
```

The most important condition to remember is:

```java
if (cur[0] <= pre[1])
```

and when merging:

```java
pre[1] = Math.max(pre[1], cur[1]);
```
