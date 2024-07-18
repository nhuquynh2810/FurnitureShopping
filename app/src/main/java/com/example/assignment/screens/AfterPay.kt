package com.example.assignment.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.assignment.R
import com.example.assignment.ui.theme.TextColor12
import com.example.assignment.ui.theme.TextColor4
import com.example.assignment.ui.theme.TextColor5

class AfterPay {
    @Composable
    fun Container(goToScreen: (String) -> Unit) {
        Body(goToScreen = goToScreen)
    }

    @Composable
    fun Body(goToScreen: (String) -> Unit){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "SUCCESS",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
            Spacer(modifier = Modifier.height(54.dp))
            Image(
                painter = painterResource(id = R.drawable.background_afterpay),
                contentDescription = "background afterpay",
                modifier = Modifier.size(270.dp, 255.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Your order will be delivered soon.\n" +
                        "Thank you for choosing our app!",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = TextColor12,
                modifier = Modifier.width(283.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        TextColor4,
                        shape = MaterialTheme.shapes.small
                    ),
                onClick = {
                }) {
                Text(
                    text = "Track your orders",
                    color = TextColor5,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunitosans)),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp)
                    .background(
                        Color.White,
                        shape = MaterialTheme.shapes.small
                    ),
                border = BorderStroke(2.dp, color = Color.Black),
                shape = MaterialTheme.shapes.small,
                onClick = {
                    goToScreen("home")
                }) {
                Text(
                    text = "BACK TO HOME",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.nunitosans)),
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
}