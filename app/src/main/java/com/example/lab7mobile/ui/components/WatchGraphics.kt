package com.example.lab7mobile.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.cos
import kotlin.math.sin

/**
 * Custom Canvas drawing for Ulysse Nardin Marine Torpilleur 42mm Watch
 */
@Composable
fun WatchView(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val centerX = width / 2f
        val centerY = height / 2f
        val watchRadius = width.coerceAtMost(height) * 0.36f

        // 1. Mesh Strap (Top and Bottom)
        val strapWidth = watchRadius * 0.95f
        val strapColor = Color(0xFFBDC3C7)
        val strapDark = Color(0xFF7F8C8D)

        // Top Strap
        drawRect(
            color = strapColor,
            topLeft = Offset(centerX - strapWidth / 2f, 0f),
            size = Size(strapWidth, centerY - watchRadius * 0.85f)
        )
        // Top Strap Mesh pattern lines
        for (y in 0..(centerY - watchRadius * 0.85f).toInt() step 8) {
            drawLine(
                color = strapDark,
                start = Offset(centerX - strapWidth / 2f, y.toFloat()),
                end = Offset(centerX + strapWidth / 2f, y.toFloat() + 6f),
                strokeWidth = 1.5f
            )
            drawLine(
                color = strapDark,
                start = Offset(centerX + strapWidth / 2f, y.toFloat()),
                end = Offset(centerX - strapWidth / 2f, y.toFloat() + 6f),
                strokeWidth = 1.5f
            )
        }

        // Bottom Strap
        drawRect(
            color = strapColor,
            topLeft = Offset(centerX - strapWidth / 2f, centerY + watchRadius * 0.85f),
            size = Size(strapWidth, height - (centerY + watchRadius * 0.85f))
        )
        // Bottom Strap Mesh pattern lines
        for (y in (centerY + watchRadius * 0.85f).toInt()..height.toInt() step 8) {
            drawLine(
                color = strapDark,
                start = Offset(centerX - strapWidth / 2f, y.toFloat()),
                end = Offset(centerX + strapWidth / 2f, y.toFloat() + 6f),
                strokeWidth = 1.5f
            )
            drawLine(
                color = strapDark,
                start = Offset(centerX + strapWidth / 2f, y.toFloat()),
                end = Offset(centerX - strapWidth / 2f, y.toFloat() + 6f),
                strokeWidth = 1.5f
            )
        }

        // 2. Stainless Steel Outer Bezel
        val bezelRadius = watchRadius * 1.05f
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFE0E0E0), Color(0xFF9E9E9E), Color(0xFFEEEEEE), Color(0xFF757575)),
                center = Offset(centerX, centerY),
                radius = bezelRadius
            ),
            center = Offset(centerX, centerY),
            radius = bezelRadius
        )

        // Bezel fluted coin-edge teeth
        val numTeeth = 120
        for (i in 0 until numTeeth) {
            val angle = (i * 360f / numTeeth) * (Math.PI / 180.0)
            val x1 = centerX + (bezelRadius - 3) * cos(angle).toFloat()
            val y1 = centerY + (bezelRadius - 3) * sin(angle).toFloat()
            val x2 = centerX + bezelRadius * cos(angle).toFloat()
            val y2 = centerY + bezelRadius * sin(angle).toFloat()
            drawLine(
                color = Color(0xFF616161),
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = 2f
            )
        }

        // 3. Winding Crown at 3 o'clock
        drawRect(
            color = Color(0xFFBDBDBD),
            topLeft = Offset(centerX + bezelRadius - 2f, centerY - 14f),
            size = Size(14f, 28f)
        )
        drawCircle(
            color = Color(0xFF9E9E9E),
            center = Offset(centerX + bezelRadius + 12f, centerY),
            radius = 10f
        )

        // 4. Blue Dial Face
        val dialRadius = watchRadius * 0.94f
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFF2B4D68),
                    Color(0xFF1B364D),
                    Color(0xFF112333),
                    Color(0xFF0B1724)
                ),
                center = Offset(centerX, centerY),
                radius = dialRadius
            ),
            center = Offset(centerX, centerY),
            radius = dialRadius
        )

        // Inner Dial ring border
        drawCircle(
            color = Color(0xFF4A6B88),
            center = Offset(centerX, centerY),
            radius = dialRadius * 0.98f,
            style = Stroke(width = 1.5f)
        )

        // 5. Dial Tick Marks
        val numTicks = 12
        for (i in 0 until numTicks) {
            val angleDeg = (i * 30 - 90).toFloat()
            val angleRad = angleDeg * (Math.PI / 180.0)

            // Outer minute tick marks
            val tick1X = centerX + (dialRadius * 0.92f) * cos(angleRad).toFloat()
            val tick1Y = centerY + (dialRadius * 0.92f) * sin(angleRad).toFloat()
            val tick2X = centerX + (dialRadius * 0.85f) * cos(angleRad).toFloat()
            val tick2Y = centerY + (dialRadius * 0.85f) * sin(angleRad).toFloat()

            drawLine(
                color = Color(0xFFDDE6ED),
                start = Offset(tick1X, tick1Y),
                end = Offset(tick2X, tick2Y),
                strokeWidth = 2.5f
            )
        }

        // Sub-dial 12 o'clock (Power Reserve)
        val sub1Y = centerY - dialRadius * 0.42f
        val sub1Radius = dialRadius * 0.22f
        drawCircle(
            color = Color(0x33FFFFFF),
            center = Offset(centerX, sub1Y),
            radius = sub1Radius
        )
        drawCircle(
            color = Color(0xFF7A9BB8),
            center = Offset(centerX, sub1Y),
            radius = sub1Radius,
            style = Stroke(width = 1f)
        )
        // Red BAS / HAUT indicator arcs
        drawArc(
            color = Color(0xFFE53935),
            startAngle = 210f,
            sweepAngle = 30f,
            useCenter = false,
            topLeft = Offset(centerX - sub1Radius, sub1Y - sub1Radius),
            size = Size(sub1Radius * 2f, sub1Radius * 2f),
            style = Stroke(width = 2.5f)
        )
        // Power Reserve needle
        drawLine(
            color = Color.White,
            start = Offset(centerX, sub1Y),
            end = Offset(centerX + sub1Radius * 0.6f * cos(-0.5).toFloat(), sub1Y + sub1Radius * 0.6f * sin(-0.5).toFloat()),
            strokeWidth = 1.5f
        )

        // Sub-dial 6 o'clock (Seconds & Date)
        val sub2Y = centerY + dialRadius * 0.40f
        val sub2Radius = dialRadius * 0.32f
        drawCircle(
            color = Color(0x33FFFFFF),
            center = Offset(centerX, sub2Y),
            radius = sub2Radius
        )
        drawCircle(
            color = Color(0xFF7A9BB8),
            center = Offset(centerX, sub2Y),
            radius = sub2Radius,
            style = Stroke(width = 1f)
        )
        // Date aperture window at bottom of subdial
        val dateY = sub2Y + sub2Radius * 0.52f
        drawCircle(
            color = Color(0xFF1B364D),
            center = Offset(centerX, dateY),
            radius = 12f
        )
        drawCircle(
            color = Color(0xFFB0BEC5),
            center = Offset(centerX, dateY),
            radius = 12f,
            style = Stroke(width = 1f)
        )

        // Seconds needle
        drawLine(
            color = Color.White,
            start = Offset(centerX, sub2Y),
            end = Offset(centerX - sub2Radius * 0.7f, sub2Y - 10f),
            strokeWidth = 1.5f
        )

        // Main Watch Hands (Hour & Minute)
        // Hour Hand
        rotate(degrees = -45f, pivot = Offset(centerX, centerY)) {
            drawLine(
                color = Color(0xFFECEFF1),
                start = Offset(centerX, centerY),
                end = Offset(centerX, centerY - dialRadius * 0.52f),
                strokeWidth = 5f,
                cap = StrokeCap.Round
            )
        }
        // Minute Hand
        rotate(degrees = 65f, pivot = Offset(centerX, centerY)) {
            drawLine(
                color = Color(0xFFFFFFFF),
                start = Offset(centerX, centerY),
                end = Offset(centerX, centerY - dialRadius * 0.75f),
                strokeWidth = 3.5f,
                cap = StrokeCap.Round
            )
        }
        // Center Pin Cap
        drawCircle(
            color = Color(0xFFCFD8DC),
            center = Offset(centerX, centerY),
            radius = 6f
        )
        drawCircle(
            color = Color(0xFF37474F),
            center = Offset(centerX, centerY),
            radius = 2.5f
        )
    }
}

