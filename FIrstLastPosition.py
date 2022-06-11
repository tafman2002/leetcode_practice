"""
Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
"""
def BinarySearch(sortedArray, low, high, target):
    # Check base case
    if high >= low:
 
        mid = (high + low) // 2
 
        # If element is present at the middle itself
        if sortedArray[mid] == target:
            return mid
 
        # If element is smaller than mid, then it can only
        # be present in left subarray
        elif sortedArray[mid] > target:
            return BinarySearch(sortedArray, low, mid - 1, target)
 
        # Else the element can only be present in right subarray
        else:
            return BinarySearch(sortedArray, mid + 1, high, target)
 
    else:
        # Element is not present in the array
        return -1

def searchRange(sortedArray: list, target: int) -> list:
    targetIndex = BinarySearch(sortedArray,0,len(sortedArray)-1,target)
    if targetIndex == -1:
        foundRange = [-1,-1]
        return foundRange
    else:
        foundRange = []
        for i in range(targetIndex, 0,-1):
            if (sortedArray[i] != target):
                foundRange.append(i+1)
                break
        foundRange.append(targetIndex)
        return foundRange

def main():
    print(searchRange([5,7,7,8,8,10], 8))
    print(searchRange([5,6,7,8,12,12,15,15,15,20,21],15))


if __name__ == "__main__":
    main()