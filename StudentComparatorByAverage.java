/*
I, Avishay Mamrud (315746560), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Comparator;

public class StudentComparatorByAverage implements Comparator<StudentCard> {
    @Override
    public int compare(StudentCard student1, StudentCard student2) {
    	int ans;
    	if(student1.getAverage()<student2.getAverage()) {
    		ans = -1;
    	}else if(student2.getAverage()<student1.getAverage()) {
    		ans = 1;
    	}else {
    		Comparator<StudentCard> cmprtr = new StudentComparatorById();
    		ans = cmprtr.compare(student1, student2);
    	}
    	return ans;
    }
}
