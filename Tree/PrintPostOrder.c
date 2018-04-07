// Arup Guha
// 1/3/2014
// Solution to COP 3502 Exercise: Given a preorder and inorder traversal of a binary tree, print out
// the corresponding postorder traversal of that tree.

#include <stdio.h>

void printPostRec(int* preOrder, int* inOrder, int startPre, int startIn, int length);
int findIndex(int* array, int item, int startIndex);

int main() {
    int pre[14] = {10, 3, 8, 7, 6, 12, 14, 1, 47, 13, 9, 18, 22, 5};
    int in[14] = {8, 3, 6, 7, 12, 10, 47, 1, 13, 14, 18, 9, 5, 22};
    printPostRec(pre, in, 0, 0, 14);
    return 0;
}

void printPostRec(int* preOrder, int* inOrder, int startPre, int startIn, int length) {

    // Base cases.
    if (length < 1) return;

    // Figure out where the root item is and the sizes of both sides of the tree.
    int middle = findIndex(inOrder, preOrder[startPre], startIn);
    int sizeLeft = middle - startIn;
    int sizeRight = length - sizeLeft - 1;

    // Recursively print both sides of the tree.
    printPostRec(preOrder, inOrder, startPre+1, startIn, sizeLeft);
    printPostRec(preOrder, inOrder, startPre+1+sizeLeft, startIn+sizeLeft+1, sizeRight);
    printf("%d ", preOrder[startPre]);
}

// Pre-condition: array stores item in startIndex or higher.
int findIndex(int* array, int item, int startIndex) {
    while (array[startIndex] != item) startIndex++;
    return startIndex;
}
