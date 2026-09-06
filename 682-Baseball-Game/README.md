# 682. Baseball Game

[LeetCode Problem](https://leetcode.com/problems/baseball-game/)

**Difficulty:** Easy

## Problem Statement

You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.

You are given a list of strings `operations`, where `operations[i]` is the `ith` operation you must apply to the record and is one of the following:

* An integer `x`.

    * Record a new score of `x`.
* `'+'`.

    * Record a new score that is the sum of the previous two scores.
* `'D'`.

    * Record a new score that is the double of the previous score.
* `'C'`.

    * Invalidate the previous score, removing it from the record.

Return *the sum of all the scores on the record after applying all the operations*.

The test cases are generated such that the answer and all intermediate calculations fit in a **32-bit** integer and that all operations are valid.

## Example 1

```text
Input: ops = ["5","2","C","D","+"]
Output: 30
Explanation:
"5" - Add 5 to the record, record is now [5].
"2" - Add 2 to the record, record is now [5, 2].
"C" - Invalidate and remove the previous score, record is now [5].
"D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
"+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
```

## Example 2

```text
Input: ops = ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation:
"5" - Add 5 to the record, record is now [5].
"-2" - Add -2 to the record, record is now [5, -2].
"4" - Add 4 to the record, record is now [5, -2, 4].
"C" - Invalidate and remove the previous score, record is now [5, -2].
"D" - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
"9" - Add 9 to the record, record is now [5, -2, -4, 9].
"+" - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
"+" - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
```

## Example 3

```text
Input: ops = ["1","C"]
Output: 0
Explanation:
"1" - Add 1 to the record, record is now [1].
"C" - Invalidate and remove the previous score, record is now [].
Since the record is empty, the total sum is 0.
```

## Constraints

* `1 <= operations.length <= 1000`
* `operations[i]` is `"C"`, `"D"`, `"+"`, or a string representing an integer in the range `[-3 * 104, 3 * 104]`.
* For operation `"+"`, there will always be at least two previous scores on the record.
* For operations `"C"` and `"D"`, there will always be at least one previous score on the record.

## Approach

We use a **Stack** to maintain all valid scores in the record.

### Step 1: Process each operation

Traverse through every operation.

### Step 2: Handle `"C"`

`"C"` means the previous score is invalid.

So, remove the top element using:

```text
st.pop()
```

### Step 3: Handle `"D"`

`"D"` means double the previous score.

The previous score is at the top of the stack:

```text
st.peek()
```

Double it and push the result:

```text
st.push(st.peek() * 2)
```

### Step 4: Handle `"+"`

`"+"` means add the previous two scores.

First, temporarily remove the last score using `pop()`.

Then:

* `first` = last score
* `second` = second-last score

Calculate:

```text
first + second
```

Restore `first` and push the new score.

### Step 5: Handle an integer

If the operation is not `"C"`, `"D"`, or `"+"`, it is an integer.

Convert it using `Integer.parseInt()` and push it onto the stack.

### Step 6: Calculate the total

After processing all operations, pop every score from the stack and add them to `ans`.

## Complexity

* **Time Complexity:** O(n)
* **Space Complexity:** O(n)
