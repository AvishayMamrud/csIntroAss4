/*
I, Avishay Mamrud (315746560), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.NoSuchElementException;

public class University {
    private StudentCardBinarySearchTree idTree;
    private StudentCardBinarySearchTree avgTree;

    public University() {
        idTree = new StudentCardBinarySearchTree(new StudentComparatorById());
        avgTree = new StudentCardBinarySearchTree(new StudentComparatorByAverage());
    }

    public StudentCard lookUp(int idNumber) {
        StudentCard lookFor = new StudentCard("", idNumber);
        return idTree.findData(lookFor);
    }

    public void balance() {
        idTree.balance();
        avgTree.balance();
    }

    public boolean add(StudentCard newStudent) {
    	boolean added = false;
    	if(lookUp(newStudent.getId())==null) {
    		idTree.insert(newStudent);
    		avgTree.insert(newStudent);
    		added = true;
    	}
    	return added;
    }


    public boolean delete(StudentCard student) {
    	boolean deleted = false;
    	if(lookUp(student.getId())!=null) {
	    	idTree.remove(student);
	    	avgTree.remove(student);
	    	deleted = true;
    	}
    	return deleted;
    }


    public boolean register(int id, int curse) {
    	StudentCard myStudent1 = lookUp(id);
    	return myStudent1.registerCourse(curse);
    }

    public boolean complete(int id, int curse, int grade) {
    	StudentCard myStudent = lookUp(id);
    	boolean ans = false;
    	if(myStudent!=null) {
	    	ans = myStudent.completeCourse(curse, grade);
	    	avgTree.remove(myStudent);
	    	avgTree.insert(myStudent);
    	}
    	return ans;
    }

    public double courseAverage(int course) {
    	double sum = 0;
    	int numOfStudents = 0;
    	for (StudentCard currStudent : avgTree) {
    		if(currStudent.hasCourse(course)!=-1 && currStudent.courseGrade(course)!=-100) {
    			numOfStudents++;
    			sum = sum + currStudent.courseGrade(course);
    		}
		}
    	if(numOfStudents==0) {
    		throw new NoSuchElementException("course " + course + " does not exists.");
    	}
    	return sum/numOfStudents;
    }
    
    public void activeStudentsByAverage() {
    	Stack<StudentCard> myStack = new StackAsDynamicArray<StudentCard>();
    	for (StudentCard studentCard : avgTree) {
			if(studentCard.isActive() & studentCard.getAverage()!=-50) {
				myStack.push(studentCard);
			}
		}
    	while (!myStack.isEmpty()) {
    		StudentCard currStudent = myStack.pop();
			System.out.println(currStudent);
		}
    	
    }
}
