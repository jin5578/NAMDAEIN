package com.tistory.jeongs0222.namdaein.model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int): SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERINFO (google_uId TEXT, nickname TEXT, push TEXT);")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insert(google_uId: String, nickname: String, push: String) {
        val db: SQLiteDatabase = writableDatabase

        db.execSQL("INSERT INTO USERINFO VALUES('" + google_uId + "', '" + nickname + "', '" + push + "');")

        db.close()
    }

    fun nicknameUpdate(nickname: String) {
        val db: SQLiteDatabase = writableDatabase

        db.execSQL("UPDATE USERINFO SET nickname = '" + nickname + "';")

        db.close()
    }

    fun pushUpdate(push: String) {
        val db: SQLiteDatabase = writableDatabase

        db.execSQL("UPDATE USERINFO SET push = '" + push + "';")

        db.close()
    }

    fun getNickname(): String? {
        var nickname: String? = null

        val db: SQLiteDatabase = readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT nickname FROM USERINFO", null)

        while(cursor.moveToNext()) {
            nickname = cursor.getString(0)
        }

        cursor.close()

        db.close()

        return nickname
    }

    fun getGoogle_uId(): String? {
        var google_uId: String? = null

        val db: SQLiteDatabase = readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT google_uId FROM USERINFO", null)

        while(cursor.moveToNext()) {
            google_uId = cursor.getString(0)
        }

        cursor.close()

        db.close()

        return google_uId
    }

    fun getPush(): String? {
        var push: String? = null

        val db: SQLiteDatabase = readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT push FROM USERINFO", null)

        while(cursor.moveToNext()) {
            push = cursor.getString(0)
        }

        cursor.close()

        db.close()

        return push
    }

}