package com.generation.controller;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );
        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 5:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 6:
                    showCoursesSummary( courseService, scanner );
                    break;
            }
        }
        while ( option != 7 );
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService, Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);

        // Add the option to enter marks
        System.out.println("Enter marks for course " + course.getName() + ": ");
        double marks = scanner.nextDouble();

        // Enroll the student and set the marks
        courseService.enrollStudent(courseId, student);
        studentService.enrollToCourse(studentId, course);
        student.setGradeForCourse(courseId, marks);

        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId + " with marks: " + marks);
    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }
    
        private static void gradeStudent(StudentService studentService, Scanner scanner) {
            System.out.println("Enter student ID: ");
            String studentId = scanner.next();
            Student student = studentService.findStudent(studentId);
            if (student != null) {
                System.out.println("Enter course code: ");
                String courseCode = scanner.next();
                Course course = studentService.getCourse(courseCode);
                if (course != null) {
                    System.out.println("Enter grade for course " + course.getName() + ": ");
                    double grade = scanner.nextDouble();
                    // Challenge: Set the grade for the student
                    student.setGradeForCourse(courseCode, grade);
                    System.out.println("Grade set successfully for course " + course.getName());
                } else {
                    System.out.println("Invalid course code");
                }
            } else {
                System.out.println("Invalid student ID");
            }
        }


    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}