package com.example.moretalk

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moretalk.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener{
            val name = binding.edtName.text.toString().lowercase()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()


            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                    signUp(name, email, password)
                }else{
                    Toast.makeText(this@SignUpActivity,"Passwords don't match.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@SignUpActivity,"Fields can't be empty.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtAlreadyRegistered.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp(name: String, email: String, password: String) {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Signing up user..")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name, email, auth.currentUser?.uid)
                    progressDialog.dismiss()
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String?) {
        val userName = "exampleName"
        val imageUrl = "https://firebasestorage.googleapis.com/v0/b/moretalk-3eae1.appspot.com/o/Default%20Images%2Fprofile.png?alt=media&token=3b8143ff-2a79-445c-9cf7-979b6313e0c3"
        val bio = "Example Bio"
        dbRef = FirebaseDatabase.getInstance().getReference()
        dbRef.child("user").child(uid!!).setValue(User(name,email, uid, imageUrl, bio, userName.lowercase()))

    }
}