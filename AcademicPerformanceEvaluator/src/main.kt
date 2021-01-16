/*

// ---------------------------------------------------------------------------------------------
// Project Name     -    Academic Performance Evaluator
// Author           -    Kirubanand R
// Concepts Used    -    Kotlin Basics, OOPS Basics, Exceptional Handling
// ---------------------------------------------------------------------------------------------

*/

// To use the NumberFormatException to avoid runtime exception
import java.lang.NumberFormatException

// To use the ceil function
import kotlin.math.ceil

class AttendancePerformanceEvaluator {

    // Private Data Members (Variables)

    // To store the Student Name
    private var studentName: String = ""

    // To store the Student Register No.
    private var studentRegNo: Long = 0

    // To store the Student Semester No.
    private var studentSemesterNo: Int = 0

    // To store the Total no. of theory subjects
    private var noOfTotalTheorySubjects: Int = 0

    // To store the Credits allotted for respective theory subjects
    private var theorySubjectCredits: ArrayList <Int> = ArrayList ()

    // To store the Total no. of laboratory subjects
    private var noOfTotalLaboratorySubjects: Int = 0

    // To store the Credits allotted for respective laboratory subjects
    private var laboratorySubjectCredits: ArrayList <Int> = ArrayList ()

    // To store the Overall Attendance Percentage in Theory classes
    private var overallAttendancePercentageForTheoryClasses: Double = 0.0

    // To store the Overall Attendance Percentage in Laboratory classes
    private var overallAttendancePercentageForLaboratoryClasses: Double = 0.0

    // To store the reason for un-eligibility (if any) for a student in writing externals exams, or in GPA calculation
    private var reasonForUnEligibility: String = ""

    // To store the Individual subject-wise GPA (if eligible) for theory subjects
    private var individualTheorySubjectsGPA: ArrayList <Double> = ArrayList ()

    // To store the Individual subject-wise GPA (if eligible) for laboratory subjects
    private var individualLaboratorySubjectsGPA: ArrayList <Double> = ArrayList ()

    // To store the Overall GPA of a student (if eligible) in the current semester
    private var overallGPA: Double = 0.0

    // ---------------------------------------------------------------------------------------------
    // A function to display the main menu of the program and return the input chosen by the user
    //      Description     - This function displays the main menu of the program and return a integer
    //                        chosen by the user
    //      Function Type   - Public Member Function
    //      Return Type     - Int
    //      Parameters      - NULL
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    fun displayMenu(): Int {
        println("A C A D E M I C   P E R F O R M A N C E   E V A L U A T O R")
        println()
        println("Choose your choice from the following : ")
        println()
        println("   1. Calculate your Academic Performance")
        println("   2. How it works")
        println()

        var choice = 0
        var check: Boolean

        do {
            // This do-while loop is executed until the user enters the proper input
            try {
                print("Input Here : ")
                choice = (readLine()!!).toInt()

                // If the user enters the choice that is not available in the menu, an ArithmeticException is thrown to
                // handle that case
                if(choice != 1 && choice != 2)
                    throw ArithmeticException()
                println()

                // If no exception is thrown, then it means the user has entered a proper input, therefore the loop should
                // be terminated
                check = false
            }
            // It catches the NumberFormatException, if in case the user enters input of any other data type other than the specified
            catch(e: NumberFormatException) {
                println("Please enter a proper integer")
                println()
                check = true
            }
            // It catches the ArithmeticException, if in case the user enters the input which is not available in the menu
            catch(e: ArithmeticException) {
                println("Invalid Input! Please enter a proper input")
                println()
                check = true
            }
        } while(check)
        return choice
    }

