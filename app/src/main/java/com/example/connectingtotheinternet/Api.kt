package com.example.connectingtotheinternet

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


class Api {
    companion object{
        private val url = "https://jsonplaceholder.typicode.com/"
        private val client = OkHttpClient.Builder().build()
        val postService: PostService? = null


        private val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }
}

interface PostService{
    @GET("posts")
    fun getPostList(): Call<List<Posts>>

    @Headers("Content-Type: application/json")
    @POST("posts")
    fun addPost(@Body post: Posts): Call<Posts>

    @Headers("Content-Type: application/json")
    @PUT("posts/{id}")
    fun putPost(@Path("id") id: String, @Body post: Posts): Call<Posts>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int): Call<Void?>?
//
//    @GET("posts/{postId}")
//    fun getPostWithId(@Path("posts") id: String) : Call<Posts>
}