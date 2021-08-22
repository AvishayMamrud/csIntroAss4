/*
I, Avishay Mamrud (315746560), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteredStudentCardIterator implements Iterator<StudentCard> {
	private BinaryTreeInOrderIterator<StudentCard> iterator;
    private StudentCard current;
    private Filter<StudentCard> filter;

    public FilteredStudentCardIterator(StudentCardBinarySearchTree StudentCardsTree, Filter<StudentCard> filter) {
    	this.filter = filter;
    	iterator = new BinaryTreeInOrderIterator<StudentCard>(StudentCardsTree.root);
    	if(iterator.hasNext()) {
    		nextHelper();
    	}else {
    		current = null;
    	}
    }

    public boolean hasNext() {
    	return current!=null;
    }

    public StudentCard next() {
    	if(current==null) {
    		throw new NoSuchElementException("iterator has no next element");
    	}
    	StudentCard ans = current;
    	nextHelper();
    	return ans;
    }
    
    //assistance function that sets initial current or next current after next() function is called
    private void nextHelper() {
    	boolean foundNext = false;
    	while(!foundNext) {
    		if(iterator.hasNext()) {
        		current = iterator.next();
        		foundNext = filter.accept(current);
    		}else {
    			foundNext = true;
    			current = null;
    		}
    	}
    }
    // Do not delete or change or implement this method
    public void remove(){
    	throw new UnsupportedOperationException("Do not change this line");
    }

}
