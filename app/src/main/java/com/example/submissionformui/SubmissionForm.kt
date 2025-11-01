package com.example.submissionformui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Menggunakan warna ungu dari gambar (Material Purple 500)
val Purple500 = Color(0xFF673AB7)
val CardBackground = Color.White // Warna Card untuk Form

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
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- 1. HEADER UNGU ---
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Purple500
        ) {
            Text(
                text = stringResource(R.string.form_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            )
        }
        // --- END HEADER ---

        // --- WRAPPER CARD PUTIH ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                // Hapus offset(y = (-16).dp) agar Card tidak overlap dengan Header
                .padding(horizontal = 24.dp, vertical = 16.dp), // Tambahkan padding vertikal agar berjarak dari header dan bottom
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Tambahkan shadow
        ) {
            // --- CONTENT FORM DI DALAM CARD ---
            Column(
                // Tambahkan padding di dalam Card (padding = 24.dp di semua sisi)
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {

                // NAMA LENGKAP
                Text(
                    text = stringResource(R.string.label_nama).uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text(text = "Isian nama lengkap", style = MaterialTheme.typography.bodyMedium) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // JENIS KELAMIN
                Text(
                    text = stringResource(R.string.label_jenis_kelamin).uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Column(Modifier.selectableGroup()) {
                    genderList.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (gender == item),
                                    onClick = { gender = item },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (gender == item),
                                onClick = null
                            )
                            Text(text = item, modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // STATUS PERKAWINAN
                Text(
                    text = stringResource(R.string.label_status_perkawinan).uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Column(Modifier.selectableGroup()) {
                    statusList.forEach { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = (statusPerkawinan == item),
                                    onClick = { statusPerkawinan = item },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (statusPerkawinan == item),
                                onClick = null
                            )
                            Text(text = item, modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ALAMAT
                Text(
                    text = stringResource(R.string.label_alamat).uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                OutlinedTextField(
                    value = alamat,
                    onValueChange = { alamat = it },
                    label = { Text(text = "Alamat", style = MaterialTheme.typography.bodyMedium) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // TOMBOL SUBMIT (Warna Ungu)
                Button(
                    onClick = {
                        submittedNama = nama
                        submittedGender = gender
                        submittedAlamat = alamat
                        submittedStatus = statusPerkawinan
                    },
                    enabled = nama.isNotEmpty() && alamat.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Purple500)
                ) {
                    Text(stringResource(R.string.btn_submit))
                }
            }
            // --- END CONTENT FORM DI DALAM CARD ---
        }
        // --- END WRAPPER CARD PUTIH ---

        // Memindahkan tampilan output ke luar Card
        Spacer(modifier = Modifier.height(8.dp))

        if (submittedNama.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C2C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("${stringResource(R.string.output_nama)} $submittedNama", color = Color.White)
                    Text("${stringResource(R.string.output_gender)} $submittedGender", color = Color.White)
                    Text("${stringResource(R.string.output_status)} $submittedStatus", color = Color.White)
                    Text("${stringResource(R.string.output_alamat)} $submittedAlamat", color = Color.White)
                }
            }
        }
    }
}