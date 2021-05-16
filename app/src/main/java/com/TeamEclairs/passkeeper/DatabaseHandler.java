package com.TeamEclairs.passkeeper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "passwordManager";

	// Contacts table name
	private static final String TABLE_PASSWORDS = "passwords";

	// Contacts Table Columns names
	private static final String KEY_TYPE = "account_type";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_URL = "url";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_NOTES = "notes";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PASSWORDS + "("
				+ KEY_TYPE + " TEXT," + KEY_USERNAME + " TEXT," + KEY_URL + " TEXT," + KEY_PASSWORD + " TEXT PRIMARY KEY,"
				+ KEY_NOTES + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORDS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addPassword(Password password) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, password.getAccountType());
		values.put(KEY_USERNAME, password.getUsername());
		values.put(KEY_URL, password.getUrl());
		values.put(KEY_PASSWORD, password.getPassword());
		values.put(KEY_NOTES, password.getNotes());

		// Inserting Row
		
		db.insert(TABLE_PASSWORDS ,null,values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	Password getPassword(String pwd) {
		SQLiteDatabase db = this.getReadableDatabase();

		
		Cursor cursor = db.query(TABLE_PASSWORDS, new String[] { KEY_TYPE,KEY_USERNAME,KEY_URL,
				KEY_PASSWORD, KEY_NOTES }, KEY_PASSWORD + "=?",
				new String[] { String.valueOf(pwd) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Password password = new Password(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
		// return contact
		return password;
	}
	
	// Getting All Contacts
	public List<Password> getAllPassword() {
		List<Password> passwordList = new ArrayList<Password>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_PASSWORDS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Password password = new Password();
				password.setAccountType(cursor.getString(0));
				password.setUsername(cursor.getString(1));
				password.setUrl(cursor.getString(2));
				password.setPassword(cursor.getString(3));
				password.setNotes(cursor.getString(4));
				// Adding contact to list
				passwordList.add(password);
			} while (cursor.moveToNext());
		}

		// return contact list
		return passwordList;
	}

	// Updating single contact
	public int updatePassword(Password password) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TYPE, password.getAccountType());
		values.put(KEY_USERNAME, password.getUsername());
		values.put(KEY_URL, password.getUrl());
		values.put(KEY_PASSWORD, password.getPassword());
		values.put(KEY_NOTES, password.getNotes());
		// updating row
		return db.update(TABLE_PASSWORDS, values, KEY_URL + " = ?",
				new String[] { String.valueOf(password.getUrl()) });
	}

	// Deleting single contact
	public void deletePassword(Password password) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PASSWORDS, KEY_URL + " = ?",
				new String[] { String.valueOf(password.getUrl()) });
		db.close();
	}


	// Getting contacts Count
	public int getPasswordCount() {
		String countQuery = "SELECT  * FROM " + TABLE_PASSWORDS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
