package com.example.assignment.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.R
import com.example.assignment.helpers.RetrofitAPI
import com.example.assignment.httpmodels.RegisterRequestModel
import com.example.assignment.httpmodels.RegisterResponseModel
import com.example.assignment.ui.theme.TextColor2
import com.example.assignment.ui.theme.TextColor4
import com.example.assignment.ui.theme.TextColor5
import com.example.assignment.ui.theme.TextColor6

class SignUp {
    @Composable
    fun Container(goToScreen: (String) -> Unit) {
        Body(goToScreen = goToScreen)
    }

    @Composable
    fun Body(goToScreen: (String) -> Unit) {

        val context = LocalContext.current

        var name by remember { mutableStateOf("quynh") }
        var email by remember { mutableStateOf("quynh123@gmail.com") }
        var password by remember { mutableStateOf("Quynh@123") }

        fun registerCallback(response: RegisterResponseModel?) {
            Toast.makeText(context, "Register successfully", Toast.LENGTH_LONG).show()
            goToScreen("login")
        }

        fun onRegisterClick() {
            try {
                val body = RegisterRequestModel(name, email, password)
                //call api
                val api = RetrofitAPI()
                api.register(body, ::registerCallback)
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to register", Toast.LENGTH_LONG).show()

            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(25.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(105.dp)
                            .height(1.dp)
                            .border(1.dp, Color.Black)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .height(63.dp)
                            .width(63.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .width(105.dp)
                            .height(1.dp)
                            .border(1.dp, Color.Black)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "WELCOME",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF303030),
                        fontFamily = FontFamily(Font(R.font.merriweather_bold))
                    )
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 25.dp)
                    .shadow(5.dp, spotColor = Color.Gray)
                    .padding(
                        vertical = 70.dp,
                        horizontal = 5.dp
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                ) {


                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(text = "Name") },
                        placeholder = { Text(text = "Enter your name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        ),
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        placeholder = { Text(text = "Enter your email") },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        ),
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "Enter your password") },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        ),
                        trailingIcon = {
                            Image(
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp),
                                painter = painterResource(id = R.drawable.icon_eye),
                                contentDescription = "Eye icon"
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Confirm Password") },
                        placeholder = { Text(text = "Enter your password again") },
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true,
                        trailingIcon = {
                            Image(
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp),
                                painter = painterResource(id = R.drawable.icon_eye),
                                contentDescription = "Eye icon"
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Gray,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        ),

                        )
                }

                Spacer(modifier = Modifier.height(40.dp))

                TextButton(
                    modifier = Modifier
                        .width(285.dp)
                        .height(50.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(
                            TextColor4,
                            shape = MaterialTheme.shapes.small
                        ),
                    onClick = { onRegisterClick() })
                {
                    Text(
                        text = "SIGN UP",
                        color = TextColor5,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.nunitosans)),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "Already have account? ",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.merriweather_bold)),
                        color = TextColor6
                    )

                    Text(
                        text = "SIGN IN ",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.merriweather_bold)),
                        color = TextColor2,
                        modifier = Modifier.clickable {
//                            startActivity(Intent(context, LoginActivity::class.java))
                            goToScreen("login")

                        }
                    )
                }
            }
        }
    }
}