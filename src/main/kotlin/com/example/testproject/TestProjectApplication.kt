package com.example.testproject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TestProjectApplication

fun main(args: Array<String>) {
	runApplication<TestProjectApplication>(*args)
}
/*
@RestController
class HelloMessage{
	@GetMapping
	fun hello(): ResponseEntity<String>{
		println("hi")
		return ResponseEntity.ok("Hello World")
	}
}
 */

@RestController
class StudentResource{
	@Autowired
	val studentsRepo= StudentsRepo()

	@GetMapping("getAllStudents")
	fun getAllStudents(): List<Student>{
		return studentsRepo.retrieveAllStudents()
	}

	@PostMapping("/createStudent")
	fun createNewStudent(@RequestBody student: Student):List<Student>{
		return studentsRepo.addStudent(student)
	}

	@DeleteMapping("/deleteByName/{name}")
	fun deleteStudent(@PathVariable name:String): List<Student>{
		return studentsRepo.deleteByName(name)
	}
}
@Repository
class StudentsRepo {
	var allStudents= mutableListOf<Student>()
	init{
	val s1 = Student("Hari",24,"Student Address 1")
	val s2 = Student("Sandy",33,"Student Address 2")
	val s3 = Student("Jenny",12,"Student Address 3")
		allStudents.add(s1)
	allStudents.add(s2)
	allStudents.add(s3)
    }

	fun retrieveAllStudents(): List<Student> {
		return allStudents
	}

	fun addStudent(newStudent: Student): List<Student>{
		allStudents.add(newStudent)
		return allStudents
	}

	fun deleteByName(name :String): List<Student>{
		var obj:Student? =allStudents.find{it.name==name}
		allStudents.remove(obj)
		return allStudents
	}
}
data class Student(val name:String,val age:Int,val address: String)
