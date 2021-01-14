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
    println("   Enter your Name : ")
    val name = readLine()
    println("   Enter your Reg. No. : ")
    val regNo = (readLine()!!).toLong()
    println("   Enter your Semester No. : ")
    val semesterNo = (readLine()!!).toInt()
    println("   Enter Total No. of Subjects : ")
    val noOfTotalSubjects = (readLine()!!).toInt()
    val noOfTotalClassesConducted = ArrayList <Int> (noOfTotalSubjects)
    val noOfTotalClassesAttended = ArrayList <Int> (noOfTotalSubjects)
    for(i in 1..noOfTotalSubjects) {
        println("   Enter Total No. of Classes conducted in Subject $i : ")
        val classesConducted = (readLine()!!).toInt()
        noOfTotalClassesConducted.add(classesConducted)
        println("   Enter Total No. of Classes attended in Subject $i : ")
        val classesAttended = (readLine()!!).toInt()
        noOfTotalClassesAttended.add(classesAttended)
    }
    println("   Enter Total No. of Internals Conducted : ")
    val noOfInternals = (readLine()!!).toInt()
    val internalsMarks = ArrayList <ArrayList <Int>> (noOfInternals)
    for(i in 1..noOfInternals) {
        val currentInternalsMarks = ArrayList <Int> (noOfTotalSubjects)
        for(j in 1..noOfTotalSubjects) {
            println("   Enter Total Marks scored (out of 100) in Internal $i in Subject $j : ")
            val currentSubjectInternalMarks = (readLine()!!).toInt()
            currentInternalsMarks.add(currentSubjectInternalMarks)
        }
        internalsMarks.add(currentInternalsMarks)
    }

    val externalsMarks = ArrayList <Int> (noOfTotalSubjects)
    for(i in 1..noOfTotalSubjects) {
        println("   Enter Total Marks scored (out of 100) in External in Subject $i : ")
        val currentSubjectExternalsMarks = (readLine()!!).toInt()
        externalsMarks.add(currentSubjectExternalsMarks)
    }

    val subjectCredits = ArrayList <Int> (noOfTotalSubjects)
    for(i in 1..noOfTotalSubjects) {
        println("   Enter the Credits of Subject $i : ")
        val currentSubjectCredits = (readLine()!!).toInt()
        subjectCredits.add(currentSubjectCredits)
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
    println("   Final Resultant GPA = (GPA in Subject1 + GPA in Subject2 + GPA in Subject3 + ... + GPA in Subjectn) / (n). n - Total no. of subjects")
    println("   Individual Subject GPA = Subject Credits * Scored Credits")
    println("   Scored Credits = ceil((External Score * (80 / 100) + Internal Score * (20 / 100)) / 10)")
}

fun calculateAttendancePercentage(noOfTotalClassesConducted: ArrayList <Int> , noOfTotalClassesAttended: ArrayList <Int>): Double {

    val individualClassAttendancePercentage = ArrayList <Double> ()
    val noOfTotalSubjects = noOfTotalClassesAttended.size

    for(index in 1..noOfTotalSubjects) {
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

    for(columnIndex in 0..(noOfTotalSubjects - 1)) {
        var currentSubjectInternalsPercentage = 0.0
        for(rowIndex in 0..(noOfInternalsConducted - 1)) {
            currentSubjectInternalsPercentage += internalsMarks[rowIndex][columnIndex]
        }
        individualSubjectsInternalsPercentage.add((currentSubjectInternalsPercentage / noOfTotalSubjects))
    }

    return individualSubjectsInternalsPercentage
}

fun calculateGPA(internalsMarks: ArrayList <Double> , externalsMarks: ArrayList <Double> , subjectCredits: ArrayList <Int>): Double {

    val noofTotalSubjects = externalsMarks.size
    val individualSubjectsGPA = ArrayList <Double> ()
    var overallGPA = 0.0

    for(index in 0..(noofTotalSubjects - 1)) {
        val currentSubjectGPA = subjectCredits[index] * ceil((internalsMarks[index] * (20 / 100) + externalsMarks[index] * (80 / 100)) / 10)
        individualSubjectsGPA.add(currentSubjectGPA)
    }

    for(item in individualSubjectsGPA) {
        overallGPA += item
    }

    overallGPA /= noofTotalSubjects

    return overallGPA
}

//fun isEligibleToWriteExternals(): Boolean {
//
//}

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
}
