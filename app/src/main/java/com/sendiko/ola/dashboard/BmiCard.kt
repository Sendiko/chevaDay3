package com.sendiko.ola.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sendiko.ola.database.BmiData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCard(
    modifier: Modifier = Modifier,
    bmiData: BmiData,
    onDeleteData: (BmiData) -> Unit,
    onUpdateData: (BmiData) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onUpdateData(bmiData) }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                AssistChip(onClick = {  }, label = { Text(text = bmiData.tanggal) })
                TextButton(
                    onClick = { onDeleteData(bmiData) },
                    content = {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "hapus")
                        Text(text = "Hapus Data")
                    }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Text(
                        text = "${bmiData.tinggiBadan} CM",
                        fontSize = 18.sp,
                        fontWeight = SemiBold
                    )
                    Text(
                        text = "${bmiData.beratBadan} KG",
                        fontSize = 18.sp,
                        fontWeight = SemiBold
                    )
                    Text(
                        text = bmiData.gender,
                        fontSize = 18.sp,
                        fontWeight = SemiBold
                    )
                }
                val formattedString = String.format("%.2f", bmiData.nilaiBmi)
                Text(
                    text = formattedString,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun BmiCardPrev() {
//    Surface {
//        BmiCard(bmiData = BmiData(
//            tanggal = "12/12/12",
//            beratBadan = 123,
//            tinggiBadan = 123,
//            nilaiBmi = 123,
//            gender = "Laki - laki"
//        )
//        )
//    }
//}