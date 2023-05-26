@file:OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)

package com.magdenbt.collectionsbenchmark.ui.viewflow

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.magdenbt.collectionsbenchmark.CollectionsType
import com.magdenbt.collectionsbenchmark.R
import com.magdenbt.collectionsbenchmark.ui.theme.AppTheme
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModel
import com.magdenbt.collectionsbenchmark.ui.viewmodelflow.StatViewModelFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagerScreen(viewModel: StatViewModel, collectionSize: Int) {
    val composeScreens = mutableListOf<ComposeScreen>().apply {
        viewModel.statModelsLD.forEach(
            action = {
                val collectionsType = it.key
                val statModels = it.value

                val title =
                    stringResource(if (collectionsType == CollectionsType.LIST) R.string.tab_collections else R.string.tab_maps)
                val columnAmount = if (collectionsType == CollectionsType.LIST) 3 else 2

                add(
                    ComposeScreen(title) {
                        ViewPagerFragmentComposeScreen({ elementsAmount ->
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

    val pagerState = rememberPagerState()
    Scaffold(topBar = { TopBar() }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Tabs(composeScreens, pagerState)
            TabContent(composeScreens, pagerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(modifier = modifier, title = { Text(stringResource(R.string.app_bar_title)) })
}

@Composable
fun Tabs(composeScreens: List<ComposeScreen>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage) {
        composeScreens.forEachIndexed { index: Int, composeScreen: ComposeScreen ->
            Tab(
                selected = index == pagerState.currentPage,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            ) {
                Text(text = composeScreen.title)
            }
        }
    }
}

@Composable
fun TabContent(composeScreens: List<ComposeScreen>, pagerState: PagerState) {
    HorizontalPager(count = composeScreens.size, state = pagerState) { page ->
        composeScreens[page].screen()
    }
}

@Preview(heightDp = 796, widthDp = 375, showBackground = true)
@Composable
fun TabScreenPreview() {
    val viewModel: StatViewModel = viewModel(factory = StatViewModelFactory(LocalContext.current))

    AppTheme {
        PagerScreen(viewModel, 100)
    }
}

typealias ComposeFun = @Composable () -> Unit

data class ComposeScreen(val title: String, val screen: ComposeFun)