    // ---------------------------------------------------------------------------------------------
    // A function to get the user information and print the Academic Performance
    //      Description     - This function gets the information (Basic , Subject , Examinations etc.
    //                        information) from the user and calculates the subject-wise and overall
    //                        GPA if the student is eligible
    //      Function Type   - Public Member Function
    //      Return Type     - Unit
    //      Parameters      - NULL
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    fun displayCalculateMenu() {
        try {
            println("A C A D E M I C   P E R F O R M A N C E   E V A L U A T O R")
            println()
            println("Enter the following details to calculate your academic performance")
            println()
            println("General Information : ")
            println()

            print("   Enter your Name : ")
            studentName = readLine()!!

            print("   Enter your Reg. No. : ")
            studentRegNo = (readLine()!!).toLong()

            // If the Register No. is less than or equal to zero, it throws an ArithmeticException
            if(studentRegNo <= 0)
                throw ArithmeticException()

            print("   Enter your Semester No. : ")
            studentSemesterNo = (readLine()!!).toInt()

            // If the Semester No. is less than or equal to zero, it throws an ArithmeticException
            if(studentSemesterNo <= 0)
                throw ArithmeticException()
            println()

            println("Subject Contact Periods Information : ")
            println()

            print("   Enter Total No. of Theory Subjects : ")
            noOfTotalTheorySubjects = (readLine()!!).toInt()

            // If the No. of total theory subjects is less than or equal to zero, it throws an ArithmeticException
            if(noOfTotalTheorySubjects <= 0)
                throw ArithmeticException()
            println()

            val noOfTotalTheoryClassesConducted = ArrayList<Int>(noOfTotalTheorySubjects)
            val noOfTotalTheoryClassesAttended = ArrayList<Int>(noOfTotalTheorySubjects)

            for (i in 1..noOfTotalTheorySubjects) {
                println("       Subject - $i : ")
                println()

                print("           Enter Total No. of Theory Classes conducted : ")
                val classesConducted = (readLine()!!).toInt()

                // If the No. of classes conducted is less than or equal to zero, it throws an ArithmeticException
                if(classesConducted <= 0)
                    throw ArithmeticException()

                noOfTotalTheoryClassesConducted.add(classesConducted)

                print("           Enter Total No. of Theory Classes attended : ")
                val classesAttended = (readLine()!!).toInt()

                // If the No. of classes attended is less than or equal to zero, it throws an ArithmeticException
                if(classesAttended <= 0)
                    throw ArithmeticException()
                println()

                noOfTotalTheoryClassesAttended.add(classesAttended)
            }

            print("   Enter Total No. of Laboratory Subjects : ")
            noOfTotalLaboratorySubjects = (readLine()!!).toInt()

            // If the No. of laboratory subjects is less than or equal to zero, it throws an ArithmeticException
            if(noOfTotalLaboratorySubjects <= 0)
                throw ArithmeticException()
            println()

            val noOfTotalLaboratoryClassesConducted = ArrayList<Int>(noOfTotalLaboratorySubjects)
            val noOfTotalLaboratoryClassesAttended = ArrayList<Int>(noOfTotalLaboratorySubjects)

            for (i in 1..noOfTotalLaboratorySubjects) {
                println("       Subject - $i : ")
                println()

                print("           Enter Total No. of Laboratory Classes conducted : ")
                val classesConducted = (readLine()!!).toInt()

                // If the No. of classes conducted is less than or equal to zero, it throws an ArithmeticException
                if(classesConducted <= 0)
                    throw ArithmeticException()

                noOfTotalLaboratoryClassesConducted.add(classesConducted)

                print("           Enter Total No. of Laboratory Classes attended : ")
                val classesAttended = (readLine()!!).toInt()

                // If the No. of classes attended is less than or equal to zero, it throws an ArithmeticException
                if(classesAttended <= 0)
                    throw ArithmeticException()
                println()

                noOfTotalLaboratoryClassesAttended.add(classesAttended)
            }

            println("Subject Credits Information : ")
            println()

            println("   Theory Subjects : ")
            println()

            for (i in 1..noOfTotalTheorySubjects) {
                print("       Enter the Credits of Theory Subject $i : ")
                val currentSubjectCredits = (readLine()!!).toInt()

                // If the subject credits is less than or equal to zero, it throws an ArithmeticException
                if(currentSubjectCredits <= 0)
                    throw ArithmeticException()
                println()

                theorySubjectCredits.add(currentSubjectCredits)
            }

            println("   Laboratory Subjects : ")
            println()

            for (i in 1..noOfTotalLaboratorySubjects) {
                print("       Enter the Credits of Laboratory Subject $i : ")
                val currentSubjectCredits = (readLine()!!).toInt()

                // If the subject credits is less than or equal to zero, it throws an ArithmeticException
                if(currentSubjectCredits <= 0)
                    throw ArithmeticException()
                println()

                laboratorySubjectCredits.add(currentSubjectCredits)
            }

            // If there is any reason for un-eligibility in writing externals or calculating GPA, it will be evaluated
            reasonForUnEligibility = ""

            // This is the Overall Attendance Percentage for Theory Classes in the current semester
            overallAttendancePercentageForTheoryClasses = calculateAttendancePercentage(noOfTotalTheoryClassesConducted, noOfTotalTheoryClassesAttended)

            // This is the Overall Attendance Percentage for Laboratory Classes in the current semester
            overallAttendancePercentageForLaboratoryClasses = calculateAttendancePercentage(noOfTotalLaboratoryClassesConducted, noOfTotalLaboratoryClassesAttended)

            println("Theory Internals Examination Information : ")
            println()

            print("   Enter Total No. of Internals Conducted for Theory Subjects: ")
            val noOfInternalsForTheorySubjects = (readLine()!!).toInt()

            // If the No. of total internals conducted for theory subjects is less than or equal to zero, it throws an ArithmeticException
            if(noOfInternalsForTheorySubjects <= 0)
                throw ArithmeticException()
            println()

            val internalsMarksInTheorySubjects = ArrayList<ArrayList<Int>>(noOfInternalsForTheorySubjects)

            for (i in 1..noOfInternalsForTheorySubjects) {
                val currentInternalsMarks = ArrayList<Int>(noOfTotalTheorySubjects)

                println("   Theory Internals Examination - $i : ")
                println()

                for (j in 1..noOfTotalTheorySubjects) {
                    print("     Enter Total Marks scored (out of 100) in Internal $i in Subject $j : ")
                    val currentSubjectInternalMarks = (readLine()!!).toInt()

                    // If the marks scored in internals in any subject is less than or equal to zero, it throws an ArithmeticException
                    if(currentSubjectInternalMarks <= 0)
                        throw ArithmeticException()
                    println()

                    currentInternalsMarks.add(currentSubjectInternalMarks)
                }

                internalsMarksInTheorySubjects.add(currentInternalsMarks)
            }

            println("Laboratory Internals Examination Information : ")
            println()

            print("   Enter Total No. of Internals Conducted for Laboratory Subjects: ")
            val noOfInternalsForLaboratorySubjects = (readLine()!!).toInt()

            // If the No. of total internals conducted for laboratory subjects is less than or equal to zero, it throws an ArithmeticException
            if(noOfInternalsForLaboratorySubjects <= 0)
                throw ArithmeticException()
            println()

            val internalsMarksInLaboratorySubjects = ArrayList<ArrayList<Int>>(noOfInternalsForLaboratorySubjects)

            for (i in 1..noOfInternalsForLaboratorySubjects) {
                val currentInternalsMarks = ArrayList<Int>(noOfTotalLaboratorySubjects)

                println("   Laboratory Internals Examination - $i : ")
                println()

                for (j in 1..noOfTotalLaboratorySubjects) {
                    print("     Enter Total Marks scored (out of 100) in Internal $i in Subject $j : ")
                    val currentSubjectInternalMarks = (readLine()!!).toInt()

                    // If the marks scored in internals in any subject is less than or equal to zero, it throws an ArithmeticException
                    if(currentSubjectInternalMarks <= 0)
                        throw ArithmeticException()
                    println()

                    currentInternalsMarks.add(currentSubjectInternalMarks)
                }

                internalsMarksInLaboratorySubjects.add(currentInternalsMarks)
            }

            // This is the Average Percentage of all Internals marks for individual theory subjects
            val individualTheorySubjectsOverallInternalsPercentage = calculateInternalsPercentage(internalsMarksInTheorySubjects)

            // This is the Average Percentage of all Internals marks for individual laboratory subjects
            val individualLaboratorySubjectsOverallInternalsPercentage = calculateInternalsPercentage(internalsMarksInLaboratorySubjects)

            // Based on the overall attendance records in each subjects, and overall internals percentage in each subject, this will be evaluated
            val isEligibleToWriteExternals = isEligibleToWriteExternals(overallAttendancePercentageForTheoryClasses , individualTheorySubjectsOverallInternalsPercentage , overallAttendancePercentageForLaboratoryClasses , individualLaboratorySubjectsOverallInternalsPercentage)

            var isEligibleToCalculateGPA = false
            overallGPA = 0.0

            // Executed only if the minimum requirements to write the externals exam is satisfied
            if (isEligibleToWriteExternals) {
                println("Theory Externals Examination : ")
                println()

                val individualTheorySubjectsExternalsPercentage = ArrayList<Int>(noOfTotalTheorySubjects)

                for (i in 1..noOfTotalTheorySubjects) {
                    print("   Enter Total Marks scored (out of 100) in Externals in Theory Subject $i : ")
                    val currentSubjectsExternalsPercentage = (readLine()!!).toInt()

                    // If the externals marks in theory subjects is less than or equal to zero, it throws an ArithmeticException
                    if(currentSubjectsExternalsPercentage <= 0)
                        throw ArithmeticException()
                    println()

                    individualTheorySubjectsExternalsPercentage.add(currentSubjectsExternalsPercentage)
                }

                println("Laboratory Externals Examination : ")
                println()

                val individualLaboratorySubjectsExternalsPercentage = ArrayList<Int>(noOfTotalLaboratorySubjects)

                for (i in 1..noOfTotalLaboratorySubjects) {
                    print("   Enter Total Marks scored (out of 100) in Externals in Laboratory Subject $i : ")
                    val currentSubjectsExternalsPercentage = (readLine()!!).toInt()

                    // If the externals marks in laboratory subjects is less than or equal to zero, it throws an ArithmeticException
                    if(currentSubjectsExternalsPercentage <= 0)
                        throw ArithmeticException()
                    println()

                    individualLaboratorySubjectsExternalsPercentage.add(currentSubjectsExternalsPercentage)
                }

                // Based on the overall externals percentage, this will be evaluated
                isEligibleToCalculateGPA = isEligibleToCalculateGPA(individualTheorySubjectsExternalsPercentage , individualLaboratorySubjectsExternalsPercentage)

                // Executed only if the minimum requirements to calculate the GPA is satisfied
                if (isEligibleToCalculateGPA) {

                    // This is the individual subject-wise GPA for theory subjects
                    individualTheorySubjectsGPA = calculateIndividualSubjectsGPA(individualTheorySubjectsOverallInternalsPercentage , individualTheorySubjectsExternalsPercentage , theorySubjectCredits)

                    // This is the individual subject-wise GPA for laboratory subjects
                    individualLaboratorySubjectsGPA = calculateIndividualSubjectsGPA(individualLaboratorySubjectsOverallInternalsPercentage , individualLaboratorySubjectsExternalsPercentage , laboratorySubjectCredits)

                    // This is the overall GPA
                    overallGPA = calculateOverallGPA(individualTheorySubjectsGPA , individualLaboratorySubjectsGPA)
                }

                // Executed if the minimum requirements to calculate the GPA is not satisfied
                else {
                    // The reason for un-eligibility to calculate GPA is evaluated
                    reasonForUnEligibility = findReasonForUnEligibilityToCalculateGPA(individualTheorySubjectsExternalsPercentage , individualLaboratorySubjectsExternalsPercentage)

                    println("Note : You are not eligible for GPA Calculation as you have not passed the minimum criteria in your Externals Exam")
                    println()
                }
            }

            // Executed if the minimum requirements to write the externals exam is not satisfied
            else {
                // The reason for un-eligibility to write externals exams is evaluated
                reasonForUnEligibility = findReasonForUnEligibilityToWriteExternals(overallAttendancePercentageForTheoryClasses , overallAttendancePercentageForLaboratoryClasses , individualTheorySubjectsOverallInternalsPercentage , individualLaboratorySubjectsOverallInternalsPercentage)

                println("Note : You cannot write Externals as you have not passed the minimum criteria to be eligible for it.")
                println()
            }

            // It displays the individual subject-wise GPA, and overall GPA, if the student is eligible
            displayAcademicResults(isEligibleToWriteExternals, isEligibleToCalculateGPA)
        }
        // It catches the NumberFormatException, if in case the user enters input of any other data type other than the specified
        catch(e: NumberFormatException) {
            println()
            println("Please enter a proper integer. Try again")
            println()
        }
        // It catches the ArithmeticException, if in case the user enters the input which is invalid arithmetically
        catch(e: ArithmeticException) {
            println()
            println("Invalid Input! Please enter a proper input. Try again")
            println()
        }
    }

