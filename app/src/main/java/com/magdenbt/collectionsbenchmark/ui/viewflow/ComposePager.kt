@file:OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)

package com.magdenbt.collectionsbenchmark.ui.viewflow

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.*
import com.magdenbt.collectionsbenchmark.CollectionsType
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import com.magdenbt.collectionsbenchmark.ui.theme.black
import com.magdenbt.collectionsbenchmark.ui.theme.white
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModel
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagerScreen(viewModel: StatViewModel, collectionSize: Int) {
    val composeScreens = getComposeScreens(viewModel, collectionSize)
    val pagerState = rememberPagerState()

    Scaffold(topBar = { TopBar() }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(composeScreens, pagerState, Modifier.height(41.dp))
            Spacer(modifier = Modifier.height(24.dp))
            TabContent(composeScreens, pagerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                stringResource(R.string.app_bar_title),
            )
        },
    )
}

@Composable
private fun Tabs(
    composeScreens: List<ComposeScreen>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val cornerRadius = 10.dp
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        CustomIndicator(tabPositions, pagerState, cornerRadius)
    }

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = indicator,
        modifier = Modifier.clip(
            RoundedCornerShape(
                bottomEnd = cornerRadius,
                bottomStart = cornerRadius,
            ),
        ),

    ) {
        composeScreens.forEachIndexed { index: Int, composeScreen: ComposeScreen ->
            Tab(
                modifier = Modifier.zIndex(2f),
                selected = index == pagerState.currentPage,
                selectedContentColor = white,
                unselectedContentColor = black,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            ) {
                Text(
                    text = composeScreen.title,
                    modifier = modifier.wrapContentHeight(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Composable
private fun CustomIndicator(
    tabPositions: List<TabPosition>,
    pagerState: PagerState,
    cornerRadius: Dp,
) {
    val transition = updateTransition(targetState = pagerState.currentPage)
    val indicatorStart by transition.animateDp(transitionSpec = {
        if (initialState < targetState) {
            spring(dampingRatio = 1f, stiffness = 50f)
        } else {
            spring(dampingRatio = 1f, stiffness = 1000f)
        }
    }, label = "") {
        tabPositions[it].left
    }

    val indicatorEnd by transition.animateDp(transitionSpec = {
        if (initialState < targetState) {
            spring(dampingRatio = 1f, stiffness = 1000f)
        } else {
            spring(dampingRatio = 1f, stiffness = 50f)
        }
    }, label = "") {
        tabPositions[it].right
    }

    val shape = if (pagerState.currentPage == 0) {
        RoundedCornerShape(bottomStart = cornerRadius)
    } else {
        RoundedCornerShape(
            bottomEnd = cornerRadius,
        )
    }

    Box(
        modifier = Modifier.offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart).width(indicatorEnd - indicatorStart)
            .fillMaxSize().background(color = black, shape).zIndex(1f),

    )
}

@Composable
private fun TabContent(composeScreens: List<ComposeScreen>, pagerState: PagerState) {
    HorizontalPager(count = composeScreens.size, state = pagerState) { page ->
        composeScreens[page].screen()
    }
}

@Preview(heightDp = 796, widthDp = 375, showBackground = true)
@Composable
private fun TabScreenPreview() {
    val viewModel: StatViewModel = viewModel(factory = StatViewModelFactory(LocalContext.current))

    AppTheme {
        PagerScreen(viewModel, 100)
    }
}

@Composable
private fun getComposeScreens(
    viewModel: StatViewModel,
    collectionSize: Int,
): List<ComposeScreen> {
    return mutableListOf<ComposeScreen>().apply {
        viewModel.statModelsLD.forEach(
            action = {
                val collectionsType = it.key
                val statModels = it.value

                val title =
                    stringResource(if (collectionsType == CollectionsType.LIST) R.string.tab_collections else R.string.tab_maps)
                val columnAmount = if (collectionsType == CollectionsType.LIST) 3 else 2

                add(
                    ComposeScreen(title) {
                        ScreenBody({ elementsAmount ->
                            collectionSize.let {
                                viewModel.startBenchmark(
                                    collectionsType,
                                    it,
                                    elementsAmount,
                                )
                            }
                        }, statModels.toMutableStateList(), columnAmount)
                    },
                )
            },
        )
    }
}

typealias ComposeFun = @Composable () -> Unit

data class ComposeScreen(val title: String, val screen: ComposeFun)
