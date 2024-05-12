package com.example.medix.data.repository

import com.example.medix.data.authentication.Resource
import com.example.medix.data.authentication.utils.await
import com.example.medix.domain.model.User
import com.example.medix.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override val currentUser: User?
        get() = runBlocking { getUserData() }

    override suspend fun login(email: String, password: String): Resource<User> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val userData = getUserData()
            if (result.user != null && userData != null) {
                Resource.Success(userData)
            } else {
                Resource.Failure(Exception("Failed to retrieve user data"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String,
        isDoctor: Boolean,
        isPatient: Boolean
    ): Resource<User> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            if (result != null && result.user != null) {
                val user = User(
                    name,
                    email,
                    "",
                    isDoctor,
                    isPatient,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    ""
                )
                Resource.Success(user)
            } else {
                Resource.Failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun getUserData(): User? {
        val currentUser = firebaseAuth.currentUser
        return if (currentUser != null) {
            val name = currentUser.displayName ?: ""
            val email = currentUser.email ?: ""
            User(
                name = name,
                email = email,
                password = "",
                isDoctor = false,
                isPatient = false,
                phone = null,
                dateOfBirth = null,
                gender = null,
                speciality = null,
                bio = null,
                address = null,
                wage = null,
                image = ""
            )
        } else {
            null
        }
    }

    /*override suspend fun login(email: String, password: String): Resource<String> {
        return try {
            val accessToken = medixApi.loginUser(email, password)
            if (accessToken.token.isNotEmpty()) {
                Resource.Success(accessToken.token)
            } else {
                Resource.Failure(Exception("Access token is empty"))
            }
        } catch (e: HttpException) {
            Resource.Failure(e)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }

    override suspend fun signup(
        name: String, email: String, password: String, isDoctor: Boolean, isPatient: Boolean
    ): Resource<String> {
        return try {
            val accessToken = medixApi.registerUser(name, email, password, isDoctor, isPatient)
            if (accessToken.token.isNotEmpty()) {
                Resource.Success(accessToken.token)
            } else {
                Resource.Failure(Exception("Access token is empty"))
            }
        } catch (e: HttpException) {
            Resource.Failure(e)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }


    override fun logout() {
        firebaseAuth.signOut()
    }*/
}