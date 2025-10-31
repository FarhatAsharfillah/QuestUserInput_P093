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
import androidx.compose.ui.semantics.Role // Import yang diperlukan untuk aksesibilitas

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

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(stringResource(R.string.label_nama)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Perbaikan untuk Jenis Kelamin ---
        Text(
            text = stringResource(R.string.label_jenis_kelamin),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start) // Pastikan Teks ini rata kiri
        )
        // Gunakan Modifier.selectableGroup() untuk aksesibilitas
        Column(Modifier.selectableGroup()) {
            genderList.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        // Mengatur padding awal 16.dp pada Row agar Radio Button sejajar dengan konten TextField di atasnya.
                        // Default padding RadioButton di Jetpack Compose sedikit lebih besar, jadi 16.dp terlihat lebih rapi.
                        .padding(start = 16.dp)
                        .selectable(
                            selected = (gender == item),
                            onClick = { gender = item },
                            role = Role.RadioButton // Tambahkan Role untuk aksesibilitas
                        )
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Setel onClick ke null. Klik ditangani oleh Row melalui selectable() untuk area klik yang lebih luas.
                    RadioButton(
                        selected = (gender == item),
                        onClick = null // <--- PENTING: Diubah menjadi null
                    )
                    Text(text = item, modifier = Modifier.padding(start = 4.dp))
                }
            }
        }
        // --- Akhir Perbaikan Jenis Kelamin ---

        Spacer(modifier = Modifier.height(16.dp))

        // --- Perbaikan untuk Status Perkawinan ---
        Text(
            text = stringResource(R.string.label_status_perkawinan),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start) // Pastikan Teks ini rata kiri
        )
        // Gunakan Modifier.selectableGroup() untuk aksesibilitas
        Column(Modifier.selectableGroup()) {
            statusList.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        // Mengatur padding awal 16.dp pada Row agar Radio Button sejajar dengan konten TextField.
                        .padding(start = 16.dp)
                        .selectable(
                            selected = (statusPerkawinan == item),
                            onClick = { statusPerkawinan = item },
                            role = Role.RadioButton // Tambahkan Role untuk aksesibilitas
                        )
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Setel onClick ke null. Klik ditangani oleh Row melalui selectable() untuk area klik yang lebih luas.
                    RadioButton(
                        selected = (statusPerkawinan == item),
                        onClick = null // <--- PENTING: Diubah menjadi null
                    )
                    Text(text = item, modifier = Modifier.padding(start = 4.dp))
                }
            }
        }
        // --- Akhir Perbaikan Status Perkawinan ---

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = alamat,
            onValueChange = { alamat = it },
            label = { Text(stringResource(R.string.label_alamat)) },
            // Ubah label menjadi 'Alamat Lengkap' agar sesuai dengan Gambar 2
            // Perhatikan: Jika string resource Anda adalah 'label_alamat', pastikan isinya adalah "Alamat Lengkap"
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                submittedNama = nama
                submittedGender = gender
                submittedAlamat = alamat
                submittedStatus = statusPerkawinan
            },
            enabled = nama.isNotEmpty() && alamat.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_submit))
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (submittedNama.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C2C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
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