/**
 * Custom Canvas drawing for Caliber UN-118 Manufacture Movement
 */
@Composable
fun MovementView(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width.coerceAtMost(height) * 0.42f

        // 1. Movement Base Plate
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color(0xFFECEFF1),
                    Color(0xFFCFD8DC),
                    Color(0xFFB0BEC5),
                    Color(0xFF78909C)
                ),
                center = Offset(centerX, centerY),
                radius = radius
            ),
            center = Offset(centerX, centerY),
            radius = radius
        )

        // Perimeter gear teeth
        val teeth = 90
        for (i in 0 until teeth) {
            val angle = (i * 360f / teeth) * (Math.PI / 180.0)
            val x1 = centerX + (radius - 2) * cos(angle).toFloat()
            val y1 = centerY + (radius - 2) * sin(angle).toFloat()
            val x2 = centerX + (radius + 4) * cos(angle).toFloat()
            val y2 = centerY + (radius + 4) * sin(angle).toFloat()
            drawLine(
                color = Color(0xFF90A4AE),
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = 2f
            )
        }

        // Circular Côtes de Genève graining rings
        for (r in 20..(radius.toInt() - 10) step 18) {
            drawCircle(
                color = Color(0x22FFFFFF),
                center = Offset(centerX, centerY),
                radius = r.toFloat(),
                style = Stroke(width = 6f)
            )
            drawCircle(
                color = Color(0x11000000),
                center = Offset(centerX, centerY),
                radius = r.toFloat() + 3f,
                style = Stroke(width = 2f)
            )
        }

        // Cutouts for balance wheel (Bottom Left quadrant)
        val balX = centerX - radius * 0.4f
        val balY = centerY + radius * 0.25f
        drawCircle(
            color = Color(0xFF263238),
            center = Offset(balX, balY),
            radius = radius * 0.28f
        )
        // Gold Balance Wheel
        drawCircle(
            color = Color(0xFFFFD54F),
            center = Offset(balX, balY),
            radius = radius * 0.24f,
            style = Stroke(width = 4f)
        )
        // Balance Wheel spokes
        for (i in 0..2) {
            val a = (i * 120) * (Math.PI / 180.0)
            drawLine(
                color = Color(0xFFFFD54F),
                start = Offset(balX, balY),
                end = Offset(balX + radius * 0.24f * cos(a).toFloat(), balY + radius * 0.24f * sin(a).toFloat()),
                strokeWidth = 3f
            )
        }

        // Winding Gear (Upper center)
        val gearX = centerX - 5f
        val gearY = centerY - radius * 0.25f
        drawCircle(
            color = Color(0xFFB0BEC5),
            center = Offset(gearX, gearY),
            radius = radius * 0.22f
        )
        drawCircle(
            color = Color(0xFF37474F),
            center = Offset(gearX, gearY),
            radius = radius * 0.22f,
            style = Stroke(width = 2f)
        )

        // Synthetic Ruby Jewels (Magenta/Red circles with gold surround)
        val jewels = listOf(
            Offset(centerX + radius * 0.35f, centerY - radius * 0.2f),
            Offset(centerX + radius * 0.15f, centerY + radius * 0.18f),
            Offset(centerX - radius * 0.1f, centerY - radius * 0.05f),
            Offset(centerX - radius * 0.35f, centerY - radius * 0.35f),
            Offset(balX, balY)
        )

        for (j in jewels) {
            // Gold chaton
            drawCircle(
                color = Color(0xFFFFD54F),
                center = j,
                radius = 9f
            )
            // Ruby jewel
            drawCircle(
                color = Color(0xFFC2185B),
                center = j,
                radius = 5.5f
            )
        }

        // Blue Screws (Flame-blued steel screws)
        val blueScrews = listOf(
            Offset(centerX + radius * 0.65f, centerY - radius * 0.4f),
            Offset(centerX + radius * 0.72f, centerY + radius * 0.25f),
            Offset(centerX - radius * 0.58f, centerY - radius * 0.62f),
            Offset(centerX - radius * 0.25f, centerY + radius * 0.65f),
            Offset(centerX + radius * 0.1f, centerY + radius * 0.68f)
        )

        for (s in blueScrews) {
            drawCircle(
                color = Color(0xFF1565C0),
                center = s,
                radius = 7.5f
            )
            drawLine(
                color = Color(0xFF0D47A1),
                start = Offset(s.x - 5f, s.y),
                end = Offset(s.x + 5f, s.y),
                strokeWidth = 2f
            )
        }

        // Center hub cap
        drawCircle(
            color = Color(0xFFECEFF1),
            center = Offset(centerX, centerY),
            radius = 18f
        )
        drawCircle(
            color = Color(0xFF78909C),
            center = Offset(centerX, centerY),
            radius = 18f,
            style = Stroke(width = 2f)
        )
    }
}

