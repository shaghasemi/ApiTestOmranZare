package com.savana.apitestomranzare

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.savana.apitestomranzare.Model.registerDataModel
import com.savana.apitestomranzare.Model.registerUserInput
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.mainFrame)

        val registerButton = view.findViewById<Button>(R.id.btn_register_register)
        val loginButton = view.findViewById<Button>(R.id.btn_login_register)

        registerButton.setOnClickListener {
            registerOneUser()
        }

        loginButton.setOnClickListener {
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun registerOneUser() {

        //getting the user values
        val mobile: String =
            view?.findViewById<EditText>(R.id.et_mobile_register)?.getText().toString().trim()
        val password: String =
            view?.findViewById<EditText>(R.id.et_pass_register)?.getText().toString().trim()
        val password_confirmation: String =
            view?.findViewById<EditText>(R.id.et_pass_confirm_register)?.getText().toString().trim()


        //building retrofit object
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://app.omranaz.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Defining retrofit api service
        val service = retrofit.create(registerApi::class.java)

        //Defining the user object as we need to pass it with the call
        val user = registerUserInput(mobile, password, password_confirmation)

        //defining the call
        val call: Call<registerDataModel> = service.createUser(
            user.mobile,
            user.password,
            user.password_confirmation
        )

        //calling the api
        call.enqueue(object : Callback<registerDataModel?> {
            override fun onResponse(
                call: Call<registerDataModel?>?,
                response: Response<registerDataModel?>
            ) {

                Log.d("TAG_1", "${response.body()?.message}")
                Log.d("TAG_2", "${response.body()}")

                /*Toast.makeText(
//                    parentFragment.requireContext(),
                    requireActivity(),
                    "${response.body()?.message}",
                    Toast.LENGTH_SHORT
                )
                    .show()*/
            }

            override fun onFailure(call: Call<registerDataModel?>?, t: Throwable) {

                Log.d("TAG_F", "${t.message}")

                /*Toast.makeText(requireActivity(), "${t.message}", Toast.LENGTH_SHORT)
                    .show()*/
            }
        })
    }
}