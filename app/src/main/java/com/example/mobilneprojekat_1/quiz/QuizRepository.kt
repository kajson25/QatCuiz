package com.example.mobilneprojekat_1.quiz

import com.example.catapult.data.database.entities.ResultDbModel
import com.example.mobilneprojekat_1.database.AppDatabase
import com.example.mobilneprojekat_1.database.entities.CatDbModel
import com.example.mobilneprojekat_1.image.asImageDbModel
import com.example.mobilneprojekat_1.networking.controller.MyApiService
import com.example.mobilneprojekat_1.user.datastore.UserStore
import com.example.mobilneprojekat_1.quiz.question_view.QuizQuestionContract.Question
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class QuizRepository @Inject constructor(
    private val catsApi: MyApiService,
    private val database: AppDatabase,
    private val store: UserStore
) {

    private var temperaments: List<String> = listOf()

    suspend fun generateQuestions(): List<Question> = coroutineScope {
        val questions = mutableListOf<Deferred<Question>>()
        repeat(2) {
            questions += async {
                withContext(Dispatchers.IO) {
                    when (Random.nextInt(3)) {
                        0 -> generateTypeOne()
                        1 -> generateTypeTwo()
                        else -> generateTypeThree()
                    }
                }
            }
        }
        questions.awaitAll()
    }

    /**
     * Fetches images for a cat from the API and saves them to the database.
     * If the images are already in the database, returns them from the database.
     */
    suspend fun fetchImagesForCat(q: Question) {
        val catId = q.catId
        var images = database.imageDao().getAllForCat(catId)

        if (images.isEmpty()) {
            val imagesApi = catsApi.getImagesForBreed(catId)
            database.imageDao().insertAll(imagesApi.map { it.asImageDbModel(catId) })

            images = database.imageDao().getAllForCat(catId)
        }

        q.catImageUrl = images.random().url
    }

    suspend fun submitResultToDatabase(score: Double) {
        withContext(Dispatchers.IO) {
            database.resultDao().insert(
                ResultDbModel(
                    username = store.getUserData().nickname,
                    result = score,
                    createdAt = System.currentTimeMillis(),
                    published = false
                )
            )
        }
    }

    /**
     * Generates a question of type 1: "Which cat is this?"
     * Shows an image of a random cat and 4 possible answers.
     */
    private suspend fun generateTypeOne(): Question {
        val cat = chooseRandomCat()     //  correct answer

        val allCats = database.catDao().getAll()
        val answers = allCats.filter { it != cat }.shuffled().take(3).map { it.name.lowercase() } + cat.name.lowercase()
        return Question("Which cat is this?", cat.id, "", answers.shuffled(), cat.name.lowercase())
    }

    /**
     * Generates a question of type 2: "Which temperament does not belong to this cat?"
     * Shows an image of a random cat and 4 possible answers.
     */
    private suspend fun generateTypeTwo(): Question {
        val cat = chooseRandomCat()       //  correct answer

        val catTemperaments = cat.temperament.split(",").map { it.trim().lowercase() }
        val allTemperaments = fetchTemperaments().map { it.trim().lowercase() }

        // wrongTemperaments = allTemperaments - catTemperaments
        val wrongTemperaments = allTemperaments.filter { it !in catTemperaments }.shuffled()
        val correctAnswer = wrongTemperaments.random()

        val answers = catTemperaments.take(3) + correctAnswer
        return Question("Which temperament does not belong to this cat?", cat.id, "", answers.shuffled(), correctAnswer)
    }

    /**
     * Generates a question of type 3: "Which temperament belongs to this cat?"
     * Shows an image of a random cat and 4 possible answers.
     */
    private suspend fun generateTypeThree(): Question {
        val cat = chooseRandomCat()     //  correct answer

        val catTemperaments = cat.temperament.split(",").map { it.trim().lowercase() }
        val allTemperaments = fetchTemperaments().map { it.trim().lowercase() }

        // wrongTemperaments = allTemperaments - catTemperaments
        val wrongTemperaments = allTemperaments.filter { it !in catTemperaments }.shuffled().take(3)
        val correctAnswer = catTemperaments.random()

        val answers = wrongTemperaments + correctAnswer
        return Question("Which temperament belongs to this cat?", cat.id, "", answers.shuffled(), correctAnswer)
    }


    private suspend fun chooseRandomCat(): CatDbModel {
        val excludedIds = listOf("mala")

        val catsList = database.catDao().getAll().filter { it.id !in excludedIds}
        return catsList.random()
    }

    private suspend fun fetchTemperaments():List<String> {
        if (temperaments.isEmpty())
            temperaments = database.catDao().getAllTemperaments()
        return temperaments
    }
}