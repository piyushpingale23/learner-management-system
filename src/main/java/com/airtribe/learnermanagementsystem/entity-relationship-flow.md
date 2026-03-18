# LMS Entity Relationship & Flow Guide

## Objective

Design a clean and scalable relationship between:

* Course
* Cohort
* Instructor
* Learner

---

## Final Relationship Design

```
Course 1 ──── * Cohort
Course 1 ──── * Instructor
Course 1 ──── * Learner

Instructor 1 ──── * Cohort
Cohort * ──── 1 Course
Cohort * ──── 1 Instructor
```

---

## Entity Design

### Course

```java
@Entity
public class Course {
    private Long courseId;
    private String courseName;
    @OneToMany(mappedBy = "course")
    private List<Cohort> cohorts;
    @OneToMany(mappedBy = "course")
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "course")
    private List<Learner> learners;
}
```

---

### Cohort (OWNER ENTITY)

```java
@Entity
public class Cohort {
    private Long cohortId;
    private String cohortName;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
}
```

---

### Instructor

```java
@Entity
public class Instructor {
    private Long instructorId;
    private String instructorName;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "instructor")
    private List<Cohort> cohorts;
}
```

---

## Project Flow (Step-by-Step)

### Step 1: Create Course

* Course is created independently
* No cohorts/instructors/learners initially

```
Course → empty relations
```

---

### Step 2: Create Cohort under Course

```
cohort.setCourse(course);
cohortRepository.save(cohort);
```

Result:

```
Course → Cohorts populated
Cohort → Course linked
```

---

### Step 3: Create Instructor under Course

```
instructor.setCourse(course);
instructorRepository.save(instructor);
```

Result:

```
Course → Instructors populated
```

---

### Step 4: Assign Instructor to Cohort

```
cohort.setInstructor(instructor);
cohortRepository.save(cohort);
```

Result:

```
Cohort → Instructor linked
Instructor → Cohorts populated
```

---

## Key Rules

### Owner Side Rule

```
@ManyToOne = OWNER SIDE
```

Always update relationship from owner side.

---
