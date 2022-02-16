# RoomKotlinClase

## Pasos para crear una aplicacion basica MVVM con ROOM
### 1. Dependencias y setup
**Nuestro plugins deberian de estar asi**
```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
}
```
Si no añadimos el kapt luego no reconocera la implementacion de las dependencias
**Dependencias**
Room
```
def room_version = "2.2.6"
implementation "androidx.room:room-runtime:$room_version"
kapt "androidx.room:room-compiler:$room_version"
implementation "androidx.room:room-ktx:$room_version"
```
Corrutinas para que el hilo principal no se sobrecargue
```
 implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2'
 implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2'
 ```
 View model y live data
 ```
def lifecycle_version = "2.3.1"
// ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
// LiveData
implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
implementation "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
```
### 2. Model
**Data Class**
Creamos una data class con los campos que tendra la tabla primera
Atributo entity indica que es una tabla, autogenerate para que la PK sea autoincremental y podemos poner anotaciones para la informacion de la columna
```
@Entity(tableName = "Libros")
data class LibrosDataClass(
    @PrimaryKey(autoGenerate = true)
    var ID: Int? = 0,
    @ColumnInfo(name = "titulo")
    var titulo : String,
    @ColumnInfo(name = "autor")
    var autor: String)
```
**Interfaz DAO**
DAO significa data access object, aqui tendremos los metodos con los que comunicarnos con la BD.
Es importante poner @Dao
Podemos usar el LiveData en el select para que se este actualizando en tiempo real y no haya que llamar al metodo siempre
```
interface DAOLibros {
    @Query("SELECT * FROM libros")
    fun getLibros():LiveData<List<LibrosDataClass>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLibro(libro:LibrosDataClass)

    @Delete
    fun deleteLibro(dltLibro: LibrosDataClass)

    @Query("UPDATE libros SET titulo=:tituloNuevo, autor=:autorNuevo WHERE id=:id")
    fun updateLibro(id: Int, tituloNuevo: String, autorNuevo: String)

    @Query("DELETE FROM Libros")
    fun deleteAll()
}
```
**Clase abstracta instancia de RoomDatabase** 
Este sera codigo que nunca cambiara, por lo cual se puede coger de modelo una vez y reutilizar siempre el codigo
```
@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase : RoomDatabase() {
    abstract val subscriberDAO: DAOLibros
    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null
        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
```
### 3. Clase repositorio de MVVM
Esta tendra como argumento de tipo interfaz que hemos creado antes.
```
class LibroRepository(private val dao: DAOLibros) {
    val subscribers = dao.getAllSubscribers()
    suspend fun insert(subscriber: Subscriber): Long {
        return dao.insertSubscriber(subscriber)
    }
    suspend fun update(subscriber: Subscriber): Int {
        return dao.updateSubscriber(subscriber)
    }
    suspend fun delete(subscriber: Subscriber): Int {
        return dao.deleteSubscriber(subscriber)
    }
    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}
```
### 4. Clase ViewModel
Esta clase tendra como argumento de tipo clase que hemos creado en el punto 3. Tendra que extender del ViewModel
```
class MainViewModel(private val repository: LibroRepository) : ViewModel() {
}
```
