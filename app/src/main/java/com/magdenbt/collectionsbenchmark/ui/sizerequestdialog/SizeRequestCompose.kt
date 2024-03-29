package com.magdenbt.collectionsbenchmark.ui.sizerequestdialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import com.magdenbt.collectionsbenchmark.ui.theme.shapes

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SizeRequestScreen(calculateAction: (Int) -> Unit) {
    var inputSize by remember {
        mutableStateOf("")
    }
    ConstraintLayout(
        Modifier
            .fillMaxHeight()
            .padding(horizontal = 30.dp),
    ) {
        val (column, button) = createRefs()
        val softwareKeyboard = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .width(375.dp)
                .constrainAs(column) { top.linkTo(parent.top, margin = 35.dp) },
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
                value = inputSize,
                onValueChange = { newValue -> inputSize = newValue },

            )
        }
        CalculateButton(
            Modifier
                .width(315.dp)
                .height(62.dp)
                .constrainAs(button) { bottom.linkTo(parent.bottom, margin = 39.dp) },
            onClick = {
                inputSize.toIntOrNull()?.let {
                    softwareKeyboard?.hide()
                    calculateAction(it)
                }
            },
        )
    }
}

@Composable
private fun InvitationText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.text_view_dialog_invitation),
        style = LocalTextStyle.current.copy(fontWeight = FontWeight.Normal),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun CollectionSizeInput(
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
        label = { Text(stringResource(R.string.input_dialog_elements_amount_hint)) },
        textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, lineHeight = 30.sp),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(

            onDone = { keyboardController?.hide() },
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@Composable
private fun CalculateButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shapes.extraSmall,
    ) {
        Text(text = stringResource(R.string.button_calculate_name))
    }
}

@Preview(widthDp = 375, heightDp = 796, showBackground = true)
@Composable
fun DialogCollectionSizeScreenPrev() {
    AppTheme {
        SizeRequestScreen({})
    }
}
