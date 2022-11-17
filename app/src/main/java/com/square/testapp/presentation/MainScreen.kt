package com.square.testapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.square.testapp.R
import com.square.testapp.domain.employee.Employee
import com.square.testapp.presentation.ui.theme.DarkBlue
import com.square.testapp.presentation.ui.theme.DeepBlue
import java.text.SimpleDateFormat

@Composable
fun MainScreen(
    viewModel: EmployeesViewModel
) {

    val isRefreshing = viewModel.isRefreshing.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val currentTime = viewModel.currentTime.collectAsState().value
    val items = viewModel.items.collectAsState().value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)

    val s = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z")

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = viewModel::refresh,
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlue)
        ) {
            item {
                Text(
                    text = stringResource(R.string.main_screen_welcome_label),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(R.string.main_screen_updated_label) + s.format(
                        currentTime
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(items) {
                EmployeeCell(
                    employee = it,
                    childModifier = Modifier.placeholder(
                        visible = isLoading,
                        highlight = PlaceholderHighlight.fade(),
                    )
                )
            }
        }
    }
}

@Composable
fun Empty() {
    Image(
        painterResource(R.drawable.empty),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.clipScrollableContainer(Orientation.Horizontal)
    )
}


@Composable
fun EmployeeCell(
    employee: Employee,
    childModifier: Modifier = Modifier,
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(16.dp)
            .background(DarkBlue),
        colors = CardDefaults.cardColors(
            containerColor = DarkBlue,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = employee.uuid,
                modifier = Modifier.align(Alignment.End),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = rememberAsyncImagePainter(employee.photoUrlBig),
                contentDescription = employee.biography,
                modifier = childModifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Full Name: ${employee.fullName}",
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Email Address: ${employee.emailAddress}",
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Team: ${employee.team}",
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Employee type: ${employee.employeeType?.getLabel()}",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}