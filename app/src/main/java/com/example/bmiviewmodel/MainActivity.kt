package com.example.bmiviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    private val bmiViewModel: BmiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIApp(bmiViewModel)
        }
    }
}

@Composable
fun BMIApp(bmiViewModel: BmiViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Body Mass Index") })
        }
    ) { paddingValues ->
        BmiCalculatorScreen(
            bmiViewModel = bmiViewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun BmiCalculatorScreen(bmiViewModel: BmiViewModel = viewModel(), modifier: Modifier = Modifier) {
    // Managing the input for height and weight using remember state
    val heightState = remember { mutableStateOf("") }
    val weightState = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Body mass index", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Input for height
        OutlinedTextField(
            value = heightState.value,
            onValueChange = { newHeight ->
                heightState.value = newHeight
                bmiViewModel.updateHeight(newHeight)
            },
            label = { Text("Height (m)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Input for weight
        OutlinedTextField(
            value = weightState.value,
            onValueChange = { newWeight ->
                weightState.value = newWeight
                bmiViewModel.updateWeight(newWeight)
            },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Display the BMI result
        Text(
            text = "Your BMI is: ${bmiViewModel.bmiResult.value ?: ""}",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMIApp(BmiViewModel())
}