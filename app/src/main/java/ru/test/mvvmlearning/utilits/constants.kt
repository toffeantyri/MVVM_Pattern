package ru.test.mvvmlearning.utilits

import ru.test.mvvmlearning.MainActivity
import ru.test.mvvmlearning.database.DatabaseRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY : DatabaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"