/**
 * Custom Canvas drawing for Watch Case Side View ("No 2969")
 */
@Composable
fun CaseSideView(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        // Main steel case body contour
        val path = Path().apply {
            moveTo(cx - w * 0.42f, cy + 12f)
            lineTo(cx - w * 0.32f, cy - 14f)
            lineTo(cx + w * 0.32f, cy - 14f)
            lineTo(cx + w * 0.42f, cy + 12f)
            lineTo(cx + w * 0.35f, cy + 18f)
            lineTo(cx - w * 0.35f, cy + 18f)
            close()
        }

        drawPath(
            path = path,
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFFE0E0E0), Color(0xFFF5F5F5), Color(0xFF9E9E9E), Color(0xFFEEEEEE)),
                start = Offset(0f, cy - 20f),
                end = Offset(0f, cy + 20f)
            )
        )
        drawPath(
            path = path,
            color = Color(0xFF757575),
            style = Stroke(width = 1.5f)
        )

        // Fluted Top Bezel ridge
        drawLine(
            color = Color(0xFF9E9E9E),
            start = Offset(cx - w * 0.31f, cy - 14f),
            end = Offset(cx + w * 0.31f, cy - 14f),
            strokeWidth = 4f
        )
        for (x in (cx - w * 0.30f).toInt()..(cx + w * 0.30f).toInt() step 6) {
            drawLine(
                color = Color(0xFF616161),
                start = Offset(x.toFloat(), cy - 18f),
                end = Offset(x.toFloat(), cy - 14f),
                strokeWidth = 2f
            )
        }

        // Lugs on left and right
        val leftLug = Path().apply {
            moveTo(cx - w * 0.32f, cy - 10f)
            lineTo(cx - w * 0.46f, cy + 16f)
            lineTo(cx - w * 0.41f, cy + 22f)
            lineTo(cx - w * 0.33f, cy + 12f)
            close()
        }
        drawPath(leftLug, brush = Brush.linearGradient(listOf(Color(0xFFE0E0E0), Color(0xFF9E9E9E))))

        val rightLug = Path().apply {
            moveTo(cx + w * 0.32f, cy - 10f)
            lineTo(cx + w * 0.46f, cy + 16f)
            lineTo(cx + w * 0.41f, cy + 22f)
            lineTo(cx + w * 0.33f, cy + 12f)
            close()
        }
        drawPath(rightLug, brush = Brush.linearGradient(listOf(Color(0xFFE0E0E0), Color(0xFF9E9E9E))))

        // Center Serial Number Plaque "No 2969"
        val plaqueRect = Offset(cx - 55f, cy - 5f)
        val plaqueSize = Size(110f, 24f)

        drawRoundRect(
            color = Color(0xFFEEEEEE),
            topLeft = plaqueRect,
            size = plaqueSize,
            cornerRadius = CornerRadius(4f, 4f)
        )
        drawRoundRect(
            color = Color(0xFF616161),
            topLeft = plaqueRect,
            size = plaqueSize,
            cornerRadius = CornerRadius(4f, 4f),
            style = Stroke(width = 1.2f)
        )

        // Screws on plaque
        drawCircle(color = Color(0xFF37474F), radius = 3f, center = Offset(cx - 46f, cy + 7f))
        drawCircle(color = Color(0xFF37474F), radius = 3f, center = Offset(cx + 46f, cy + 7f))
    }
}

