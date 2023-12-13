package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person implements Evaluation {
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    // Assuming you have a Map to store grades for each course
    private final Map<String, Double> grades = new HashMap<>();

    public Student(String id, String name, String email, Date birthDate) {
        super(id, name, email, birthDate);
    }

    public void enrollToCourse(Course course) {
        courses.add(course);
        System.out.println("Enrolled in course: " + course.getName());
    }

    public void registerApprovedCourse(Course course) {
        approvedCourses.put(course.getCode(), course);
    }

    public void setGradeForCourse(String courseCode, double grade) {
        grades.put(courseCode, grade);
    }

    public double getGradeForCourse(String courseCode) {
        // Assuming you have a Map<String, Double> to store grades for each course
        if (grades.containsKey(courseCode)) {
            return grades.get(courseCode);
        } else {
            return 0.0; // or some default value if the grade is not found
        }
    }

    public boolean isCourseApproved(String courseCode) {
        // TODO implement this method
        return approvedCourses.containsKey(courseCode);
    }

    public List<Course> findPassedCourses() {
        List<Course> passedCourses = new ArrayList<>();
        for (Course course : approvedCourses.values()) {
            if (getGradeForCourse(course.getCode()) >= 5.0) {
                passedCourses.add(course);
            }
        }
        return passedCourses;
    }

    public boolean isAttendingCourse(String courseCode) {
        return courses.stream().anyMatch(course -> course.getCode().equals(courseCode));
    }

    @Override
    public double getAverage() {
        if (courses.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Course course : courses) {
            sum += getGradeForCourse(course.getCode());
        }

        return sum / courses.size();
    }

    @Override
    public List<Course> getApprovedCourses() {
        List<Course> approved = new ArrayList<>();
        for (Course course : approvedCourses.values()) {
            approved.add(course);
        }
        return approved;
    }

    @Override
    public String toString() {
        return "Student {" + super.toString() + "}";
    }
}
