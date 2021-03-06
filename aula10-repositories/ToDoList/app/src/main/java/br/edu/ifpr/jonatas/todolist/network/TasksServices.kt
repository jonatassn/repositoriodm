package br.edu.ifpr.jonatas.todolist.network

import br.edu.ifpr.jonatas.todolist.entities.Task
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface TasksServices {
    @Headers("Accept: application/json")
    @GET("tasks")
    fun getAll(): Single<List<Task>>

//    fun findById(id: Int): Task?
//
    @Headers("Accept: application/json")
    @POST("tasks")
    @FormUrlEncoded
    fun createTask(
        @Field("task[title]")
        title: String,
        @Field("task[description]")
        description: String,
        @Field("task[done]")
        done: Boolean
    ): Single<Task>
//
    @Headers("Accept: application/json")
    @PUT("tasks/{id}")
    @FormUrlEncoded
    fun update(
                @Path("id")
                id:Long,
               @Field("task[title]")
               title: String,
               @Field("task[description]")
               description: String,
               @Field("task[done]")
               done: Boolean
    ) : Completable
//
    @Headers("Accept: application/json")
    @DELETE("tasks/{id}")
    fun remove(@Path("id")
               id:Long
    ) : Completable
}