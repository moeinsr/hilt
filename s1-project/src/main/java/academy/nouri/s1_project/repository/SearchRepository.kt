package academy.nouri.s1_project.repository

import academy.nouri.s1_project.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api:ApiServices){
    suspend fun searchMovie(name:String) = api.searchMovie(name)
}