package com.example.pracainzynierska

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ReturnThis
import androidx.appcompat.app.ActionBar
import com.example.pracainzynierska.databinding.ActivityProfileBinding
import com.example.pracainzynierska.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding: ActivityProfileBinding
    //ActionBar
    private lateinit var actionBar: ActionBar
    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configure ActionBar
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        //INIT FIREBASE AUTH
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if  (firebaseUser != null) {
            //user not null, user is logged in, get user info
            val email = firebaseUser.email
            //set to text view
            binding.emailTv.text = email

        }
        else{
            //user is null, user is not loggedin , go to login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}