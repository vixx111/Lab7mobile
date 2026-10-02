package com.example.lab7mobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab7mobile.R

@Composable
fun ExecutiveStoryScreen(
    onNavigateBack: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    onMeetTeamClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0E14))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Upper Executive Portrait Photo
        Image(
            painter = painterResource(id = R.drawable.patrick_portrait),
            contentDescription = "Patrick Pruniaux Portrait",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .align(Alignment.TopCenter)
        )

        // Top Navigation Controls
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onNavigateBack() }
            )

            Text(
                text = "Manufacture\nof Freedom",
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif,
                lineHeight = 18.sp
            )

            Text(
                text = "SKIP",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                modifier = Modifier.clickable { onSkipClick() }
            )
        }

        // Bottom Overlapping Dark Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.48f)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(Color(0xFF1B2E3D))
                .padding(horizontal = 28.dp, vertical = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Anchor Icon & Details
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_anchor),
                        contentDescription = "Anchor Logo",
                        tint = Color.White,
                        modifier = Modifier
                            .size(28.dp)
                            .padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Patrick Pruniaux",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Text(
                        text = "In 2014, Patrick was hired by Apple, in Cupertino, to prepare the launch of the Apple Watch as a member of the Special Projects team.",
                        color = Color(0xFFA5B3C0),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                // Bottom Outlined Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .clip(RoundedCornerShape(27.dp))
                        .border(1.dp, Color(0xFF384E60), RoundedCornerShape(27.dp))
                        .clickable { onMeetTeamClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "MEET OUR TEAM OF ULYSSES",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                }
            }
        }
    }
}
