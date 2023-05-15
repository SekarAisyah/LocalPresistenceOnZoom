package com.example.localpresistenceonzoom.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class UserManager (private val context : Context) {

    private val Context.dataStore by preferencesDataStore(name = "userpref")

    private val NAME = stringPreferencesKey("user_name")
    private val AGE = intPreferencesKey("user_age")

    //Simpan Data
    suspend fun saveData(nama : String, umur : Int){
        context.dataStore.edit{
            it[NAME] = nama
            it[AGE] = umur
        }
    }

    // read data nama from data store
    val userNama : Flow<String> = context.dataStore.data.map {
        it[NAME]?: ""
    }

    // read data umut form data store
    val userUmur : Flow<Int> = context.dataStore.data.map {
        it[AGE]?: 0
    }

    //delete data
    suspend fun deleteData(){
        context.dataStore.edit {
            it.clear()
        }
    }


}