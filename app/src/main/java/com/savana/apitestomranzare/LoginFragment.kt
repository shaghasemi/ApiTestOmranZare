package com.savana.apitestomranzare

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.savana.apitestomranzare.Model.loginDataModel
import com.savana.apitestomranzare.Model.loginUserInput
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    var loggedIn: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(requireActivity(), R.id.mainFrame)
        val registerButton = view.findViewById<Button>(R.id.btn_register_login)
        val loginButton = view.findViewById<Button>(R.id.btn_login_login)

        registerButton.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }

        loginButton.setOnClickListener {
            loginUser()

            /*if (loggedIn == true) {
                navController.navigate(R.id.homeFragment)
            }*/
        }
    }

    fun loginUser() {

        // Getting the user values
        val mobile: String =
            view?.findViewById<EditText>(R.id.et_mobile_login)?.getText().toString().trim()
        val password: String =
            view?.findViewById<EditText>(R.id.et_pass_login)?.getText().toString().trim()

        // Building retrofit object
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://app.omranaz.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Defining retrofit api service
        val loginService = retrofit.create(labApi::class.java)

        // Defining the user object as we need to pass it with the call
        val user = loginUserInput(mobile, password)

        // Defining the call
        val call: Call<loginDataModel> = loginService.loginUser(
            user.mobile,
            user.password
        )
        // Calling the api
        call.enqueue(object : Callback<loginDataModel?> {

            override fun onResponse(
                call: Call<loginDataModel?>?,
                response: Response<loginDataModel?>
            ) {

                Log.d("TAG_1_LOGIN", "${response.body()?.message}")
                Log.d("TAG_2_LOGIN", "${response.body()}")
                Toast.makeText(
                    requireActivity(),
                    "${response.body()?.message}",
                    Toast.LENGTH_SHORT
                )
                    .show()

                val navController = Navigation.findNavController(requireActivity(), R.id.mainFrame)
                if (response.body()!!.success) {
                    navController.navigate(R.id.homeFragment)
                }

            }

            override fun onFailure(call: Call<loginDataModel?>?, t: Throwable) {

                Log.d("TAG_F_LOGIN", "${t.message}")
                Toast.makeText(requireActivity(), "${t.message}", Toast.LENGTH_SHORT)
                    .show()

                loggedIn = false
            }
        })
    }
}