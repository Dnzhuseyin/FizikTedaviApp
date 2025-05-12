package com.example.fiziktedaviapp.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiziktedaviapp.navigation.Screen
import com.example.fiziktedaviapp.ui.theme.*
import java.util.*
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    
    // Kullanıcı adı (daha sonra model sınıfından alınacak)
    val userName = "Ahmet"
    
    // Bugünkü tamamlanan egzersiz yüzdesi (daha sonra model sınıfından alınacak)
    val completionPercentage = 65
    
    // Motivasyon cümleleri
    val motivationalQuotes = listOf(
        "Düzenli egzersiz yapmak iyileşme sürecini hızlandırır.",
        "Her gün yapılan küçük egzersizler, büyük sonuçlar getirir.",
        "Sağlıklı bir gelecek için bugün harekete geç!",
        "En iyi yatırım sağlığına yaptığın yatırımdır.",
        "Egzersizlerinizi tamamladığınızda, vücudunuz size teşekkür edecek."
    )
    
    // Rastgele bir motivasyon cümlesi seç
    val randomQuote = remember { motivationalQuotes[Random.nextInt(motivationalQuotes.size)] }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fizik Tedavi") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Ayarlar",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = SurfaceLight,
                contentColor = DarkBlue
            ) {
                NavigationBar(
                    containerColor = SurfaceLight,
                    contentColor = DarkBlue
                ) {
                    NavigationBarItem(
                        selected = true,
                        onClick = { /* Zaten Dashboard ekranındayız */ },
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Ana Sayfa") },
                        label = { Text("Ana Sayfa") }
                    )
                    
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(Screen.ExerciseList.route) },
                        icon = { Icon(Icons.Outlined.FitnessCenter, contentDescription = "Egzersizler") },
                        label = { Text("Egzersizler") }
                    )
                    
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(Screen.ExerciseCalendar.route) },
                        icon = { Icon(Icons.Outlined.CalendarMonth, contentDescription = "Takvim") },
                        label = { Text("Takvim") }
                    )
                    
                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate(Screen.Profile.route) },
                        icon = { Icon(Icons.Outlined.Person, contentDescription = "Profil") },
                        label = { Text("Profil") }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundLight)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Karşılama mesajı
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Hoş geldin,",
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextSecondary
                    )
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
                
                // Profil resmi (şimdilik renk kartı olarak)
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(DarkBlue)
                        .clickable { navController.navigate(Screen.Profile.route) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = userName.first().toString(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
            
            // İlerleme daire grafiği
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = { completionPercentage / 100f },
                    modifier = Modifier.fillMaxSize(),
                    strokeWidth = 16.dp,
                    color = MediumGreen,
                    trackColor = LightBlue,
                    strokeCap = StrokeCap.Round
                )
                
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "%$completionPercentage",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Tamamlandı",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextSecondary
                    )
                }
            }
            
            // Kısayol kartları
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ShortcutCard(
                    title = "Egzersize Başla",
                    icon = Icons.Outlined.PlayArrow,
                    backgroundColor = LightBlue,
                    contentColor = DarkBlue,
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(Screen.ExerciseList.route) }
                )
                
                ShortcutCard(
                    title = "Puan Tablosu",
                    icon = Icons.Outlined.EmojiEvents,
                    backgroundColor = LightGreen,
                    contentColor = DarkGreen,
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(Screen.Leaderboard.route) }
                )
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ShortcutCard(
                    title = "İlerlemen",
                    icon = Icons.Outlined.ShowChart,
                    backgroundColor = LightBlue,
                    contentColor = DarkBlue,
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(Screen.ExerciseCalendar.route) }
                )
                
                ShortcutCard(
                    title = "Terapist",
                    icon = Icons.Outlined.VideoCall,
                    backgroundColor = LightGreen,
                    contentColor = DarkGreen,
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate(Screen.TherapistCall.route) }
                )
            }
            
            // Motivasyon cümlesi
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MediumBlue.copy(alpha = 0.1f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Lightbulb,
                        contentDescription = null,
                        tint = DarkBlue,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                    )
                    
                    Text(
                        text = randomQuote,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextPrimary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ShortcutCard(
    title: String,
    icon: ImageVector,
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier.size(32.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = title,
                color = contentColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
} 