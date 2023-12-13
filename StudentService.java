package com.generation.service;

import com.generation.model.Course;
import com.generation.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService
{
    private final Map<String, Student> students = new HashMap<>();

    public void subscribeStudent( Student student )
    {
        students.put( student.getId(), student );
    }

    public Student findStudent( String studentId )
    {
        if ( students.containsKey( studentId ) )
        {
            return students.get( studentId );
        }
        return null;
    }

    public Course getCourse(String courseCode) {
        CourseService courseService = new CourseService();
        return courseService.getCourse(courseCode);
    }

    public boolean isSubscribed( String studentId )
    {
        
        return students.containsKey(studentId);
    }

    public void showSummary()
    {
    	for (Student student : students.values()) {
            System.out.println("Student: " + student.getName() + ", ID: " + student.getId());
            System.out.println("Enrolled Courses: " + student.getApprovedCourses());
            System.out.println("------------------------------");
        }
    }

    public void enrollToCourse( String studentId, Course course )
    {
        if ( students.containsKey( studentId ) )
        {
            students.get( studentId ).enrollToCourse( course );
        }
    }


}