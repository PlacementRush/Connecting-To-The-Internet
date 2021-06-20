package com.example.connectingtotheinternet

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var superListView: ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        superListView = findViewById<ListView>(R.id.superListView)

//        getAllPosts()
        val updatedData = Posts()
        updatedData.userId = 1
        updatedData.title = "Changed Title"
        updatedData.body = "Changed Demo Text"

        updatePost(1, updatedData)
        deletePost(101)
    }

    fun createPost(view: View) {

        val title = findViewById<EditText>(R.id.title)
        val body = findViewById<EditText>(R.id.body)

        if (!title.text.isBlank() && !body.text.isBlank()) {

            val post = Posts()

            post.id = null
            post.userId = 1
            post.title = title.text.toString()
            post.body = body.text.toString()

            addPost(post)
        }
    }

    private fun addPost(postData: Posts){
        val retrofit = Api.buildService(PostService::class.java)
        retrofit.addPost(postData).enqueue(
            object : Callback<Posts> {
                override fun onFailure(call: Call<Posts>, t: Throwable) {
                    Log.e("Post List Error", "${t?.message}")
                }
                override fun onResponse( call: Call<Posts>, response: Response<Posts>) {
                    val addedUser = response.body()
                    Log.i("Post response code", response.code().toString())
                    Log.i("Post Added", addedUser?.toString())
                    Log.i("Post UserID", addedUser?.userId.toString())
                    Log.i("Post ID", addedUser?.id.toString())
                    Log.i("Post Title", addedUser?.title)
                    Log.i("Post Body", addedUser?.body)
                    Toast.makeText(applicationContext, "Post added with code " + response.code().toString(), Toast.LENGTH_SHORT).show()

                }
            }
        )
    }
    
    private fun updatePost(id: Int?, updatedData: Posts?) {

        val retrofit = Api.buildService(PostService::class.java)
        retrofit.putPost(id.toString(), updatedData!!).enqueue(
            object : Callback<Posts> {
                override fun onFailure(call: Call<Posts>, t: Throwable) {
                    Log.e("Post List Error", "${t?.message}")
                }

                override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                    val updatedUser = response.body()
                    Log.i("Updated response code", response.code().toString())
                    Log.i("Updated Post", updatedUser?.toString())
                    Log.i("Updated UserID", updatedUser?.userId.toString())
                    Log.i("Updated ID", updatedUser?.id.toString())
                    Log.i("Updated Title", updatedUser?.title)
                    Log.i("Updated Body", updatedUser?.body)
                    Toast.makeText(applicationContext, "Post updated with code " + response.code().toString(), Toast.LENGTH_SHORT).show()

                }

            }
        )

    }

    private fun deletePost(id: Int) {
        val retrofit = Api.buildService(PostService::class.java)
        retrofit.deletePost(id)?.enqueue(object : Callback<Void?> {
            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.e("Post List Error", "${t?.message}")
            }

            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Log.i("Post Deleted", "The post has been successfully deleted")
                Toast.makeText(applicationContext, "Post deleted", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    private fun getAllPosts(){
//        val postList = Api.buildService(PostService::class.java).getPostList()
//
//
//        postList.enqueue(object : Callback<List<Posts>> {
//            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
//                Log.e("Post List Error", "${t?.message}")
//            }
//
//            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
//                var posts = response?.body()!!
//
//                Log.i("Posts Result", "$posts")
//
//                val adapter = PostAdapter(applicationContext, posts)
//                superListView.adapter = adapter
//
//            }
//
//        })
//    }


}
