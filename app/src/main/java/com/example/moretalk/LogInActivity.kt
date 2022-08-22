package com.example.moretalk

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moretalk.databinding.ActivityLogInBinding
import com.example.moretalk.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.txtSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                login(email, password)
            }else{
                Toast.makeText(this@LogInActivity,"Fields can't be empty.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(email: String, password: String) {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Signing in..")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss()
                    val intent = Intent(this@LogInActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            val intent = Intent(this@LogInActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}