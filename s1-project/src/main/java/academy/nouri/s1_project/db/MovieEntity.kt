package academy.nouri.s1_project.db

import academy.nouri.s1_project.utils.Constants
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.MOVIES_TABLE)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var poster: String = "",
    var title: String = "",
    var rate: String = "",
    var country: String = "",
    var year: String = ""
)
