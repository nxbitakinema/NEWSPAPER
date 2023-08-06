package com.nx.nxfcd.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nx.nxfcd.core.Constants.ERROR
import com.nx.nxfcd.core.Constants.HEAD_NAME
import com.nx.nxfcd.data.AppRepositoryImpl
import com.nx.nxfcd.domain.model.Cd
import com.nx.nxfcd.domain.model.Failure
import com.nx.nxfcd.domain.model.Success
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun CdList(
    cdViewModel: CdViewModel = viewModel(factory = CdViewModelFactory(AppRepositoryImpl()))
) {
    when(
        val cdListx = cdViewModel.booksStateFlow.asStateFlow().collectAsState().value
    ) {

        is Failure -> { Text(text = ERROR) }

        is Success -> {
            val cdListxx = cdListx.querySnapshot?.toObjects(Cd::class.java)
            cdListxx?.let {
                Column {
                    Text(
                        text = HEAD_NAME,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(14.dp)
                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(cdListxx) {
                            Card(
                                modifier = Modifier.fillMaxWidth().padding(14.dp),
                                shape = RoundedCornerShape(2.dp)
                            ) {
                                CdDetail(it)
                            }
                        }
                    }
                }
            }
        }
        else -> {}
    }
}