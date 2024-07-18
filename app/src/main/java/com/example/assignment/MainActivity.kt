package com.example.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment.models.UserInfo
import com.example.assignment.screens.AfterPay
import com.example.assignment.screens.Cart
import com.example.assignment.screens.Detail
import com.example.assignment.screens.Home
import com.example.assignment.screens.LogOut
import com.example.assignment.screens.Login
import com.example.assignment.screens.SignUp
import com.example.assignment.screens.Welcome
import com.example.assignment.ui.theme.AssignmentTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                Body()
            }
        }
    }

    @Composable
    fun Body() {

        val loginScreen = Login();
        val homeScreen = Home();
        val detailScreen = Detail();
        val signUpScreen = SignUp();
        val welcomeScreen = Welcome();
        val cartScreen = Cart();
        val afterPayScreen = AfterPay();
        val logoutScreen = LogOut();

        // khai báo navigation quản lí chuyển đổi các màn hình
        val navController = rememberNavController()

        fun goToScreen(screen: String) {
            navController.navigate(screen)
        }

        // đọc từ shared preferences
        fun readShared(): UserInfo {
            val sharedPrep = getSharedPreferences("user", MODE_PRIVATE)
            return UserInfo(
                null,
                email = sharedPrep.getString("email", null),
                null,
                null,
            )
        }

        // khai báo state chứa info
        var userInfo by remember { mutableStateOf(readShared()) }

        //lưu vào shared preferences
        fun saveShared(user: UserInfo) {
            val sharePrep = getSharedPreferences("user", MODE_PRIVATE)
            with(sharePrep.edit()) {
                putString("email", user.email)
                apply()
            }
            userInfo = user
        }

        //thông tin giỏ hàng
        var cartInfo by remember { mutableStateOf(listOf<com.example.assignment.models.Cart>()) }

        // cập nhật giỏ hàng
        val context = LocalContext.current
        fun updateCart(item: com.example.assignment.models.Cart) {
            // kiểm tra item có trong giỏ hàng chưa
            val index = cartInfo.indexOfFirst { it.product._id == item.product._id }
            if (index == -1) {
                // chưa có thì thêm vào
                cartInfo = cartInfo + item

                Toast.makeText(context, "Thêm sản phẩm", Toast.LENGTH_LONG).show()
            } else {
                // có rồi thì cập nhật số lượng
                // nếu số lượng giảm về 0 thì xóa item đó
                if (item.quantity + cartInfo[index].quantity == 0) {
                    cartInfo = cartInfo.filterIndexed { i, _ -> i != index }
                } else {
                    cartInfo = cartInfo.mapIndexed { i, cart ->
                        if (i == index) {
                            com.example.assignment.models.Cart(
                                product = cart.product,
                                quantity = cart.quantity + item.quantity
                            )
                        } else {
                            cart
                        }
                    }
                }
            }
        }

        fun clearCart(){
            cartInfo = listOf()
        }

        // stack navigation
        NavHost(
            navController = navController,
            startDestination = if (userInfo.email.isNullOrEmpty()) "welcome" else "home"
        ) {
            composable("login") {
                loginScreen.Container(
                    goToScreen = { goToScreen(it) },
                    saveUserInfo = { saveShared(it) })
            }
            composable("home") { homeScreen.Container(goToScreen = { goToScreen(it) }) }
            composable("cart") {
                cartScreen.Container(
                    goToScreen = { goToScreen(it) },
                    updateCart = { updateCart(it) },
                    cartInfo = cartInfo,
                    clearCart = { clearCart()}
                )
            }
//            composable("detail") { detailScreen.Container(goToScreen = { goToScreen(it) }) }

            composable(
                "detail/{value}",
                arguments = listOf(navArgument("value") { defaultValue = "" })
            ) { backStackEntry ->
                detailScreen.Container(
                    backStackEntry.arguments?.getString("value"),
                    goToScreen = { goToScreen(it) },
                    updateCart = { updateCart(it) }
                )
            }

            composable("signup") { signUpScreen.Container(goToScreen = { goToScreen(it) }) }
            composable("welcome") { welcomeScreen.Container(goToScreen = { goToScreen(it) }) }
            composable("afterpay") { afterPayScreen.Container(goToScreen = { goToScreen(it) }) }
            composable("logout") { logoutScreen.Container(saveUserInfo = { saveShared(it) }) }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreView() {
        AssignmentTheme {
            Body()
        }
    }
}



