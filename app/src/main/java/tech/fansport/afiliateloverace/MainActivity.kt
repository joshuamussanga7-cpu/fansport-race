package tech.fansport.afiliateloverace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color Palette
val NeonGreen = Color(0xFF00FF00)
val DarkBackground = Color(0xFF000000)
val SurfaceColor = Color(0xFF0A0A0A)
val TableHeaderColor = Color(0xFF003300)
val BorderColor = Color(0xFF1A1A1A)

data class LeaderboardEntry(
    val rank: Int,
    val affiliateId: String,
    val country: String,
    val ftds: Int,
    val totalDeposits: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LeaderboardScreen()
            }
        }
    }
}

@Composable
fun LeaderboardScreen() {
    val leaderboardData = listOf(
        LeaderboardEntry(1, "3477453", "Tanzania", 257, "$12,192"),
        LeaderboardEntry(2, "3528955", "Tanzania", 140, "$11,852"),
        LeaderboardEntry(3, "4028909", "Tanzania", 191, "$7,796"),
        LeaderboardEntry(4, "3992628", "Tanzania", 115, "$4,959"),
        LeaderboardEntry(5, "3100943", "Tanzania", 126, "$4,060"),
        LeaderboardEntry(6, "3263853", "Tanzania", 89, "$3,015"),
        LeaderboardEntry(7, "4145804", "Tanzania", 147, "$2,111"),
        LeaderboardEntry(8, "3244213", "Tanzania", 70, "$1,809"),
        LeaderboardEntry(9, "3122884", "Tanzania", 62, "$1,618"),
        LeaderboardEntry(10, "4001112", "Tanzania", 116, "$1,219")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Top Header Section ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            // Background Image/Effect (Simulating the girl and palms)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, DarkBackground)
                        )
                    )
            )
            
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Language Switcher
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Box(
                        modifier = Modifier
                            .background(NeonGreen, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("EN", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Logo
                Text(
                    text = "FANSPORT",
                    color = NeonGreen,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "$5,000 PRIZE FUND!",
                    color = NeonGreen,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "THE RACE OF AFFILIATIONS",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        // --- Steps Section ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StepIconItem(label = "LOG IN", iconColor = NeonGreen)
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = NeonGreen)
            StepIconItem(label = "ATTRACT AT LEAST\n50 FTD", iconColor = NeonGreen)
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = NeonGreen)
            StepIconItem(label = "EACH FTD MUST\nMAKE A DEPOSIT OF\nAT LEAST $2", iconColor = NeonGreen)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- Join Button ---
        Button(
            onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
            modifier = Modifier
                .width(200.dp)
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "JOIN",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )
        }

        Text(
            text = "RULES",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        // --- Leaderboard Section ---
        Text(
            text = "AFFILIATE RACE - $5,000 PRIZE POOL!",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Table
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TableHeaderColor, RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableHeaderCell("RANK", 0.15f)
                TableHeaderCell("AFFILIATE ID", 0.25f)
                TableHeaderCell("COUNTRY", 0.25f)
                TableHeaderCell("FTDS", 0.15f)
                TableHeaderCell("TOTAL DEPOSITS($)", 0.20f)
            }

            // Data Rows
            leaderboardData.forEach { entry ->
                LeaderboardRow(entry)
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // --- Footer Logos ---
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("M-PESA  AstroPay  tether  BINANCE PAY  airtel  halotel  tigo  vodacom", 
                color = Color.Gray, fontSize = 10.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun StepIconItem(label: String, iconColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(90.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFF1A1A1A), RoundedCornerShape(8.dp))
                .border(1.dp, iconColor, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Placeholder for icons (Phone, Wallet, Box)
            Box(modifier = Modifier.size(24.dp).background(iconColor, CircleShape))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 9.sp,
            lineHeight = 11.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LeaderboardRow(entry: LeaderboardEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .background(SurfaceColor)
            .border(0.5.dp, BorderColor)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TableDataCell(entry.rank.toString(), 0.15f, color = NeonGreen, isBold = true)
        TableDataCell(entry.affiliateId, 0.25f)
        TableDataCell(entry.country, 0.25f, isItalic = true)
        TableDataCell(entry.ftds.toString(), 0.15f)
        TableDataCell(entry.totalDeposits, 0.20f, color = NeonGreen, isBold = true)
    }
}

@Composable
fun RowScope.TableHeaderCell(text: String, weight: Float) {
    Text(
        text = text,
        modifier = Modifier.weight(weight),
        color = Color.White,
        fontSize = 11.sp,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RowScope.TableDataCell(
    text: String, 
    weight: Float, 
    color: Color = Color.White, 
    isBold: Boolean = false,
    isItalic: Boolean = false
) {
    Text(
        text = text,
        modifier = Modifier.weight(weight),
        color = color,
        fontSize = 13.sp,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        textAlign = TextAlign.Center,
        fontStyle = if (isItalic) androidx.compose.ui.text.font.FontStyle.Italic else androidx.compose.ui.text.font.FontStyle.Normal
    )
}