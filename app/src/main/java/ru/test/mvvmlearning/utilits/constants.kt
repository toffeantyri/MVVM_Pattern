package ru.test.mvvmlearning.utilits

import ru.test.mvvmlearning.MainActivity
import ru.test.mvvmlearning.database.DatabaseRepository

const val TAG = "MyLog"

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY : DatabaseRepository
const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"

const val TYPE_FIREBASE  = "type_firebase"
lateinit var EMAIL : String
lateinit var PASSWORD : String