/**
 * Custom Canvas drawing for Milanese Mesh Clasp
 */
@Composable
fun ClaspView(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f
        val cy = h / 2f

        // Mesh Band left & right
        drawRect(
            color = Color(0xFFB0BEC5),
            topLeft = Offset(0f, cy - 14f),
            size = Size(w, 28f)
        )
        // Mesh pattern lines
        for (x in 0..w.toInt() step 6) {
            drawLine(
                color = Color(0xFF78909C),
                start = Offset(x.toFloat(), cy - 14f),
                end = Offset(x.toFloat() + 4f, cy + 14f),
                strokeWidth = 1.2f
            )
        }

        // Center Clasp Latch Box
        drawRoundRect(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFFF5F5F5), Color(0xFFCFD8DC), Color(0xFF90A4AE)),
                start = Offset(cx - 40f, 0f),
                end = Offset(cx + 40f, 0f)
            ),
            topLeft = Offset(cx - 45f, cy - 20f),
            size = Size(90f, 40f),
            cornerRadius = CornerRadius(4f, 4f)
        )
        drawRoundRect(
            color = Color(0xFF616161),
            topLeft = Offset(cx - 45f, cy - 20f),
            size = Size(90f, 40f),
            cornerRadius = CornerRadius(4f, 4f),
            style = Stroke(width = 1.5f)
        )

        // Safety fold-over latch
        drawRoundRect(
            brush = Brush.linearGradient(listOf(Color(0xFFFFFFFF), Color(0xFFE0E0E0))),
            topLeft = Offset(cx - 15f, cy - 22f),
            size = Size(30f, 44f),
            cornerRadius = CornerRadius(3f, 3f)
        )
        drawRoundRect(
            color = Color(0xFF757575),
            topLeft = Offset(cx - 15f, cy - 22f),
            size = Size(30f, 44f),
            cornerRadius = CornerRadius(3f, 3f),
            style = Stroke(width = 1f)
        )
    }
}

