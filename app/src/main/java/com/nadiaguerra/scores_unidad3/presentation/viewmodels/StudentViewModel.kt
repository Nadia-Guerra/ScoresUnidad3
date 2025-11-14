package com.nadiaguerra.scores_unidad3.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nadiaguerra.scores_unidad3.data.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class StudentViewModel: ViewModel() {

    private val _studentList = MutableStateFlow<List<Student>>(emptyList())
    val studentList: StateFlow<List<Student>> = _studentList.asStateFlow()

    private val _studentGroup = MutableStateFlow<Char?>(null)
    private var idCounter = 1

    fun filterGroup(group: Char) {
        _studentGroup.value = group
    }

    fun createStudent(
        id: Int,
        name: String,
        lastname: String,
        group: Char,
        score: Int
    ): Int {
        val newStudent = Student(
            id = idCounter++,
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
                it.id = studentId
            }
        }
    }

    fun editStudent(
        studentId: Int,
        newName: String,
        newLastName: String,
        newGroup: Char,
        newScore: Int
    ){
        _studentList.update { currentList ->
            currentList.map { student ->
                if (student.id = studentId){
                    student.copy(
                        id = studentId,
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

}