onCreate()

When we create DataBase at a first time (i.e Database is not exists) onCreate() create database with version which is passed in  SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)

onCreate() method is creating the tables you’ve defined and executing any other code you’ve written. However, this method will only be called if the SQLite file is missing in your app’s data directory (/data/data/your.apps.classpath/databases).

This method will not be called if you’ve changed your code and relaunched in the emulator. If you want onCreate() to run you need to use adb to delete the SQLite database file.

onUpgrade()

SQLiteOpenHelper should call the super constructor.
The onUpgrade() method will only be called when the version integer is larger than the current version running in the app.
If you want the onUpgrade() method to be called, you need to increment the version number in your code.
