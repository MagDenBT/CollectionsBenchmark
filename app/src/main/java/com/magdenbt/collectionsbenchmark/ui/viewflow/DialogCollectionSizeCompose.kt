package com.magdenbt.collectionsbenchmark.ui.viewflow

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import com.magdenbt.collectionsbenchmark.ui.theme.shapes

@Composable
fun DialogCollectionSizeScreen() {
    Column(
        modifier = Modifier
            .width(375.dp)
            .padding(start = 30.dp, end = 30.dp, top = 35.dp),
    ) {
        InvitationText(
            Modifier
                .width(315.dp)
                .height(49.dp)
                .wrapContentHeight(Alignment.CenterVertically),
        )

        Spacer(Modifier.height(21.dp))
        CollectionSizeInput(
            Modifier
                .width(315.dp)
                .height(64.dp),
        )

        CalculateButton(
            Modifier
                .width(315.dp)
                .height(62.dp),
        )
    }
}

@Composable
fun InvitationText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.text_view_dialog_invitation),
        style = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionSizeInput(modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        value = stringResource(R.string.input_dialog_elements_amount_hint),
        onValueChange = { /*ToDo*/ },
    )
}

@Composable
fun CalculateButton(modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier,
        shape = shapes.extraSmall,
    ) {
        Text(text = stringResource(R.string.button_calculate_name))
    }
}

@Preview(widthDp = 375, showBackground = true)
@Composable
fun DialogCollectionSizeScreenPrev() {
    AppTheme {
        DialogCollectionSizeScreen()
    }
}
