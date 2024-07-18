package com.example.assignment.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.assignment.R
import com.example.assignment.helpers.RetrofitAPI
import com.example.assignment.httpmodels.ProductModel
import com.example.assignment.httpmodels.ProductResponseModel
import com.example.assignment.ui.theme.AssignmentTheme
import com.example.assignment.ui.theme.TextColor12
import com.example.assignment.ui.theme.TextColor4
import com.example.assignment.ui.theme.TextColor6

data class TabBarItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
)

class Home {

    @Composable
    fun RenderItem(product: ProductModel, goToScreen: (String) -> Unit) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images[0])
                .size(Size.ORIGINAL)
                .build()
        )

        Column(
            modifier = Modifier.clickable {
                //truyền id sản phẩm sang detail
                goToScreen("detail/${product._id}")
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .paint(
                        //ảnh product
                        painter = painter,
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_bag),
                    contentDescription = "",
                    modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.BottomEnd)
                        .padding(20.dp)
                )
            }

            Text(
                modifier = Modifier.padding(top = 10.dp),
                //tên product
                text = product.name,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.galesio_semi_bold)),
                color = TextColor12
            )

            Text(
                //giá product
                text = "$ ${product.price}",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.galesio_semi_bold)),
                color = TextColor4
            )
        }
    }

    @Composable
    fun ProductGrid(goToScreen: (String) -> Unit) {
        val context = LocalContext.current
        var productList by remember {
            mutableStateOf(listOf<ProductModel>())
        }

        fun productsCallback(response: ProductResponseModel?) {
            if (response != null) {
                Log.d("TAG", "productsCallback: -------------------------")
                productList = response.data

            }
        }

        fun getProducts() {
            try {
                val api = RetrofitAPI()
                val category = "65f2031afc13ae2f66316ebb"
                api.getProductsByCategoryId(category, ::productsCallback)
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to get product", Toast.LENGTH_LONG).show()
            }
        }
        getProducts()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp),
//            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp), //space between two items vertically
            horizontalArrangement = Arrangement.spacedBy(20.dp)//space between two items horizontally
        ) {
            items(productList) { product ->
                RenderItem(product = product, goToScreen = goToScreen)
            }
        }
    }

    @Composable
    fun Container(goToScreen: (String) -> Unit) {
//        Body(goToScreen = goToScreen)
        MainTabs(goToScreen)
    }

    @Composable
    fun Body(goToScreen: (String) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            //Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_logout),
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { goToScreen("logout") },
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Make home",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.gelasio)),
                        color = TextColor6
                    )
                    Text(
                        text = "BEAUTIFUL",
                        fontSize = 25.sp,
                        fontFamily = FontFamily(Font(R.font.galesio_semi_bold)),
                        color = TextColor4
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.icon_cart),
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { goToScreen("cart") }
                )
            }

            Column(
                modifier = Modifier.padding(top = 15.dp)
            )
            {
                ProductGrid(goToScreen)
            }

        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MainTabs(
        goToScreen: (String) -> Unit,
//        saveUserInfo: (UserInfo) -> Unit
    ) {
        // setting up the individual tabs
        val homeTab = TabBarItem(
            title = "Home",
            selectedIcon = R.drawable.home_selected,
            unselectedIcon = R.drawable.home
        )
        val alertsTab = TabBarItem(
            title = "Alerts",
            selectedIcon = R.drawable.bell_selected,
            unselectedIcon = R.drawable.bell
        )
        val settingsTab = TabBarItem(
            title = "Settings",
            selectedIcon = R.drawable.marker_selected,
            unselectedIcon = R.drawable.marker
        )
        val moreTab = TabBarItem(
            title = "More",
            selectedIcon = R.drawable.person_selected,
            unselectedIcon = R.drawable.person
        )
        // creating a list of all the tabs
        val tabBarItems = listOf(homeTab, alertsTab, settingsTab, moreTab)
        // creating our navController
        val navController = rememberNavController()

        //khai báo các màn hình

//        val homeScreen = Home()
//        val cartScreen = Cart()
        Scaffold(bottomBar = { TabView(tabBarItems, navController) }) {
            NavHost(navController = navController, startDestination = homeTab.title) {
                composable(homeTab.title) {
                    Body(goToScreen)
                }
                composable(alertsTab.title) {
                    Text(alertsTab.title)
                }
                composable(settingsTab.title) {
                    Text(settingsTab.title)
                }
                composable(moreTab.title) {
//                    logoutScreen.Container(saveUserInfo)
                }
            }
        }
    }

    @Composable
    fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
        var selectedTabIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        NavigationBar {
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.title)
                    },
                    icon = {
                        TabBarIconView(
                            isSelected = selectedTabIndex == index,
                            selectedIcon = tabBarItem.selectedIcon,
                            unselectedIcon = tabBarItem.unselectedIcon,
                            title = tabBarItem.title,
                        )
                    },
                    label = { Text(tabBarItem.title) })
            }
        }
    }


    @Composable
    fun TabBarIconView(
        isSelected: Boolean,
        selectedIcon: Int,
        unselectedIcon: Int,
        title: String,
    ) {
        Image(
            painter = painterResource(id = if (isSelected) selectedIcon else unselectedIcon),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            contentDescription = title
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreView() {
        AssignmentTheme {
            Container {

            }
        }
    }
}