    // ---------------------------------------------------------------------------------------------
    // A function to display the user, on what basis the Academic Performance is evaluated
    //      Description     - This function displays the minimum requirements to write externals exam
    //                        and be eligible for GPA calculation
    //      Function Type   - Public Member Function
    //      Return Type     - Unit
    //      Parameters      - NULL
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    fun displayWorksMenu() {
        println("A C A D E M I C   P E R F O R M A N C E   E V A L U A T O R")
        println()

        println("Your Academic Performance is evaluated based on the following")
        println()
        println("   1. Minimum Attendance percentage should be 75 in each subject (>= 75 %)")
        println("   2. Minimum Internal Marks percentage should be 70 in each subject in each internals (>= 70 %)")
        println()

        println("Only if the above two conditions is satisfied, you will be allowed to write the External Exam and be eligible for GPA Calculation")
        println()

        println("Weightage for Internals and Externals is calculated based on the following")
        println()
        println("   1. For Internals : 20 %")
        println("   2. For Externals : 80 %")
        println("Note : Minimum External Marks percentage should be 40 in each subject to be eligible for GPA Calculation")
        println()

        println("GPA is calculated based on the following")
        println()
        println("   Final Resultant GPA (10 point scale) = (GPA in Subject-1 + GPA in Subject-2 + GPA in Subject-3 + ... + GPA in Subject-n) / (n). n - Total no. of subjects")
        println("   Individual Subject GPA (10 point scale) = (Subject Credits * Scored Credits) / (Subject Credits * 10)")
        println("   Scored Credits = ceil((External Score * (80 / 100) + Internal Score * (20 / 100)) / 10)")
    }

