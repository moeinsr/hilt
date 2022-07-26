package academy.nouri.s1_project.repository

import academy.nouri.s1_project.api.ApiServices
import academy.nouri.s1_project.models.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: ApiServices) {
    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)
}