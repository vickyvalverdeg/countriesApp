package com.androidsquad.countriesapp.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.androidsquad.countriesapp.R

@Composable
fun BottomBarContainer() {
    val selectedIndex = remember { mutableStateOf(0) }
    val context = LocalContext.current
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.background_bottom_bar),
        elevation = 0.dp
    ) {
        BottomNavigationItem(icon = {
            Image(
                painter = painterResource(id = R.drawable.dot_icon),
                contentDescription = "Label",
                modifier = Modifier
                    .size(20.dp)
            )
        },
            label = { Text(text = "Label") },
            selected = (selectedIndex.value == 0),
            onClick = {
                Toast.makeText(context, "1 clicked!", Toast.LENGTH_SHORT).show()
                selectedIndex.value = 0

            })

        BottomNavigationItem(icon = {
            Image(
                painter = painterResource(id = R.drawable.triangle_icon),
                contentDescription = "Label",
                modifier = Modifier
                    .size(20.dp)
            )
        },
            label = { Text(text = "Label") },
            selected = (selectedIndex.value == 1),
            onClick = {
                Toast.makeText(context, "2 clicked!", Toast.LENGTH_SHORT).show()
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Image(
                painter = painterResource(id = R.drawable.triangle_icon),
                contentDescription = "Label",
                modifier = Modifier
                    .size(20.dp)
            )
        },
            label = { Text(text = "Label") },
            selected = (selectedIndex.value == 2),
            onClick = {
                Toast.makeText(context, "3 clicked!", Toast.LENGTH_SHORT).show()
                selectedIndex.value = 2
            })
    }
}