    // ---------------------------------------------------------------------------------------------
    // A function to display the academic results to the user
    //      Description     - This function displays the Academic Performance (Results) to the user
    //                        and if he/she is eligible, then the individual subject-wise GPA and
    //                        the overall GPA is also displayed, else the reason for un-eligibility
    //                        is displayed
    //      Function Type   - Private Member Function
    //      Return Type     - Unit
    //      Parameters      - isEligibleToWriteExternals  , isEligibleToCalculateGPA -> Boolean Type
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun displayAcademicResults(isEligibleToWriteExternals: Boolean , isEligibleToCalculateGPA: Boolean) {
        println("A C A D E M I C   P E R F O R M A N C E   E V A L U A T O R")
        println()

        println("Y O U R   R E S U L T S - ")
        println()

        // To display the student's general information
        println("General Information : ")
        println()
        println("Name : $studentName")
        println("Register No. : $studentRegNo")
        println("Semester No. : $studentSemesterNo")
        println()

        println("Subject Credits Information : ")
        println()

        println("   Theory : ")
        println()

        // To display the subject-wise credits for theory subjects
        for(index in 0 until noOfTotalTheorySubjects)
            println("       Subject - ${index + 1} : ${theorySubjectCredits[index]}")
        println()

        println("   Laboratory : ")
        println()

        // To display the subject-wise credits for laboratory subjects
        for(index in 0 until noOfTotalLaboratorySubjects)
            println("       Subject - ${index + 1} : ${laboratorySubjectCredits[index]}")
        println()

        // To display the overall attendance percentage in theory and laboratory subjects
        println("Overall Attendance Percentage")
        println()
        println("   Theory Classes : $overallAttendancePercentageForTheoryClasses")
        println("   Laboratory Classes : $overallAttendancePercentageForLaboratoryClasses")
        println()

        // Executed only if the student is eligible to write externals exam
        if(isEligibleToWriteExternals) {
            println("Eligible to write Externals : YES")
            println()
        }

        // Executed if the student is not eligible to write externals exam and the reason for it is also displayed
        else {
            println("Eligible to write Externals : NO")

            println("Reason for UnEligibility : $reasonForUnEligibility")
            println()
        }

        // Executed only if the student is eligible for GPA calculation and individual subject-wise and overall GPA is also displayed
        if(isEligibleToCalculateGPA) {
            println("Eligible to calculate GPA : YES")
            println()

            println("Individual Subject-wise GPA : ")
            println()

            println("   Theory Subjects : ")
            println()

            // To display the individual subject-wise GPA for theory subjects
            for(index in 0 until noOfTotalTheorySubjects)
                println("       Subject - ${index + 1} : ${individualTheorySubjectsGPA[index]}")
            println()

            println("   Laboratory Subjects : ")
            println()

            // To display the individual subject-wise GPA for laboratory subjects
            for(index in 0 until noOfTotalLaboratorySubjects)
                println("       Subject - ${index + 1} : ${individualLaboratorySubjectsGPA[index]}")
            println()

            // Overall GPA for the semester
            println("Overall GPA : $overallGPA")
        }

        // Executed if the student is not eligible for GPA calculation and the reason for it is also displayed
        else {
            println("Eligible to calculate GPA : NO")

            println("Reason for UnEligibility : $reasonForUnEligibility")
            println()
        }
    }

