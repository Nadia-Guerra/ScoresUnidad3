package com.nadiaguerra.scores_unidad3.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.nadiaguerra.scores_unidad3.data.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class StudentViewModel : ViewModel() {
    private val _studentList = MutableStateFlow<List<Student>>(emptyList())
    val studentList: StateFlow<List<Student>> = _studentList.asStateFlow()
    private var idCounter = 1

    fun createStudent(
        name: String,
        lastname: String,
        group: Char,
        score: Int
    ): Int {
        val newStudent = Student(
            id = idCounter,
            name = name,
            lastname = lastname,
            group = group,
            score = score,
        )

        val newId = idCounter
        idCounter++

        _studentList.update { currentList ->
            currentList + newStudent
        }

        return newId
    }

    fun deleteStudent(studentId: Int) {
        _studentList.update { currentList ->
            currentList.filterNot {
                it.id == studentId
            }
        }
    }

    fun editStudent(
        studentId: Int,
        newName: String,
        newLastName: String,
        newGroup: Char,
        newScore: Int
    ) {
        _studentList.update { currentList ->
            currentList.map { student ->
                if (student.id == studentId) {
                    student.copy(
                        name = newName,
                        lastname = newLastName,
                        group = newGroup,
                        score = newScore
                    )
                } else {
                    student
                }
            }
        }
    }

    fun filterByGroup(group: Char): List<Student> {
        return _studentList.value.filter { it.group == group }
    }

    fun get3BestStudents(): List<Student> {
        return _studentList.value
            .sortedByDescending { it.score }
            .take(3)
    }

    fun get3WorstStudents(): List<Student> {
        return _studentList.value
            .sortedBy { it.score }
            .take(3)
    }

    fun getAllStudentsByGroup(): List<Char> { // para mostrar el estudiante junto a su grupo, no modifica
        // si se requiere una descripcion, ejemplo:
        //val description = student.map { "${it.name} del grupo ${it.group}" }
        return _studentList.value
            .map { it.group }
            .distinct() //elimina duplicados, asi como un group by en sql LKJJSKJSA
            .sorted()
    }

    fun getAverageScoresByGroup(group: Char): Double {
        val students = _studentList.value.filter { it.group == group }

        if (students.isEmpty()){
            return 0.0
        }
        val totalScore = students.sumOf { it.score }
        return totalScore.toDouble()/students.size
    }


}