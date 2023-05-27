package com.magdenbt.collectionsbenchmark.ui.viewflow

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.magdenbt.collectionsbenchmark.CollectionsType
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.modelflow.StatModel
import com.magdenbt.collectionsbenchmark.modelflow.StatRepository
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import com.magdenbt.collectionsbenchmark.ui.theme.black
import com.magdenbt.collectionsbenchmark.ui.theme.shapes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScreenBody(
    startAction: (Int) -> Unit,
    statModels: SnapshotStateList<StatModel>?,
    columnAmount: Int,
) {
    var elementAmount by remember {
        mutableStateOf("")
    }

    Column(Modifier.padding(start = 26.dp, end = 12.dp)) {
        Row(Modifier.height(62.dp)) {
            ElementsAmountsInput(
                Modifier
                    .fillMaxHeight()
                    .width(210.dp),
                elementAmount,
            ) {
                elementAmount = it
            }

            StartButton(
                Modifier
                    .fillMaxHeight()
                    .width(127.dp),
            ) {
                elementAmount.toIntOrNull()?.let { startAction(it) }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        ResultsGrid(
            columnAmount = columnAmount,
            statModels = statModels,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ElementsAmountsInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(stringResource(R.string.input_elements_amount_hint)) },
        textStyle = LocalTextStyle.current.copy(
            fontSize = 17.sp,
            lineHeight = 26.sp,
            fontWeight = FontWeight.W500,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@Composable
private fun StartButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = modifier, shape = shapes.extraSmall) {
        Text(stringResource(R.string.button_start_name))
    }
}

@Composable
fun ResultsGrid(
    modifier: Modifier = Modifier,
    columnAmount: Int,
    statModels: SnapshotStateList<StatModel>?,
) {
//    val tempState = statModels.observeAsState()

    LazyVerticalGrid(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(11.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp),
        columns = GridCells.Fixed(columnAmount),
    ) {
        statModels?.apply {
            items(this) {
                Card(colors = cardColors(contentColor = black), shape = RoundedCornerShape(16.dp)) {
                    Text(
                        text = it.status.value,
                        maxLines = 3,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .height(105.dp)
                            .width(105.dp)
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(4.dp),

                        style = LocalTextStyle.current.copy(
                            fontSize = 10.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight.W400,
                        ),
                    )
                }
                if (it.busy.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(20.dp),
                        strokeWidth = 16.dp,
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 375, heightDp = 557, showBackground = true)
@Composable
private fun ViewPagerFragmentComposeScreenPrev() {
    val statModels =
        StatRepository(LocalContext.current).getModels(CollectionsType.LIST).toMutableStateList()
    AppTheme {
        ScreenBody({ elementsAmount ->
            testOnStart(
                statModels,
                elementsAmount,
            )
        }, statModels, 3)
    }
}

private fun testOnStart(statModels: SnapshotStateList<StatModel>?, elementsAmount: Int) {
    statModels?.forEach {
        it.busy.value = true
    }

    CoroutineScope(Dispatchers.IO).launch {
        statModels?.forEach {
            delay(1000)
            it.duration = "$elementsAmount"
            delay(300)
            it.busy.value = false
        }
    }
}
