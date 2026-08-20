package com.droidraksha.mobile.ui.screens.applist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidraksha.mobile.ui.components.AppRowItem
import com.droidraksha.mobile.ui.components.GlassCard
import com.droidraksha.mobile.ui.theme.*
import androidx.compose.foundation.border

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(
    viewModel: AppListViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToAppDetail: (String) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(BackgroundSurface, BackgroundDark)
    )

    Scaffold(
        containerColor = Color.Transparent,
        modifier = Modifier.background(backgroundBrush),
        topBar = {
            TopAppBar(
                title = { Text("Threat Intelligence Feed", color = TextPrimary, style = Typography.titleLarge, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                placeholder = { Text("Search by name or package...", color = TextSecondary, style = Typography.bodyMedium) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                trailingIcon = {
                    if (state.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.onSearchQueryChanged("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear", tint = TextSecondary)
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardLevel2,
                    unfocusedContainerColor = CardLevel2,
                    focusedBorderColor = AccentCyan,
                    unfocusedBorderColor = DividerHairline,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Filter Chips Row
            val filters = listOf("ALL", "CRITICAL", "HIGH", "MEDIUM", "LOW", "SAFE", "SIDELOADED")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = state.selectedFilter == filter
                    val chipBg = if (isSelected) AccentCyan else CardLevel2
                    val textColor = if (isSelected) BackgroundDark else TextSecondary
                    val chipBorder = if (isSelected) Color.Transparent else DividerHairline
                    
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .background(chipBg)
                            .clickable { viewModel.onFilterSelected(filter) }
                            .border(1.dp, chipBorder, RoundedCornerShape(100.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = filter,
                            color = textColor,
                            style = Typography.labelSmall,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // App Count Label
            Text(
                text = "Showing ${state.filteredApps.size} apps",
                color = TextSecondary,
                style = Typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            // App Inventory List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.filteredApps, key = { it.packageName }) { app ->
                    GlassCard(modifier = Modifier.fillMaxWidth()) {
                        AppRowItem(app = app, onClick = { onNavigateToAppDetail(app.packageName) })
                    }
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }
    }
}
