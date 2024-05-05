package com.sendiko.ola.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.ola.database.BmiData

@Composable
fun BmiCard(
    modifier: Modifier = Modifier,
    bmiData: BmiData
) {
    OutlinedCard(modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            AssistChip(onClick = {  }, label = { Text(text = bmiData.tanggal) })
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Text(text = "Tinggi Badan: ${bmiData.tinggiBadan}", fontSize = 16.sp)
                    Text(text = "Berat Badan: ${bmiData.beratBadan}", fontSize = 16.sp)
                    Text(text = bmiData.gender, fontSize = 16.sp)
                }
                Text(
                    text = bmiData.nilaiBmi.toString(),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun BmiCardPrev() {
    Surface {
        BmiCard(bmiData = BmiData(
            tanggal = "12/12/12",
            beratBadan = 123,
            tinggiBadan = 123,
            nilaiBmi = 123,
            gender = "Laki - laki"
        )
        )
    }
}