    // ---------------------------------------------------------------------------------------------
    // A function to calculate the attendance percentage
    //      Description     - This function calculates the overall attendance percentage of a student
    //      Function Type   - Private Member Function
    //      Return Type     - Double
    //      Parameters      - noOfTotalClassesConducted , noOfTotalClassesAttended -> ArrayList <Int>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun calculateAttendancePercentage(noOfTotalClassesConducted: ArrayList <Int> , noOfTotalClassesAttended: ArrayList <Int>): Double {

        val individualClassAttendancePercentage = ArrayList <Double> ()
        val noOfTotalSubjects = noOfTotalClassesAttended.size

        // To calculate the attendance percentage for each subject by (classes attended / total classes) * 100
        for(index in 0 until noOfTotalSubjects) {
            individualClassAttendancePercentage.add(((noOfTotalClassesAttended[index]).toDouble() / noOfTotalClassesConducted[index]) * 100)
        }

        var overallAttendancePercentage = 0.0

        for(item in individualClassAttendancePercentage) {
            // If attendance in any subject is less than 75 (minimum requirement) it is returned directly
            if(item < 75)
                return item

            // Else, it is added to the overall attendance percentage
            overallAttendancePercentage += item
        }

        // Overall attendance percentage is divided by total no. of subjects to get the average attendance percentage
        overallAttendancePercentage /= noOfTotalSubjects

