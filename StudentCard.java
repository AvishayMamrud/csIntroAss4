import java.util.Iterator;

/*
I, Avishay Mamrud (315746560), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/


public class StudentCard {
    private static final int DEFAULT_NOT_COMPLETED_GRADE = -100;
    private String name;
    private int id;
    private double average;
    private int numOfCompletedCourses;
    private List<SimpleIntPair> courses;

    public StudentCard(String name, int id) {
        this.name = name;
        this.id = id;
        this.numOfCompletedCourses = 0;
        this.average = -50;
        this.courses = new DynamicArray<>();
    }

    /**
     * A getter of the field name.
     *
     * @return the field name..
     */
    public String getName() {
        return name;
    }

    /**
     * A getter of the field ID.
     *
     * @return the field ID..
     */
    public int getId() {
        return id;
    }


    /**
     * A getter of the field average.
     *
     * @return the field average.
     */
    public double getAverage() {
        return average;
    }


    /**
     * This method searches for a course in the list of courses.
     *
     * @return the index of the course if found, -1 otherwise.
     */
    public int hasCourse(int course) {
    	int ans = -1;
    	Iterator<SimpleIntPair> Itr = courses.iterator();
    	int counter = 0;
    	int currCourse = -1;
    	while(Itr.hasNext() & currCourse<course) {
    		currCourse = Itr.next().getFirst();
    		if(currCourse==course) {
    			ans = counter;
    		}
    		counter = counter + 1;
    	}
    	return ans;
    }

    /**
     * This method checks if the student has a course in progress.
     *
     * @return true if the student has a course in progress.
     */
    public boolean isActive() {
    	Iterator<SimpleIntPair> Itr = courses.iterator();
    	boolean isActive = false;
    	while(Itr.hasNext() & !isActive) {
    		if(Itr.next().getSecond()==-100) {
    			isActive = true;
    		}
    	}
    	return isActive;
    }

    /**
     * This method returns the grade of the student in the requested course.
     *
     * @return the grade if the course appears in the courses list, -50 otherwise.
     */
    public int courseGrade(int course) {
    	int courseIndex = hasCourse(course);
    	if(courseIndex==-1) {
    		throw new IllegalArgumentException("no such course for this student");
    	}
    	return courses.get(courseIndex).getSecond();
    }

    /**
     * This method adds a course with the default non completed grade.
     *
     * @return true if the course was not in the list and false otherwise.
     */
    public boolean registerCourse(int course) {
    	boolean done = true;
    	Iterator<SimpleIntPair> Itr = courses.iterator();
    	SimpleIntPair myPair = new SimpleIntPair(course, DEFAULT_NOT_COMPLETED_GRADE);
    	int counter = 0;
    	int currCourse = -1;
    	while(Itr.hasNext() & currCourse<course) {
    		currCourse = Itr.next().getFirst();
    		if(currCourse==course) {
    			done = false;
    		}else if(currCourse>course) {
    			courses.add(counter, myPair);
    		}
    		counter = counter + 1;
    	}
    	if(courses.size()==counter & currCourse<course) {
			done = courses.add(myPair);
    	}
    	return done;
    }

    /**
     * This method completes a course by updating the grade.
     *
     * @return true if the student was registered to the course and it was in progress.
     */
    public boolean completeCourse(int course, int grade) {
    	boolean done = false;
    	if(grade>100 | grade<0) {
    		throw new IllegalArgumentException("give me a valid grade");
    	}
    	Iterator<SimpleIntPair> Itr = courses.iterator();
    	int currCourseNum = -1;
    	while(Itr.hasNext() & currCourseNum<course) {
    		SimpleIntPair currCourse = Itr.next();
    		currCourseNum = currCourse.getFirst();
    		if(currCourseNum==course) {
    			if(currCourse.getSecond()==-100) {
    				currCourse.setSecond(grade);
	    			average = (average*numOfCompletedCourses + grade)/(numOfCompletedCourses + 1);
	    			numOfCompletedCourses = numOfCompletedCourses + 1;
	    			done = true;
    			}
    		}
    	}
    	return done;
    }


    @Override
    public String toString() {
        return getName() + " " + getId();
    }

}

