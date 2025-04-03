package com.example.struku.presentation.receipts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.struku.domain.model.ReceiptItem

@Composable
fun ReceiptDetailScreen(
    viewModel: ReceiptDetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Receipt Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        // Add back icon
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            state.receipt?.let { receipt ->
                item {
                    Text(
                        text = "Store: ${receipt.storeName}",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Date: ${receipt.date}",
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Items:",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                
                items(receipt.items) { item ->
                    ReceiptItemRow(item)
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total:",
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = "$${receipt.total}",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReceiptItemRow(item: ReceiptItem) {
    // Fix: Add missing properties
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Fix: Use the description property
                Text(text = item.description, style = MaterialTheme.typography.body1)
                Text(text = "${item.quantity} × $${item.unitPrice}", style = MaterialTheme.typography.caption)
            }
            // Fix: Calculate item total from unitPrice and quantity
            Text(text = "$${item.unitPrice * item.quantity}", style = MaterialTheme.typography.body2)
        }
    }
}