# Academic-Performance-Evaluator
A project to evaluate the Academic Performance of a student using his/her Attendance, Internal marks and External marks. It is a sample project to practice the basics of Kotlin and its OOPS concepts. 

## Concepts Used
- `Kotlin Basics`
- `Kotlin OOPS`
- `Exceptional Handling`

## How it works
Your Academic Performance is evaluated based on the following
  1. `Minimum Attendance` percentage should be 75 in each subject `(>= 75 %)`
  2. `Minimum Internal Marks` percentage should be 70 in each subject in each internals `(>= 70 %)`
  
_Note: Only if the above two conditions is satisfied, you will be allowed to write the External Exam and be eligible for GPA Calculation_

Weightage for Internals and Externals is calculated based on the following
  1. For Internals : `20 %`
  2. For Externals : `80 %`
  
_Note : `Minimum External Marks` percentage should be `40` in each subject to be eligible for GPA Calculation_

`GPA` is calculated based on the following

  - `Final Resultant GPA` (**10** point scale) = (Individual Subject GPA of all subjects) / (Total no. of subjects) 
  
  - `Individual Subject GPA` (**10** point scale) = (Subject Credits * `Scored Credits`) / (Subject Credits * 10)
  
  - `Scored Credits` = ceil((External Score * (80 / 100) + Internal Score * (20 / 100)) / 10)



### Pull Requests
I am open to PRs and would really appreciate you to contribute to this repository. 
