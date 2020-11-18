package com.savana.apitestomranzare

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.savana.apitestomranzare.Model.LoginDataModel
import com.savana.apitestomranzare.Model.loginUserInput
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

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
        }
    }

    fun loginUser() {

        //getting the user values
        val mobile: String =
            view?.findViewById<EditText>(R.id.et_mobile_login)?.getText().toString().trim()
        val password: String =
            view?.findViewById<EditText>(R.id.et_pass_login)?.getText().toString().trim()

        //building retrofit object
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://app.omranaz.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Defining retrofit api service
        val loginService = retrofit.create(loginApi::class.java)

        //Defining the user object as we need to pass it with the call
        val user = loginUserInput(mobile, password)

        //defining the call
        val call: Call<LoginDataModel> = loginService.loginUser(
            user.mobile,
            user.password,
        )

        //calling the api
        call.enqueue(object : Callback<LoginDataModel?> {

            override fun onResponse(
                call: Call<LoginDataModel?>?,
                response: Response<LoginDataModel?>
            ) {

                Log.d("TAG_1_LOGIN", "${response.body()?.message}")
                Log.d("TAG_2_LOGIN", "${response.body()}")

                Toast.makeText(
                    requireActivity(),
                    "${response.body()?.message}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onFailure(call: Call<LoginDataModel?>?, t: Throwable) {

                Log.d("TAG_F_LOGIN", "${t.message}")

                Toast.makeText(requireActivity(), "${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}