/**
 * Custom Canvas drawing for Patrick Pruniaux Portrait
 */
@Composable
fun PortraitView(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val cx = w / 2f

        // Dark background
        drawRect(color = Color(0xFF080D12))

        // Dramatic lighting gradient background behind head
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF2C3E50), Color(0xFF0F171E), Color(0xFF080D12)),
                center = Offset(cx, h * 0.35f),
                radius = w * 0.7f
            ),
            center = Offset(cx, h * 0.35f),
            radius = w * 0.7f
        )

        // Shoulders / Suit Jacket
        val suitPath = Path().apply {
            moveTo(cx - w * 0.45f, h)
            lineTo(cx - w * 0.38f, h * 0.65f)
            lineTo(cx - w * 0.18f, h * 0.58f)
            lineTo(cx + w * 0.18f, h * 0.58f)
            lineTo(cx + w * 0.45f, h * 0.68f)
            lineTo(cx + w * 0.50f, h)
            close()
        }
        drawPath(
            path = suitPath,
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF1E2A38), Color(0xFF101721), Color(0xFF0A0E14))
            )
        )

        // White Shirt Collar
        val shirtPath = Path().apply {
            moveTo(cx - w * 0.15f, h * 0.58f)
            lineTo(cx, h * 0.68f)
            lineTo(cx + w * 0.15f, h * 0.58f)
            lineTo(cx + w * 0.08f, h * 0.54f)
            lineTo(cx - w * 0.08f, h * 0.54f)
            close()
        }
        drawPath(shirtPath, color = Color(0xFFECEFF1))

        // Neck
        drawRect(
            brush = Brush.horizontalGradient(listOf(Color(0xFF795548), Color(0xFFA1887F), Color(0xFF5D4037))),
            topLeft = Offset(cx - w * 0.12f, h * 0.42f),
            size = Size(w * 0.24f, h * 0.16f)
        )

        // Face Shape
        val facePath = Path().apply {
            moveTo(cx - w * 0.22f, h * 0.22f)
            cubicTo(
                cx - w * 0.24f, h * 0.35f,
                cx - w * 0.18f, h * 0.46f,
                cx, h * 0.48f
            )
            cubicTo(
                cx + w * 0.18f, h * 0.46f,
                cx + w * 0.24f, h * 0.35f,
                cx + w * 0.22f, h * 0.22f
            )
            cubicTo(
                cx + w * 0.20f, h * 0.10f,
                cx - w * 0.20f, h * 0.10f,
                cx - w * 0.22f, h * 0.22f
            )
            close()
        }

        drawPath(
            path = facePath,
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFF8D6E63),
                    Color(0xFFBCAAA4),
                    Color(0xFFD7CCC8),
                    Color(0xFF8D6E63),
                    Color(0xFF4E342E)
                )
            )
        )

        // Short Grey/Blonde Hair
        val hairPath = Path().apply {
            moveTo(cx - w * 0.23f, h * 0.22f)
            lineTo(cx - w * 0.20f, h * 0.11f)
            lineTo(cx, h * 0.08f)
            lineTo(cx + w * 0.20f, h * 0.11f)
            lineTo(cx + w * 0.23f, h * 0.22f)
            lineTo(cx + w * 0.18f, h * 0.15f)
            lineTo(cx, h * 0.13f)
            lineTo(cx - w * 0.18f, h * 0.15f)
            close()
        }
        drawPath(hairPath, brush = Brush.verticalGradient(listOf(Color(0xFFB0BEC5), Color(0xFF78909C))))

        // Eyes & Brows
        // Left Eye
        drawCircle(color = Color(0xFF263238), radius = 6f, center = Offset(cx - w * 0.09f, h * 0.27f))
        drawCircle(color = Color(0xFF80DEEA), radius = 3f, center = Offset(cx - w * 0.09f, h * 0.27f))
        // Right Eye
        drawCircle(color = Color(0xFF263238), radius = 6f, center = Offset(cx + w * 0.09f, h * 0.27f))
        drawCircle(color = Color(0xFF80DEEA), radius = 3f, center = Offset(cx + w * 0.09f, h * 0.27f))

        // Nose Bridge
        drawLine(
            color = Color(0x664E342E),
            start = Offset(cx, h * 0.25f),
            end = Offset(cx, h * 0.37f),
            strokeWidth = 3.5f
        )

        // Lips
        drawLine(
            color = Color(0xFF5D4037),
            start = Offset(cx - w * 0.07f, h * 0.41f),
            end = Offset(cx + w * 0.07f, h * 0.41f),
            strokeWidth = 3f,
            cap = StrokeCap.Round
        )
    }
}
