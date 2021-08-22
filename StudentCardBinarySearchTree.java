/*
I, Avishay Mamrud (315746560), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Comparator;

public class StudentCardBinarySearchTree extends BinarySearchTree<StudentCard> {


    public StudentCardBinarySearchTree(Comparator<StudentCard> myComparator) {
        super(myComparator);
    }

    public void balance() {
    	List<StudentCard> List = new DynamicArray<StudentCard>();
    	StudentCardBinarySearchTree myNewTree = new StudentCardBinarySearchTree(getComparator());
    	for (StudentCard currSC : this) {
    		List.add(currSC);
		}
    	buildBalancedTree(myNewTree, List, 0, List.size()-1);
    }

    private void buildBalancedTree(StudentCardBinarySearchTree tree, List<StudentCard> list, int low, int high) {
    	int middle = (high + low)/2;// + (high + low)%2;
    	if(list.size()>0) {
	    	tree.insert(list.get(middle));
	    	if(middle+1<=high)
	    		buildBalancedTree(tree, list, middle+1, high);
	    	if(middle-1>=low)
	    		buildBalancedTree(tree, list, low, middle-1);
	    	this.root = tree.root;
    	}
    }
}