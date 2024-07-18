package com.example.assignment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment.R
import com.example.assignment.ui.theme.AssignmentTheme
import com.example.assignment.ui.theme.TextColor1
import com.example.assignment.ui.theme.TextColor2
import com.example.assignment.ui.theme.TextColor3
import com.example.assignment.ui.theme.TextColor4
import com.example.assignment.ui.theme.TextColor5

class Welcome {
    @Composable
    fun Container(goToScreen : (String) -> Unit) {
       Body(goToScreen = goToScreen)
    }

    @Composable
    fun Body(goToScreen : (String) -> Unit){
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds
                )
        ) {
//            Main()
            Column {
                Text(
                    text = "Make your".uppercase(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.galesio_semi_bold)),
                    color = TextColor1,
                    modifier = Modifier.padding(start = 30.dp),

                    )
                Text(
                    text = "Home beautiful".uppercase(),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.galesio_semi_bold)),
                    color = TextColor2,
                    modifier = Modifier.padding(start = 30.dp, top = 15.dp),

                    )
                Text(
                    textAlign = TextAlign.Justify,
                    text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.galesio_semi_bold)),
                    color = TextColor3,
                    modifier = Modifier.padding(horizontal = 60.dp, vertical = 35.dp),

                    )
                Spacer(modifier = Modifier.height(150.dp))
                TextButton(
                    modifier = Modifier
                        .width(160.dp)
                        .height(55.dp)
                        .align(Alignment.CenterHorizontally)
//                .padding(vertical = 150.dp)
                        .background(
                            TextColor4,
                            shape = MaterialTheme.shapes.small
                        ),
                    onClick = {
                        goToScreen("login")
//                    context.startActivity(Intent(context, LoginActivity::class.java))
                    }) {
                    Text(
                        text = "Get Started",
                        color = TextColor5,
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreView() {
        AssignmentTheme {
//            Container()
        }
    }
}