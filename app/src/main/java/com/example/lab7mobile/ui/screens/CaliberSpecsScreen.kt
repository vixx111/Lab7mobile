package com.example.lab7mobile.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab7mobile.R

@Composable
fun CaliberSpecsScreen(
    onNavigateBack: () -> Unit = {},
    onNavigateNext: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ULYSSE  NARDIN",
                    color = Color(0xFF13222F),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    fontFamily = FontFamily.SansSerif
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    tint = Color(0xFF13222F),
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onMenuClick() }
                )
            }

            // Top Carousel (Caliber Movement Photo with Left/Right Arrows)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Previous",
                    tint = Color(0xFF8E9DAA),
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onNavigateBack() }
                )

                Image(
                    painter = painterResource(id = R.drawable.movement),
                    contentDescription = "Caliber UN-118 Movement",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(0.9f)
                        .clickable { onNavigateNext() }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Next",
                    tint = Color(0xFF8E9DAA),
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onNavigateNext() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Technical Details Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Text(
                    text = "Caliber UN–118",
                    color = Color(0xFF13222F),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )

                Text(
                    text = "Manufacture movement",
                    color = Color(0xFF13222F),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
                )

                // Divider line
                Box(
                    modifier = Modifier
                        .width(36.dp)
                        .height(2.dp)
                        .background(Color(0xFF8E9DAA))
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Specs Grid (2 columns)
                // Row 1
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpecItem(
                        title = "NUMBER\nOF PARTS",
                        value = "260",
                        modifier = Modifier.weight(1f)
                    )
                    SpecItem(
                        title = "FREQUENCY\n(HZ)",
                        value = "4",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Row 2
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpecItem(
                        title = "POWER\nRESERVE (H)",
                        value = "60",
                        modifier = Modifier.weight(1f)
                    )
                    SpecItem(
                        title = "FREQUENCY\n(VPH)",
                        value = "28 800",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Row 3
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SpecItem(
                        title = "FUNCTIONS",
                        value = "H/M/S Date",
                        modifier = Modifier.weight(1f)
                    )
                    SpecItem(
                        title = "BALANCE TYPE",
                        value = "DCS",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Features Section Header
                Text(
                    text = "Features",
                    color = Color(0xFF13222F),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Feature Thumbnails Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Feature 1: Side Profile No 2969
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFE4E8EE))
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.case_side),
                            contentDescription = "Case Side Profile",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    // Feature 2: Clasp
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFE4E8EE))
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.clasp),
                            contentDescription = "Clasp",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SpecItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = Color(0xFF8E9DAA),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            lineHeight = 14.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = value,
            color = Color(0xFF13222F),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
