import java.util.Arrays;

class MinimumOperationsToMakeArraySumDivisibleByK {

    public int minOperations(int[] nums, int k) {
        return Arrays.stream(nums).sum() % k;
    }
}