        return overallAttendancePercentage
    }

    // ---------------------------------------------------------------------------------------------
    // A function to calculate the overall internals percentage
    //      Description     - This function calculates the overall internals percentage of a student
    //                        for individual theory and laboratory subjects
    //      Function Type   - Private Member Function
    //      Return Type     - ArrayList <Double>
    //      Parameters      - internalsMarks -> ArrayList <ArrayList <Int>>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun calculateInternalsPercentage(internalsMarks: ArrayList <ArrayList <Int>>): ArrayList <Double> {

        val individualSubjectsInternalsPercentage = ArrayList <Double> ()
        val noOfTotalSubjects = internalsMarks[0].size
        val noOfInternalsConducted = internalsMarks.size

        // To calculate the average internals percentage for each subject in all internals conducted
        for(columnIndex in 0 until noOfTotalSubjects) {
            var currentSubjectInternalsPercentage = 0.0

            // To calculate the sum of marks in each subject in all internals
            for(rowIndex in 0 until noOfInternalsConducted) {
                currentSubjectInternalsPercentage += internalsMarks[rowIndex][columnIndex]
            }

            // To calculate the average internal marks for each subjects by dividing the sum of marks with total no. of internals conducted
            individualSubjectsInternalsPercentage.add((currentSubjectInternalsPercentage / noOfInternalsConducted))
        }

        return individualSubjectsInternalsPercentage
    }

    // ---------------------------------------------------------------------------------------------
    // A function to check whether a student is eligible to write externals exams or not
    //      Description     - This function checks the minimum requirements for a student to write the
    //                        externals exams and returns whether he/she is eligible for it or not
    //      Function Type   - Private Member Function
    //      Return Type     - Boolean
    //      Parameters      - overallAttendancePercentageForTheoryClasses -> Double
    //                        overallAttendancePercentageForLaboratoryClasses -> Double
    //                        individualTheorySubjectsOverallInternalsPercentage -> ArrayList <Double>
    //                        individualLaboratorySubjectsOverallInternalsPercentage -> ArrayList <Double>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun isEligibleToWriteExternals(overallAttendancePercentageForTheoryClasses: Double , individualTheorySubjectsOverallInternalsPercentage: ArrayList <Double> , overallAttendancePercentageForLaboratoryClasses: Double , individualLaboratorySubjectsOverallInternalsPercentage: ArrayList <Double>): Boolean {

        // If minimum attendance requirements is not satisfied
        if(overallAttendancePercentageForTheoryClasses < 75 || overallAttendancePercentageForLaboratoryClasses < 75)
            return false

        // If minimum internals percentage in theory subjects in not satisfied
        for(item in individualTheorySubjectsOverallInternalsPercentage) {
            if(item < 70)
                return false
        }

        // If minimum internals percentage in laboratory subjects in not satisfied
        for(item in individualLaboratorySubjectsOverallInternalsPercentage) {
            if(item < 70)
                return false
        }

        // If all the minimum requirements is satisfied
        return true
    }

    // ---------------------------------------------------------------------------------------------
    // A function to check whether a student is eligible for GPA calculation or not
    //      Description     - This function checks the minimum requirements for a student to be
    //                        eligible for GPA calculation or not based on the performance in the
    //                        externals exams
    //      Function Type   - Private Member Function
    //      Return Type     - Boolean
    //      Parameters      - theoryExternalsMarks -> ArrayList <Int>
    //                        laboratoryExternalsMarks -> ArrayList <Int>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun isEligibleToCalculateGPA(theoryExternalsMarks: ArrayList<Int> , laboratoryExternalsMarks: ArrayList <Int>): Boolean {

        // If minimum externals percentage in theory subjects is not satisfied
        for(item in theoryExternalsMarks) {
            if(item < 40)
                return false
        }

        // If minimum externals percentage in laboratory subjects is not satisfied
        for(item in laboratoryExternalsMarks) {
            if(item < 40)
                return false
        }

        // If all the minimum requirements is satisfied
        return true
    }

    // ---------------------------------------------------------------------------------------------
    // A function to calculate subject-wise GPA
    //      Description     - This function calculates the subject-wise GPA based on the subject
    //                        credit system, and marks scored in the internals and externals exams
    //      Function Type   - Private Member Function
    //      Return Type     - ArrayList <Double>
    //      Parameters      - internalsMarks -> ArrayList <Double>
    //                        externalsMarks , subjectCredits -> ArrayList <Int>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun calculateIndividualSubjectsGPA(internalsMarks: ArrayList <Double> , externalsMarks: ArrayList <Int> , subjectCredits: ArrayList <Int>): ArrayList <Double> {

        val noOfTotalSubjects = externalsMarks.size
        val individualSubjectsGPA = ArrayList <Double> ()

        // To calculate the subject-wise GPA
        // Here, internals weightage is 20 % and externals weightage is 80 %
        // The GPA is calculate by (scored credits) / (total credits) for each subject
        for (index in 0 until noOfTotalSubjects) {
            val currentSubjectGPA = ((subjectCredits[index] * ceil((internalsMarks[index] * (20.0 / 100) + externalsMarks[index] * (80.0 / 100)) / 10)) / (subjectCredits[index] * 10)) * 10
            individualSubjectsGPA.add(currentSubjectGPA)
        }

        return individualSubjectsGPA
    }

    // ---------------------------------------------------------------------------------------------
    // A function to calculate overall GPA
    //      Description     - This function calculates the overall GPA using the GPA of individual
    //                        subjects
    //      Function Type   - Private Member Function
    //      Return Type     - Double
    //      Parameters      - individualTheorySubjectsGPA -> ArrayList <Double>
    //                        individualLaboratorySubjectsGPA -> ArrayList <Double>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun calculateOverallGPA(individualTheorySubjectsGPA: ArrayList <Double> , individualLaboratorySubjectsGPA: ArrayList <Double>): Double{

        var overallGPA = 0.0
        val noOfTotalTheorySubjects = individualTheorySubjectsGPA.size
        val noOfTotalLaboratorySubjects = individualLaboratorySubjectsGPA.size
        val noOfTotalSubjects = noOfTotalTheorySubjects + noOfTotalLaboratorySubjects

        // To calculate the sum of GPA of individual theory subjects
        for(item in individualTheorySubjectsGPA) {
            overallGPA += item
        }

        // To calculate the sum of GPA of individual laboratory subjects
        for(item in individualLaboratorySubjectsGPA) {
            overallGPA += item
        }

        // Overall GPA is calculated as (Total GPA for individual subjects) / (Total no. of subjects)
        overallGPA /= noOfTotalSubjects
        return overallGPA
    }

    // ---------------------------------------------------------------------------------------------
    // A function to find the reason for un-eligibility to write externals exams
    //      Description     - This function finds the reason why a student is declared as un-eligible to
    //                        write the externals exams
    //      Function Type   - Private Member Function
    //      Return Type     - String
    //      Parameters      - overallAttendancePercentageForTheoryClasses -> Double
    //                        overallAttendancePercentageForLaboratoryClasses -> Double
    //                        individualTheorySubjectsOverallInternalsPercentage -> ArrayList <Double>
    //                        individualLaboratorySubjectsOverallInternalsPercentage -> ArrayList <Double>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun findReasonForUnEligibilityToWriteExternals(overallAttendancePercentageForTheoryClasses: Double , overallAttendancePercentageForLaboratoryClasses: Double , individualTheorySubjectsOverallInternalsPercentage: ArrayList <Double> , individualLaboratorySubjectsOverallInternalsPercentage: ArrayList <Double>): String {

        var isPoorAttendanceInTheoryClasses = false
        var isPoorAttendanceInLaboratoryClasses = false
        var isPoorPerformanceInTheorySubjectsInternals = false
        var isPoorPerformanceInLaboratorySubjectsInternals = false
        var reasonForUnEligibility = ""

        // If attendance records in theory classes is poor
        if(overallAttendancePercentageForTheoryClasses < 75)
            isPoorAttendanceInTheoryClasses = true

        // if attendance records in laboratory classes is poor
        if(overallAttendancePercentageForLaboratoryClasses < 75)
            isPoorAttendanceInLaboratoryClasses = true

        // To check if internals performance in theory subjects is poor
        for(item in individualTheorySubjectsOverallInternalsPercentage) {

            // If internals performance in theory subjects is poor
            if(item < 70) {
                isPoorPerformanceInTheorySubjectsInternals = true
                break
            }
        }

        // To check if internals performance in laboratory subjects is poor
        for(item in individualLaboratorySubjectsOverallInternalsPercentage) {

            // If internals performance in laboratory subject is poor
            if(item < 70) {
                isPoorPerformanceInLaboratorySubjectsInternals = true
                break
            }
        }

        // Based on the above collected information, the reason for un-eligibility of a student to write externals exams is determined
        if(isPoorAttendanceInLaboratoryClasses && isPoorAttendanceInTheoryClasses)
            reasonForUnEligibility = "Poor Attendance Record in Both Theory and Laboratory Classes"
        else if(isPoorAttendanceInTheoryClasses)
            reasonForUnEligibility = "Poor Attendance Record in Theory Classes"
        else if(isPoorAttendanceInLaboratoryClasses)
            reasonForUnEligibility = "Poor Attendance Record in Laboratory Classes"

        if(reasonForUnEligibility != "" && (isPoorPerformanceInTheorySubjectsInternals || isPoorPerformanceInLaboratorySubjectsInternals))
            reasonForUnEligibility += " and "

        if(isPoorPerformanceInTheorySubjectsInternals && isPoorPerformanceInLaboratorySubjectsInternals)
            reasonForUnEligibility += "Poor Performance in Both Theory and Laboratory Subjects in Internals"
        else if(isPoorPerformanceInTheorySubjectsInternals)
            reasonForUnEligibility += "Poor Performance in Theory Subjects in Internals"
        else if(isPoorPerformanceInLaboratorySubjectsInternals)
            reasonForUnEligibility += "Poor Performance in Laboratory Subjects in Internals"

        return reasonForUnEligibility
    }

    // ---------------------------------------------------------------------------------------------
    // A function to find the reason for un-eligibility for GPA calculation
    //      Description     - This function finds the reason why a student is declared as un-eligible
    //                        for GPA calculation
    //      Function Type   - Private Member Function
    //      Return Type     - String
    //      Parameters      - individualTheorySubjectsExternalsPercentage -> ArrayList <Int>
    //                        individualLaboratorySubjectsExternalsPercentage -> ArrayList <Int>
    //      Bugs            - NULL
    // ---------------------------------------------------------------------------------------------
    private fun findReasonForUnEligibilityToCalculateGPA(individualTheorySubjectsExternalsPercentage: ArrayList<Int>, individualLaboratorySubjectsExternalsPercentage: ArrayList<Int>): String {

        var isPoorPerformanceInTheorySubjectsExternals = false
        var isPoorPerformanceInLaboratorySubjectsExternals = false

        // To check if externals performance in theory subjects is poor
        for (item in individualTheorySubjectsExternalsPercentage) {

            // If internals performance in theory subject is poor
            if (item < 40) {
                isPoorPerformanceInTheorySubjectsExternals = true
                break
            }
        }

        // To check if externals performance in laboratory subjects is poor
        for (item in individualLaboratorySubjectsExternalsPercentage) {

            // If internals performance in laboratory subject is poor
            if (item < 40) {
                isPoorPerformanceInLaboratorySubjectsExternals = true
                break
            }
        }

        // Based on the above collected information, the reason for un-eligibility of a student for GPA calculation is determined
        return if(isPoorPerformanceInTheorySubjectsExternals && isPoorPerformanceInLaboratorySubjectsExternals)
            "Poor Performance in Both Theory And Laboratory Subjects in Externals"
        else if(isPoorPerformanceInTheorySubjectsExternals)
            "Poor Performance in Theory Subjects in Externals"
        else if(isPoorPerformanceInLaboratorySubjectsExternals)
            "Poor Performance in Laboratory Subjects in Externals"
        else
            ""
    }
}

