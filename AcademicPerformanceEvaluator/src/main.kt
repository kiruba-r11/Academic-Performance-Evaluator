import kotlin.math.ceil

fun displayMenu(): Int {
    println("A C A D E M I C   P E R F O R M A N C E   E V A L U A T O R")
    println()
    println("Choose your choice from the following : ")
    println()
    println("   1. Calculate your Academic Performance")
    println("   2. How it works")
    println()
    println("Input Here : ")
    val choice = (readLine()!!).toInt()
    return choice
}

fun displayCalculateMenu() {
    println("A C A D E M I C   P E R F O R M A N C E   E V A L U A T O R")
    println()
    println("Enter the following details to calculate your academic performance")
    println()
//    println("General Information : ")
//    println()
//    println("   Enter your Name : ")
//    val name = readLine()
//    println("   Enter your Reg. No. : ")
//    val regNo = (readLine()!!).toLong()
//    println("   Enter your Semester No. : ")
//    val semesterNo = (readLine()!!).toInt()
    println("Subject Contact Periods Information : ")
    println()
    println("   Enter Total No. of Theory Subjects : ")
    val noOfTotalTheorySubjects = (readLine()!!).toInt()
    val noOfTotalTheoryClassesConducted = ArrayList <Int> (noOfTotalTheorySubjects)
    val noOfTotalTheoryClassesAttended = ArrayList <Int> (noOfTotalTheorySubjects)
    for(i in 1..noOfTotalTheorySubjects) {
        println("       Subject - $i : ")
        println()
        println("           Enter Total No. of Theory Classes conducted : ")
        val classesConducted = (readLine()!!).toInt()
        noOfTotalTheoryClassesConducted.add(classesConducted)
        println("           Enter Total No. of Theory Classes attended : ")
        val classesAttended = (readLine()!!).toInt()
        noOfTotalTheoryClassesAttended.add(classesAttended)
    }
    println("   Enter Total No. of Laboratory Subjects : ")
    val noOfTotalLaboratorySubjects = (readLine()!!).toInt()
    val noOfTotalLaboratoryClassesConducted = ArrayList <Int> (noOfTotalLaboratorySubjects)
    val noOfTotalLaboratoryClassesAttended = ArrayList <Int> (noOfTotalLaboratorySubjects)
    for(i in 1..noOfTotalLaboratorySubjects) {
        println("       Subject - $i : ")
        println()
        println("           Enter Total No. of Laboratory Classes conducted : ")
        val classesConducted = (readLine()!!).toInt()
        noOfTotalLaboratoryClassesConducted.add(classesConducted)
        println("           Enter Total No. of Laboratory Classes attended : ")
        val classesAttended = (readLine()!!).toInt()
        noOfTotalLaboratoryClassesAttended.add(classesAttended)
    }
    println("Subject Credits Information : ")
    println()
    println("   Theory Subjects : ")
    println()
    val theorySubjectCredits = ArrayList <Int> (noOfTotalTheorySubjects)
    for(i in 1..noOfTotalTheorySubjects) {
        println("       Enter the Credits of Theory Subject $i : ")
        val currentSubjectCredits = (readLine()!!).toInt()
        theorySubjectCredits.add(currentSubjectCredits)
    }
    println("   Laboratory Subjects : ")
    println()
    val laboratorySubjectCredits = ArrayList <Int> (noOfTotalLaboratorySubjects)
    for(i in 1..noOfTotalLaboratorySubjects) {
        println("       Enter the Credits of Laboratory Subject $i : ")
        val currentSubjectCredits = (readLine()!!).toInt()
        laboratorySubjectCredits.add(currentSubjectCredits)
    }
    var reasonForUnEligibility = ""
    val overallAttendancePercentageForTheoryClasses = calculateAttendancePercentage(noOfTotalTheoryClassesConducted , noOfTotalTheoryClassesAttended)
    val overallAttendancePercentageForLaboratoryClasses = calculateAttendancePercentage(noOfTotalLaboratoryClassesConducted , noOfTotalLaboratoryClassesAttended)
    println("Theory Internals Examination Information : ")
    println()
    println("   Enter Total No. of Internals Conducted for Theory Subjects: ")
    val noOfInternalsForTheorySubjects = (readLine()!!).toInt()
    val internalsMarksInTheorySubjects = ArrayList <ArrayList <Int>> (noOfInternalsForTheorySubjects)
    for(i in 1..noOfInternalsForTheorySubjects) {
        val currentInternalsMarks = ArrayList <Int> (noOfTotalTheorySubjects)
        println("Theory Internals Examination - $i : ")
        println()
        for(j in 1..noOfTotalTheorySubjects) {
            println("   Enter Total Marks scored (out of 100) in Internal $i in Subject $j : ")
            val currentSubjectInternalMarks = (readLine()!!).toInt()
            currentInternalsMarks.add(currentSubjectInternalMarks)
        }
        internalsMarksInTheorySubjects.add(currentInternalsMarks)
    }
    println("Laboratory Internals Examination Information : ")
    println()
    println("   Enter Total No. of Internals Conducted for Laboratory Subjects: ")
    val noOfInternalsForLaboratorySubjects = (readLine()!!).toInt()
    val internalsMarksInLaboratorySubjects = ArrayList <ArrayList <Int>> (noOfInternalsForLaboratorySubjects)
    for(i in 1..noOfInternalsForLaboratorySubjects) {
        val currentInternalsMarks = ArrayList <Int> (noOfTotalLaboratorySubjects)
        println("Laboratory Internals Examination - $i : ")
        println()
        for(j in 1..noOfTotalLaboratorySubjects) {
            println("   Enter Total Marks scored (out of 100) in Internal $i in Subject $j : ")
            val currentSubjectInternalMarks = (readLine()!!).toInt()
            currentInternalsMarks.add(currentSubjectInternalMarks)
        }
        internalsMarksInLaboratorySubjects.add(currentInternalsMarks)
    }

    val individualTheorySubjectsOverallInternalsPercentage = calculateInternalsPercentage(internalsMarksInTheorySubjects)
    val individualLaboratorySubjectsOverallInternalsPercentage = calculateInternalsPercentage(internalsMarksInLaboratorySubjects)
    val isEligibleToWriteExternals = isEligibleToWriteExternals(overallAttendancePercentageForTheoryClasses , individualTheorySubjectsOverallInternalsPercentage , overallAttendancePercentageForLaboratoryClasses , individualLaboratorySubjectsOverallInternalsPercentage)
    if(isEligibleToWriteExternals) {
        println("Theory Externals Examination : ")
        println()
        val individualTheorySubjectsExternalsPercentage = ArrayList <Int> (noOfTotalTheorySubjects)
        for (i in 1..noOfTotalTheorySubjects) {
            println("   Enter Total Marks scored (out of 100) in Externals in Theory Subject $i : ")
            val currentSubjectsExternalsPercentage = (readLine()!!).toInt()
            individualTheorySubjectsExternalsPercentage.add(currentSubjectsExternalsPercentage)
        }
        println("Laboratory Externals Examination : ")
        println()
        val individualLaboratorySubjectsExternalsPercentage = ArrayList <Int> (noOfTotalLaboratorySubjects)
        for (i in 1..noOfTotalLaboratorySubjects) {6

            println("   Enter Total Marks scored (out of 100) in Externals in Laboratory Subject $i : ")
            val currentSubjectsExternalsPercentage = (readLine()!!).toInt()
            individualLaboratorySubjectsExternalsPercentage.add(currentSubjectsExternalsPercentage)
        }
        val isEligibleToCalculateGPA = isEligibleToCalculateGPA(individualTheorySubjectsExternalsPercentage , individualLaboratorySubjectsExternalsPercentage)
        if(isEligibleToCalculateGPA) {
            val individualTheorySubjectsGPA = calculateIndividualSubjectsGPA(individualTheorySubjectsOverallInternalsPercentage , individualTheorySubjectsExternalsPercentage , theorySubjectCredits)
            val individualLaboratorySubjectsGPA = calculateIndividualSubjectsGPA(individualLaboratorySubjectsOverallInternalsPercentage , individualLaboratorySubjectsExternalsPercentage , laboratorySubjectCredits)
            val overallGPA = calculateOverallGPA(individualTheorySubjectsGPA , individualLaboratorySubjectsGPA)

            for(item in individualLaboratorySubjectsGPA) {
                println(item)
            }

            println("Overall : $overallGPA")

        }
        else {
            reasonForUnEligibility = findReasonForUnEligibilityToCalculateGPA(individualTheorySubjectsExternalsPercentage , individualLaboratorySubjectsExternalsPercentage)
            println(reasonForUnEligibility)
            println("Note : You are not eligible for GPA Calculation as you have not passed the minimum criteria in your Externals Exam")
        }
    }
    else {
        reasonForUnEligibility = findReasonForUnEligibilityToWriteExternals(overallAttendancePercentageForTheoryClasses , overallAttendancePercentageForLaboratoryClasses , individualTheorySubjectsOverallInternalsPercentage , individualLaboratorySubjectsOverallInternalsPercentage)
        println(reasonForUnEligibility)
        println("Note : You cannot write Externals as you have not passed the minimum criteria to be eligible for it.")
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

fun calculateAttendancePercentage(noOfTotalClassesConducted: ArrayList <Int> , noOfTotalClassesAttended: ArrayList <Int>): Double {

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

fun calculateInternalsPercentage(internalsMarks: ArrayList <ArrayList <Int>>): ArrayList <Double> {

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

fun isEligibleToWriteExternals(overallAttendancePercentageForTheoryClasses: Double , individualTheorySubjectsOverallInternalsPercentage: ArrayList <Double> , overallAttendancePercentageForLaboratoryClasses: Double , individualLaboratorySubjectsOverallInternalsPercentage: ArrayList<Double>): Boolean {

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

fun isEligibleToCalculateGPA(theoryExternalsMarks: ArrayList<Int> , laboratoryExternalsMarks: ArrayList <Int>): Boolean {

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

fun calculateIndividualSubjectsGPA(internalsMarks: ArrayList <Double> , externalsMarks: ArrayList <Int> , subjectCredits: ArrayList <Int>): ArrayList <Double> {

    val noOfTotalSubjects = externalsMarks.size
    val individualSubjectsGPA = ArrayList<Double>()

//    for(item in internalsMarks)
//        println(item)
//
//    for(item in externalsMarks)
//        println(item)

    for (index in 0 until noOfTotalSubjects) {
        var scored = (internalsMarks[index] * (20 / 100) + externalsMarks[index].toDouble() * (80 / 100))
        println(scored)
        val currentSubjectGPA = (subjectCredits[index] * ceil((internalsMarks[index] * (20 / 100) + externalsMarks[index] * (80 / 100)) / 10)) / (subjectCredits[index] * 10)
        individualSubjectsGPA.add(currentSubjectGPA)
    }

    return individualSubjectsGPA
}

fun calculateOverallGPA(individualTheorySubjectsGPA: ArrayList <Double> , individualLaboratorySubjectsGPA: ArrayList <Double>): Double{

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

fun findReasonForUnEligibilityToWriteExternals(overallAttendancePercentageForTheoryClasses: Double , overallAttendancePercentageForLaboratoryClasses: Double , individualTheorySubjectsOverallInternalsPercentage: ArrayList <Double> , individualLaboratorySubjectsOverallInternalsPercentage: ArrayList <Double>): String {

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
    else
        reasonForUnEligibility = "Poor Attendance Record in Laboratory Classes"

    if(reasonForUnEligibility != "")
        reasonForUnEligibility += " and "

    if(isPoorPerformanceInTheorySubjectsInternals && isPoorPerformanceInLaboratorySubjectsInternals)
        reasonForUnEligibility += "Poor Performance in Both Theory and Laboratory Subjects in Internals"
    else if(isPoorPerformanceInTheorySubjectsInternals)
        reasonForUnEligibility += "Poor Performance in Theory Subjects in Internals"
    else
        reasonForUnEligibility += "Poor Performance in Laboratory Subjects in Internals"

    return reasonForUnEligibility
}

fun findReasonForUnEligibilityToCalculateGPA(individualTheorySubjectsExternalsPercentage: ArrayList <Int> , individualLaboratorySubjectsExternalsPercentage: ArrayList <Int>): String {

    var isPoorPerformanceInTheorySubjectsExternals = false
    var isPoorPerformanceInLaboratorySubjectsExternals = false
    var reasonForUnEligibility = ""

    for(item in individualTheorySubjectsExternalsPercentage) {
        if(item < 40) {
            isPoorPerformanceInTheorySubjectsExternals = true
            break
        }
    }

    for(item in individualLaboratorySubjectsExternalsPercentage) {
        if(item < 40) {
            isPoorPerformanceInLaboratorySubjectsExternals = true
            break
        }
    }

    if(isPoorPerformanceInTheorySubjectsExternals && isPoorPerformanceInLaboratorySubjectsExternals)
        reasonForUnEligibility = "Poor Performance in Both Theory And Laboratory Subjects in Externals"
    else if(isPoorPerformanceInTheorySubjectsExternals)
        reasonForUnEligibility = "Poor Performance in Theory Subjects in Externals"
    else
        reasonForUnEligibility = "Poor Performance in Laboratory Subjects in Externals"

    return reasonForUnEligibility
}

fun main(args: Array <String>) {
//    var repeat = 0
//    do {
//        val choice = displayMenu()
//        when (choice) {
//            1 -> displayCalculateMenu()
//            2 -> displayWorksMenu()
//        }
//        println("Do you want to continue (1 / 0) : ")
//        repeat = (readLine()!!).toInt()
//    } while(repeat == 1)

    displayCalculateMenu()
}
