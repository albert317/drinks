package com.albert.infinitespirit.login

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(goToDrinks: () -> Unit) {
    val viewModel: SignInViewModel = hiltViewModel()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.getGoogleAuthCredential(result.data) {
                    goToDrinks()
                }
            } else {
                // Handle error
            }
        }
    )

    LoginContent({
        launcher.launch(viewModel.signInIntent())
    }, {
        viewModel.signOut()
    }) {
        viewModel.removeAccess()
    }
}


@Composable
fun LoginContent(
    onSignInClick: () -> Unit = {},
    logOut: () -> Unit = {},
    removeAccess: () -> Unit = {}
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.back_ground),
            contentDescription = "my image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Text(
                text = "Infinity Spirit",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(50.dp))
            Icon(
                ImageVector.vectorResource(id = R.drawable.cocktail_svgrepo_com),
                contentDescription = null,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button( onClick = {
                onSignInClick()
            }, colors = ButtonDefaults.buttonColors(Color.White)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_google),
                        contentDescription = null,
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Iniciar sesión con Google", color = Color.Black)
                }
            }
        }
        /*Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Login Screen")
            Button(onClick = {
                onSignInClick()
            }) {
                Text(text = "Iniciar sesión con Google")
            }
            Button(onClick = {
                logOut()
            }) {
                Text(text = "LogOut")
            }
            Button(onClick = {
                removeAccess()
            }) {
                Text(text = "Remove Access")
            }
        }*/
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun LoginContentPreview() {
    LoginContent()
}