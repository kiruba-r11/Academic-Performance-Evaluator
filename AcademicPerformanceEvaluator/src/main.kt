import java.lang.NumberFormatException
import kotlin.math.ceil

class AttendancePerformanceEvaluator {

    private var studentName: String = ""
    private var studentRegNo: Long = 0
    private var studentSemesterNo: Int = 0
    private var noOfTotalTheorySubjects: Int = 0
    private var theorySubjectCredits: ArrayList <Int> = ArrayList ()
    private var noOfTotalLaboratorySubjects: Int = 0
    private var laboratorySubjectCredits: ArrayList <Int> = ArrayList ()
    private var overallAttendancePercentageForTheoryClasses: Double = 0.0
    private var overallAttendancePercentageForLaboratoryClasses: Double = 0.0
    private var reasonForUnEligibility: String = ""
    private var individualTheorySubjectsGPA: ArrayList <Double> = ArrayList ()
    private var individualLaboratorySubjectsGPA: ArrayList <Double> = ArrayList ()
    private var overallGPA: Double = 0.0

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
            try {
                print("Input Here : ")
                choice = (readLine()!!).toInt()
                if(choice != 1 && choice != 2)
                    throw ArithmeticException()
                println()
                check = false
            }
            catch(e: NumberFormatException) {
                println("Please enter a proper integer")
                println()
                check = true
            }
            catch(e: ArithmeticException) {
                println("Invalid Input! Please enter a proper input")
                println()
                check = true
            }
        } while(check)
        return choice
    }

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
            if(studentRegNo <= 0)
                throw ArithmeticException()
            print("   Enter your Semester No. : ")
            studentSemesterNo = (readLine()!!).toInt()
            if(studentSemesterNo <= 0)
                throw ArithmeticException()
            println()
            println("Subject Contact Periods Information : ")
            println()
            print("   Enter Total No. of Theory Subjects : ")
            noOfTotalTheorySubjects = (readLine()!!).toInt()
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
                if(classesConducted <= 0)
                    throw ArithmeticException()
                noOfTotalTheoryClassesConducted.add(classesConducted)
                print("           Enter Total No. of Theory Classes attended : ")
                val classesAttended = (readLine()!!).toInt()
                if(classesAttended <= 0)
                    throw ArithmeticException()
                println()
                noOfTotalTheoryClassesAttended.add(classesAttended)
            }
            print("   Enter Total No. of Laboratory Subjects : ")
            noOfTotalLaboratorySubjects = (readLine()!!).toInt()
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
                if(classesConducted <= 0)
                    throw ArithmeticException()
                noOfTotalLaboratoryClassesConducted.add(classesConducted)
                print("           Enter Total No. of Laboratory Classes attended : ")
                val classesAttended = (readLine()!!).toInt()
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
                if(currentSubjectCredits <= 0)
                    throw ArithmeticException()
                println()
                laboratorySubjectCredits.add(currentSubjectCredits)
            }
            reasonForUnEligibility = ""
            overallAttendancePercentageForTheoryClasses = calculateAttendancePercentage(noOfTotalTheoryClassesConducted, noOfTotalTheoryClassesAttended)
            overallAttendancePercentageForLaboratoryClasses = calculateAttendancePercentage(noOfTotalLaboratoryClassesConducted, noOfTotalLaboratoryClassesAttended)
            println("Theory Internals Examination Information : ")
            println()
            print("   Enter Total No. of Internals Conducted for Theory Subjects: ")
            val noOfInternalsForTheorySubjects = (readLine()!!).toInt()
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
                    if(currentSubjectInternalMarks <= 0)
                        throw ArithmeticException()
                    println()
                    currentInternalsMarks.add(currentSubjectInternalMarks)
                }
                internalsMarksInLaboratorySubjects.add(currentInternalsMarks)
            }
            val individualTheorySubjectsOverallInternalsPercentage = calculateInternalsPercentage(internalsMarksInTheorySubjects)
            val individualLaboratorySubjectsOverallInternalsPercentage = calculateInternalsPercentage(internalsMarksInLaboratorySubjects)
            val isEligibleToWriteExternals = isEligibleToWriteExternals(overallAttendancePercentageForTheoryClasses , individualTheorySubjectsOverallInternalsPercentage , overallAttendancePercentageForLaboratoryClasses , individualLaboratorySubjectsOverallInternalsPercentage)
            var isEligibleToCalculateGPA = false
            overallGPA = 0.0
            if (isEligibleToWriteExternals) {
                println("Theory Externals Examination : ")
                println()
                val individualTheorySubjectsExternalsPercentage = ArrayList<Int>(noOfTotalTheorySubjects)
                for (i in 1..noOfTotalTheorySubjects) {
                    print("   Enter Total Marks scored (out of 100) in Externals in Theory Subject $i : ")
                    val currentSubjectsExternalsPercentage = (readLine()!!).toInt()
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
                    if(currentSubjectsExternalsPercentage <= 0)
                        throw ArithmeticException()
                    println()
                    individualLaboratorySubjectsExternalsPercentage.add(currentSubjectsExternalsPercentage)
                }
                isEligibleToCalculateGPA = isEligibleToCalculateGPA(individualTheorySubjectsExternalsPercentage , individualLaboratorySubjectsExternalsPercentage)
                if (isEligibleToCalculateGPA) {
                    individualTheorySubjectsGPA = calculateIndividualSubjectsGPA(individualTheorySubjectsOverallInternalsPercentage , individualTheorySubjectsExternalsPercentage , theorySubjectCredits)
                    individualLaboratorySubjectsGPA = calculateIndividualSubjectsGPA(individualLaboratorySubjectsOverallInternalsPercentage , individualLaboratorySubjectsExternalsPercentage , laboratorySubjectCredits)
                    overallGPA = calculateOverallGPA(individualTheorySubjectsGPA , individualLaboratorySubjectsGPA)
                }
                else {
                    reasonForUnEligibility = findReasonForUnEligibilityToCalculateGPA(individualTheorySubjectsExternalsPercentage , individualLaboratorySubjectsExternalsPercentage)
                    println("Note : You are not eligible for GPA Calculation as you have not passed the minimum criteria in your Externals Exam")
                    println()
                }
            }
            else {
                reasonForUnEligibility = findReasonForUnEligibilityToWriteExternals(overallAttendancePercentageForTheoryClasses , overallAttendancePercentageForLaboratoryClasses , individualTheorySubjectsOverallInternalsPercentage , individualLaboratorySubjectsOverallInternalsPercentage)
                println("Note : You cannot write Externals as you have not passed the minimum criteria to be eligible for it.")
                println()
            }

            displayAcademicResults(isEligibleToWriteExternals, isEligibleToCalculateGPA)
        }
        catch(e: NumberFormatException) {
            println()
            println("Please enter a proper integer. Try again")
            println()
        }
        catch(e: ArithmeticException) {
            println()
            println("Invalid Input! Please enter a proper input. Try again")
            println()
        }
    }

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

    private fun displayAcademicResults(isEligibleToWriteExternals: Boolean , isEligibleToCalculateGPA: Boolean) {
        println("A C A D E M I C   P E R F O R M A N C E   E V A L U A T O R")
        println()
        println("Y O U R   R E S U L T S - ")
        println()
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
        for(index in 0 until noOfTotalTheorySubjects)
            println("       Subject - ${index + 1} : ${theorySubjectCredits[index]}")
        println()
        println("   Laboratory : ")
        println()
        for(index in 0 until noOfTotalLaboratorySubjects)
            println("       Subject - ${index + 1} : ${laboratorySubjectCredits[index]}")
        println()
        println("Overall Attendance Percentage")
        println()
        println("   Theory Classes : $overallAttendancePercentageForTheoryClasses")
        println("   Laboratory Classes : $overallAttendancePercentageForLaboratoryClasses")
        println()
        if(isEligibleToWriteExternals) {
            println("Eligible to write Externals : YES")
            println()
        }
        else {
            println("Eligible to write Externals : NO")
            println("Reason for UnEligibility : $reasonForUnEligibility")
            println()
        }
        if(isEligibleToCalculateGPA) {
            println("Eligible to calculate GPA : YES")
            println()
            println("Individual Subject-wise GPA : ")
            println()
            println("   Theory Subjects : ")
            println()
            for(index in 0 until noOfTotalTheorySubjects)
                println("       Subject - ${index + 1} : ${individualTheorySubjectsGPA[index]}")
            println()
            println("   Laboratory Subjects : ")
            println()
            for(index in 0 until noOfTotalLaboratorySubjects)
                println("       Subject - ${index + 1} : ${individualLaboratorySubjectsGPA[index]}")
            println()
            println("Overall GPA : $overallGPA")
        }
        else {
            println("Eligible to calculate GPA : NO")
            println("Reason for UnEligibility : $reasonForUnEligibility")
            println()
        }
    }

    private fun calculateAttendancePercentage(noOfTotalClassesConducted: ArrayList <Int> , noOfTotalClassesAttended: ArrayList <Int>): Double {

        val individualClassAttendancePercentage = ArrayList <Double> ()
        val noOfTotalSubjects = noOfTotalClassesAttended.size

        for(index in 0 until noOfTotalSubjects) {
            individualClassAttendancePercentage.add(((noOfTotalClassesAttended[index]).toDouble() / noOfTotalClassesConducted[index]) * 100)
        }

        var overallAttendancePercentage = 0.0

        for(item in individualClassAttendancePercentage) {
            if(item < 75)
                return item
            overallAttendancePercentage += item
        }

        overallAttendancePercentage /= noOfTotalSubjects

        return overallAttendancePercentage
    }

    private fun calculateInternalsPercentage(internalsMarks: ArrayList <ArrayList <Int>>): ArrayList <Double> {

        val individualSubjectsInternalsPercentage = ArrayList <Double> ()
        val noOfTotalSubjects = internalsMarks[0].size
        val noOfInternalsConducted = internalsMarks.size

        for(columnIndex in 0 until noOfTotalSubjects) {
            var currentSubjectInternalsPercentage = 0.0
            for(rowIndex in 0 until noOfInternalsConducted) {
                currentSubjectInternalsPercentage += internalsMarks[rowIndex][columnIndex]
            }
            individualSubjectsInternalsPercentage.add((currentSubjectInternalsPercentage / noOfInternalsConducted))
        }

        return individualSubjectsInternalsPercentage
    }

    private fun isEligibleToWriteExternals(overallAttendancePercentageForTheoryClasses: Double , individualTheorySubjectsOverallInternalsPercentage: ArrayList <Double> , overallAttendancePercentageForLaboratoryClasses: Double , individualLaboratorySubjectsOverallInternalsPercentage: ArrayList<Double>): Boolean {

        if(overallAttendancePercentageForTheoryClasses < 75 || overallAttendancePercentageForLaboratoryClasses < 75)
            return false
        for(item in individualTheorySubjectsOverallInternalsPercentage) {
            if(item < 70)
                return false
        }
        for(item in individualLaboratorySubjectsOverallInternalsPercentage) {
            if(item < 70)
                return false
        }
        return true
    }

    private fun isEligibleToCalculateGPA(theoryExternalsMarks: ArrayList<Int> , laboratoryExternalsMarks: ArrayList <Int>): Boolean {

        for(item in theoryExternalsMarks) {
            if(item < 40)
                return false
        }

        for(item in laboratoryExternalsMarks) {
            if(item < 40)
                return false
        }
        return true
    }

    private fun calculateIndividualSubjectsGPA(internalsMarks: ArrayList <Double> , externalsMarks: ArrayList <Int> , subjectCredits: ArrayList <Int>): ArrayList <Double> {

        val noOfTotalSubjects = externalsMarks.size
        val individualSubjectsGPA = ArrayList <Double> ()

        for (index in 0 until noOfTotalSubjects) {
            val currentSubjectGPA = ((subjectCredits[index] * ceil((internalsMarks[index] * (20.0 / 100) + externalsMarks[index] * (80.0 / 100)) / 10)) / (subjectCredits[index] * 10)) * 10
            individualSubjectsGPA.add(currentSubjectGPA)
        }

        return individualSubjectsGPA
    }

    private fun calculateOverallGPA(individualTheorySubjectsGPA: ArrayList <Double> , individualLaboratorySubjectsGPA: ArrayList <Double>): Double{

        var overallGPA = 0.0
        val noOfTotalTheorySubjects = individualTheorySubjectsGPA.size
        val noOfTotalLaboratorySubjects = individualLaboratorySubjectsGPA.size
        val noOfTotalSubjects = noOfTotalTheorySubjects + noOfTotalLaboratorySubjects

        for(item in individualTheorySubjectsGPA) {
            overallGPA += item
        }

        for(item in individualLaboratorySubjectsGPA) {
            overallGPA += item
        }

        overallGPA /= noOfTotalSubjects
        return overallGPA
    }

    private fun findReasonForUnEligibilityToWriteExternals(overallAttendancePercentageForTheoryClasses: Double , overallAttendancePercentageForLaboratoryClasses: Double , individualTheorySubjectsOverallInternalsPercentage: ArrayList <Double> , individualLaboratorySubjectsOverallInternalsPercentage: ArrayList <Double>): String {

        var isPoorAttendanceInTheoryClasses = false
        var isPoorAttendanceInLaboratoryClasses = false
        var isPoorPerformanceInTheorySubjectsInternals = false
        var isPoorPerformanceInLaboratorySubjectsInternals = false
        var reasonForUnEligibility = ""

        if(overallAttendancePercentageForTheoryClasses < 75)
            isPoorAttendanceInTheoryClasses = true

        if(overallAttendancePercentageForLaboratoryClasses < 75)
            isPoorAttendanceInLaboratoryClasses = true

        for(item in individualTheorySubjectsOverallInternalsPercentage) {
            if(item < 70) {
                isPoorPerformanceInTheorySubjectsInternals = true
                break
            }
        }

        for(item in individualLaboratorySubjectsOverallInternalsPercentage) {
            if(item < 70) {
                isPoorPerformanceInLaboratorySubjectsInternals = true
                break
            }
        }

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

    private fun findReasonForUnEligibilityToCalculateGPA(individualTheorySubjectsExternalsPercentage: ArrayList<Int>, individualLaboratorySubjectsExternalsPercentage: ArrayList<Int>): String {

        var isPoorPerformanceInTheorySubjectsExternals = false
        var isPoorPerformanceInLaboratorySubjectsExternals = false

        for (item in individualTheorySubjectsExternalsPercentage) {
            if (item < 40) {
                isPoorPerformanceInTheorySubjectsExternals = true
                break
            }
        }

        for (item in individualLaboratorySubjectsExternalsPercentage) {
            if (item < 40) {
                isPoorPerformanceInLaboratorySubjectsExternals = true
                break
            }
        }

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

fun main(args: Array <String>) {
    var repeat = 0
    val objectForAttendancePerformanceEvaluator = AttendancePerformanceEvaluator()
    do {
        when (objectForAttendancePerformanceEvaluator.displayMenu()) {
            1 -> objectForAttendancePerformanceEvaluator.displayCalculateMenu()
            2 -> objectForAttendancePerformanceEvaluator.displayWorksMenu()
        }
        println()
        var check: Boolean
        do {
            try {
                print("Do you want to continue (1 / 0) : ")
                repeat = (readLine()!!).toInt()
                println()
                check = false
            } catch (e: NumberFormatException) {
                println("Please enter a proper integer")
                println()
                check = true
            }
        } while(check)
    } while (repeat == 1)
}