// ---------------------------------------------------------------------------------------------
// Main Function
//      Description     - This function is the entry-point to the program and complete flow of
//                        program is driven by this function
//      Function Type   - Main Function
//      Return Type     - Unit
//      Parameters      - args -> Array <String>
//      Bugs            - NULL
// ---------------------------------------------------------------------------------------------
fun main(args: Array <String>) {
    var repeat = 0

    // Object creation for the class: AttendancePerformanceEvaluator
    val objectForAttendancePerformanceEvaluator = AttendancePerformanceEvaluator()

    do {
        // Based on the user's input , the following member functions are called
        when(objectForAttendancePerformanceEvaluator.displayMenu()) {
            1 -> objectForAttendancePerformanceEvaluator.displayCalculateMenu()
            2 -> objectForAttendancePerformanceEvaluator.displayWorksMenu()
        }

        println()

        var check: Boolean
        do {
            // This do-while loop is executed until the user enters the proper input
            try {
                print("Do you want to continue (1 / 0) : ")
                repeat = (readLine()!!).toInt()
                println()

                // If no exception is thrown, then it means the user has entered a proper input, therefore the loop should
                // be terminated
                check = false
            }

            // It catches the NumberFormatException, if in case the user enters input of any other data type other than the specified
            catch (e: NumberFormatException) {
                println("Please enter a proper integer")
                println()
                check = true
            }
        } while(check)

    } while (repeat == 1)
}
