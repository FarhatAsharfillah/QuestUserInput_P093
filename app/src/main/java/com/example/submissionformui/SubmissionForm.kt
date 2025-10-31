package com.example.submissionformui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun SubmissionForm(modifier: Modifier = Modifier) {
    var nama by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var statusPerkawinan by remember { mutableStateOf("") }

    var submittedNama by remember { mutableStateOf("") }
    var submittedAlamat by remember { mutableStateOf("") }
    var submittedGender by remember { mutableStateOf("") }
    var submittedStatus by remember { mutableStateOf("") }

    val genderList = listOf(
        stringResource(R.string.gender_laki),
        stringResource(R.string.gender_perempuan)
    )

    val statusList = listOf(
        stringResource(R.string.status_janda),
        stringResource(R.string.status_lajang),
        stringResource(R.string.status_duda)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.form_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }