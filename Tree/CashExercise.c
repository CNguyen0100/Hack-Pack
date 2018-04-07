// Arup Guha
// 1/3/2014

// Solution to COP 3502H Spring 2013 Cash Exercise

// Given a binary tree with family inheritance, determine the maximal sum of cash
// stored in a subset of nodes where for no node is there another node in the subset
// that is in the subtree specified by that node.

#include <stdio.h>

struct node {
    int ID;
    int cash;
    struct node* left;
    struct node* right;
};

typedef struct node nodeT;

nodeT* insert(nodeT* root, nodeT* newNode, int pathlen, int path);
nodeT* makeNewNode(int myID, int mycash);
int maxValue(nodeT* root);

int main() {

    nodeT* root = makeNewNode(8, 1237);
    root = insert(root,makeNewNode(17, 386), 1, 0);
    root = insert(root,makeNewNode(3, 888), 1, 1);
    root = insert(root,makeNewNode(19, 85), 2, 0);
    root = insert(root,makeNewNode(14, 394), 2, 2);
    root = insert(root,makeNewNode(9, 323), 2, 1);
    root = insert(root,makeNewNode(21, 296), 2, 3);
    root = insert(root,makeNewNode(27, 121), 3, 2);
    root = insert(root,makeNewNode(12, 191), 3, 1);
    root = insert(root,makeNewNode(11, 133), 3, 3);
    root = insert(root,makeNewNode(29,164), 3, 7);
    root = insert(root,makeNewNode(16, 45), 4, 2);
    root = insert(root,makeNewNode(13,149), 4, 10);
    root = insert(root,makeNewNode(23, 89), 4, 1);
    root = insert(root,makeNewNode(10,100), 4, 9);
    root = insert(root,makeNewNode(26,200), 4, 7);
    root = insert(root,makeNewNode(24, 99), 5, 2);
    root = insert(root,makeNewNode(15, 87), 5, 10);
    root = insert(root,makeNewNode(7, 64), 5, 26);
    root = insert(root,makeNewNode(6, 131), 5, 7);
    root = insert(root,makeNewNode(28, 68), 5, 23);
    root = insert(root,makeNewNode(22, 63), 6, 2);
    root = insert(root,makeNewNode(2, 37), 6, 34);
    root = insert(root,makeNewNode(18, 54), 6, 26);
    root = insert(root,makeNewNode(25, 7), 6, 58);
    printf("Max value for this tree is %d.\n", maxValue(root));

    return 0;
}

// Inserts newNode into the tree rooted at root at a distance pathlen from the root.
// path in binary specifies the path to insert the node. This path must be valid or there
// will be a NPE. Least significant bit of 0 indicates that the next move is left. If it's
// 1, go right...
nodeT* insert(nodeT* root, nodeT* newNode, int pathlen, int path) {

    if (pathlen == 0) return newNode;

    if (path%2 == 0)
        root->left = insert(root->left, newNode, pathlen-1, path/2);
    else
        root->right = insert(root->right, newNode, pathlen-1, path/2);

    return root;
}

// Returns a single new node with these parameters.
nodeT* makeNewNode(int myID, int mycash) {
    nodeT* node = malloc(sizeof(nodeT));
    node->ID = myID;
    node->cash = mycash;
    node->left = NULL;
    node->right = NULL;
}

// Solves the problem at hand.
int maxValue(nodeT* root) {

    // Easy answer.
    if (root == NULL) return 0;

    // Try left and right, if not null.
    int leftSide = maxValue(root->left);
    int rightSide = maxValue(root->right);

    // I just want to choose which is larger, the root of the sum of what I
    // can obtain in both the left and right trees.
    if (root->cash > leftSide + rightSide)
        return root->cash;
    return leftSide + rightSide;
}
