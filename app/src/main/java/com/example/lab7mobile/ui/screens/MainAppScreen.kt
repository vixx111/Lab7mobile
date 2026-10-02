package com.example.lab7mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun MainAppScreen() {
    val pagerState = rememberPagerState(initialPage = 0) { 3 }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF13222F)),
    ) {
        // Horizontal Pager with 3 screens (Screen 21, Screen 20, Screen 22)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            when (page) {
                0 -> WatchShowcaseScreen(
                    onNavigateToDetails = {
                        coroutineScope.launch { pagerState.animateScrollToPage(1) }
                    },
                    onNavigateToStory = {
                        coroutineScope.launch { pagerState.animateScrollToPage(2) }
                    },
                    onMenuClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(1) }
                    },
                )
                1 -> CaliberSpecsScreen(
                    onNavigateBack = {
                        coroutineScope.launch { pagerState.animateScrollToPage(0) }
                    },
                    onNavigateNext = {
                        coroutineScope.launch { pagerState.animateScrollToPage(2) }
                    },
                    onMenuClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(2) }
                    },
                )
                2 -> ExecutiveStoryScreen(
                    onNavigateBack = {
                        coroutineScope.launch { pagerState.animateScrollToPage(1) }
                    },
                    onSkipClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(0) }
                    },
                    onMeetTeamClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(0) }
                    },
                )
            }
        }

        // Navigation Tabs Overlay for easy switching between photos 21, 20, 22
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0x99000000))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val tabs = listOf("Screen 21 (Watch)", "Screen 20 (Caliber)", "Screen 22 (Story)")
            tabs.forEachIndexed { index, title ->
                val isSelected = pagerState.currentPage == index
                Text(
                    text = title,
                    color = if (isSelected) Color.White else Color(0xFF8E9DAA),
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch { pagerState.animateScrollToPage(index) }
                        }
                        .padding(horizontal = 4.dp, vertical = 2.dp),
                )
            }
        }
    }
}
